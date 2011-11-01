package gui_develop;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class BinView
extends JPanel {
	
	private static final long serialVersionUID = -4245235521548402554L;
	
	TileView constantTile;
	
	public BinView() {
		super();
		
		setLayout(null);
		
		Dimension binDimension = new Dimension(200, 100);
		setPreferredSize(binDimension);
		setSize(getPreferredSize());
		

		constantTile = new TileView(false, null, null);
		constantTile.setBounds(
				( getWidth() - constantTile.getWidth() ) / 2,
				( getHeight() - constantTile.getHeight() ) / 2,
				constantTile.getWidth(), 
				constantTile.getHeight());

		add(constantTile);
		
		setBackground(Color.black);
	}

}
