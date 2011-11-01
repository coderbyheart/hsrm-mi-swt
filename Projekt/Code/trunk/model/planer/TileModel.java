package model.planer;

import java.util.*;

/**
 * Model für eine Kachel mir Straßen
 */
public class TileModel extends BaseTileModel
{
	
	private String id;
	private String name;
	private String description;
	private ArrayList<RouteModel> routes;
	private ArrayList<TrafficlightModel>trafficlights;

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setRoutes(ArrayList<RouteModel> routes) {
		this.routes = routes;
	}

	public ArrayList<RouteModel> getRoutes() {
		return routes;
	}
	

	/**
	 * <p>
	 * Gibt an, ob es eine Ampel-Version dieser Kachel gibt
	 * <p>
	 * Leitet sich aus allen Strecken dieser Kachel ab {@link #getRoutes()},
	 * und ob deren Punkte {@link RouteModel#getStart()}, {@link RouteModel#getEnd()}
	 * eine Ampel haben {@link LocationModel#isTrafficlight()()}
	 */
	public boolean hasTrafficlight()
	{
		boolean hasTrafficLight = false;
		for(RouteModel route : routes){
			if(route.getStart().isTrafficlight() && route.getEnd().isTrafficlight()) hasTrafficLight = true;
			else hasTrafficLight = false;
		}
		return hasTrafficLight;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setTrafficlights(ArrayList<TrafficlightModel> trafficlights) {
		this.trafficlights = trafficlights;
	}

	public ArrayList<TrafficlightModel> getTrafficlights() {
		return trafficlights;
	}
}