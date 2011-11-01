package gui_develop;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import model.planer.TileModel;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import view.MenuView;
import config.AppConfig;
import data.DataFactory;
import data.datamanager.TileManager;
import data.dataobject.TileData;
import data.datasource.DataSource;
import data.datasource.xml.XMLDataSource;

//TODO: Bilder der Toolar-Kacheln aus XML

public class GuiTestApp extends JFrame {

	private static final long serialVersionUID = -2350761930786701790L;

	JLayeredPane layeredPane;
	WorldView world;
	ToolbarView toolbar;
	BinView bin;

	private DataFactory df;

	private MenuView menu;

	public GuiTestApp() {
		super("Verkehrsplaner-GUI");

		// Set up logging
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);

		// DataFactory initialisieren
		AppConfig config = new AppConfig();
		DataSource ds = new XMLDataSource(config.getXmlDir());
		df = new DataFactory();
		df.setDataSource(ds);

		// In der Toolbar und auf der Welt werden zufaellig eingefaerbte
		// bunte Kacheln angezeigt, diese dienen der Repraesentation
		// von Straßen- und Fahrzeugkacheln.

		this.setPreferredSize(new Dimension(800, 600));

		layeredPane = new JLayeredPane();

		BinDragDrop binDragDrop = new BinDragDrop(this);
		ToolbarDragDrop toolbarDragDrop = new ToolbarDragDrop(this);

		world = new WorldView(binDragDrop);
		// Liste mit Kacheln bauen
		TileManager tm = new TileManager(config);
		ArrayList<TileModel> tiles = new ArrayList<TileModel>();
		for (TileData tileData : df.getTiles()) {
			tiles.add(tm.getModelFromData(tileData));
		}
		toolbar = new ToolbarView(toolbarDragDrop, tiles);
		bin = new BinView();

		menu = new MenuView(config, df);
		layeredPane.add(menu);

		layeredPane.add(bin);
		layeredPane.add(world);
		layeredPane.add(toolbar);

		add(layeredPane);

		pack();

		layeredPane.setPreferredSize(getSize());
		resize();

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// catch resize events
		addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				resize();
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
			}
		});

	}

	private void resize() {
		menu.setBounds(0, 0, getWidth(), 20);
		world.setBounds(0, 20, getWidth() - 200, getHeight() - 20);
		toolbar.setBounds(getWidth() - 200, 20, 200, getHeight() - 20);
		toolbar.setSize(200, getHeight() - 120 - 20);

		bin.setBounds(getWidth() - 200, getHeight() - 100 - 20, 200, 100);

	}

	public static void main(String[] args) {
		new GuiTestApp();
	}

}