package model.planer;

/**
 * Model für ein Kurvenstück.
 */
public class CurveModel extends StraightModel
{

	/**
	 * "Corner" gibt die Ecke an, um die sich die Kurve bewegt
	 */
	private LocationModel corner;

	/**
	 * 
	 * @return 
	 */
	public LocationModel getCorner()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param corner
	 * @return 
	 */
	public void setCorner(LocationModel corner)
	{
		throw new UnsupportedOperationException();
	}
}