package model.planer;

import java.util.*;

/**
 * Model für eine Kachel mir Straßen
 */
public class TileModel extends BaseTileModel
{

	private String name;
	private String description;
	private ArrayList<RouteModel> routes;

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

	/**
	 * 
	 * @return 
	 */
	public RouteModel[] getRoutes()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param routes
	 * @return 
	 */
	public void setRoutes(RouteModel[] routes)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <p>
	 * Gibt an, ob es eine Ampel-Version dieser Kachel gibt
	 * <p>
	 * Leitet sich aus allen Strecken dieser Kachel ab {@link #getRoutes()},
	 * und ob deren Punkte {@link RouteModel#getStart()}, {@link RouteModel#getEnd()}
	 * eine Ampel haben {@link LocationModel#hasTrafficLight()}
	 * @return 
	 */
	public boolean hasTrafficlight()
	{
		throw new UnsupportedOperationException();
	}
}