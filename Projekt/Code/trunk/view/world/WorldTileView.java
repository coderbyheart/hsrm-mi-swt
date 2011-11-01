package view.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.planer.LocationModel;
import model.planer.PlacedTileModel;
import model.planer.PlacedTrafficlightModel;
import model.planer.RouteModel;
import model.planer.TrafficlightModel;
import model.planer.TrafficlightPhase;

import org.apache.log4j.Logger;

import util.SVGConverter;
import util.SVGConverterException;
import util.Transform;
import view.View;
import config.AppConfig;

/**
 * <p>
 * <b>Verwaltet die Ansicht der platzierten Kacheln</b>
 * <p>
 * Wenn die Ampel-Version der Kachel {@link PlacedTileModel#isTrafficlightsEnabled() altiv ist} werden zu allen
 * {@link PlacedTileModel#getRoutes() Strecken}, 
 * {@link LocationModel#isTrafficlight() die über eine Ampel verfügen} Ampeln gezeichnet.
 * <p>
 * Dies geschieht, in dem in Fahrtrichtung Rechts neben den Endpunkt einer
 * {@link RouteModel Strecke} die Ampel in der jeweils aktuellen 
 * {@link PlacedTrafficlightModel#getPhase() Phase} eine {@link PlacedTrafficlightModel#getSourceForCurrentPhase() Grafik} gezeichnet wird.
 */
@SuppressWarnings("serial")
public class WorldTileView extends JPanel implements View, Observer {
	private PlacedTileModel tile;
	private BufferedImage placedTileImage;
	private BufferedImage [] trafficlightImages = new BufferedImage[5];
	private static Logger logger = Logger.getLogger(WorldTileView.class);
	private AppConfig config;
	private int top;
	private int left;
	private BufferedImage placedTileImageOriginal;

	public WorldTileView(AppConfig config, PlacedTileModel tileModel) {
		super();
		this.config = config;
		setLayout(null);
		setModel(tileModel);
	}


	public PlacedTileModel getModel() {
		return this.tile;
	}

	public void setModel(PlacedTileModel model) {
		//Set Tile-Model
		this.tile = model;
		model.addObserver(this);
		//Set Trafficlightmodels
		for(TrafficlightModel t : model.getTrafficlights()){
			t.addObserver(this);
		}
			
		try {
			//Tile Image
			placedTileImageOriginal = ImageIO.read(SVGConverter.getPNG(model.getSource()));
			placedTileImage = Transform.rotateImage(placedTileImageOriginal, model.getRotation());
			
			//Trafficlight Images
			if(!model.getTrafficlights().isEmpty()){
				trafficlightImages[0] = ImageIO.read(SVGConverter.getPNG(model.getTrafficlights().get(0).getOffSource()));
				trafficlightImages[1] = ImageIO.read(SVGConverter.getPNG(model.getTrafficlights().get(0).getGreenSource()));
				trafficlightImages[2] = ImageIO.read(SVGConverter.getPNG(model.getTrafficlights().get(0).getYellowSource()));
				trafficlightImages[3] = ImageIO.read(SVGConverter.getPNG(model.getTrafficlights().get(0).getRedSource()));
				trafficlightImages[4] = ImageIO.read(SVGConverter.getPNG(model.getTrafficlights().get(0).getRedYellowSource()));
			}
		} catch (SVGConverterException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(placedTileImage, 0, 0, getSize().width, getSize().height, null);
				
		//Ampelzeichnen
		if(tile.isTrafficlightsEnabled()){
			for(TrafficlightModel traff : tile.getTrafficlights()){
				BufferedImage img = setImageForTrafficlight(traff.getPhase());
		
				double posx = getSize().width/((double)config.getModelTileSize()/traff.getLeft())+((getSize().width/6)/2);
				double posy = getSize().height/((double)config.getModelTileSize()/traff.getTop())+((getSize().height/6)/2);
				Point2D a = new Point2D.Double(posx, posy);
				Point2D p = Transform.rotatePoint(a, tile.getRotation(),getSize().width-(getSize().width/6)); //TODO: check Ampeldreheung
				
				g2d.drawImage(
						Transform.rotateImageDegrees(img,traff.getRotation()+Math.toDegrees(tile.getRotation())),(int)p.getX(),
						(int)p.getY(),getSize().width/6,getSize().height/6,null);
			}
			
		}
	}
	
	private BufferedImage setImageForTrafficlight(TrafficlightPhase phase) {
		switch(phase){
		case OFF: return trafficlightImages[0];
		case GREEN: return trafficlightImages[1];
		case YELLOW: return trafficlightImages[2];
		case RED: return trafficlightImages[3];
		case RED_YELLOW: return trafficlightImages[4];
		}
		//Falls alles fehlschlägt, dass Off-Img zurückgeben
		return trafficlightImages[0];
	}


	/**
	 * @param top the top to set
	 */
	public void setTop(int top) {
		this.top = top;
	}

	/**
	 * @return the top
	 */
	public int getTop() {
		return top;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(int left) {
		this.left = left;
	}

	/**
	 * @return the left
	 */
	public int getLeft() {
		return left;
	}


	public void update(Observable o, Object arg) {
		if (arg.equals(PlacedTileModel.ROTATION_CHANGED)) {
			placedTileImage = Transform.rotateImage(placedTileImageOriginal, ((PlacedTileModel) o).getRotation());
			repaint();
		}
		if (arg.equals(PlacedTrafficlightModel.PHASE_CHANGED)) {
			repaint();
		}
	}
}