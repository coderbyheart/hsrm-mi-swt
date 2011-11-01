package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import model.planer.*;
import model.toolbar.TrafficlightModel;
import model.toolbar.TrashModel;

/**
 * View für die Werkzeugleiste
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: ToolbarView.java 227 2011-01-17 11:45:42Z mtack001 $
 */
public class ToolbarView extends JPanel implements View {

	private TrashModel trash;
	private TrafficlightModel trafficlight;
	private ArrayList<TileModel> tiles;
	private ArrayList<VehicleModel> vehicles;
	
	JPanel trashPanel = new JPanel();
	JPanel tilesPanel = new JPanel();

	
	public void buildToolbar(){
		Dimension toolbarDimension = new Dimension(200, 600);
		Dimension binDimension = new Dimension(200, 100);
		Dimension tilesDimension = new Dimension(200, 500);

		setPreferredSize(toolbarDimension);
		trashPanel.setPreferredSize(binDimension);
		tilesPanel.setPreferredSize(tilesDimension);

		JScrollBar scroll = new JScrollBar();
		tilesPanel.add(scroll);

		tilesPanel.setBackground(Color.gray);
		trashPanel.setBackground(Color.black);

		add(tilesPanel, BorderLayout.NORTH);
		add(trashPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Setzt das Model für den Papierkorb
	 * 
	 * @param trash
	 */
	public void setTrash(TrashModel trash) {
		this.trash = trash;
	}

	/**
	 * Setzt die Models für die Kacheln
	 * 
	 * @param tiles
	 */
	public void setTiles(ArrayList<TileModel> tiles) {
		this.tiles = tiles;
	}

	/**
	 * Setzt die Models für die Fahrzeuge
	 * 
	 * @param vehicles
	 */
	public void setVehicles(ArrayList<VehicleModel> vehicles) {
		this.vehicles = vehicles;
	}

	/**
	 * Setzt das Model für das Ampel-Icon
	 * 
	 * @param trafficlight
	 */
	public void setTrafficlight(TrafficlightModel trafficlight) {
		this.trafficlight = trafficlight;
	}

	/**
	 * @return Die Breite der Werkzeugeleiste in Pixeln zurück
	 * @todo Tastächliche Breite zurück geben
	 */
	public int getWidth() {
		return 200;
	}
}