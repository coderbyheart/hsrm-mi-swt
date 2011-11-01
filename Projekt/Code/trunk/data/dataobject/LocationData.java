package data.dataobject;

import data.LocationType;

/**
 * Data-Klasse für ein Punkt eines Strassenstückes
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LocationData.java 191 2011-01-12 17:59:08Z sfran001 $
 */
public class LocationData extends DataObject {

	private LocationType type;
	private boolean trafficlight = false;

	/**
	 * Konstruktor
	 * 
	 * @param type
	 */
	public LocationData(LocationType type) {
		this.setType(type);
	}

	/**
	 * Konstruktor
	 * 
	 * @param type
	 * @param trafficlight
	 */
	public LocationData(LocationType type, boolean trafficlight) {
		this.setType(type);
		this.setTrafficlight(trafficlight);
	}

	/**
	 * Setzt den Type des Punktes
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(LocationType type) {
		this.type = type;
	}

	/**
	 * Gibt den Typ des Punktes zurück
	 * 
	 * @return the type
	 */
	public LocationType getType() {
		return type;
	}
	
	public String getTypeString(){
		String type = "";
		switch(this.type) {
		case CENTER:
			type = "C";
			break;
		case NORTH:
			type = "N";
			break;
		case EAST:
			type = "E";
			break;
		case SOUTH:
			type = "S";
			break; 
		case WEST:
			type = "W";
			break; 
		case NORTHEAST:
			type = "NE";
			break;
		case SOUTHEAST:
			type = "SE";
			break;
		case SOUTHWEST:
			type = "SW";
			break;
		case NORTHWEST:
			type = "NW";
			break;
		}
		return type;
	}

	
	
	/**
	 * String-Repräsentation erzeugen
	 * 
	 * @return String
	 */
	public String toString()
	{
		return String.format("%s%s", getType().toString(), isTrafficlight() ? "*" : "");
	}

	public void setTrafficlight(boolean trafficlight) {
		this.trafficlight = trafficlight;
	}

	public boolean isTrafficlight() {
		return trafficlight;
	}

}