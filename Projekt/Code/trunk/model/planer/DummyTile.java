package model.planer;

import java.awt.Color;

/**
 * Dummy-Kachel mit zufälliger Farbe
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: DummyTile.java 223 2011-01-17 08:13:32Z mtack001 $
 */
public class DummyTile {

	Color color;

	public DummyTile() {
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);

		color = new Color(r, g, b);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
