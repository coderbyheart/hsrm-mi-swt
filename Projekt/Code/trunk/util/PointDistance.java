package util;

import java.awt.geom.Point2D;

public class PointDistance {
	private Point2D point;
	private double distance;

	public PointDistance(Point2D point, double distance) {
		this.setPoint(point);
		this.setDistance(distance);
	}

	public String toString() {
		return String.format("%s: %dx%d Entfernung: %d", this.getClass(),
				(int) getPoint().getX(), (int) getPoint().getY(),
				(int) getDistance());
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}

	public Point2D getPoint() {
		return point;
	}
}
