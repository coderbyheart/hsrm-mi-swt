package model.planer;

/**
 * Model für eine platzierte Kachel
 */
public class PlacedTileModel extends TileModel
{

	private int left;
	private int top;
	private int rotation;
	private PlacedVehicleModel[] placedVehicles;
	private boolean trafficlightEnabled = false;

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

	public PlacedVehicleModel[] getPlacedVehicles()
	{
		return this.placedVehicles;
	}

	public void setPlacedVehicles(PlacedVehicleModel[] placedVehicles)
	{
		this.placedVehicles = placedVehicles;
	}

	public boolean isTrafficlightEnabled()
	{
		return this.trafficlightEnabled;
	}

	public void setTrafficlightEnabled(boolean trafficlightEnabled)
	{
		this.trafficlightEnabled = trafficlightEnabled;
	}

	/**
	 * Fügt ein Fahrzeug einer Kachel hinzu
	 * @param vehicle
	 * @return 
	 */
	public void addVehicle(PlacedVehicleModel vehicle)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Lässt ein Fahrzeug aus der Kachel nehmen
	 * @param vehicle
	 * @return 
	 */
	public void removeVehicle(PlacedVehicleModel vehicle)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Schaltet die Ampel eine Phase weiter
	 * @return 
	 */
	public void next()
	{
		throw new UnsupportedOperationException();
	}
}