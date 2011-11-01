package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import view.world.WorldView;

import app.GUIController;

import config.AppConfig;

/**
 * Verwaltet das Hauptfenster der Anwendung und erzeugt drei Unterviews für die
 * Menüleiste, die Werkzeugleiste und die Welt
 * 
 * @version $Id: AppView.java 246 2011-01-18 19:12:12Z sfran001 $
 */
public class AppView extends JPanel implements View {
	private int width;
	private int height;
	private ToolbarView toolbar;
	private WorldView world;
	private AppConfig config;
	private static Logger logger = Logger.getLogger(GUIController.class);

	/**
	 * Konstruktor
	 * 
	 * <p>Besitzt diese Unter-Views:
	 * <ul>
	 * <li>
	 * Die {@link MenuView View für die Menüleiste}, die {@link AppConfig} liefert hierzu die {@link AppConfig#getMinMenuHeight() Mindesthöhe} und die {@link AppConfig#getMaxMenuHeight() Maximalhöhe}.
	 * <li>
	 * Die {@link ToolbarView View für die Werkzeugleiste}, die {@link AppConfig} liefert hierzu die {@link AppConfig#getMinToolbarWidth() Mindestbreite} und die {@link AppConfig#getMaxToolbarWidth() Maximalbreite}.
	 * <li>
	 * Die {@link WorldView View für die Welt} 
	 * </ul>
	 */
	public AppView(AppConfig config, int width, int height) {
		logger.info(String.format("Erzeuge neue AppView mit %dx%d Pixeln", width, height));
		this.config = config;
		setWidth(width);
		setHeight(height);
	}
	
	public void setTollbarView(ToolbarView toolbar){
		this.toolbar = toolbar;
	}
	

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ToolbarView getToolbarView() {
		return toolbar;
	}

	public WorldView getWorldView() {
		return world;
	}

	/**
	 * Gibt die maximale Anzahl vertikaler Kacheln in der Welt-View zurück
	 * 
	 * @return Maximale Anzahl vertikaler Kacheln
	 */
	public int getMaxVerTiles() {
		int max = (int) Math.floor((getWidth() - toolbar.getWidth())
				/ config.getMinTileSize());
		return Math.max(max, 0);
	}

	/**
	 * Gibt die maximale Anzahl horizontaler Kacheln in der Welt-View zurück
	 * 
	 * @return Maximale Anzahl horizontaler Kacheln
	 */
	public int getMaxHorTiles() {
		int max = (int) Math.floor((getHeight())
				/ config.getMinTileSize());
		return Math.max(max, 0);
	}

	/**
	 * Setzt die maximalen Anzahl von Kacheln entsprechend der aktuellen
	 * Fenstergröße
	 * 
	 * @todo sollte sich (automatisch) beim Resizen des Fensters aktualisieren
	 */
	@SuppressWarnings("unused")
	private void updateMaxTiles() {
	}

	public void addComponents() {
		if(toolbar != null){
			add(toolbar,BorderLayout.EAST);
		}
		if(world != null){
			add(world,BorderLayout.WEST);
		}
		
	}

	public void setWorldView(WorldView loadWorld) {
		this.world = loadWorld;
		
	}
}