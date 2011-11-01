package model.planer;

import data.LocationType;

/**
 * Model für ein Start-/Endpunkt eines Strassenstückes.
 * 
 * @version $Id: LocationModel.java 191 2011-01-12 17:59:08Z sfran001 $
 */
public class LocationModel extends PlanerModel {

	private LocationType type;
	private boolean trafficlight;

	/**
	 * Gibt den Typ zurück
	 * 
	 * @return Typ
	 */
	public LocationType getType() {
		return this.type;
	}

	/**
	 * Setzt den Typ
	 * 
	 * @param type
	 */
	public void setType(LocationType type) {
		this.type = type;
	}


	/**
	 * Gibt an, ob es ein Nord-Punkt ist
	 * 
	 * @return true|false
	 */
	public boolean isNorth() {
		return type == LocationType.NORTH;
	}

	/**
	 * Gibt an, ob es ein Ost-Punkt ist
	 * 
	 * @return true|false
	 */
	public boolean isEast() {
		return type == LocationType.EAST;
	}

	/**
	 * Gibt an, ob es ein Süd-Punkt ist
	 * 
	 * @return true|false
	 */
	public boolean isSouth() {
		return type == LocationType.SOUTH;
	}

	/**
	 * Gibt an, ob es ein West-Punkt ist
	 * 
	 * @return true|false
	 */
	public boolean isWest() {
		return type == LocationType.WEST;
	}

	/**
	 * Gibt an, ob es ein Zentrums-Punkt ist
	 * 
	 * @return true|false
	 */
	public boolean isCenter() {
		return type == LocationType.CENTER;
	}

	/**
	 * Gibt an, ob es ein Nord-Ost-Punkt ist
	 * 
	 * @return true|false
	 */
	public boolean isNorthEast() {
		return type == LocationType.NORTHEAST;
	}

	/**
	 * Gibt an, ob es ein Süd-Ost-Punkt ist
	 * 
	 * @return true|false
	 */
	public boolean isSouthEast() {
		return type == LocationType.SOUTHEAST;
	}

	/**
	 * Gibt an, ob es ein Süd-West-Punkt ist
	 * 
	 * @return true|false
	 */
	public boolean isSouthWest() {
		return type == LocationType.SOUTHWEST;
	}

	/**
	 * Gibt an, ob es ein Nord-West-Punkt ist
	 * 
	 * @return true|false
	 */
	public boolean isNorthWest() {
		return type == LocationType.NORTHWEST;
	}

	public void setTrafficlight(boolean trafficlight) {
		this.trafficlight = trafficlight;
	}

	public boolean isTrafficlight() {
		return trafficlight;
	}
}