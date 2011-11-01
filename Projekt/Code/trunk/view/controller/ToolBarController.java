package view.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import app.GUIController;

import config.AppConfig;

import model.planer.TileModel;
import model.planer.VehicleModel;
import model.toolbar.TrafficlightModel;
import model.toolbar.TrashModel;
import data.DataFactory;
import data.datamanager.TileManager;
import data.datamanager.VehicleManager;
import data.dataobject.TileData;
import data.dataobject.VehicleData;
import view.*;

/**
 * Controller für die Werkzeugleiste
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: ToolBarController.java 251 2011-01-19 08:58:39Z mtack001 $
 */
public class ToolBarController extends SubController {
	private static Logger logger = Logger.getLogger(ToolBarController.class);
	private ToolbarView view;

	public ToolBarController(DataFactory dataFactory, AppConfig config) {
		super(dataFactory, config);
	}

	/**
	 * @see SubController#load()
	 */
	@Override
	protected void load() {
		view = new ToolbarView();
		DataFactory df = getDataFactory();
		logger.info("Lade Daten für meine Views");
		
		// Mülleimer und Ampel
		// Da diese ein reines GUI-Element sind, bekommen diese die Quelle zum Bild
		// nicht aus den persistenten Daten, sondern diese wird "hart" gesetzt
		TrashModel trashModel = new TrashModel();
		trashModel.setSource(config.getGfxFile("Muell.svg"));
		((ToolbarView) view).setTrash(trashModel);
		logger.debug(String.format("Mülleimer-Model: %s", trashModel));
		
		TrafficlightModel trafficlightModel = new TrafficlightModel();
		trafficlightModel.setSource(config.getGfxFile("Ampel.svg"));
		((ToolbarView) view).setTrafficlight(trafficlightModel);
		logger.debug(String.format("Ampel-Model: %s", trafficlightModel));
		
		// Kacheln laden
		TileManager tm = new TileManager(config);
		ArrayList<TileModel> tiles = new ArrayList<TileModel>();
		for (TileData tileData : df.getTiles()) {
			TileModel tileModel = tm.getModelFromData(tileData);
			tiles.add(tileModel);
			logger.debug(String.format("Kachel-Model: %s", tileModel.getSource().getAbsolutePath()));
		}
		logger.debug(String.format("%d Kacheln geladen", tiles.size()));
		((ToolbarView) view).setTiles(tiles);
		
		// Fahrzeuge laden
		VehicleManager vm = new VehicleManager(config);
		ArrayList<VehicleModel> vehicles = new ArrayList<VehicleModel>();
		for (VehicleData vehicleData : df.getVehicles()) {
			VehicleModel vehicleModel = vm.getModelFromData(vehicleData);
			vehicles.add(vehicleModel);
			logger.debug("Fahrzeug-Model: " + vehicleModel.getSource().getAbsolutePath());
		}
		logger.debug(String.format("%d Fahrzeuge geladen", vehicles.size()));
		((ToolbarView) view).setVehicles(vehicles);
		((ToolbarView) view).buildToolbar();
	}

	public ToolbarView getView() {
		return view;
	}

}