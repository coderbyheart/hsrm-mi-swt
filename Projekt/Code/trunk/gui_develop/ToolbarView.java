package gui_develop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.planer.TileModel;
import util.SVGConverter;
import util.SVGConverterException;

public class ToolbarView extends JPanel {

	private static final long serialVersionUID = 7062904728415022095L;

	JScrollPane scroll;
	Box tiles;
	JPanel tileArea = new JPanel();

	ArrayList<TileView> moveableTiles = new ArrayList<TileView>();

	/**
	 * @param dragdrop
	 * @param tileList
	 *            Die Liste mit allen verfügbaren Straßenkacheln
	 */
	public ToolbarView(AbstractDragDrop dragdrop, ArrayList<TileModel> tileList) {
		super();

		setLayout(null);

		tiles = Box.createVerticalBox();
		tiles.setAutoscrolls(true);
		tiles.setBackground(Color.gray);
		setBorder(null);

		for (TileModel tileModel : tileList) {
			try {
				BufferedImage tileImage = ImageIO.read(SVGConverter
						.getPNG(tileModel.getSource()));
				TileView tileView = new TileView(true, dragdrop, tileImage);
				moveableTiles.add(tileView);
				if (moveableTiles.size() > 1) tiles.add(Box.createVerticalStrut(5));
				tiles.add(tileView, BorderLayout.CENTER);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SVGConverterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		tileArea.add(tiles, BorderLayout.CENTER);
		tileArea.setBackground(Color.gray);
		tileArea.setBorder(null);

		scroll = new JScrollPane(tileArea);
		scroll.setBorder(null);
		add(scroll, BorderLayout.NORTH);

	}

	public void setSize(int width, int height) {
		super.setSize(width, height);

		scroll.setPreferredSize(new Dimension(200, height));
		scroll.setSize(scroll.getPreferredSize());
	}

}
