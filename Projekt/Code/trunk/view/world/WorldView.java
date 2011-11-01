package view.world;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import view.View;

import config.AppConfig;
import model.planer.*;

/**
 * Verwaltet die Kartenansicht
 * 
 * @version $Id: WorldView.java 236 2011-01-17 13:23:54Z mtack001 $
 */
@SuppressWarnings("serial")
public class WorldView extends JPanel implements View, Observer {
	private static Logger logger = Logger.getLogger(WorldView.class);
	private WorldModel world;
	private ArrayList<WorldTileView> tiles;
	private ArrayList<WorldBaseTileView> baseTiles;
	private AppConfig config;
	private JLayeredPane layer;
	private ArrayList<WorldVehicleView> vehicles;
	private HashMap<PlacedVehicleModel, WorldVehicleView> vehicle2view;
	private static Integer LEVEL_BG				= new Integer(0);
	private static Integer LEVEL_TILE 			= new Integer(1);
	private static Integer LEVEL_TRAFFICLIGHT	= new Integer(2); 
	private static Integer LEVEL_VEHICLE 		= new Integer(3);
	private static Integer LEVEL_PATH	 		= new Integer(4);
	private static Integer LEVEL_DRAGANDDROP 	= new Integer(5);
	
	
	/**
	 * Zoom-Faktor der View
	 */
	private double zoom;

	/**
	 * Die gezoomte Kachelgröße zurück
	 */
	private int zoomedTileSize;
	private WorldPathView pathView;
	private WorldDragAndDropHandler dndHandler;

