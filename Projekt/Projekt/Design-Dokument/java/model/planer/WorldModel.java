package model.planer;

import java.util.*;

/**
 * Model für eine Welt
 */
public class WorldModel extends PlanerModel
{

	private int height;
	private int width;
	private ArrayList<VehicleModel> vehicles;
	private ArrayList<PlacedTileModel> placedTiles;
	private ArrayList<TileModel> tiles;

	public int getHeight()
	{
		return this.height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getWidth()
	{
		return this.width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * 
	 * @return 
	 */
	public BaseTileModel getBasetile()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param baseTile
	 * @return 
	 */
	public void setBaseTile(BaseTileModel baseTile)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt die Kacheln einer Welt zurück
	 * @return 
	 */
	public TileModel[] getTiles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tiles
	 * @return 
	 */
	public void setTiles(TileModel[] tiles)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public VehicleModel[] getVehicles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param vehicles
	 * @return 
	 */
	public void setVehicles(VehicleModel[] vehicles)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public PlacedTileModel[] getPlacedTiles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param placedTiles
	 * @return 
	 */
	public void setPlacedTiles(PlacedTileModel[] placedTiles)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tile
	 * @return 
	 */
	public void removeFromPlacedTiles(PlacedTileModel tile)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Entfernt die Kachel placedTile aus der Welt
	 * @param placedTile
	 * @return 
	 */
	public void removePlacedTile(PlacedTileModel placedTile)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tile
	 * @return 
	 */
	public void addPlacedTile(PlacedTileModel tile)
	{
		throw new UnsupportedOperationException();
	}
}