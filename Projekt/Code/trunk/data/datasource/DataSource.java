package data.datasource;

import java.io.File;
import java.util.ArrayList;

import data.dataobject.*;

/**
 * Interface für alle Datenquellen
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: DataSource.java 244 2011-01-18 18:20:41Z sfran001 $
 */
public interface DataSource
{

	/**
	 * Gibt die Standard-Liste mit allen verfügbaren Fahrzeugen zurück
	 * @return Liste mit Fahrzeugen
	 */
	ArrayList<VehicleData> getVehicles();
	

	/**
	 * Gibt die Standard-Liste mit allen verfügbaren Kacheln zurück
	 * @return Liste mit Kacheln
	 */
	ArrayList<TileData> getTiles();

	/**
	 * Gibt die {@link BaseTileData Basis-Kachel} zurück
	 * 
	 * @return Basis-Kachel
	 */
	BaseTileData getBaseTile();

	/**
	 * Ein Datenobject speichern
	 * @param dataObject
	 * @param identifier 
	 * @param gfxDir 
	 */
	void save(DataObject dataObject, String identifier);

	/**
	 * Liste mit allen Texten einer Sprache zurück geben
	 * @param language
	 * @return Texte
	 */
	ArrayList<TextData> getTexts(String language);
	
	/**
	 * Gibt den Text mit der Bezeichnung identifier der Sprache language zurück
	 * 
	 * @param language
	 * @param identifier
	 * @return Text
	 */
	TextData getText(String language, String identifier);
	
	/**
	 * Liste mit den Texten der Sprache language in der Gruppe group zurück geben
	 * 
	 * @param language
	 * @param group
	 * @return Texte
	 */
	ArrayList<TextData> getTextGroup(String language, String group);

	/**
	 * Erzeugt eine Welte, die mit der Bezeichnung identifier gespeichert ist
	 * 
	 * @param identifier Bezeichnung der Welt
	 * @return Die vollständig geladenen Welt 
	 */
	WorldData getWorld(String identifier);


	
}