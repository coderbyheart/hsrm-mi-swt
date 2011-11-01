package data.dataobject;

import java.util.*;

/**
 * Data-Klasse für eine Kachel mit Straße
 */
public class TileData extends BaseTileData
{

	private String id;
	private String name;
	private String description;
	private ArrayList<RouteData> routes;

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

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
	 * Gibt die Strecken dieser Kacheln zurück
	 * @return 
	 */
	public RouteData[] getRoutes()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Setzt die Strecken dieser Kachel
	 * @param routes
	 * @return 
	 */
	public void setRoutes(RouteData[] routes)
	{
		throw new UnsupportedOperationException();
	}
}