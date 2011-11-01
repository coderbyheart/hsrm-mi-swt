package data.datamanager;

import java.util.ArrayList;

import config.AppConfig;

import model.planer.BaseTileModel;
import model.planer.PlacedTileModel;
import model.planer.PlacedTrafficlightModel;
import model.planer.TileModel;
import model.planer.TrafficlightModel;
import model.planer.VehicleModel;
import model.planer.WorldModel;
import data.dataobject.*;

/**
 * Enthält die Logik zum Manipulieren einer World
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: WorldManager.java 244 2011-01-18 18:20:41Z sfran001 $
 */
public class WorldManager extends Manager {

	/**
	 * @see Manager#Manager(AppConfig)
	 */
	public WorldManager(AppConfig config) {
		super(config);
	}
	
	/**
	 * Erzeugt ein {@link WorldData Welt-DataObject} aus eine {@link WorldModel
	 * World-Model}
	 * 
	 * @param worldModel
	 * @return Welt-DataObject
	 */
	public WorldData getDataFromModel(WorldModel worldModel){
		//Neue World-Data
		WorldData worldData = new WorldData(worldModel.getWidth(),worldModel.getHeight());
		
		//BaseTileData
		BaseTileData baseTileData = new BaseTileData();
		baseTileData.setSource(worldModel.getBaseTile().getSource().toString());
		worldData.setBaseTile(baseTileData);
		
		// Verfügbare Kacheln
		TileManager tm = new TileManager(config);
		ArrayList <TileData> tiles = new ArrayList <TileData>();
		worldData.setTiles(tiles);
		for(TileModel tileModel : worldModel.getTiles()){	
			tiles.add(tm.getDataFromModel(tileModel));
		}
		
		// Verfügbare Fahrzeuge
		VehicleManager vm = new VehicleManager(config);
		ArrayList <VehicleData> vehicles = new ArrayList<VehicleData>();
		worldData.setVehicles(vehicles);
		for(VehicleModel vehicleModel : worldModel.getVehicles()){
			vehicles.add(vm.getDataFromModel(vehicleModel));
		}
		
		// Platzierte Kacheln
		PlacedTileManager pm = new PlacedTileManager(config); 
		ArrayList <PlacedTileData> placedTiles = new ArrayList<PlacedTileData>();
		worldData.setPlacedTiles(placedTiles);
		
		for(PlacedTileModel placedTileModel : worldModel.getPlacedTiles()){
			placedTiles.add(pm.getDataFromModel(placedTileModel,worldModel));
		}
		
				
		return worldData;
	}
	
	/**
	 * Erzeugt ein {@link WorldModel Welt-Model} aus eine {@link WorldData
	 * World-DataObject}
	 * 
	 * @param worldData
	 * @return Welt-Model
	 */
	public WorldModel getModelFromData(WorldData worldData) {

		WorldModel worldModel = new WorldModel(config, worldData.getWidth(), worldData.getHeight());

		// Basiskachel
		BaseTileModel baseTile = new BaseTileModel();
		baseTile.setSource(config.getGfxFile(
				worldData.getBaseTile().getSource()));
		worldModel.setBaseTile(baseTile);
		
		// Verfügbare Kacheln
		TileManager tm = new TileManager(config);
		ArrayList<TileModel> tiles = new ArrayList<TileModel>();
		worldModel.setTiles(tiles);
		for (TileData tileData : worldData.getTiles()) {
			tiles.add(tm.getModelFromData(tileData));
		}

		// Verfügbare Fahrzeuge
		VehicleManager vm = new VehicleManager(config);
		ArrayList<VehicleModel> vehicles = new ArrayList<VehicleModel>();
		worldModel.setVehicles(vehicles);
		for (VehicleData vehicleData : worldData.getVehicles()) {
			vehicles.add(vm.getModelFromData(vehicleData));
		}

		// Platzierte Kacheln
		PlacedTileManager pm = new PlacedTileManager(config); 
		ArrayList<PlacedTileModel> placedTiles = new ArrayList<PlacedTileModel>();
		worldModel.setPlacedTiles(placedTiles);
		for(PlacedTileData placedTileData: worldData.getPlacedTiles()) {
			placedTiles.add(pm.getModelFromData(placedTileData, worldModel));
		}

		return worldModel;
	}
}