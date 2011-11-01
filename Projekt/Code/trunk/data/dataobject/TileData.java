package data.dataobject;

import java.util.ArrayList;

/**
 * Data-Klasse für eine Kachel mit Straße
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: TileData.java 232 2011-01-17 12:56:21Z sfran001 $
 */
public class TileData extends BaseTileData {

	private String id;
	private String name;
	private String description;
	private TrafficlightData trafficlight;
	private ArrayList<RouteData> routes;

	/**
	 * Gibt die ID zurück
	 * 
	 * @return ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Setzt die ID
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gibt den Namen zurück
	 * 
	 * @return Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setzt den Namen
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt die Beschreibung zurück
	 * 
	 * @return Beschreibung
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setzt die Beschreibung
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gibt die Strecken dieser Kacheln zurück
	 * 
	 * @return Strecken
	 */
	public ArrayList<RouteData> getRoutes() {
		return routes;
	}

	/**
	 * Setzt die Strecken dieser Kachel
	 * 
	 * @param routes
	 */
	public void setRoutes(ArrayList<RouteData> routes) {
		this.routes = routes;
	}
	
	/**
	 * Erzeugt die String-Repräsentation des Objektes
	 * 
	 * @return string
	 */
	public String toString()
	{
		String strecken = "";
		for(RouteData route: getRoutes()) {
			if (route instanceof CurveData) {
				strecken = strecken.concat(String.format(", %s(%s)%s", route.getStart(), ((CurveData) route).getCorner(), route.getEnd()));
			} else {
				strecken = strecken.concat(String.format(", %s->%s", route.getStart(), route.getEnd()));
			}
		}
		return String.format("%s #%s (%s) %s (%d Strecken %s)", this.getClass().getName(), getId(), getName(), getSource(), getRoutes().size(), strecken);
	}

	public void setTrafficlight(TrafficlightData trafficlight) {
		this.trafficlight = trafficlight;
	}

	public TrafficlightData getTrafficlight() {
		return trafficlight;
	}
}