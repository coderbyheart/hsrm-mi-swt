package data.datamanager;

import java.util.ArrayList;

import model.planer.PlacedTileModel;
import model.planer.PlacedVehicleModel;
import model.planer.RouteModel;
import model.planer.WorldModel;
import config.AppConfig;
import data.dataobject.PlacedTileData;
import data.dataobject.PlacedVehicleData;
import data.dataobject.RouteData;
import data.dataobject.WorldData;

/**
 * Manager für Kacheln
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: PlacedTileManager.java 227 2011-01-17 11:45:42Z mtack001 $
 */
public class PlacedTileManager extends TileManager {

	/**
	 * @see Manager#Manager(AppConfig)
	 */
	public PlacedTileManager(AppConfig config) {
		super(config);
	}
	
	/**
	 * Erzeugt ein {@link PlacedTileModel Kachel-Model} aus einem {@link PlacedTileData
	 * DataObject}
	 * 
	 * @param tileData
	 * @return Model
	 */
	public PlacedTileModel getModelFromData(PlacedTileData tileData, WorldModel world) {
		PlacedVehicleManager vm = new PlacedVehicleManager(config);
		PlacedTileModel tileModel = new PlacedTileModel(config, world);
		buildModelFromData(tileModel, tileData);
		tileModel.setLeft(tileData.getLeft());
		tileModel.setTop(tileData.getTop());
		tileModel.setRotation(Math.toRadians(tileData.getRotation() > 180 ? tileData.getRotation() - 360 : tileData.getRotation()));
		tileModel.setTrafficlightsEnabled(tileData.isTrafficlight());
		ArrayList<PlacedVehicleModel> vehicles = new ArrayList<PlacedVehicleModel>();
		tileModel.setPlacedVehicles(vehicles);
		for(PlacedVehicleData placedVehicleData: tileData.getVehicles()) {
			PlacedVehicleModel placedVehicleModel = vm.getModelFromData(placedVehicleData, world);
			placedVehicleModel.setTile(tileModel);
			vehicles.add(placedVehicleModel);
		}
		return tileModel;
	}

	public PlacedTileData getDataFromModel(PlacedTileModel tileModel, WorldModel worldModel) {
		
		PlacedVehicleManager vm = new PlacedVehicleManager(config);
		PlacedTileData tileData = new PlacedTileData();
		buildDataFromModel(tileData, tileModel);
		tileData.setLeft(tileModel.getLeft());
		tileData.setTop(tileModel.getTop());
		tileData.setRotation((int) (Math.toDegrees(tileModel.getRotation())<0?
				Math.toDegrees(tileModel.getRotation())+360:
					Math.toDegrees(tileModel.getRotation())));
		tileData.setTrafficlight(tileModel.isTrafficlightsEnabled());
		ArrayList<PlacedVehicleData> vehicles = new ArrayList<PlacedVehicleData>();
		tileData.setVehicles(vehicles);
		for(PlacedVehicleModel placedVehicleModel: tileModel.getPlacedVehicles()) {
			PlacedVehicleData placedVehicleData = vm.getDataFromModel(placedVehicleModel,worldModel);
			vehicles.add(placedVehicleData);
		}
		return tileData;
	}
}
