package data.datasource.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import data.dataobject.CurveData;
import data.dataobject.DataObject;
import data.dataobject.PlacedTileData;
import data.dataobject.PlacedVehicleData;
import data.dataobject.RouteData;
import data.dataobject.StraightData;
import data.dataobject.TileData;
import data.dataobject.VehicleData;
import data.dataobject.WorldData;

/**
 * XML-Quelle einer Welt
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: WorldXMLSource.java 247 2011-01-18 19:21:40Z sfran001 $
 */
public class WorldXMLSource extends XMLSource {
	
	private static Logger logger = Logger.getLogger(TileXMLSource.class);
	private static final String MIME_SVG = "image/svg+xml";
	
	public WorldXMLSource(File xmlDir) {
		super(xmlDir);
	}

	//TODO: valides Speichern - Position der Fahrzeuge stimmt noch nicht
	public WorldXMLSource(DataObject dataObject, File xsd) {		
		try {
			
			//neues Dokument
            xmlDoc = newDocument(xsd);

            //create the root element and add it to the document
            xmlRoot = xmlDoc.createElement("world");
            xmlRoot.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            xmlRoot.setAttribute("xsi:noNamespaceSchemaLocation", xsd.getName());
            xmlDoc.appendChild(xmlRoot);

            //All WorldTiles
            Element worldTiles = xmlDoc.createElement("worldtiles");
            xmlRoot.appendChild(worldTiles);
           
            //BaseTile
            Element baseTile = xmlDoc.createElement("basetile");
            worldTiles.appendChild(baseTile);
            Element baseTileSource = xmlDoc.createElement("source");
            baseTileSource.setAttribute("file", new File(((WorldData)dataObject).getBaseTile().getSource()).getName());
            baseTileSource.setAttribute("mime", MIME_SVG);
            baseTile.appendChild(baseTileSource);
            
            //TrafficLight //TODO: Trafficlight nicht statisch abspeichern
            Element trafficlight = xmlDoc.createElement("trafficlight");
            worldTiles.appendChild(trafficlight);
            Element offSource = xmlDoc.createElement("off-source");
            trafficlight.appendChild(offSource);
            offSource.setAttribute("file", "Ampel-aus.svg");
            offSource.setAttribute("mime", MIME_SVG);
            Element redSource = xmlDoc.createElement("red-source");
            trafficlight.appendChild(redSource);
            redSource.setAttribute("file", "Ampel-rot.svg");
            redSource.setAttribute("mime", MIME_SVG);
            Element redyellowSource = xmlDoc.createElement("redyellow-source");
            trafficlight.appendChild(redyellowSource);
            redyellowSource.setAttribute("file", "Ampel-rotgelb.svg");
            redyellowSource.setAttribute("mime", MIME_SVG);
            Element greenSource = xmlDoc.createElement("green-source");
            trafficlight.appendChild(greenSource);
            greenSource.setAttribute("file", "Ampel-gruen.svg");
            greenSource.setAttribute("mime", MIME_SVG);
            Element yellowSource = xmlDoc.createElement("yellow-source");
            trafficlight.appendChild(yellowSource);
            yellowSource.setAttribute("file", "Ampel-gelb.svg");
            yellowSource.setAttribute("mime", MIME_SVG);

            
            //All Tiles
            Element tiles = xmlDoc.createElement("tiles");
            worldTiles.appendChild(tiles);
            for(TileData tileData : ((WorldData)dataObject).getTiles()){
            	//Tile
            	Element tile = xmlDoc.createElement("tile");
            	tiles.appendChild(tile);
            	tile.setAttribute("id", tileData.getId());
            	//Name
            	Element name = xmlDoc.createElement("name");
            	tile.appendChild(name);
            	Text nameT = xmlDoc.createTextNode(tileData.getName());
            	name.appendChild(nameT);
            	//Beschreibung
            	Element description = xmlDoc.createElement("description");
            	tile.appendChild(description);
            	Text descriptionT = xmlDoc.createTextNode(tileData.getDescription());
            	description.appendChild(descriptionT);
            	//Source
            	Element source = xmlDoc.createElement("source");
            	tile.appendChild(source);
            	source.setAttribute("file", new File(tileData.getSource()).getName());
            	source.setAttribute("mime", MIME_SVG);
            	//Routes
            	for(RouteData routeData : tileData.getRoutes()){
            		if(routeData instanceof StraightData){
            			Element straight = xmlDoc.createElement("straight");
                    	tile.appendChild(straight);
                    	Element start = xmlDoc.createElement("start");
                    	straight.appendChild(start);
                    	start.setAttribute("location", routeData.getStart().getTypeString());
                    	Element end = xmlDoc.createElement("end");
                    	straight.appendChild(end);
                    	end.setAttribute("location", routeData.getEnd().getTypeString());
                    	end.setAttribute("trafficlight", routeData.getEnd().isTrafficlight()+"");
            		}
            		if(routeData instanceof CurveData){
            			Element curve = xmlDoc.createElement("curve");
                    	tile.appendChild(curve);
                    	Element start = xmlDoc.createElement("start");
                    	curve.appendChild(start);
                    	start.setAttribute("location", routeData.getStart().getTypeString());
                    	Element end = xmlDoc.createElement("end");
                    	curve.appendChild(end);
                    	end.setAttribute("location", routeData.getEnd().getTypeString());
                    	Element corner = xmlDoc.createElement("corner");
                    	curve.appendChild(corner);
                    	corner.setAttribute("location", ((CurveData) routeData).getCorner().getTypeString());
                    	
            		}
                	
            	}
            }
        	//All Vehicles
            Element vehicles = xmlDoc.createElement("vehicles");
            worldTiles.appendChild(vehicles);
            for(VehicleData vehicleData : ((WorldData)dataObject).getVehicles()){
            	//Vehicle
            	Element vehicle = xmlDoc.createElement("vehicle");
            	vehicles.appendChild(vehicle);
            	vehicle.setAttribute("speed", vehicleData.getSpeed()+"");
            	vehicle.setAttribute("id", vehicleData.getId());
            	//Name
            	Element name = xmlDoc.createElement("name");
            	vehicle.appendChild(name);
            	Text nameT = xmlDoc.createTextNode(vehicleData.getName());
            	name.appendChild(nameT);
            	//Beschreibung
            	Element description = xmlDoc.createElement("description");
            	vehicle.appendChild(description);
            	Text descriptionT = xmlDoc.createTextNode(vehicleData.getDescription());
            	description.appendChild(descriptionT);
            	//Source
            	Element source = xmlDoc.createElement("source");
            	vehicle.appendChild(source);
            	source.setAttribute("file", new File(vehicleData.getSource()).getName());
            	source.setAttribute("mime", MIME_SVG);
            }
            //Placed tiles and vehicles
            Element board = xmlDoc.createElement("board");
            xmlRoot.appendChild(board);
            board.setAttribute("width", ((WorldData)dataObject).getWidth()+"");
            board.setAttribute("height", ((WorldData)dataObject).getHeight()+"");
            for(PlacedTileData tileData : ((WorldData)dataObject).getPlacedTiles()){
            	//Fields
            	Element field = xmlDoc.createElement("field");
            	board.appendChild(field);
            	field.setAttribute("tile", tileData.getId());
            	field.setAttribute("top", tileData.getTop()+"");
            	field.setAttribute("left", tileData.getLeft()+"");
            	field.setAttribute("rotation", tileData.getRotation()+"");
            	if(tileData.isTrafficlight()){
            		field.setAttribute("trafficlight", "true");
            	}
            	for(PlacedVehicleData vehicleData : tileData.getVehicles()){
            		Element vehicle = xmlDoc.createElement("vehicle");
                	field.appendChild(vehicle);
                	vehicle.setAttribute("vehicle", vehicleData.getId());
                	vehicle.setAttribute("left",vehicleData.getLeft()+"");
                	vehicle.setAttribute("top", vehicleData.getTop()+"");
                	vehicle.setAttribute("rotation", vehicleData.getRotation()+"");
            	}

            }
                    	
            
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		

	}

	public void save(String identifier){			
		
		try{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");	        
	        DOMSource        source = new DOMSource(xmlDoc);
	        FileOutputStream os     = new FileOutputStream(new File("resource/xml/"+identifier+".xml"));
	        StreamResult     result = new StreamResult( os );
	        transformer.transform( source, result );
		}catch(TransformerException e){
			
		}catch(FileNotFoundException e){
			
		}
		
	}
	
	/**
	 * @see XMLSource#getXmlSchema()
	 */
	@Override
	protected File getXmlSchema() {
		return new File(xmlFile.getParent(), "world.xsd");
	}

	/**
	 * Liest die XML-Datei ein und erzeugt daraus ein {@link WorldData
	 * Dataobject}.
	 * 
	 * @return WorldData
	 */
	public WorldData getWorld() {
		logger.info(String.format("Lade Welt aus %s", xmlFile));
		
		Element xmlBoard = (Element) xmlRoot.getElementsByTagName("board").item(0);

		// Lese Breite und Höhe der Welt
		WorldData worldData = new WorldData(Integer.parseInt(xmlBoard.getAttribute("width")), Integer.parseInt(xmlBoard.getAttribute("height")));
		
		// Befülle Kacheln
		Element xmlWorldTiles = (Element) xmlRoot.getElementsByTagName("worldtiles").item(0);
		TileXMLSource tileSource = new TileXMLSource();
		if (xmlWorldTiles != null) {
			tileSource.setXmlSchema(getXmlSchema());
			tileSource.setXmlFile(xmlFile);
		} else {
			tileSource.setXmlFile(new File(xmlFile.getParent(), "tiles.xml"));
		}
		worldData.setBaseTile(tileSource.getBaseTile());
		worldData.setTiles(tileSource.getTileList());
		worldData.setVehicles(tileSource.getVehicleList());
		
		// Befülle platzierte Kacheln
		NodeList fields = xmlBoard.getChildNodes();
		ArrayList<PlacedTileData> placedTiles = new ArrayList<PlacedTileData>();
		worldData.setPlacedTiles(placedTiles);
		for(int i = 0; i < fields.getLength(); i++) {
			Element xmlPlacedTile = (Element) fields.item(i);
			Element xmlTile = xmlDoc.getElementById(xmlPlacedTile.getAttribute("tile"));
			PlacedTileData placedTile = new PlacedTileData();
			placedTiles.add(placedTile);
			tileSource.fillTileDataFromElement(placedTile, xmlTile);
			placedTile.setLeft(Integer.parseInt(xmlPlacedTile.getAttribute("left")));
			placedTile.setTop(Integer.parseInt(xmlPlacedTile.getAttribute("top")));
			placedTile.setRotation(Integer.parseInt(xmlPlacedTile.getAttribute("rotation")));
			if(xmlPlacedTile.getAttribute("trafficlight").equals("true")){
				placedTile.setTrafficlight(true);
			}
			// Fahrzeuge
			NodeList vehicles = xmlPlacedTile.getChildNodes();
			ArrayList<PlacedVehicleData> placedVehicles = new ArrayList<PlacedVehicleData>();
			placedTile.setVehicles(placedVehicles);
			for(int j = 0; j < vehicles.getLength(); j++) {
				Element xmlPlacedVehicle = (Element) vehicles.item(j);
				Element xmlVehicle = xmlDoc.getElementById(xmlPlacedVehicle.getAttribute("vehicle"));
				PlacedVehicleData placedVehicle = new PlacedVehicleData();
				placedVehicles.add(placedVehicle);
				tileSource.fillVehicleDataFromElement(placedVehicle, xmlVehicle);
				placedVehicle.setLeft(Integer.parseInt(xmlPlacedVehicle.getAttribute("left")));
				placedVehicle.setTop(Integer.parseInt(xmlPlacedVehicle.getAttribute("top")));
				placedVehicle.setRotation(Integer.parseInt(xmlPlacedVehicle.getAttribute("rotation")));
			}
		}
		return worldData;
	}

	
}