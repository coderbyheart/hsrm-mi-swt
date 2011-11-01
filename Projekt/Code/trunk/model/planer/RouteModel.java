package model.planer;

import java.awt.Point;

/**
 * Model für ein Strassenstück
 * 
 * @version $Id: RouteModel.java 159 2011-01-08 18:10:54Z mtack001 $
 */
public abstract class RouteModel extends PlanerModel {
	private LocationModel start;
	private LocationModel end;

	/**
	 * Gibt den Startpunkt zurück
	 * 
	 * @return Startpunkt
	 */
	public LocationModel getStart() {
		return this.start;
	}

	/**
	 * Setzt den Startpunkt
	 * 
	 * @param location
	 */
	public void setStart(LocationModel location) {
		this.start = location;
	}

	/**
	 * Gibt den Endpunkt zurück
	 * 
	 * @return Endpunkt
	 */
	public LocationModel getEnd() {
		return this.end;
	}

	/**
	 * Setzt den Endpunkt
	 * 
	 * @param location
	 */
	public void setEnd(LocationModel location) {
		this.end = location;
	}

	public Point getStartPoint(int tileSize) {
		return getPointFromLocation(start, tileSize);
	}
	


	public Point getEndPoint(int tileSize) {
		return getPointFromLocation(end, tileSize);
	}
	protected Point getPointFromLocation(LocationModel location, int tileSize) {
		Point point = new Point();
		switch(location.getType()) {
		case CENTER:
			point.setLocation(tileSize / 2, tileSize / 2);
			break;
		case NORTH:
			point.setLocation(tileSize / 2, 0);
			break;
		case EAST:
			point.setLocation(tileSize, tileSize / 2);
			break;
		case SOUTH:
			point.setLocation(tileSize / 2, tileSize);
			break; 
		case WEST:
			point.setLocation(0, tileSize / 2);
			break; 
		case NORTHEAST:
			point.setLocation(0, 0);
			break;
		case SOUTHEAST:
			point.setLocation(tileSize, tileSize);
			break;
		case SOUTHWEST:
			point.setLocation(0, tileSize);
			break;
		case NORTHWEST:
			point.setLocation(tileSize, 0);
			break;
		}
		return point;
	}
}