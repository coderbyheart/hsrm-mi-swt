package data.datasource.xml;

import java.io.File;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import data.LocationType;
import data.dataobject.BaseTileData;
import data.dataobject.CurveData;
import data.dataobject.LocationData;
import data.dataobject.RouteData;
import data.dataobject.StraightData;
import data.dataobject.TileData;
import data.dataobject.TrafficlightData;
import data.dataobject.VehicleData;

/**
 * XML-Quelle für Kacheln
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: TileXMLSource.java 234 2011-01-17 13:00:44Z sfran001 $
 */
public class TileXMLSource extends XMLSource {
	private static Logger logger = Logger.getLogger(TileXMLSource.class);
	TrafficlightData trafficlight;
	ArrayList<TileData> tiles;
	ArrayList<VehicleData> vehicles;
	private File xmlSchema;

	/**
	 * @see XMLSource#XMLSource(File)
	 */
	public TileXMLSource(File xmlFile) {
		super(xmlFile);
	}

	/**
	 * @see XMLSource#XMLSource()
	 */
	public TileXMLSource() {
	}
	
	private TrafficlightData getTrafficlight() {

		if (trafficlight == null) {

			logger.debug(String.format("Lade Ampel aus %s", xmlFile
					.getAbsolutePath()));

			trafficlight = new TrafficlightData();
			// Ampel laden
			NodeList xmlTiles = xmlRoot.getElementsByTagName("trafficlight")
					.item(0).getChildNodes();
			for (int i = 0; i < xmlTiles.getLength(); i++) {
				if(xmlTiles.item(i).getNodeName().equals("off-source"))
					trafficlight.setOffSource(xmlTiles.item(i).getAttributes().getNamedItem("file").getNodeValue());
				if(xmlTiles.item(i).getNodeName().equals("red-source"))
					trafficlight.setRedSource(xmlTiles.item(i).getAttributes().getNamedItem("file").getNodeValue());
				if(xmlTiles.item(i).getNodeName().equals("redyellow-source"))
					trafficlight.setRedYellowSource(xmlTiles.item(i).getAttributes().getNamedItem("file").getNodeValue());
				if(xmlTiles.item(i).getNodeName().equals("green-source"))
					trafficlight.setGreenSource(xmlTiles.item(i).getAttributes().getNamedItem("file").getNodeValue());
				if(xmlTiles.item(i).getNodeName().equals("yellow-source"))
					trafficlight.setYellowSource(xmlTiles.item(i).getAttributes().getNamedItem("file").getNodeValue());
			}
		}

		return trafficlight;
	}
	
	
	/**
	 * Erzeugt die Standard-Liste mit Kacheln
	 * 
	 * @return Liste mit Kacheln
	 */
	public ArrayList<TileData> getTileList() {

		if (tiles == null) {

			logger.debug(String.format("Lade Kacheln aus %s", xmlFile
					.getAbsolutePath()));

			tiles = new ArrayList<TileData>();
			// Kacheln laden
			NodeList xmlTiles = xmlRoot.getElementsByTagName("tiles")
					.item(0).getChildNodes();
			for (int i = 0; i < xmlTiles.getLength(); i++) {
				Element xmlTile = (Element) xmlTiles.item(i);
				TileData tileData = new TileData();
				fillTileDataFromElement(tileData, xmlTile);
				logger.debug(tileData);
				tiles.add(tileData);
			}
		}

		return tiles;
	}

	/**
	 * Befüllt eine Kachel aus einem XML-Element
	 * 
	 * @param tileData
	 * @param xmlTile
	 */
	protected void fillTileDataFromElement(TileData tileData, Element xmlTile) {
		// ID
		tileData.setId(xmlTile.getAttribute("id"));
		// Strecken
		ArrayList<RouteData> tileRoutes = new ArrayList<RouteData>();
		tileData.setRoutes(tileRoutes);
		
		//AmpelData
		tileData.setTrafficlight(getTrafficlight());
		
		NodeList props = xmlTile.getChildNodes();
		for (int j = 0; j < props.getLength(); j++) {
			Element prop = (Element) props.item(j);
			if (prop.getNodeName().equals("source")) {
				// Source
				tileData.setSource(prop.getAttribute("file"));
			} else if (prop.getNodeName().equals("name")) {
				// Name
				tileData.setName(prop.getTextContent());
			} else if (prop.getNodeName().equals("description")) {
				// description
				tileData.setDescription(prop.getTextContent());
			} else if (prop.getNodeName().equals("straight")
					|| prop.getNodeName().equals("curve")) {
				// Strecke
				RouteData tileRoute;
				if (prop.getNodeName().equals("curve")) {
					tileRoute = new CurveData();
				} else {
					tileRoute = new StraightData();
				}
				NodeList locations = prop.getChildNodes();
				for (int k = 0; k < locations.getLength(); k++) {
					Element location = (Element) locations.item(k);
					if (location.getNodeName().equals("start")) {
						tileRoute.setStart(locationDataFromString(location));						

					} else if (location.getNodeName().equals("end")) {
						tileRoute.setEnd(locationDataFromString(location));
							
					} else if (location.getNodeName().equals("corner")) {
						((CurveData) tileRoute)
								.setCorner(locationDataFromString(location));
					}
				}
				tileRoutes.add(tileRoute);
			}
		}
	}

