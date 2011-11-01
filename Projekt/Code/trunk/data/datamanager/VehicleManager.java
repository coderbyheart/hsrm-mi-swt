package data.datamanager;

import config.AppConfig;
import model.planer.VehicleModel;
import data.dataobject.VehicleData;

/**
 * Manager für Kacheln
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: VehicleManager.java 178 2011-01-11 13:21:45Z sfran001 $
 */
public class VehicleManager extends Manager {

	/**
	 * @see Manager#Manager(AppConfig)
	 */
	public VehicleManager(AppConfig config) {
		super(config);
	}

	/**
	 * Erzeugt ein {@link VehicleModel Kachel-Model} aus einem
	 * {@link VehicleData DataObject}
	 * 
	 * @param vehicleData
	 * @return Model
	 */
	public VehicleModel getModelFromData(VehicleData vehicleData) {
		VehicleModel vehicleModel = new VehicleModel();
		buildModelFromData(vehicleModel, vehicleData);
		return vehicleModel;
	}

	/**
	 * Befüllt das Model mit den Daten
	 * 
	 * @param vehicleModel
	 * @param vehicleData
	 */
	protected void buildModelFromData(VehicleModel vehicleModel,
			VehicleData vehicleData) {
		vehicleModel.setId(vehicleData.getId());
		vehicleModel.setDescription(vehicleData.getDescription());
		vehicleModel.setName(vehicleData.getName());
		vehicleModel.setSource(config.getGfxFile(vehicleData.getSource()));
		vehicleModel.setSpeed(vehicleData.getSpeed());
	}

	public VehicleData getDataFromModel(VehicleModel vehicleModel) {
		VehicleData vehicleData = new VehicleData();
		buildDataFromModel(vehicleData, vehicleModel);
		return vehicleData;
	}
	
	/**
	 * Befüllt DataObject
	 * 
	 * @param vehicleData
	 * @param vehicleModel
	 */
	protected void buildDataFromModel(VehicleData vehicleData,
			VehicleModel vehicleModel) {
		vehicleData.setId(vehicleModel.getId());
		vehicleData.setDescription(vehicleModel.getDescription());
		vehicleData.setName(vehicleModel.getName());
		vehicleData.setSource(vehicleModel.getSource().toString());
		vehicleData.setSpeed(vehicleModel.getSpeed());
	}
}
