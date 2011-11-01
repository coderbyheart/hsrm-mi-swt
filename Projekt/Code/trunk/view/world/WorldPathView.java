package view.world;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import config.AppConfig;

import view.View;

import model.planer.PlacedVehicleModel;
import model.planer.WorldModel;

/**
 * Zeichnet (im {@link AppConfig#debugRoutes() Debug-Modus} die Strecken
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version  $Id: WorldPathView.java 236 2011-01-17 13:23:54Z mtack001 $
 */
public class WorldPathView extends JPanel implements View {
	private static final long serialVersionUID = 4691953348028329745L;
	private WorldModel world;
	private int zoomedTileSize;
	private double zoom;
	private AppConfig config;

	public WorldPathView(AppConfig config, WorldModel world, double zoom,
			int zoomedTileSize) {
		this.world = world;
		this.config = config;
		this.setZoom(zoom);
		this.setZoomedTileSize(zoomedTileSize);
		setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (!config.debugRoutes())
			return;
		// Strecke zeichnen
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(43, 96, 206));
		g2d.setStroke(new BasicStroke(2));
		GeneralPath path = world.getPath(getZoom());
		g2d.draw(path);
		
		// Punkte des Pfades zeichnen
		PathIterator p = path.getPathIterator(null);
		FlatteningPathIterator f = new FlatteningPathIterator(p, config.getFlatteningPathIteratorResolution());
		g2d.setColor(new Color(172, 85, 21));
		while (!f.isDone()) {
			float[] pts = new float[6];
			switch (f.currentSegment(pts)) {
			case PathIterator.SEG_MOVETO:
			case PathIterator.SEG_LINETO:
				g2d.draw(new Ellipse2D.Double(pts[0] - 1, pts[1] - 1, 2, 2));
			}
			f.next();
		}
		
		// Ziele der Fahrzeuge zeichen
		g2d.setColor(Color.CYAN);
		g2d.setStroke(new BasicStroke(1));
		for(PlacedVehicleModel vehicle: world.getPlacedVehicles()) {
			for(Point2D target: vehicle.getTargets()) {
				g2d.draw(new Ellipse2D.Double((target.getX() + vehicle.getGlobalX()) * zoom - 5, (target.getY() + vehicle.getGlobalY()) * zoom - 5, 10, 10));
			}
		}
	}

	/**
	 * @param zoomedTileSize
	 *            the zoomedTileSize to set
	 */
	public void setZoomedTileSize(int zoomedTileSize) {
		this.zoomedTileSize = zoomedTileSize;
	}

	/**
	 * @return the zoomedTileSize
	 */
	public int getZoomedTileSize() {
		return zoomedTileSize;
	}

	/**
	 * @param zoom
	 *            the zoom to set
	 */
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	/**
	 * @return the zoom
	 */
	public double getZoom() {
		return zoom;
	}

}
