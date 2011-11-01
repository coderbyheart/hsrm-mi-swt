package data.dataobject;

import java.util.*;

/**
 * Data-Klasse für eine Welt
 */
public class WorldData extends DataObject
{

	private int width;
	private int height;
	private ArrayList<PlacedTileData> placedTiles;
	private BaseTileData baseTile;
	private ArrayList<TileData> tiles;
	private ArrayList<VehicleData> vehicles;

	public int getWidth()
	{
		return this.width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 * 
	 * @return 
	 */
	public BaseTileData getBasetile()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param basetile
	 * @return 
	 */
	public void setBasetile(BaseTileData basetile)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public TileData[] getTiles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tiles
	 * @return 
	 */
	public void setTiles(TileData[] tiles)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public PlacedTileData[] getPlacedTiles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param placedTiles
	 * @return 
	 */
	public void setPlacedTiles(PlacedTileData[] placedTiles)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public VehicleData[] getVehicleData()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param vehicles
	 * @return 
	 */
	public void setVehicleData(VehicleData[] vehicles)
	{
		throw new UnsupportedOperationException();
	}
}