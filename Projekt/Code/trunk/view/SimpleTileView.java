package view;

import model.planer.DummyTile;

import javax.swing.JPanel;

/**
 * Dummy einer Kachel
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: SimpleTileView.java 223 2011-01-17 08:13:32Z mtack001 $
 */
public class SimpleTileView extends JPanel {
	private static final long serialVersionUID = 303228970167196867L;
	DummyTile tile = new DummyTile();
	private boolean moveable;

	public SimpleTileView(boolean moveable) {
		this.moveable = moveable;
		setBackground(tile.getColor());

	}
	
	public SimpleTileView clone()
	{
		SimpleTileView clone = new SimpleTileView(moveable);
		clone.tile.setColor(tile.getColor());
		clone.setBackground(tile.getColor());
		return clone;
	}
}
