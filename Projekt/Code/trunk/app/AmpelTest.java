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

import simulation.controller.TrafficLightSimulationController;
import simulation.controller.VehicleSimulationController;
import view.world.WorldView;

import config.AppConfig;
import data.DataFactory;
import data.datamanager.WorldManager;
import data.dataobject.WorldData;
import data.datasource.DataSource;
import data.datasource.xml.XMLDataSource;

/**
 * Testen der Ampeln
 * 
 * Animationsbefehle Tastatur-Befehle:
 * <ul>
 * <li>s: Simulation stoppen bzw. starten
 * <li>a: Alert für AMpeln an/aus schalten
 * </ul>
 * 
 * 
 * @author Simon Franzen
 * @version $Id$
 */
@SuppressWarnings("serial")
public class AmpelTest extends JFrame implements KeyListener {
	
	private AppConfig config; 
	private TrafficLightSimulationController trafficlightSim;
	private VehicleSimulationController vehicleSim;
	private Timer simulationTimer;
	
	private DataSource ds;
	private DataFactory df;
	private WorldManager manager;
	private WorldData worldData;
	private WorldModel world;
	private WorldView view;
	
	private boolean alert;

	public static void main(String[] args) {
		// Set up logging
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
		new AmpelTest("ampeltest");
		

	}

	public AmpelTest(String map) {
		// Let's Swing
		super("Verkehrsplaner: AmpelTest-Karte");
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
		trafficlightSim = new TrafficLightSimulationController(world);
		vehicleSim = new VehicleSimulationController(world);
		view.setSize(800, 600);
		getContentPane().add(view);
		simulationTimer = new Timer(1500/config.getFps(), new ActionListener() {
			int traff = 0;
			public void actionPerformed(ActionEvent e) {
				if(traff==10){
					if(alert){
						trafficlightSim.alert();
					}else{
						trafficlightSim.next();
					}
					traff=0;
				}

				vehicleSim.next();
				traff++;
			}
		});
		
		addKeyListener(this);
		
		setVisible(true);
		
//		simulationTimer.start();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 's':
			if (simulationTimer.isRunning()) {
				simulationTimer.stop();
			} else {
				simulationTimer.start();
			}
			break;
		case 'a':
			if (alert) {
				alert=false;
			} else {
				alert=true;
				trafficlightSim.off();
			}
			break;
		case 'f':
			saveWorld();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
