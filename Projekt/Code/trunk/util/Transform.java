package util;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Hilfsklasse für Transformationen
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: Transform.java 214 2011-01-16 11:46:29Z mtack001 $
 */
public abstract class Transform {

	/**
	 * Dreht einen Punkt
	 * 
	 * @param point
	 * @param rotation
	 * @return gedrehter Punkt
	 */
	public static Point2D rotatePoint(Point2D point, double rotation, int size) {
		double cosPhi = Math.cos(rotation);
		double sinPhi = Math.sin(rotation);

		double centerX = size / 2;
		double centerY = size / 2;
		double rotX = point.getX() - centerX;
		double rotY = point.getY() - centerY;

		return new Point2D.Double(cosPhi * rotX - sinPhi * rotY + centerX,
				sinPhi * rotX + cosPhi * rotY + centerY);
	}

	/**
	 * Dreht ein Polygon
	 * 
	 * @param polygon
	 * @param rotation
	 */
	public static Polygon rotatePolygon(Polygon polygon, double rotation,
			int size) {
		Polygon newPolygon = new Polygon();
		for (int i = 0; i < polygon.npoints; i++) {
			Point2D point = new Point2D.Double(polygon.xpoints[i],
					polygon.ypoints[i]);
			point = rotatePoint(point, rotation, size);
			newPolygon.addPoint((int) point.getX(), (int) point.getY());
		}
		return newPolygon;
	}

	/**
	 * Dreht ein Bild
	 * 
	 * @param src
	 * @param rotation
	 * @return Gedrehtes Bild
	 */
	public static BufferedImage rotateImage(BufferedImage src, double rotation) {
		AffineTransform affineTransform = AffineTransform.getRotateInstance(
				rotation, src.getWidth() / 2, src.getHeight() / 2);
		int iType = src.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : src
				.getType();
		BufferedImage rotatedImage = new BufferedImage(src.getWidth(),
				src.getHeight(), iType);
		Graphics2D g = (Graphics2D) rotatedImage.getGraphics();
		g.setTransform(affineTransform);
		g.drawImage(src, 0, 0, null);
		return rotatedImage;
	}
	
	/**
	   * Hilfsmethode, die eine Grafik (BufferedImage) drehen kann. Verwendet
	   * AffineTransform zum Drehen. Wichtig ist, den Drehpunkt in die Mitte der
	   * Grafik zu legen.
	   * 
	   * @param src
	   *          Grafik
	   * @param degrees
	   *          um soviele Grad wird gedreht
	   * @return neue gedrehte Grafik
	   */
	  public static BufferedImage rotateImageDegrees(BufferedImage src, double degrees) {
	    AffineTransform affineTransform = AffineTransform.getRotateInstance(Math
	        .toRadians(degrees), src.getWidth() / 2, src.getHeight() / 2);
	    BufferedImage rotatedImage = new BufferedImage(src.getWidth(), src
	        .getHeight(), src.getType());
	    Graphics2D g = (Graphics2D)rotatedImage.getGraphics();
	    g.setTransform(affineTransform);
	    g.drawImage(src, 0, 0, null);
	    return rotatedImage;
	  }
	

	/**
	 * Fixt eine Winkel-Angabe
	 * 
	 * @param angrad
	 *            Winkel-Angabe im Bogenmaß (n-faches von π)
	 * @return gesäuberter Winkel im Bogenmaß
	 */
	public static double fixAngle(double angrad) {
		if (toRoundedDegrees(angrad) == -180)
			angrad = Math.PI;
		if (toRoundedDegrees(angrad) == -0)
			angrad = 0;
		if (toRoundedDegrees(angrad) == 180)
			angrad = Math.PI;
		// Winkel ist größer als 180 Grad
		if (Math.abs(angrad) > Math.PI) {
			// Normieren auf 360
			angrad = angrad % (Math.PI * 2);
			// Überschrittenen Teil holen
			double diff = Math.PI * 2 - angrad;
			if (angrad > 0) {
				angrad = -diff;
			} else if (angrad < 0) {
				angrad = diff;
			}
			angrad = fixAngle(angrad);
		}
		return angrad;
	}

	/**
	 * Fixt eine Winkel-Angabe
	 * 
	 * @param angrad
	 *            Winkel-Angabe im Bogenmaß (n-faches von π)
	 * @return gesäuberter Winkel in Grad
	 */
	public static int fixAngle2Degree(double angrad) {
		return toRoundedDegrees(fixAngle(angrad));
	}

	/**
	 * Hilfsmethode zum korrigieren von Rundungsfehlern
	 * 
	 * @param angrad
	 * @return Winkel in Grad
	 */
	public static int toRoundedDegrees(double angrad) {
		return (int) Math.round(Math.toDegrees(angrad));
	}

	/**
	 * Hilfsmethode für Math.atan2, die y invertiert, da das geometrische
	 * Koordinaten-System "unten" bei 0 anfängt, während das Koordinaten-System
	 * der Anwendung "oben".
	 * 
	 * @param x
	 *            X-Koordinate innerhalb der Anwendung
	 * @param y
	 *            Y-Koordinate innerhalb der Anwendung
	 * @return atan2 für diese Koordinaten
	 */
	public static double screenAtan2(double x, double y) {
		return Math.atan2(x, -y);
	}

}