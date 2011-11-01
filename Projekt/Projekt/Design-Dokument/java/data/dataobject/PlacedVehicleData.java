package data.dataobject;

/**
 * Data-Klasse für ein platziertes Fahrzeug
 */
public class PlacedVehicleData extends VehicleData
{

	private int left;
	private int top;
	private int rotation;

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
}