	/**
	 * Erzeugt ein LocationType aus einem String
	 */
	private LocationData locationDataFromString(Element xmlLocation) {
		String locationString = xmlLocation.getAttribute("location");
		LocationType locationType;
		if (locationString.equals("NE")) {
			locationType = LocationType.NORTHEAST;
		} else if (locationString.equals("E")) {
			locationType = LocationType.EAST;
		} else if (locationString.equals("SE")) {
			locationType = LocationType.SOUTHEAST;
		} else if (locationString.equals("S")) {
			locationType = LocationType.SOUTH;
		} else if (locationString.equals("SW")) {
			locationType = LocationType.SOUTHWEST;
		} else if (locationString.equals("W")) {
			locationType = LocationType.WEST;
		} else if (locationString.equals("NW")) {
			locationType = LocationType.NORTHWEST;
		} else if (locationString.equals("C")) {
			locationType = LocationType.CENTER;
		} else {
			locationType = LocationType.NORTH;
		}

		LocationData locationData = new LocationData(locationType);

		Node trafficlight = xmlLocation.getAttributes().getNamedItem(
				"trafficlight");
		
		if (trafficlight != null
				&& Boolean.parseBoolean(trafficlight.getTextContent())) {
			locationData.setTrafficlight(Boolean.parseBoolean(trafficlight.getTextContent()));
		}
		return locationData;
	}

	/**
	 * Erzeugt die Standard-Liste mit Fahrzeugen
	 * 
	 * @return Liste mit Fahrzeugen
	 */
	public ArrayList<VehicleData> getVehicleList() {

		if (vehicles == null) {

			logger.debug(String.format("Lade Fahrzeuge aus %s", xmlFile
					.getAbsolutePath()));

			vehicles = new ArrayList<VehicleData>();
			// Fahrzeuge laden
			NodeList xmlVehicles = xmlRoot
					.getElementsByTagName("vehicles").item(0).getChildNodes();
			for (int i = 0; i < xmlVehicles.getLength(); i++) {
				Element xmlVehicle = (Element) xmlVehicles.item(i);
				if (!xmlVehicle.getNodeName().equals("vehicle"))
					continue;
				VehicleData vehicleData = new VehicleData();
				fillVehicleDataFromElement(vehicleData, xmlVehicle);
				logger.debug(vehicleData);
				vehicles.add(vehicleData);
			}

		}

		return vehicles;
	}

	/**
	 * Befüllt ein Fahrzeug aus einem XML-Element
	 * 
	 * @param vehicleData
	 * @param xmlVehicle
	 */
	protected void fillVehicleDataFromElement(VehicleData vehicleData,
			Element xmlVehicle) {
		// ID
		vehicleData.setId(xmlVehicle.getAttribute("id"));
		NodeList props = xmlVehicle.getChildNodes();
		// Speed
		vehicleData.setSpeed(Float.parseFloat(xmlVehicle.getAttribute("speed")));
		for (int j = 0; j < props.getLength(); j++) {
			Node prop = props.item(j);
			if (prop.getNodeName().equals("source")) {
				// Source
				vehicleData.setSource(prop.getAttributes().getNamedItem("file")
						.getTextContent());
			} else if (prop.getNodeName().equals("name")) {
				// Name
				vehicleData.setName(prop.getTextContent());
			} else if (prop.getNodeName().equals("description")) {
				// description
				vehicleData.setDescription(prop.getTextContent());
			}
		}
	}

	/**
	 * @see XMLSource#getXmlSchema()
	 */
	@Override
	protected File getXmlSchema() {
		if (xmlSchema == null) {
			xmlSchema = new File(xmlFile.getParent(), "tiles.xsd");
		}
		return xmlSchema;
	}

	/**
	 * Überschreibt das Standard-Schema
	 */
	protected void setXmlSchema(File xmlSchema) {
		this.xmlSchema = xmlSchema;
	}

	/**
	 * Gibt die Basiskachel zurück
	 * 
	 * @return Basiskachel
	 */
	public BaseTileData getBaseTile() {
		logger.debug(String.format("Lade Basiskachel aus %s", xmlFile
				.getAbsolutePath()));
		Element xmlBaseTile = (Element) xmlRoot
				.getElementsByTagName("basetile").item(0).getChildNodes()
				.item(0);
		BaseTileData baseTile = new BaseTileData();
		baseTile.setSource(xmlBaseTile.getAttribute("file"));
		return baseTile;
	}
}