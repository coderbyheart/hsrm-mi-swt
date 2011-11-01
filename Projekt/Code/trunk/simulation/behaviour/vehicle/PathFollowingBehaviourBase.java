package simulation.behaviour.vehicle;

import java.awt.geom.Point2D;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import config.AppConfig;

import util.PointDistance;
import util.Transform;

import model.planer.IRotateable;

/**
 * Basisklasse für Pfadverfolgung
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: PathFollowingBehaviourBase.java 192 2011-01-12 21:01:19Z mtack001 $
 */
public class PathFollowingBehaviourBase {

	protected AppConfig config;

	private static Logger logger = Logger
			.getLogger(PathFollowingBehaviourBase.class);

	/**
	 * Merkt sich die letzten n Wegpunkte
	 */
	protected LinkedList<Point2D> lastTargets = new LinkedList<Point2D>();

	private IRotateable vehicle;

	public PathFollowingBehaviourBase(AppConfig config, IRotateable vehicle) {
		this.config = config;
		this.vehicle = vehicle;
	}

	public boolean checkPoint(PointDistance pointDistance,
			double angleThreshold, double minDistance, double maxDistance,
			Point2D currentTarget) {

		if (pointDistance.getDistance() < minDistance) {
			if (config.debugLogVehicles())
				logger.debug(String.format("Entfernung zu klein: %s",
						pointDistance));
			return false;
		}
		if (pointDistance.getDistance() > maxDistance) {
			if (config.debugLogVehicles())
				logger.debug(String.format("Entfernung zu groß: %s",
						pointDistance));
			return false;
		}

		double angle = getAngle(pointDistance);

		if (Math.abs(angle) > angleThreshold) {
			if (config.debugLogVehicles())
				logger.debug(String.format(
						"Winkel zu groß: %s (%.5f = %d) > %.5f", pointDistance,
						angle, (int) Math.toDegrees(angle), angleThreshold));
			return false;
		}
		if (currentTarget != null
				&& !pointDistance.getPoint().equals(currentTarget)) {
			// Neues Ziel
			// Bereits besucht
			if (lastTargets.contains(pointDistance.getPoint())) {
				if (config.debugLogVehicles())
					logger.debug(String.format("Bereits besucht: %dx%d",
							(int) pointDistance.getPoint().getX(),
							(int) pointDistance.getPoint().getY()));
				return false;
			}
		}
		return true;
	}

	/**
	 * Berechne Winkel zum Punkt
	 * 
	 * @param pointDistance
	 * @return Winkel zum Punkt
	 */
	public double getAngle(PointDistance pointDistance) {
		double angle = Transform.screenAtan2(pointDistance.getPoint().getX(),
				pointDistance.getPoint().getY());
		angle = angle - vehicle.getRotation();
		return Transform.fixAngle(angle);
	}
}
