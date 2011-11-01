package config;

import java.awt.geom.FlatteningPathIterator;
import java.io.File;

/**
 * Stellt Konfigurationseinstellungen zur Verfügung
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id$
 */
public class AppConfig {
	
	private String language = "de";
	private boolean debugRoutes = false;
	private boolean debugVehicles = false;
	private boolean debugLogVehicles = false;
	private boolean debugTiles = false;
	
	/**
	 * Gibt den Ordner mit Grafik-Dateien zurück
	 * 
	 * @return Pfad zum Ordner mit Grafikdateien
	 */
	public File getGfxDir() {
		return new File("resource/gfx");
	}

	/**
	 * Gibt den Dateinamen der Datei source im Ordner mit Grafik-Dateien zurück
	 * 
	 * @param source
	 * @return Dateiname
	 */
	public File getGfxFile(String source) {
		return new File(getGfxDir(), source);
	}

	/**
	 * Gibt den Ordner mit XML-Dateien zurück
	 * 
	 * @return Pfad zum Ordner mit XML-Dateien
	 */
	public File getXmlDir() {
		return new File("resource/xml");
	}

	/**
	 * Gibt den Dateinamen der Datei xmlfile im Ordner mit XML-Dateien zurück
	 * 
	 * @param xmlfile
	 * @return Dateiname
	 */
	public File getXmlFile(String xmlfile) {
		return new File(getXmlDir(), xmlfile);
	}

	/**
	 * Setzt die Sprache der Anwendung
	 * 
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gibt die Sprache der Anwendung zurück
	 * 
	 * @return Sprache
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @return Die minimale Kachelgröße in Pixeln
	 */
	public int getMinTileSize() {
		return 80;
	}
	
	/**
	 * @return Die Kachelgröße in Pixeln, die die Models zur Berechnung der Positionen verwenden
	 */
	public int getModelTileSize() {
		return 100;
	}

	/**
	 * @return Die minimale Werkzeugleistenbreite in Pixeln
	 */
	public int getMinToolbarWidth() {
		return 100;
	}

	/**
	 * @return Die maximale Werkzeugleistenbreite in Pixeln
	 */
	public int getMaxToolbarWidth() {
		return 200;
	}

	/**
	 * @return Die minimale Menüleistenhöhe in Pixeln
	 */
	public int getMinMenuHeight() {
		return 20;
	}

	/**
	 * @return Die maximale Menüleistenhöhe in Pixeln
	 */
	public int getMaxMenuHeight() {
		return 50;
	}
	
	/**
	 * @return Die minimale Bildschirmbreite in Pixeln
	 */
	public int getMinScreenWidth()
	{
		return 800;
	}
	
	/**
	 * @return Die minimale Bildschirmhöhe in Pixeln
	 */
	public int getMinScreenHeight()
	{
		return 600;
	}

	/**
	 * @param debugRoutes If routes should be debugged
	 */
	public void setDebugRoutes(boolean debugRoutes) {
		this.debugRoutes = debugRoutes;
	}

	/**
	 * @return If routes should be debugged
	 */
	public boolean debugRoutes() {
		return debugRoutes;
	}
	
	/**
	 * @param debugVehicles If vehicles should be debugged
	 */
	public void setDebugVehicles(boolean debugVehicles) {
		this.debugVehicles  = debugVehicles;
	}

	/**
	 * @return If vehicles should be debugged
	 */
	public boolean debugVehicles() {
		return debugVehicles;
	}
	
	/**
	 * @param debugTiles If vehicles should be debugged
	 */
	public void setDebugTiles(boolean debugTiles) {
		this.debugTiles  = debugTiles;
	}

	/**
	 * @return If vehicles should be debugged
	 */
	public boolean debugTiles() {
		return debugTiles;
	}

	/**
	 * @return Frames pro Sekunde, mit der gerendert wird
	 */
	public int getFps() {
		return 15;
	}

	/**
	 * @return Die Auflösung die zum Rastern von Pfaden mit dem {@link FlatteningPathIterator} verwendet wird
	 */
	public double getFlatteningPathIteratorResolution() {
		return 1;
	}

	/**
	 * @param debugLogVehicles the debugLogVehicles to set
	 */
	public void setDebugLogVehicles(boolean debugLogVehicles) {
		this.debugLogVehicles = debugLogVehicles;
	}

	/**
	 * @return the debugLogVehicles
	 */
	public boolean debugLogVehicles() {
		return debugLogVehicles;
	}

	public String getAppName() {
		return "Verkehrsplaner 2010swt03";
	}
}