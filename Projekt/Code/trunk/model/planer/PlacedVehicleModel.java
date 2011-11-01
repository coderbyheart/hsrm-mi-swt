package model.planer;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import simulation.behaviour.vehicle.IMovingBehaviour;
import simulation.behaviour.vehicle.PathFollowingBehaviour;
import util.Transform;
import config.AppConfig;

/**
 * Model für ein platziertes Fahrzeug
 */
public class PlacedVehicleModel extends VehicleModel implements IRotateable {
	private int localX;
	private int localY;
	private double rotation;
	private PlacedTileModel tile;
	private WorldModel world;
	private AppConfig config;
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(PlacedVehicleModel.class);
	private ArrayList<IMovingBehaviour> behaviours = new ArrayList<IMovingBehaviour>();
	private ArrayList<Point2D> vectors = new ArrayList<Point2D>();
	private ArrayList<Point2D> targets = new ArrayList<Point2D>();

	public static final int POSITION_CHANGED = 1;
	public static final int  ROTATION_CHANGED = 2;
	
	/**
	 * Maximale Drehgeschwindigkeit ("Wendigkeit") in Grad
	 */
	private double maxTurn = Math.toRadians(45);

	public PlacedVehicleModel(AppConfig config, WorldModel world) {
		this.config = config;
		this.world = world;
		// behaviours.add(new SimpleMovingBehaviour(this));
		behaviours.add(new PathFollowingBehaviour(config, this, world));
	}

	/**
	 * Liefert die X-Position dieser Kachel relativ zur linken oberen Ecke der Kachel zurück
	 * @return int
	 */
	public int getLocalX() {
		return this.localX;
	}

	/**
	 * Setzt die X-Position dieser Kachel relativ zur linken oberen Ecke der Welt
	 * @param left
	 */
	public void setLocalX(int left) {
		this.localX = left;
		updateParentTile();
	}

	/**
	 * Liefert die Y-Position dieser Kachel relativ zur linken oberen Ecke der Welt zurück
	 * @return int
	 */
	public int getLocalY() {
		return this.localY;
	}

	/**
	 * Setzt die Y-Position dieser Kachel relativ zur linken oberen Ecke der Welt
	 * @param top
	 */
	public void setLocalY(int top) {
		this.localY = top;
		updateParentTile();
	}
	
	/**
	 * Prüft, ob sich das Fahrzeug in einer anderen Kachel befindet
	 */
	private void updateParentTile() {
		if (tile == null) return;
		int newLeftIndex = world.getLeftIndex(getGlobalX());
		int newTopIndex = world.getTopIndex(getGlobalY());
		if (tile.getLeft() != newLeftIndex || tile.getTop() != newTopIndex) {
			PlacedTileModel newTile = world.getTileAt(newLeftIndex, newTopIndex);
			if (newTile != null) {
				// Koordinaten fixen
				localX -= newTile.getX() - tile.getX();
				localY -= newTile.getY() - tile.getY();
				tile = newTile;
			}
		}
	}

	public double getRotation() {
		return this.rotation;
	}

	public void setRotation(double rotation) {
		rotation = Transform.fixAngle(rotation);
		if (rotation != this.rotation) {
			setChanged();
			notifyObservers(ROTATION_CHANGED);
		}
		this.rotation = rotation;
	}

	public PlacedTileModel getTile() {
		return this.tile;
	}

	public void setTile(PlacedTileModel tile) {
		this.tile = tile;
	}

	/**
	 * Gibt die X-Position des Fahrzeuges in der Welt zurück
	 * 
	 * Hierzu wird die {@link AppConfig#getModelTileSize() interne Standardgröße
	 * der Kacheln} berücksichtigt, die einzelnen Views müssen diese Angabe dann
	 * noch entsprechend ihres Zoomfaktors umrechnen
	 * 
	 * @return X-Position des Fahrzeuges in der Welt
	 */
	public int getGlobalX() {
		return ((world.getTileIndex(tile) % world.getWidth()))
				* config.getModelTileSize() + getLocalX();
	}