	/**
	 * Erzeugt eine neue View
	 * 
	 * @param model
	 * @param config
	 */
	public WorldView(AppConfig config, WorldModel model) {
		super();
		this.config = config;
		this.setZoom(1);
		logger.info("Neue Welt-View");
		setLayout(null);
		layer = new JLayeredPane();
		layer.setOpaque(false);
		layer.setLayout(null);
		layer.setSize(getSize());
		add(layer);

		dndHandler = new WorldDragAndDropHandler(this);
		
		this.setModel(model);
		
		// Bei Anpassung der View-Größe, Kachelgröße anpassen
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				layer.setSize(getSize());
				if (pathView != null) pathView.setSize(getSize());
				updateTileSize();
				updateTilePositions();
			}
		});		
	}

	public WorldModel getModel() {
		return this.world;
	}

	/**
	 * Setzt das Model
	 * 
	 * @param world
	 */
	public void setModel(WorldModel world) {
		logger.info(String.format("Bekomme neue Welt: %s", world));
		this.world = world;
		world.addObserver(this);

		// Fülle mit Basiskacheln
		baseTiles = new ArrayList<WorldBaseTileView>();
		for (int i = 0; i < world.getWidth() * world.getHeight(); i++) {
			WorldBaseTileView baseTileView = new WorldBaseTileView(world.getBaseTile(), i);
			baseTiles.add(baseTileView );
			layer.add(baseTileView , LEVEL_BG);
		}
		
		// Platzierte Kacheln
		tiles = new ArrayList<WorldTileView>();
		for (PlacedTileModel tileModel : world.getPlacedTiles()) {
			WorldTileView tileView = new WorldTileView(config, tileModel);
			tiles.add(tileView);
			dndHandler.addTile(tileView);
			layer.add(tileView, LEVEL_TILE);
			tileModel.addObserver(this);
		}
		
		// Fahrzeuge
		vehicles = new ArrayList<WorldVehicleView>();
		vehicle2view = new HashMap<PlacedVehicleModel, WorldVehicleView>();
		for (PlacedTileModel tileModel : world.getPlacedTiles()) {
			for (PlacedVehicleModel vehicleModel : tileModel
					.getPlacedVehicles()) {
				WorldVehicleView vehicleView = new WorldVehicleView(config,
						vehicleModel);
				vehicles.add(vehicleView);
				layer.add(vehicleView, LEVEL_VEHICLE);
				vehicleModel.addObserver(this);
				vehicle2view.put(vehicleModel, vehicleView);
			}
		}
		
		// Kachelgröße an Anwendungsfenster anpassen
		updateTilePositions();

		// Strecken
		pathView = new WorldPathView(config, world, zoom, zoomedTileSize);
		pathView.setSize(getSize());
		layer.add(pathView, LEVEL_PATH);
	}

	/**
	 * Kachelgröße an Anwendungsfenster anpassen
	 */
	private void updateTileSize() {
		int tileWidth = getSize().width / world.getWidth();
		int tileHeight = getSize().height / world.getHeight();
		int newTileSize = Math.max(config.getMinTileSize(),
				Math.min(tileWidth, tileHeight));
		setZoom((float) newTileSize / (float) config.getModelTileSize());
	}

	/**
	 * Kachelpositionen an Anwendungsfenster anpassen
	 */
	private void updateTilePositions() {
		
		for (WorldBaseTileView baseTileView : baseTiles) {
			int xpos = baseTileView.getIndex() % world.getWidth();
			int ypos = (int) Math.floor(baseTileView.getIndex() / world.getWidth());
			baseTileView.setLocation(zoomedTileSize * xpos, zoomedTileSize * ypos);
			baseTileView.setSize(zoomedTileSize, zoomedTileSize);
		}
		
		for (WorldTileView tileView : tiles) {
			tileView.setLocation((int)(tileView.getModel().getLeft() * zoomedTileSize), (int)(tileView.getModel().getTop() * zoomedTileSize));
			tileView.setSize(zoomedTileSize, zoomedTileSize);
		}
		
		for (WorldVehicleView vehicleView : vehicles) {
			vehicleView.setSize(zoomedTileSize, zoomedTileSize);
			vehicleView.setLocation(
					(int) (vehicleView.getVehicle().getGlobalX() * zoom)
							- zoomedTileSize / 2, (int) (vehicleView
							.getVehicle().getGlobalY() * zoom) - zoomedTileSize / 2);
		}
	}
	
	

	/**
	 * @param zoom
	 *            Setzt den Zoom-Faktor der View
	 */
	public void setZoom(double zoom) {
		if (this.zoom != zoom) {
			this.zoom = zoom;
			this.zoomedTileSize = (int) (config.getModelTileSize() * zoom);
			if (pathView != null) {
				pathView.setZoomedTileSize(zoomedTileSize);
				pathView.setZoom(zoom);
			}
		}
	}

	/**
	 * @return Gibt den Zoom-Faktor der View zurück
	 */
	public double getZoom() {
		return zoom;
	}

	/**
	 * Wird aufgerufen, wenn ein {@link Observable} sich ändert.
	 * 
	 * Hier werden {@link PlacedVehicleModel Fahrzeuge}, {@link PlacedTileModel platzierte Kacheln} und {@link WorldModel die Welt} beobachtet.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof PlacedVehicleModel) {
			if (arg.equals(PlacedVehicleModel.POSITION_CHANGED)) {
				PlacedVehicleModel vehicle = (PlacedVehicleModel) o;
				vehicle2view.get(vehicle).setLocation(
						(int) (vehicle.getGlobalX() * zoom) - zoomedTileSize / 2,
						(int) (vehicle.getGlobalY() * zoom) - zoomedTileSize / 2);
			}
		}
		if (o instanceof PlacedTileModel) {
			if (arg.equals(PlacedTileModel.POSITION_CHANGED)) {
				updateTilePositions();
			}
		}
		if (o instanceof WorldModel) {
			if (arg.equals(WorldModel.PATH_CHANGED)) {
				repaint();
			}
		}
	}

	public JLayeredPane getLayer() {
		return layer;
	}

	public Integer getTileLevel() {
		return LEVEL_TILE;
	}

	public Integer getDragAndDropLevel() {
		return LEVEL_DRAGANDDROP;
	}

	public int getZoomedTileSize() {
		return zoomedTileSize;
	}
}