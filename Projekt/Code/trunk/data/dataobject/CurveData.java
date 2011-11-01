package data.dataobject;

/**
 * Data-Klasse für eine Kurve
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: CurveData.java 134 2010-12-29 20:45:57Z mtack001 $
 */
public class CurveData extends RouteData {

	private LocationData corner;

	/**
	 * Gibt den Eckpunkt zurück
	 * 
	 * @return Eckpunkt
	 */
	public LocationData getCorner() {
		return corner;
	}

	/**
	 * Setzt den Eckpunkt
	 * 
	 * @param corner
	 */
	public void setCorner(LocationData corner) {
		this.corner = corner;
	}
}