package gui_develop;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Tile implements Serializable {

	private static final long serialVersionUID = -3726580421282823984L;

	Color color;
	BufferedImage image; 
	
	int rotation = 0;

	public Tile() {
//		int r = (int) (Math.random() * 255);
//		int g = (int) (Math.random() * 255);
//		int b = (int) (Math.random() * 255);
//
//		color = new Color(r, g, b);
	}

	public Color getColor() {
		return color;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		// fix rotation positive
		while ( rotation > 3 ) {
			rotation -= 4;
		}
		// fix rotation negative
		while ( rotation < 0 ) {
			rotation += 4;
		}
		
		this.rotation = rotation;
	}
	
	public Tile clone()
	{
		Tile clone = new Tile();
		clone.setImage(getImage());
//		clone.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
		return clone;
	}
	
}
