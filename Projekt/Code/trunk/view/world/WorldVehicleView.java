package view.world;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import config.AppConfig;

import model.planer.PlacedVehicleModel;
import util.SVGConverter;
import util.SVGConverterException;
import util.Transform;
import view.View;

/**
 * Zeichnet ein Fahrzeug
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: WorldVehicleView.java 250 2011-01-19 08:15:42Z mtack001 $
 */
public class WorldVehicleView extends JPanel implements View, Observer {
	
	private static final long serialVersionUID = 7896169145523682621L;

	private PlacedVehicleModel vehicle;
	private AppConfig config;

	private BufferedImage placedVehicleImageOriginal;

	private BufferedImage placedVehicleImage;
	
	private static Logger logger = Logger.getLogger(WorldVehicleView.class);
	
	public WorldVehicleView(AppConfig config, PlacedVehicleModel model) {
		this.config = config;
		this.setVehicle(model);
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(placedVehicleImage, 0, 0, getSize().width, getSize().height, null);
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(PlacedVehicleModel vehicle) {
		this.vehicle = vehicle;
		this.vehicle.addObserver(this);
		try {
			placedVehicleImageOriginal = ImageIO.read(SVGConverter.getPNG(vehicle.getSource()));
			updateRotation();
		} catch (SVGConverterException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void updateRotation() {
		placedVehicleImage = Transform.rotateImage(placedVehicleImageOriginal, vehicle.getRotation());
	}

	/**
	 * @return the vehicle
	 */
	public PlacedVehicleModel getVehicle() {
		return vehicle;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o.equals(vehicle) && arg.equals(PlacedVehicleModel.ROTATION_CHANGED)) {
			updateRotation();
		}
	}
}