	/**
	 * Gibt die Y-Position des Fahrzeuges in der Welt zurück
	 * 
	 * @see PlacedVehicleModel#getGlobalX()
	 * 
	 * @return Y-Position des Fahrzeuges in der Welt
	 */
	public int getGlobalY() {
		return (int) (Math.floor(world.getTileIndex(tile) / world.getWidth()))
				* config.getModelTileSize() + getLocalY();
	}

	/**
	 * Bewegt das Fahrzeug einen Schritt weiter
	 */
	public void move() {
		vectors = new ArrayList<Point2D>();
		targets = new ArrayList<Point2D>();
		
		// Hohle Bewegungen aus den Verhalten
		for (IMovingBehaviour behaviour : behaviours) {
			Point2D target = behaviour.getTarget();
			double speed = behaviour.getSpeed();
			// if (config.debugVehicles()) logger.debug(String.format("Bekomme Richtung: %dx%d (%d)", (int)target.getX(), (int)target.getY(), (int)speed));
			getTargets().add(target);
			vectors.add(toVector(target, speed));
		}
		// Berechne resultierenden Vektor
		Point2D resultingVector = getResultingVector(vectors);
		// if (config.debugVehicles()) logger.debug(String.format("Ziel: %dx%d", (int)resultingVector.getX(), (int)resultingVector.getY()));
		
		// Change direction if required, but at max maxTurn degrees
		double resultingRotation = Transform.screenAtan2(resultingVector.getX(), resultingVector.getY());
		double angleDiff = rotation - resultingRotation;
		if (angleDiff > 0) {
			angleDiff = Math.min(getMaxTurn(), angleDiff);
		} else {
			angleDiff = Math.max(-getMaxTurn(), angleDiff);
		}
		double newAngle = rotation - angleDiff;
		setRotation(newAngle);
		
		// Move to new Target
		double xDiff = getPixelSpeed() * Math.sin(newAngle);
		double yDiff = getPixelSpeed() * -Math.cos(newAngle);
		
		localX = localX + (int)xDiff;
		localY  = localY + (int)yDiff;
		updateParentTile();
		
		// if (config.debugVehicles()) logger.debug(String.format("Bewege mich um %dx%d auf %dx%d", (int)xDiff, (int)yDiff, left, top));
		setChanged();
		notifyObservers(POSITION_CHANGED);
			
	}
	
	/**
	 * @return die Streck in Pixeln zurück, die das Fahrzeug pro Frame zurück legt
	 */
	public  double getPixelSpeed() {
		return config.getModelTileSize() / getSpeed() / config.getFps();
	}

	private Point2D getResultingVector(ArrayList<Point2D> vectors) {
		double x = 0;
		double y = 0;
		for (Point2D p : vectors) {
			x += p.getX();
			y += p.getY();
		}
		return new Point2D.Double(x, y);
	}

	private Point2D toVector(Point2D target, double speed) {
		if (target.getX() == 0 && target.getX() == target.getY()) return new Point(0, 0);
		int pixelSpeed = (int) (config.getModelTileSize() / speed);
		double targetRadian = Transform.screenAtan2(target.getX(), target.getY());
		double xDist = pixelSpeed * Math.sin(targetRadian);
		double yDist = pixelSpeed * -Math.cos(targetRadian);
		return new Point2D.Double(xDist, yDist);
	}

	/**
	 * @param maxTurn the maxTurn to set
	 */
	public void setMaxTurn(double maxTurn) {
		this.maxTurn = maxTurn;
	}

	/**
	 * @return the maxTurn
	 */
	public double getMaxTurn() {
		return maxTurn;
	}

	/**
	 * @param targets the targets to set
	 */
	public void setTargets(ArrayList<Point2D> targets) {
		this.targets = targets;
	}

	/**
	 * @return the targets
	 */
	public ArrayList<Point2D> getTargets() {
		return targets;
	}
}