package model.planer;

import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.*;

import org.apache.log4j.Logger;

import config.AppConfig;

import util.Transform;

/**
 * Model für eine Welt
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: WorldModel.java 247 2011-01-18 19:21:40Z sfran001 $
 */
public class WorldModel extends PlanerModel implements Observer {

	public static final int PATH_CHANGED = 1;
	private int height;
	private int width;
	private TrafficlightModel trafficlightModel;
	private ArrayList<VehicleModel> vehicles = new ArrayList<VehicleModel>();
	private ArrayList<PlacedTileModel> placedTiles = new ArrayList<PlacedTileModel>();
	private ArrayList<TileModel> tiles = new ArrayList<TileModel>();
	private BaseTileModel baseTile;
	private AppConfig config;
	private static Logger logger = Logger.getLogger(WorldModel.class);
	public static int TILESIZE;

	/**
	 * Erzeugt ein neues Model mit x mal y Kacheln
	 * 
	 * @param x
	 * @param y
	 */
	public WorldModel(AppConfig config, int x, int y) {
		this.config = config;
		setWidth(x);
		setHeight(y);
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Entfernt die Kachel placedTile aus der Welt
	 * 
	 * @param placedTile
	 */
	public void removePlacedTile(PlacedTileModel placedTile) {
		this.placedTiles.remove(placedTile);
	}

	public void setVehicles(ArrayList<VehicleModel> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<VehicleModel> getVehicles() {
		return vehicles;
	}

	public void setPlacedTiles(ArrayList<PlacedTileModel> placedTiles) {
		this.placedTiles = placedTiles;
		// Kacheln beobachten, ob sich die Positionen ändern
		for(PlacedTileModel placedTile: placedTiles) {
			placedTile.addObserver(this);
		}
	}

	public ArrayList<PlacedTileModel> getPlacedTiles() {
		return placedTiles;
	}

	public void setTiles(ArrayList<TileModel> tiles) {
		this.tiles = tiles;
	}

	public ArrayList<TileModel> getTiles() {
		return tiles;
	}

	/**
	 * @return Liste mit platzierten Fahrzeugen aller Kacheln zurück
	 */
	public ArrayList<PlacedVehicleModel> getPlacedVehicles() {
		ArrayList<PlacedVehicleModel> placedVehicles = new ArrayList<PlacedVehicleModel>();
		for (PlacedTileModel tile : getPlacedTiles()) {
			for (PlacedVehicleModel vehicle : tile.getPlacedVehicles()) {
				placedVehicles.add(vehicle);
			}
		}
		return placedVehicles;
	}

	/**
	 * Setzt das Model für die Basiskachel
	 * 
	 * @param baseTile
	 */
	public void setBaseTile(BaseTileModel baseTile) {
		logger.debug(String.format("Basiskachel: %s", baseTile));
		this.baseTile = baseTile;
	}

	/**
	 * Gibt das Model für die Basiskachel zurück
	 * 
	 * @return Basiskachel
	 */
	public BaseTileModel getBaseTile() {
		return baseTile;
	}

	/**
	 * Erzeugt die String-Repräsentation des Objektes
	 * 
	 * @return string
	 */
	public String toString() {
		return String.format("%s (%dx%d)\n> %d Kacheln\n> %d Fahrzeuge\n> %d platzierte Kacheln\n> %d platzierte Fahrzeuge", 
				this.getClass().getName(),
				getWidth(), 
				getHeight(),
				getTiles().size(),
				getVehicles().size(),
				getPlacedTiles().size(),
				getPlacedVehicles().size()
			);
	}

	/**
	 * @param tileModel
	 * @return Index-Position der Kacheln
	 */
	public int getTileIndex(PlacedTileModel tileModel) {
		return tileModel.getTop() * getWidth() + tileModel.getLeft();	
	}
	
	/**
	 * @return Ein {@link GeneralPath} aller Strecken
	 */
	public GeneralPath getPath()
	{
		return getPath(1);
	}
	
	/**
	 * @param zoom Zoom-Faktor
	 * @return Ein {@link GeneralPath} aller Strecken
	 */
	public GeneralPath getPath(double zoom)
	{
		int tileSize = zoom == 1 ? config.getModelTileSize() : (int) (config.getModelTileSize() * zoom);
		GeneralPath path = new GeneralPath();
		for(PlacedTileModel tile: placedTiles) {
			double xShift = tile.getX() * zoom;
			double yShift = tile.getY() * zoom;
			for(RouteModel route: tile.getRoutes()) {
				Point2D start = Transform.rotatePoint(route.getStartPoint(tileSize), tile.getRotation(), tileSize);
				Point2D end = Transform.rotatePoint(route.getEndPoint(tileSize), tile.getRotation(), tileSize);
				// Move Points
				start.setLocation(start.getX() + xShift, start.getY() + yShift);
				end.setLocation(end.getX() + xShift, end.getY() + yShift);
				if (route instanceof StraightModel) {
					Polygon p = new Polygon();
					p.addPoint((int)start.getX(), (int)start.getY());
					p.addPoint((int)end.getX(), (int)end.getY());
					path.moveTo(start.getX(), start.getY());
					path.lineTo(end.getX(), end.getY());
				}
				if (route instanceof CurveModel) {
					path.moveTo(start.getX(), start.getY());
					path.curveTo(start.getX(), start.getY(), (tileSize / 2) + xShift, (tileSize / 2) + yShift, end.getX(), end.getY());
				}
			}
		}
		return path;
	}

	/**
	 * Gibt die Position von links für die Position d zurück
	 * 
	 * @param d
	 * @return Position von links
	 */
	public int getLeftIndex(double d) {
		int leftIndex = (int) (d / config.getModelTileSize());
		return leftIndex;
	}
	
	/**
	 * Gibt die Position von oben für die Position d zurück
	 * 
	 * @param d
	 * @return Position von oben
	 */
	public int getTopIndex(double d) {
		int topIndex = (int) (d / config.getModelTileSize());
		return topIndex;
	}

	public void update(Observable o, Object arg) {
		if (o instanceof PlacedTileModel) {
			if (arg.equals(PlacedTileModel.POSITION_CHANGED)) {
				setChanged();
				notifyObservers(PATH_CHANGED);
			}
		}
	}

	/**
	 * Gibt die {@link PlacedTileModel Kachel} mit dem Index x und y zurück
	 * 
	 * @param leftIndex
	 * @param topIndex
	 * @return {@link PlacedTileModel Kachel}
	 */
	public PlacedTileModel getTileAt(int leftIndex, int topIndex) {
		for(PlacedTileModel tile: placedTiles) {
			if (tile.getLeft() != leftIndex || tile.getTop() != topIndex) continue;
			return tile;
		}
		return null;
	}	

	public void setTrafficlightModel(TrafficlightModel trafficlightModel) {
		this.trafficlightModel = trafficlightModel;
	}

	public TrafficlightModel getTrafficlightModel() {
		return trafficlightModel;
	}
}