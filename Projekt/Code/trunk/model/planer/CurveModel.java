package model.planer;

import java.awt.geom.Point2D;

/**
 * Model für ein Kurvenstück.
 */
public class CurveModel extends RouteModel
{
	/**
	 * "Corner" gibt die Ecke an, um die sich die Kurve bewegt
	 */
	private LocationModel corner;

	public LocationModel getCorner()
	{
		return this.corner;
	}
	
	public void setCorner(LocationModel corner)
	{
		this.corner = corner;
	}

	public Point2D getCornerPoint(int tileSize) {
		return getPointFromLocation(corner, tileSize);
	}
}