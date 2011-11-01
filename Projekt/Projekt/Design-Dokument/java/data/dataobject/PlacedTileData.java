package data.dataobject;

import java.util.*;

/**
 * Data-Klasse für eine platzierte Klasse
 */
public class PlacedTileData extends TileData
{

	private int left;
	private int top;
	private int rotation;
	private ArrayList<PlacedVehicleData> vehicles;

	public int getLeft()
	{
		return this.left;
	}

	public void setLeft(int left)
	{
		this.left = left;
	}

	public int getTop()
	{
		return this.top;
	}

	public void setTop(int top)
	{
		this.top = top;
	}

	public int getRotation()
	{
		return this.rotation;
	}

	public void setRotation(int rotation)
	{
		this.rotation = rotation;
	}

	/**
	 * 
	 * @return 
	 */
	public PlacedVehicleData[] getVehicles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param vehicles
	 * @return 
	 */
	public void setVehicles(PlacedVehicleData[] vehicles)
	{
		throw new UnsupportedOperationException();
	}
}