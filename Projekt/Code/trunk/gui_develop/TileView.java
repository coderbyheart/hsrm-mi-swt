package gui_develop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TileView extends JPanel {

	private static final long serialVersionUID = -2442573847439237366L;

	boolean moveable;
	AbstractDragDrop dragdrop;
	BufferedImage tileImage;

	// JPanel BaseTile = new JPanel();
	// JPanel Curve = new JPanel();
	// JPanel Straight = new JPanel();
	// JPanel CrossRoad = new JPanel();
	// JPanel DoubleCurve = new JPanel();
	// JPanel Junction = new JPanel();

	Tile tile = new Tile();

	boolean selected = false;

	public TileView(boolean moveable, AbstractDragDrop dragdrop,
			BufferedImage tileImage) {
		super();

		this.moveable = moveable;
		this.dragdrop = dragdrop;
		this.tileImage = tileImage;

		Dimension tileDimension;
		if (moveable)
			tileDimension = new Dimension(100, 100);
		else
			tileDimension = new Dimension(70, 70);

		setSize(tileDimension);
		setPreferredSize(tileDimension);
		// setBackground(tile.color);

		setLayout(null);

		if (dragdrop != null) {
			addMouseListener(dragdrop);
			addMouseMotionListener(dragdrop);
		}
	}

	public void paintComponent(Graphics g) {
		BufferedImage resizedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = resizedImage.createGraphics();
		g2d.drawImage(tileImage, 0, 0, 100, 100, null);
		g2d.dispose();
		g.drawImage(resizedImage, 0, 0, null);
	}

	public TileView clone() {
		TileView clone = new TileView(moveable, dragdrop, tileImage);
		clone.setTile(getTile().clone());
		return clone;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
//		setBackground(tile.color);
		repaint();

		selected = false;
	}

	// rotate tile
	public void rotate() {
		tile.setRotation(this.tile.getRotation() + 1);

		// demo

		// just testing, change color too
//		tile.setColor(new Tile().color);
//		setBackground(this.tile.color);

		tile.setImage(new Tile().image);
		repaint();
		
		System.out.println(this.tile.getRotation());

	}

	// reset / delete tile
	public void reset() {
		this.tile.setColor(Color.BLACK);
		setBackground(tile.color);
		repaint();

		System.out.println("reset");
	}
}
