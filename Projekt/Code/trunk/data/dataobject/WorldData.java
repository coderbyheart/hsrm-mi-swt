package data.dataobject;

import java.util.*;

/**
 * Data-Klasse für eine Welt
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: WorldData.java 233 2011-01-17 12:56:55Z sfran001 $
 */
public class WorldData extends DataObject {

	private int width;
	private int height;
	private ArrayList<PlacedTileData> placedTiles = new ArrayList<PlacedTileData>();
	private BaseTileData baseTile;
	private ArrayList<TileData> tiles = new ArrayList<TileData>();
	private ArrayList<VehicleData> vehicles = new ArrayList<VehicleData>();

	/**
	 * Erzeugt eine neue Welt mit der Breite
	 * 
	 * @param width
	 * @param height
	 */
	public WorldData(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gibt die {@link BaseTileData Basis-Kachel} zurück
	 * 
	 * @return Basis-Kachel
	 */
	public BaseTileData getBaseTile() {
		return baseTile;
	}

	/**
	 * Setzt die {@link BaseTileData Basis-Kachel}
	 * 
	 * @param baseTile
	 */
	public void setBaseTile(BaseTileData baseTile) {
		this.baseTile = baseTile;
	}

	/**
	 * Gibt die Liste der in der Welt {@link TileData verfügbaren Kacheln}
	 * zurück
	 * 
	 * @return Kacheln
	 */
	public ArrayList<TileData> getTiles() {
		return tiles;
	}

	/**
	 * Setzt die Liste der in der Welt {@link TileData verfügbaren Kacheln}
	 * 
	 * @param tiles
	 */
	public void setTiles(ArrayList<TileData> tiles) {
		this.tiles = tiles;
	}

	/**
	 * Gibt die Liste der in der Welt {@link PlacedTileData platzierten Kacheln}
	 * zurück
	 * 
	 * @return platzierte Kacheln
	 */
	public ArrayList<PlacedTileData> getPlacedTiles() {
		return placedTiles;
	}

	/**
	 * Setzt die Liste der in der Welt {@link PlacedTileData platzierten
	 * Kacheln}
	 * 
	 * @param placedTiles
	 */
	public void setPlacedTiles(ArrayList<PlacedTileData> placedTiles) {
		this.placedTiles = placedTiles;
	}

	/**
	 * Gibt die Liste der in der Welt {@link VehicleData verfügbaren Fahrzeuge}
	 * zurück
	 * 
	 * @return verfügbare Fahrzeuge
	 */
	public ArrayList<VehicleData> getVehicles() {
		return vehicles;
	}

	/**
	 * Setzt die Liste der in der Welt {@link VehicleData verfügbaren Fahrzeuge}
	 * @param vehicles
	 */
	public void setVehicles(ArrayList<VehicleData> vehicles) {
		this.vehicles = vehicles;
	}
	
	/**
	 * Erzeugt die String-Repräsentation des Objektes
	 * 
	 * @return string
	 */
	public String toString()
	{
		return String.format("%s (%dx%d)", this.getClass().getName(), getWidth(), getHeight());
	}
}