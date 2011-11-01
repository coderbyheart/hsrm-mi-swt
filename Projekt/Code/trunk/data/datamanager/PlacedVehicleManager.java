package data.datamanager;

import config.AppConfig;
import model.planer.PlacedTileModel;
import model.planer.PlacedVehicleModel;
import model.planer.VehicleModel;
import model.planer.WorldModel;
import data.dataobject.PlacedVehicleData;
import data.dataobject.VehicleData;

/**
 * Manager für Kacheln
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: PlacedVehicleManager.java 238 2011-01-17 19:27:51Z mtack001 $
 */
public class PlacedVehicleManager extends VehicleManager {

	/**
	 * @see Manager#Manager(AppConfig)
	 */
	public PlacedVehicleManager(AppConfig config) {
		super(config);
	}

	/**
	 * Erzeugt ein {@link VehicleModel Kachel-Model} aus einem
	 * {@link VehicleData DataObject}
	 * 
	 * @param vehicleData
	 * @return Model
	 */
	public PlacedVehicleModel getModelFromData(PlacedVehicleData vehicleData, WorldModel world) {
		PlacedVehicleModel vehicleModel = new PlacedVehicleModel(config, world);
		buildModelFromData(vehicleModel, vehicleData);
		vehicleModel.setLocalX(vehicleData.getLeft());
		vehicleModel.setLocalY(vehicleData.getTop());
		vehicleModel.setRotation(Math.toRadians(vehicleData.getRotation() > 180 ? vehicleData.getRotation() - 360 : vehicleData.getRotation()));
		return vehicleModel;
	}
	
	public PlacedVehicleData getDataFromModel(PlacedVehicleModel vehicleModel, WorldModel worldModel) {
		PlacedVehicleData vehicleData = new PlacedVehicleData();
		buildDataFromModel(vehicleData, vehicleModel);
		vehicleData.setLeft((int)(((float)vehicleModel.getLocalX() / (float)config.getModelTileSize()) * 100));
		vehicleData.setTop((int)(((float)vehicleModel.getLocalY() / (float)config.getModelTileSize()) * 100));
		vehicleData.setRotation((int) (Math.toDegrees(vehicleModel.getRotation())<0?
				Math.toDegrees(vehicleModel.getRotation())+360:
					Math.toDegrees(vehicleModel.getRotation())));
		return vehicleData;
	}
}
