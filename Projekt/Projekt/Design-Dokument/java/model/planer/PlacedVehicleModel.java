package model.planer;

/**
 * Model für ein platziertes Fahrzeug
 */
public class PlacedVehicleModel extends VehicleModel
{

	private int left;
	private int top;
	private int rotation;
	private PlacedTileModel tile;

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

	public PlacedTileModel getTile()
	{
		return this.tile;
	}

	public void setTile(PlacedTileModel tile)
	{
		this.tile = tile;
	}
}