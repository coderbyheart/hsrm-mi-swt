package model.planer;

/**
 * Model für ein Start-/Endpunkt eines Strassenstückes.
 */
public class LocationModel extends PlanerModel
{

	private String type;
	private boolean trafficlight;

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * 
	 * @return 
	 */
	public boolean hasTrafficLight()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isNorth()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isEast()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isSouth()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isWest()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isCenter()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isNorthEast()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isSouthEast()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isSouthWest()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public boolean isNorthWest()
	{
		throw new UnsupportedOperationException();
	}
}