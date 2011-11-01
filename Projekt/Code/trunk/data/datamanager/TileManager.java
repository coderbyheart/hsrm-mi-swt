package data.datamanager;

import java.util.ArrayList;

import config.AppConfig;

import model.planer.CurveModel;
import model.planer.LocationModel;
import model.planer.RouteModel;
import model.planer.StraightModel;
import model.planer.TileModel;
import model.planer.TrafficlightModel;
import data.dataobject.CurveData;
import data.dataobject.LocationData;
import data.dataobject.RouteData;
import data.dataobject.StraightData;
import data.dataobject.TileData;

/**
 * Manager für Kacheln
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: TileManager.java 254 2011-01-19 13:02:20Z sfran001 $
 */
public class TileManager extends Manager {

	/**
	 * @see Manager#Manager(AppConfig)
	 */
	public TileManager(AppConfig config) {
		super(config);
	}

	/**
	 * Erzeugt ein {@link RouteModel Strecken-Model} aus einem {@link RouteData
	 * Strecken-DataObject}.
	 * 
	 * @param routeData
	 * @return Strecken-Model
	 */
	private RouteModel routeModelFromData(RouteData routeData) {
		RouteModel routeModel;
		if (routeData instanceof CurveData) {
			routeModel = new CurveModel();
			((CurveModel) routeModel).setCorner(locationModelFromData(((CurveData) routeData).getCorner()));
		} else {
			routeModel = new StraightModel();
		}
		routeModel.setStart(locationModelFromData(routeData.getStart()));
		routeModel.setEnd(locationModelFromData(routeData.getEnd()));
		routeModel.getStart().setTrafficlight(routeData.getStart().isTrafficlight());
		routeModel.getEnd().setTrafficlight(routeData.getEnd().isTrafficlight());
		return routeModel;
	}

	private LocationModel locationModelFromData(LocationData locationData) {
		LocationModel locationModel = new LocationModel();
		locationModel.setType(locationData.getType());
		return locationModel;
	}

	/**
	 * Erzeugt ein {@link TileModel Kachel-Model} aus einem {@link TileData
	 * DataObject}
	 * 
	 * @param tileData
	 * @return Model
	 */
	public TileModel getModelFromData(TileData tileData) {
		TileModel tileModel = new TileModel();
		buildModelFromData(tileModel, tileData);
		return tileModel;
	}

	/**
	 * Befüllt das Model mit den Daten
	 * 
	 * @param tileData
	 * @param tileModel
	 */
	protected void buildModelFromData(TileModel tileModel, TileData tileData) {
		tileModel.setId(tileData.getId());
		tileModel.setDescription(tileData.getDescription());
		tileModel.setName(tileData.getName());
		tileModel.setSource(config.getGfxFile(tileData.getSource()));
		// Strecken
		ArrayList<RouteModel> routes = new ArrayList<RouteModel>();
		ArrayList<TrafficlightModel> trafficlights = new ArrayList<TrafficlightModel>();

		for (RouteData routeData : tileData.getRoutes()) {
			//Strecke
			RouteModel routeModel = routeModelFromData(routeData);
			routes.add(routeModel);
			//Ampeln
			if(routeData.getStart().isTrafficlight() || routeData.getEnd().isTrafficlight()){
				TrafficlightModel traffMod = traffModelFromData(routeData,tileData);
				trafficlights.add(traffMod);
			}
		}
			
		tileModel.setRoutes(routes);
		tileModel.setTrafficlights(trafficlights);
	}

	private TrafficlightModel traffModelFromData(RouteData routeData, TileData tileData) {
		TrafficlightModel traffMod = new TrafficlightModel();
		
		traffMod.setOffSource(config.getGfxFile(
				tileData.getTrafficlight().getOffSource()));
		traffMod.setRedSource(config.getGfxFile(
				tileData.getTrafficlight().getRedSource()));
		traffMod.setRedYellowSource(config.getGfxFile(
				tileData.getTrafficlight().getRedYellowSource()));
		traffMod.setGreenSource(config.getGfxFile(
				tileData.getTrafficlight().getGreenSource()));
		traffMod.setYellowSource(config.getGfxFile(
				tileData.getTrafficlight().getYellowSource()));
		
		//Drehung
		switch(routeData.getStart().getType()){
			case NORTH: traffMod.setRotation(180);
						traffMod.setLeft(14);
						traffMod.setTop(9);
						break;
			case SOUTH: traffMod.setRotation(0);
						traffMod.setLeft(53);
						traffMod.setTop(58);
						break;
			case WEST: traffMod.setRotation(90);
						traffMod.setLeft(58);
						traffMod.setTop(14);
						break;
			case EAST: traffMod.setRotation(270);
						traffMod.setLeft(9);
						traffMod.setTop(53);
						break;
		}
		

		return traffMod;
	}

	public TileData getDataFromModel(TileModel tileModel) {
		TileData tileData = new TileData();
		buildDataFromModel(tileData, tileModel);
		return tileData;
	}
	
	protected void buildDataFromModel(TileData tileData, TileModel tileModel){
		tileData.setId(tileModel.getId());
		tileData.setName(tileModel.getName());
		tileData.setDescription(tileModel.getDescription());
		tileData.setSource(tileModel.getSource().toString());
		// Strecken
		ArrayList<RouteData> routes = new ArrayList<RouteData>();
		for (RouteModel routeModel : tileModel.getRoutes()) {
			RouteData routeData = routeDataFromModel(routeModel);
			routes.add(routeData);
		}
		tileData.setRoutes(routes);
	}
	
	/**
	 * Erzeugt ein {@link RouteData Strecken-DataObject} aus einem {@link RouteModel
	 * Strecken-Model}.
	 * 
	 * @param routeModel
	 * @return Strecken-DataObject
	 */
	private RouteData routeDataFromModel(RouteModel routeModel) {
		RouteData routeData;
		if (routeModel instanceof CurveModel) {
			routeData = new CurveData();
			((CurveData) routeData).setCorner(locationDataFromModel(((CurveModel) routeModel).getCorner()));
		} else {
			routeData = new StraightData();
		}
		routeData.setStart(locationDataFromModel(routeModel.getStart()));
		routeData.setEnd(locationDataFromModel(routeModel.getEnd()));
		routeData.getStart().setTrafficlight(routeModel.getStart().isTrafficlight());
		routeData.getEnd().setTrafficlight(routeModel.getEnd().isTrafficlight());
		return routeData;
	}

	private LocationData locationDataFromModel(LocationModel locationModel) {
		LocationData locationData = new LocationData(locationModel.getType());
		return locationData;
	}
}
