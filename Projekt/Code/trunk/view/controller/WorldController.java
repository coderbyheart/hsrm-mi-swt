package view.controller;

import org.apache.log4j.Logger;

import app.GUIController;
import config.AppConfig;
import data.DataFactory;
import data.datamanager.*;
import view.*;
import view.world.WorldView;
import data.dataobject.*;
import exception.WorldTooLargeException;
import model.planer.*;

/**
 * Controller für die Welt
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: WorldController.java 251 2011-01-19 08:58:39Z mtack001 $
 */
public class WorldController extends SubController
{
	private static Logger logger = Logger.getLogger(WorldController.class);
	private WorldManager manager;
	private WorldView view;

	public WorldController(DataFactory dataFactory, AppConfig config)
	{
		super(dataFactory, config);
		manager = new WorldManager(config);
	}

	/**
	 * Fügt eine Kachel an der Stelle left, top hinzu
	 * @param tile
	 * @param left
	 * @param top
	 * @param rotation
	 */
	public void addTileAt(TileModel tile, int left, int top, int rotation)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Speichert die aktuelle Welt ???
	 */
	public void saveWorld(String filename, WorldModel worldModel)
	{
		logger.info(String.format("Speicher Welt %s", filename));
		
	}

	/**
	 * Entfernt eine Kachel
	 * @param placedTile
	 */
	public void removeTile(PlacedTileModel placedTile)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Entfernt eine Kachel
	 * @param placedVehicle
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
	 */
	public void addVehicleAt(VehicleModel vehicle, int left, int top, int rotation)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @param model
	 */
	public void enableTrafficlight(PlacedTileModel model)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @see SubController#load()
	 */
	@Override
	protected void load() {
		// Nothing to do.
	}

	/**
	 * Erzeugt eine neue Welt
	 */
	public void newWorld() {
		logger.info(String.format("Erzeuge leere Welt mit %dx%d Kacheln", 5, 5));
		WorldData worldData = getDataFactory().getNewWorld(5, 5);
		WorldModel model = manager.getModelFromData(worldData);
		setView(new WorldView(config, model)); 
		getView().setSize(800, 600);
	}

	/**
	 * Lädt eine Welt die unter name abgespeichert ist
	 * 
	 * @param name
	 * @throws WorldTooLargeException 
	 */
	public void loadWorld(String name) throws WorldTooLargeException {
		logger.info(String.format("Lade Welt %s", name));
		WorldData worldData = getDataFactory().getWorld(name);
		logger.debug(String.format("Welt mit %dx%d Kacheln geladen", worldData.getWidth(), worldData.getHeight()));
		WorldModel model = manager.getModelFromData(worldData);
		setView(new WorldView(config, model));
		
//		if (worldData.getWidth() > view.getWidth() || worldData.getHeight() > view.getHeight()) {
//			throw new WorldTooLargeException(name);
//		}
//		logger.debug(model);
		
		/*
		logger.info(String.format("Lade Welt %s", name));
		WorldData worldData = getDataFactory().getWorld(name);
		logger.debug(String.format("Welt mit %dx%d Kacheln geladen", worldData.getWidth(), worldData.getHeight()));
		WorldView worldView = (WorldView)view;
		if (worldData.getWidth() > worldView.getWidth() || worldData.getHeight() > worldView.getHeight()) {
			throw new WorldTooLargeException(name);
		}
		WorldModel model = manager.getModelFromData(worldData);
		logger.debug(model);
		worldView.setModel(model);
		*/
	}

	public void setView(WorldView view) {
		this.view = view;
	}

	public WorldView getView() {
		return view;
	}
}