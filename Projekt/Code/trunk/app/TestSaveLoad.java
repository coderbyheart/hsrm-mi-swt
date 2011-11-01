package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import model.planer.WorldModel;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import simulation.controller.VehicleSimulationController;
import view.world.WorldView;

import config.AppConfig;
import data.DataFactory;
import data.datamanager.WorldManager;
import data.dataobject.WorldData;
import data.datasource.DataSource;
import data.datasource.xml.XMLDataSource;

/**
 * Testen Laden und speichern
 * 
 * Animationsbefehle Tastatur-Befehle:
 * <ul>
 * <li>d: Debugging ein- bzw. ausschalten
 * <li>m: Animation einen Schritt weiter laufen lassen
 * <li>s: Simulation stoppen bzw. starten
 * <li>f: Welt in XML-Datei abspeichern
 * </ul>
 * 
 * 
 * @author Simon Franzen
 * @version $Id$
 */
@SuppressWarnings("serial")
public class TestSaveLoad extends JFrame implements KeyListener {
	
	private AppConfig config; 
	private VehicleSimulationController vehicleSim;
	private Timer simulationTimer;
	
	private DataSource ds;
	private DataFactory df;
	private WorldManager manager;
	private WorldData worldData;
	private WorldModel world;
	private WorldView view;

	public static void main(String[] args) {
		// Set up logging
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
		new TestSaveLoad("world-example-8x6"); 
//		new TestSaveLoad("neue-map"); 

	}

	public TestSaveLoad(String map) {
		// Let's Swing
		super("Verkehrsplaner: TestSave-Karte");
		// Standardgröße
		setSize(800, 600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		config = new AppConfig();
		ds = new XMLDataSource(config.getXmlDir());
		df = new DataFactory();
		df.setDataSource(ds);

		manager = new WorldManager(config);
		worldData = df.getWorld(map);
		world = manager.getModelFromData(worldData);
		view = new WorldView(config, world);
		vehicleSim = new VehicleSimulationController(world);
		view.setSize(800, 600);
		getContentPane().add(view);
		
		simulationTimer = new Timer(1000 / config.getFps(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vehicleSim.next();
				repaint();
			}
		});
		
		addKeyListener(this);
		
		setVisible(true);
		
		// simulationTimer.start();
		
		//
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyChar()) {
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
		case 'f':
			saveWorld();
			break;
		}
	}

	private void saveWorld() {
		//save
		WorldData changedWorldData = manager.getDataFromModel(world);
		df.save(changedWorldData, "neue-map");
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
