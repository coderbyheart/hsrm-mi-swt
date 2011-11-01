package view.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import util.SVGConverter;
import util.SVGConverterException;
import view.View;
import model.planer.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Zeichnet eine Basiskachel
 */
@SuppressWarnings("serial")
public class WorldBaseTileView extends JPanel implements View {
	private BaseTileModel tile;
	private BufferedImage baseTileImage;
	private static Logger logger = Logger.getLogger(WorldBaseTileView.class);
	private int index;

	public WorldBaseTileView(BaseTileModel baseTileModel, int i) {
		super();
		this.setModel(baseTileModel);
		this.setIndex(i);
		setLayout(null);
	}

	public BaseTileModel getModel() {
		return tile;
	}

	public void setModel(BaseTileModel tile) {
		this.tile = tile;
		try {
			baseTileImage = ImageIO.read(SVGConverter.getPNG(tile.getSource()));
		} catch (SVGConverterException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(baseTileImage, 0, 0, getSize().width, getSize().height,
				null);
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}