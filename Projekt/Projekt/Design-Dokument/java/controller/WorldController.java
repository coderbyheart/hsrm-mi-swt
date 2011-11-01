package controller;

import data.datamanager.*;
import view.*;
import data.dataobject.*;
import model.planer.*;

/**
 * Controller für die Welt
 */
public class WorldController extends SubController
{

	private WorldManager manager;
	private WorldView worldView;
	private WorldData worldData;
	private WorldModel model;

	/**
	 * Konstruktor
	 * @return 
	 */
	public WorldController()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Fügt eine Kachel an der Stelle left, top hinzu
	 * @param tile
	 * @param left
	 * @param top
	 * @param rotation
	 * @return 
	 */
	public void addTileAt(TileModel tile, int left, int top, int rotation)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Speichert die aktuelle Welt
	 * @return 
	 */
	public void saveWorld()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Entfernt eine Kachel
	 * @param placedTile
	 * @return 
	 */
	public void removeTile(PlacedTileModel placedTile)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Entfernt eine Kachel
	 * @param placedVehicle
	 * @return 
	 */
	public void removeVehicle(PlacedVehicleModel placedVehicle)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Fügt einer Kachel ein Fahrzeug hinzu
	 * @param vehicle
	 * @param left
	 * @param top
	 * @param rotation
	 * @return 
	 */
	public void addVehicleAt(VehicleModel vehicle, int left, int top, int rotation)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param model
	 * @return 
	 */
	public void enableTrafficlight(PlacedTileModel model)
	{
		throw new UnsupportedOperationException();
	}
}