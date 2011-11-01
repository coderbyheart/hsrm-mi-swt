package data.datasource.xml;

import data.datasource.*;
import data.dataobject.*;

/**
 * XML-Datenquelle
 */
public class XMLDataSource implements DataSource
{

	private TileXMLSource tile;
	private LanguageXMLSource language;
	private WorldXMLSource world;

	/**
	 * Lädt die Liste der Fahrzeuge aus der tiles.xml
	 * @return 
	 */
	@Override
	public VehicleData[] getVehicles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Lädt die Liste der Kacheln aus der tiles.xml
	 * @return 
	 */
	@Override
	public TileData[] getTiles()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Speichert ein Datenobjekt als XML
	 * @param dataObject
	 * @return 
	 */
	@Override
	public void save(DataObject dataObject)
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

	/**
	 * Lädt die Texte aus der lang.xml
	 * @param language
	 * @return 
	 */
	@Override
	public TextData[] getTexts(String language)
	{
		throw new UnsupportedOperationException();
	}
}