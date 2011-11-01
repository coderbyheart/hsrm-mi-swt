package data.dataobject;

import java.util.*;

import model.planer.PlacedTrafficlightModel;

/**
 * Data-Klasse für eine platzierte Klasse
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: PlacedTileData.java 230 2011-01-17 12:04:04Z sfran001 $
 */
public class PlacedTileData extends TileData {

	private int left;
	private int top;
	private int rotation;
	private boolean trafficlight = false;
	private ArrayList<PlacedVehicleData> vehicles = new ArrayList<PlacedVehicleData>();
	
	public int getLeft() {
		return this.left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return this.top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getRotation() {
		return this.rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public ArrayList<PlacedVehicleData> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<PlacedVehicleData> vehicles) {
		this.vehicles = vehicles;
	}
	
	/**
	 * Erzeugt die String-Repräsentation des Objektes
	 * 
	 * @return string
	 */
	public String toString()
	{
		return String.format("%s #%s %d/%d %d°", this.getClass().getName(), getId(), getTop(), getLeft(), Math.toDegrees(getRotation()));
	}

	public void setTrafficlight(boolean trafficlight) {
		this.trafficlight = trafficlight;
	}

	public boolean isTrafficlight() {
		return trafficlight;
	}

}