package data.datasource;

import data.dataobject.*;

/**
 * Interface für alle Datenquellen
 */
public interface DataSource
{

	/**
	 * Liste mit verfügbaren Fahrzeugen zurück geben
	 * @return 
	 */
	VehicleData[] getVehicles();

	/**
	 * Liste mit verfügbaren Kacheln zurück geben
	 * @return 
	 */
	TileData[] getTiles();

	/**
	 * Ein Datenobject speichern
	 * @param dataObject
	 * @return 
	 */
	void save(DataObject dataObject);

	/**
	 * Liste mit allen Texten einer Sprache zurück geben
	 * @param language
	 * @return 
	 */
	TextData[] getTexts(String language);

	/**
	 * 
	 * @param filename
	 * @return 
	 */
	WorldData getWorld(String filename);
}