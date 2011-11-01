package data.datasource.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import data.datasource.*;
import data.dataobject.*;

/**
 * XML-Datenquelle
 * 
 * Lädt ihre Daten aus XML-Dateien
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: XMLDataSource.java 261 2011-01-19 18:24:40Z mtack001 $
 */
public class XMLDataSource implements DataSource {

	private TileXMLSource tile;
	private HashMap<String, LanguageXMLSource> languages = new HashMap<String, LanguageXMLSource>();
	private File xmlDir;

	/**
	 * Konstruktor, bekommt als Parameter den Pfad zum Verzeichnis mit den
	 * XML-Dateien
	 * 
	 * @param xmlDir
	 */
	public XMLDataSource(File xmlDir) {
		this.setXmlDir(xmlDir);
	}

	/**
	 * @see DataSource#getVehicles()
	 */
	@Override
	public ArrayList<VehicleData> getVehicles() {
		return tile.getVehicleList();
	}

	/**
	 * @see DataSource#getTiles()
	 */
	@Override
	public ArrayList<TileData> getTiles() {
		return tile.getTileList();
	}
	
	/**
	 * @see DataSource#getBaseTile()
	 */
	@Override
	public BaseTileData getBaseTile() {
		return tile.getBaseTile();
	}

	/**
	 * @see DataSource#save(DataObject, String)
	 */
	@Override
	public void save(DataObject dataObject, String identifier) {
		if(dataObject instanceof WorldData) {
			WorldXMLSource source = new WorldXMLSource(dataObject, new File(getXmlDir(), "world.xsd"));
			source.save(identifier);	
		}
	}

	/**
	 * @see DataSource#getWorld(String)
	 */
	public WorldData getWorld(String identifier) {
		WorldXMLSource source;
		// Schon Datei?
		File identifierFile = new File(identifier);
		if (identifierFile.isFile()) {
			source = new WorldXMLSource(identifierFile);
		} else {
			source = new WorldXMLSource(new File(getXmlDir(), identifier.concat(".xml")));
			
		}
		return source.getWorld();
	}

	/**
	 * @see DataSource#getTexts(String)
	 */
	@Override
	public ArrayList<TextData> getTexts(String language) {
		return getLanguage(language).getTexts();
	}

	/**
	 * @see DataSource#getText(String, String)
	 */
	@Override
	public TextData getText(String language, String identifier) {
		LanguageXMLSource source = getLanguage(language);
		for(TextData textData: source.getTexts()) {
			if(textData.getId().equals(identifier)) return textData;
		}
		return null;	
	}

	/**
	 * @see DataSource#getTextGroup(String, String)
	 */
	@Override
	public ArrayList<TextData> getTextGroup(String language, String group) {
		LanguageXMLSource source = getLanguage(language);
		ArrayList<TextData> texts = new ArrayList<TextData>();
		for(TextData textData: source.getTexts()) {
			if(!textData.getGroup().equals(group)) continue;
				texts.add(textData);
		}
		return texts;
	}

	/**
	 * @param xmlDir
	 *            the xmlDir to set
	 */
	public void setXmlDir(File xmlDir) {
		this.xmlDir = xmlDir;
		tile = new TileXMLSource(new File(xmlDir, "tiles.xml"));
	}

	/**
	 * @return the xmlDir
	 */
	public File getXmlDir() {
		return xmlDir;
	}

	/**
	 * Gibt die {@link LanguageXMLSource} für die entsprechende Sprache zurück
	 * 
	 * @param language
	 */
	private LanguageXMLSource getLanguage(String language) {
		if (!languages.containsKey(language)) {
			languages.put(language, new LanguageXMLSource(new File(getXmlDir(), language.concat(".xml"))));
		}
		return languages.get(language);
	}

	public TrafficlightData getTrafficlight() {
		
		return this.getTrafficlight();
	}

}