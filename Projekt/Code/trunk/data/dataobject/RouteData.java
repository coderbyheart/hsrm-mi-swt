package data.dataobject;

/**
 * Data-Klasse für ein Strassenstück
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: RouteData.java 134 2010-12-29 20:45:57Z mtack001 $
 */
public abstract class RouteData extends DataObject {

	private LocationData start;
	private LocationData end;

	/**
	 * Konstruktor
	 */
	public RouteData() {
	}

	/**
	 * Konstruktor mit Start- und Endpunkt
	 */
	public RouteData(LocationData start, LocationData end) {
		setStart(start);
		setEnd(end);
	}

	/**
	 * Gibt den Startpunkt zurück
	 * 
	 * @return Startpunkt
	 */
	public LocationData getStart() {
		return start;
	}

	/**
	 * Gibt den Enpunkt zurück
	 * 
	 * @return Endpunkt
	 */
	public LocationData getEnd() {
		return end;
	}

	/**
	 * Setzt den Startpunkt
	 * 
	 * @param start
	 */
	public void setStart(LocationData start) {
		this.start = start;
	}

	/**
	 * Setzt den Endpunkt
	 * 
	 * @param end
	 */
	public void setEnd(LocationData end) {
		this.end = end;
	}
}