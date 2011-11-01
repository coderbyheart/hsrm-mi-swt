package data.dataobject;

/**
 * Data-Klasse für ein Strassenstück
 */
public abstract class RouteData extends DataObject
{

	private LocationData start;
	private LocationData end;

	/**
	 * 
	 * @return 
	 */
	public LocationData getStart()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public LocationData getEnd()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param start
	 * @return 
	 */
	public void setStart(LocationData start)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param end
	 * @return 
	 */
	public void setEnd(LocationData end)
	{
		throw new UnsupportedOperationException();
	}
}