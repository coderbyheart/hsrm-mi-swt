package data;

import data.datasource.*;
import data.dataobject.*;

/**
 * Erzeugt {@link DataObject}
 */
public abstract class DataFactory implements DataSource
{

	private DataSource dataSource;

	public DataSource getDataSource()
	{
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	/**
	 * Gibt die Standard-Liste mit allen Fahrzeugen zurück
	 * @return 
	 */
	public VehicleData[] getVehicles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt die Standard-Liste mit allen Kacheln zurück
	 * @return 
	 */
	public TileData[] getTiles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Speichert ein Daten-Objekt
	 * @param dataObject
	 * @return 
	 */
	public void save(DataObject dataObject)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Erzeugt ein neues Welt-Objekt mit der angegeben Breite und Höhe in Kacheln
	 * @param maxHorTiles
	 * @param maxVerTiles
	 * @return 
	 */
	public WorldData getNewWorld(int maxHorTiles, int maxVerTiles)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt die Standard-Basiskacheln zurück
	 * @return 
	 */
	public BaseTileData getBaseTile()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt die Standard-Ampel zurück
	 * @return 
	 */
	public TrafficlightData getTrafficlight()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt die (übersetzten Texte zurück)
	 * @param language
	 * @return 
	 */
	public TextData[] getTexts(String language)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Gibt den übersetzten Text mit der ID id zurück
	 * @param id
	 * @param language
	 * @return 
	 */
	public TextData getText(String id, String language)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param filename
	 * @return 
	 */
	public WorldData getWorld(String filename)
	{
		throw new UnsupportedOperationException();
	}
}