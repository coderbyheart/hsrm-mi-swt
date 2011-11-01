package app;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import model.planer.WorldModel;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import simulation.controller.VehicleSimulationController;
import view.MenuView;
import view.SimpleTileView;
import view.world.WorldView;
import config.AppConfig;
import data.DataFactory;
import data.datamanager.WorldManager;
import data.dataobject.WorldData;
import data.datasource.DataSource;
import data.datasource.xml.XMLDataSource;

public class DragDropDemo extends JFrame implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -3281411230048053044L;
	private JFrame frame;
	private int toolbarWidth = 100;
	private int defaultWidth = 780;
	private int defaultHeight = 540;
	private boolean dragging;
	private JLayeredPane layeredPane;
	private SimpleTileView draggedTile;
	private int startX;
	private int startY;
	private JPanel toolbarPanel;
	private JScrollPane scrollPane;
	private JPanel worldView;
	private JPanel tilePanel;
	private JPanel trashPanel;
	private AppConfig config;
	private DataFactory df;
	private static Logger logger = Logger.getLogger(DragDropDemo.class);
	private VehicleSimulationController vehicleSim;
	private Timer simulationTimer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Set up logging
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logger.info("Erzeuge neues Fenster");
					DragDropDemo window = new DragDropDemo("world-example-8x6");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param mapName 
	 */
	public DragDropDemo(String mapName) {
		config = new AppConfig();
		DataSource ds = new XMLDataSource(config.getXmlDir());
		df = new DataFactory();
		df.setDataSource(ds);
		initialize();
		initWorld(mapName);
	}

	private void initWorld(String mapName) {
		WorldManager manager = new WorldManager(config);
		WorldData worldData = df.getWorld(mapName);
		WorldModel world = manager.getModelFromData(worldData);
		if (worldView == null) {
			worldView = new WorldView(config, world);
			layeredPane.add(worldView, new Integer(0));
			worldView.setBounds(0, 0, defaultWidth - toolbarWidth, defaultHeight);
			worldView.setSize(defaultWidth - toolbarWidth, defaultHeight);
		} else {
			layeredPane.remove(worldView);
			simulationTimer.stop();
			worldView = new WorldView(config, world);
			layeredPane.add(worldView, new Integer(0));
		}
		vehicleSim = new VehicleSimulationController(world);
		simulationTimer = new Timer(1000 / config.getFps(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vehicleSim.next();
				repaint();
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(config.getAppName());
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initMenu();
		
		frame.getContentPane().setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setSize(defaultWidth, defaultHeight);
		frame.getContentPane().add(layeredPane);
		
		toolbarPanel = new JPanel();
		toolbarPanel.setLocation(defaultWidth - toolbarWidth, 0);
		toolbarPanel.setSize(new Dimension(toolbarWidth, defaultHeight));
		toolbarPanel.setMinimumSize(new Dimension(100, 10));
		layeredPane.add(toolbarPanel, new Integer(0));
		toolbarPanel.setLayout(null);
	
		tilePanel = new JPanel();
		tilePanel.setBounds(0, 0, toolbarWidth - 25, (toolbarWidth - 25) * 10);
		tilePanel.setLayout(new GridLayout(10, 1, 0, 0));
		tilePanel.setPreferredSize(new Dimension(toolbarWidth - 25, (toolbarWidth - 25) * 10));
		for(int i = 0; i < 10; i++) {
			SimpleTileView tileView = new SimpleTileView(true);
			tilePanel.add(tileView);
			tileView.addMouseListener(this);
			tileView.addMouseMotionListener(this);
		}
		scrollPane = new JScrollPane(tilePanel);
		scrollPane.setBounds(0, 0, toolbarWidth, defaultHeight - toolbarWidth);
		toolbarPanel.add(scrollPane);
		
		trashPanel = new JPanel();
		trashPanel.setBounds(0, defaultHeight - toolbarWidth, toolbarWidth, toolbarWidth );
		toolbarPanel.add(trashPanel);
		trashPanel.setLayout(new GridLayout(1, 1, 0, 0));
		trashPanel.add(new SimpleTileView(false));
		
		// Bei Anpassung der View-Größe, Kachelgröße anpassen
		frame.getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				layeredPane.setSize(frame.getWidth(), frame.getHeight());
				layeredPane.setSize(frame.getContentPane().getSize());
				worldView.setBounds(0, 0, frame.getContentPane().getWidth() - toolbarWidth, frame.getContentPane().getHeight());
				toolbarPanel.setLocation(frame.getContentPane().getWidth() - toolbarWidth, 0);
				toolbarPanel.setSize(new Dimension(toolbarWidth, frame.getContentPane().getHeight()));
				scrollPane.setBounds(0, 0, toolbarWidth, frame.getContentPane().getHeight() - toolbarWidth);
				trashPanel.setBounds(0, frame.getContentPane().getHeight() - toolbarWidth, toolbarWidth, toolbarWidth);
			}
		});	
	}

	/**
	 * Menü erzeugen und Aktionen darauf definieren 
	 */
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		final MenuView menuView = new MenuView(config, df);
		menuBar.add(menuView);
		// Simulation
		menuView.simCntrl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (menuView.simCntrl.getState()) {
                            simulationTimer.start();
                    } else {
                            simulationTimer.stop();
                    }
            }
	    });
		menuView.simDebug.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                    boolean state = menuView.simDebug.getState();
	                    config.setDebugRoutes(state);
	                    config.setDebugVehicles(state);
	                    config.setDebugTiles(state);
	                    repaint();
	                    worldView.repaint();
	            }
	    });
		menuView.simNext.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                    if (!simulationTimer.isRunning()) {
	                            vehicleSim.next();
	                            repaint();
	                    }
	            }
	    });
		// Datei öffnen Dialog
		final JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setCurrentDirectory(config.getXmlDir());
		chooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals(
						JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
						|| e.getPropertyName().equals(
								JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
				}
			}
		});
		menuView.laden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				System.err.println("laden");
				// chooser.setVisible(true);
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					// chooser.setVisible(false);
					initWorld(chooser.getSelectedFile().toString());
				}
			}
		});
	}

	@Override
	public void mouseDragged(MouseEvent ev) {
		if(dragging) {
			draggedTile.setLocation(ev.getXOnScreen() - startX + toolbarPanel.getX(), ev.getYOnScreen() - startY + ((SimpleTileView) ev.getSource()).getY() - scrollPane.getVerticalScrollBar().getValue());
		}
	}

	@Override
	public void mouseMoved(MouseEvent ev) {
	}

	@Override
	public void mouseClicked(MouseEvent ev) {
	}

	@Override
	public void mouseEntered(MouseEvent ev) {
	}

	@Override
	public void mouseExited(MouseEvent ev) {
	}

	@Override
	public void mousePressed(MouseEvent ev) {
		dragging = true;
		draggedTile = ((SimpleTileView) ev.getSource()).clone();
		draggedTile.setSize(100, 100);
		layeredPane.add(draggedTile, new Integer(1));
		startX = ev.getXOnScreen();
		startY = ev.getYOnScreen();
		// Position der Kachel muss um Position der Werkzeugleiste korrigiert werden
		draggedTile.setLocation(ev.getXOnScreen() - startX + toolbarPanel.getX(), ev.getYOnScreen() - startY + ((SimpleTileView) ev.getSource()).getY() - scrollPane.getVerticalScrollBar().getValue());
	}

	@Override
	public void mouseReleased(MouseEvent ev) {
		dragging = false;
		layeredPane.remove(draggedTile);
		layeredPane.repaint();
	}
}
