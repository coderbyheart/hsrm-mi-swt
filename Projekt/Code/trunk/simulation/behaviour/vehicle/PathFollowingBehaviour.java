package simulation.behaviour.vehicle;

import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;

import util.PointDistance;
import util.PointDistanceComparator;

import config.AppConfig;

import model.planer.PlacedVehicleModel;
import model.planer.WorldModel;

/**
 * Bewegungsverhalten: Der Straße folgen
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: PathFollowingBehaviour.java 236 2011-01-17 13:23:54Z mtack001 $
 */
public class PathFollowingBehaviour extends PathFollowingBehaviourBase
		implements IMovingBehaviour {

	private WorldModel world;
	private static Logger logger = Logger
			.getLogger(PathFollowingBehaviour.class);
	/**
	 * Punkt in einem Winkel Größer als
	 * {@link PathFollowingBehaviour#defaultAngleThreshold} werden ignoriert
	 */
	private double defaultAngleThreshold = Math.toRadians(45);
	private double minDistance = 5;
	private double maxDistance = 150;
	private int numVisited = 100;
	private Point2D currentTarget;
	private Point2D myPoint;
	private PlacedVehicleModel vehicle;

	public PathFollowingBehaviour(AppConfig config, PlacedVehicleModel vehicle,
			WorldModel world) {
		super(config, vehicle);
		this.vehicle = vehicle;
		this.world = world;
	}

	@Override
	public Point2D getTarget() {
		myPoint = new Point2D.Double(vehicle.getGlobalX(), vehicle.getGlobalY());

		if (getCurrentTarget() != null) {
			Point2D deltaPoint = new Point2D.Double(getCurrentTarget().getX()
					- myPoint.getX(), getCurrentTarget().getY()
					- myPoint.getY());
			PointDistance deltaPointDistance = new PointDistance(deltaPoint,
					getCurrentTarget().distance(myPoint));
			if (checkPoint(deltaPointDistance, defaultAngleThreshold))
				return deltaPoint;
		}

		// Finde den nächst gelegenen Punkt, der in Fahrtrichtung ist
		GeneralPath path = world.getPath();
		PathIterator p = path.getPathIterator(null);
		FlatteningPathIterator f = new FlatteningPathIterator(p,
				config.getFlatteningPathIteratorResolution());
		ArrayList<PointDistance> points = new ArrayList<PointDistance>();
		while (!f.isDone()) {
			float[] pts = new float[6];
			switch (f.currentSegment(pts)) {
			case PathIterator.SEG_MOVETO:
			case PathIterator.SEG_LINETO:
				Point2D point = new Point2D.Double(pts[0], pts[1]);
				points.add(new PointDistance(point, point.distance(myPoint)));
			}
			f.next();
		}
		Collections.sort(points, new PointDistanceComparator());

		PointDistance targetPointDistance = getNextPoint(points,
				defaultAngleThreshold);
		// Winkel erweitern
		if (targetPointDistance == null) {
			double angleThreshold = defaultAngleThreshold;
			while (targetPointDistance == null && angleThreshold <= Math.PI * 2) {
				logger.warn(String.format(
						"Kein Zielpunkt gefunden, Erweitere Radius auf: %.2f", angleThreshold));
				angleThreshold += Math.toRadians(10);
				// logger.debug(String.format("Erweitere Winkel auf : %.5f = %d",
				// angleThreshold, (int) Math.toDegrees(angleThreshold)));
				targetPointDistance = getNextPoint(points, angleThreshold);
			}
		}

		Point2D targetPoint;
		if (targetPointDistance == null) {
			// Kein Punkt gefunden?
			// Drehen und weiterfahren
			logger.warn(String.format(
					"Kein Zielpunkt gefunden, Linkskurve: %s", vehicle));
			double xDist = vehicle.getPixelSpeed()
					* Math.sin(vehicle.getRotation() + vehicle.getMaxTurn());
			double yDist = vehicle.getPixelSpeed()
					* -Math.cos(vehicle.getRotation() + vehicle.getMaxTurn());
			targetPoint = new Point2D.Double(xDist, yDist);
		} else {
			// logger.debug(String.format("Ziel: %s", targetPointDistance));
			targetPoint = new Point2D.Double(targetPointDistance.getPoint()
					.getX(), targetPointDistance.getPoint().getY());
		}
		return targetPoint;
	}

	protected PointDistance getNextPoint(ArrayList<PointDistance> points,
			double angleThreshold) {
		for (PointDistance pointDistance : points) {
			PointDistance deltaPointDistance = new PointDistance(
					new Point2D.Double(pointDistance.getPoint().getX()
							- myPoint.getX(), pointDistance.getPoint().getY()
							- myPoint.getY()), pointDistance.getDistance());
			
			if (!checkPoint(deltaPointDistance, angleThreshold))
				continue;

			if (lastTargets.size() >= numVisited) {
				lastTargets.removeFirst();
			}
			if (!pointDistance.getPoint().equals(getCurrentTarget())) {
				lastTargets.add(pointDistance.getPoint());
				setCurrentTarget(pointDistance.getPoint());
			}
			return deltaPointDistance;
		}
		return null;
	}

	public boolean checkPoint(PointDistance pointDistance, double angleThreshold) {
		return checkPoint(pointDistance, angleThreshold, minDistance,
				maxDistance, getCurrentTarget());
	}

	@Override
	public double getSpeed() {
		return vehicle.getPixelSpeed();
	}

	/**
	 * @param currentTarget
	 *            the currentTarget to set
	 */
	public void setCurrentTarget(Point2D currentTarget) {
		this.currentTarget = currentTarget;
	}

	/**
	 * @return the currentTarget
	 */
	public Point2D getCurrentTarget() {
		return currentTarget;
	}

}
