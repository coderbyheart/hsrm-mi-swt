package app;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.Timer;

import model.planer.TileModel;
import model.planer.VehicleModel;
import model.planer.WorldModel;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import simulation.controller.SimulationController;
import simulation.controller.VehicleSimulationController;
import view.AppView;
import view.MenuView;
import view.ToolbarView;
import view.View;
import view.controller.DialogueController;
import view.controller.SubController;
import view.controller.ToolBarController;
import view.controller.WorldController;
import view.world.WorldView;
import config.AppConfig;
import data.DataFactory;
import data.datasource.DataSource;
import data.datasource.xml.XMLDataSource;
import exception.WorldTooLargeException;

/**
 * Basis-Klasse der Anwendung
 * 
 * Der GUIController erzeugt die {@link SubController} und übergibt ihnen die
 * {@link DataFactory} sowie die {@link View} mit der sie arbeiten
 * 
 * @version $Id: GUIController.java 256 2011-01-19 17:33:13Z mtack001 $
 * @todo Wenn sich das {@link WorldModel} ändert, z.B. durch Laden einer
 *       Welt, muss auch die {@link ToolbarView Werkzeugleiste} entsprechende
 *       der in der Welt verfügbaren {@link TileModel Kacheln} und
 *       {@link VehicleModel Fahzeuge} angepasst werden.
 * @deprecated Die Haupt-App-Klasse wird {@link DragDropDemo}
 */
@SuppressWarnings("serial")
public class GUIController extends JFrame implements KeyListener {

	private ToolBarController toolbar;
	private DialogueController dialogue;
	private WorldController world;
	private SimulationController simulation;
	private VehicleSimulationController vehicleSim;
	private Timer simulationTimer;
	private AppView appView;
	protected DataFactory df;
	protected AppConfig config;
	private static Logger logger = Logger.getLogger(GUIController.class);

	/**
	 * Main-Methode.
	 * 
	 * @param argv
	 */
	public static void main(String[] argv) {
		// Set up logging
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
		logger.info("Erzeuge neuen Master-Controller");
		new GUIController();
		logger.info("Fertig.");
	}

	/**
	 * Erzeugt den Controller und die dazugehörigen Untercontroller
	 */
	public GUIController() {
		super("Verkehrsplaner");
		logger.debug("Config");
		config = new AppConfig();
		DataSource ds = new XMLDataSource(config.getXmlDir());
		df = new DataFactory();
		df.setDataSource(ds);
		initController();
		
		setSize(config.getMinScreenWidth(), config.getMinScreenHeight());
		
		// Views *nach* hängen von Fenstergröße ab
		initViews(); 
		
		//Welt aus Datei laden
		// TODO: Fehler beim Laden anzeigen
		try {
			world.loadWorld("ampeltest");
		} catch (WorldTooLargeException e) {
			logger.error(String.format(
					"Konnte Welt %s nicht laden, weil sie zu groß ist.",
					e.getName()));
		}
		
		
		//Set ContentPane
		setContentPane(appView);
		
		// Bei Anpassung der View-Größe, Kachelgröße anpassen
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				appView.setHeight(getHeight());
				appView.setWidth(getWidth());
				repaint();
			}
		});	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Views in AppView setzen
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(new MenuView(config, df));
		appView.setTollbarView((ToolbarView) toolbar.getView());
		appView.setWorldView((WorldView) world.getView());
		
		//Komponenten hinzufügen
		appView.addComponents();
		setVisible(true);
		
		////////////
		/*
		 * Es wird eine neue WeltView erstellt, aber nicht gezeichnet... ;(
		 * 
		 */
	}

	/**
	 * Erzeugt die Sub-Controller
	 */
	protected void initController() {
		logger.info("Erzeuge Sub-Controller");
		logger.debug("Toolbar");
		toolbar = new ToolBarController(df, config);
		logger.debug("Dialoge");
		dialogue = new DialogueController(df, config);
		logger.debug("Welt");
		world = new WorldController(df, config);
		logger.debug("Simulation");
		simulation = new SimulationController();
	}

	/**
	 * Richtet die Views für die Controller ein
	 */
	private void initViews() {
		logger.info("Erzeuge AppView");
		logger.debug("Master");
		appView = new AppView(config, getWidth(), getHeight());
//		logger.debug("Toolbar");
//		toolbar.setView(appView.getToolbarView());
//		logger.debug("Menü");
//		menu.setView(appView.getMenuView());
//		logger.debug("World");
//		world.setView(appView.getWorldView());
	}

	/**
	 * Gibt den Dialog-Controller zurück
	 */
	protected DialogueController getDialogueController() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Öffnet den Dialiog zum Laden einer Welt und lädt dann anschliepend die
	 * Welt
	 */
	public void loadWorldAction() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'd':
			config.setDebugRoutes(!config.debugRoutes());
			config.setDebugVehicles(!config.debugVehicles());
			config.setDebugTiles(!config.debugTiles());
			repaint();
			break;
		case 'm':
			if (!simulationTimer.isRunning()) {
				vehicleSim.next();
				repaint();
			}
			break;
		case 's':
			if (simulationTimer.isRunning()) {
				simulationTimer.stop();
			} else {
				simulationTimer.start();
			}
			break;
		}
	}

	public void keyTyped(KeyEvent e) {

	}
}