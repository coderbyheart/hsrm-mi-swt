package model.planer;

import java.util.ArrayList;

import util.Transform;

import config.AppConfig;

/**
 * Model für eine platzierte Kachel
 */
public class PlacedTileModel extends TileModel
{

	public static final int ROTATION_CHANGED = 1;
	public static final int LEFT_CHANGED = 2;
	public static final int TOP_CHANGED = 3;
	public static final int POSITION_CHANGED = 4;
	private int left;
	private int top;
	private double rotation;
	private boolean trafficlightsEnabled = false;
	private ArrayList <PlacedVehicleModel> placedVehicles;
	private WorldModel world;
	private AppConfig config;
	
	public PlacedTileModel(AppConfig config, WorldModel world)
	{
		this.config = config;
		this.setWorld(world);
	}

	public int getLeft()
	{
		return this.left;
	}

	public void setLeft(int left)
	{
		this.left = left;
		setChanged();
		notifyObservers(LEFT_CHANGED);
	}

	public int getTop()
	{
		return this.top;
	}

	public void setTop(int top)
	{
		this.top = top;
		setChanged();
		notifyObservers(TOP_CHANGED);
	}
	
	public void setPosition(int top, int left) {
		
		setTop(top);
		setLeft(left);
		setChanged();
		notifyObservers(POSITION_CHANGED);
	}

	public double getRotation()
	{
		return this.rotation;
	}

	public void setRotation(double rotation)
	{
		this.rotation = rotation;
		updateTrafficlightPositions();
		setChanged();
		notifyObservers(ROTATION_CHANGED);
	}

	

	private void updateTrafficlightPositions() {
		//TODO: Ampeln mit drehen
		
	}

	public ArrayList<PlacedVehicleModel> getPlacedVehicles() {
		return placedVehicles;
	}

	public void setPlacedVehicles(ArrayList<PlacedVehicleModel> placedVehicles) {
		this.placedVehicles = placedVehicles;
	}

	/**
	 * Fügt ein Fahrzeug einer Kachel hinzu
	 * @param vehicle
	 */
	public void addVehicle(PlacedVehicleModel vehicle)
	{
		this.placedVehicles.add(vehicle);
	}

	/**
	 * Lässt ein Fahrzeug aus der Kachel nehmen
	 * @param vehicle
	 */
	public void removeVehicle(PlacedVehicleModel vehicle)
	{
		this.placedVehicles.remove(vehicle);
	}

	/**
	 * @param world the world to set
	 */
	public void setWorld(WorldModel world) {
		this.world = world;
	}

	/**
	 * @return the world
	 */
	public WorldModel getWorld() {
		return world;
	}
	
	/**
	 * Gibt die X-Position der Kachel in der Welt zurück
	 * 
	 * Hierzu wird die {@link AppConfig#getModelTileSize() interne Standardgröße
	 * der Kacheln} berücksichtigt, die einzelnen Views müssen diese Angabe dann
	 * noch entsprechend ihres Zoomfaktors umrechnen
	 * 
	 * @return X-Position des Fahrzeuges in der Welt
	 */
	public int getX() {
		return ((world.getTileIndex(this) % world.getWidth()))
				* config.getModelTileSize();
	}

	/**
	 * Gibt die Y-Position der Kachel in der Welt zurück
	 * 
	 * @see PlacedTileModel#getX() 
	 * @return Y-Position des Fahrzeuges in der Welt
	 */
	public int getY() {
		return (int) (Math.floor(world.getTileIndex(this) / world.getWidth()))
				* config.getModelTileSize();
	}

	/**
	 * Setzt, ob die Ampel, die auf dieser Kachel vorhanden sind, aktiv sind (und damit angezeigt werden)
	 * 
	 * @param trafficlightsEnabled
	 */
	public void setTrafficlightsEnabled(boolean trafficlightsEnabled) {
		this.trafficlightsEnabled = trafficlightsEnabled;
	}

	/**
	 * Gibt an, ob die Ampel, die auf dieser Kachel vorhanden sind, aktiv sind (und damit angezeigt werden)
	 * 
	 * @return boolean 
	 */
	public boolean isTrafficlightsEnabled() {
		return trafficlightsEnabled;
	}

	public void rotateRight() {
		setRotation(Transform.fixAngle(getRotation() + Math.PI / 2));
	}
}