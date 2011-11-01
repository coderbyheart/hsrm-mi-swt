package gui_develop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class WorldView 
extends JPanel {

	private static final long serialVersionUID = 4508288235510368808L;
	
	TileView tiles[] = new TileView[25];
	
	public WorldView(AbstractDragDrop dragdrop) {
		super();
		
		Dimension worldDimension = new Dimension(600, 600);
		setPreferredSize(worldDimension);
		setBackground(new Color(150, 255, 150));

		setLayout(new GridLayout(5,5));
		
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new TileView(false, dragdrop, null);
			add(tiles[i]);
		}

	}

}
