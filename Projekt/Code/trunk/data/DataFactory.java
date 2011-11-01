package data;

import java.io.File;
import java.util.ArrayList;

import data.datasource.*;
import data.dataobject.*;

/**
 * Die DataFactory erzeugt {@link DataObject Datenobjekte} aus einer
 * {@link DataSource Datenquelle}.
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: DataFactory.java 244 2011-01-18 18:20:41Z sfran001 $
 */
public class DataFactory implements DataSource {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @see DataSource#getVehicles()
	 */
	public ArrayList<VehicleData> getVehicles() {
		return dataSource.getVehicles();
	}

	/**
	 * @see DataSource#getTiles()
	 */
	public ArrayList<TileData> getTiles() {
		return dataSource.getTiles();
	}

	/**
	 * @see DataSource#getBaseTile()
	 */
	public BaseTileData getBaseTile() {
		return dataSource.getBaseTile();
	}

	/**
	 * Speichert ein Daten-Objekt
	 * 
	 * @param dataObject
	 * @param identifier 
	 */
	public void save(DataObject dataObject, String identifier) 
	{	
		dataSource.save(dataObject, identifier);
	}
	
	/**
	 * Erzeugt ein neue {@link WorldData Welt} mit der angegeben Breite und Höhe in Kacheln
	 * 
	 * @param width
	 * @param height
	 * @return Welt
	 */
	public WorldData getNewWorld(int width, int height) {
		WorldData worldData = new WorldData(width, height);
		
		// Basiskachel
		worldData.setBaseTile(getBaseTile());
		
		// Verfügbare Kacheln
		worldData.setTiles(getTiles());
		
		// Verfügbare Fahrzeuge
		worldData.setVehicles(getVehicles());
		
		return worldData;
	}

	/**
	 * @see DataSource#getTexts(String)
	 */
	@Override
	public ArrayList<TextData> getTexts(String language) {
		return dataSource.getTexts(language);
	}

	/**
	 * @see DataSource#getText(String, String)
	 */
	@Override
	public TextData getText(String language, String id) {
		return dataSource.getText(language, id);
	}

	/**
	 * @see DataSource#getTextGroup(String, String)
	 */
	@Override
	public ArrayList<TextData> getTextGroup(String language, String group) {
		return dataSource.getTextGroup(language, group);
	}

	/**
	 * @see DataSource#getWorld(String)
	 */
	public WorldData getWorld(String filename) {
		return dataSource.getWorld(filename);
	}

	
}