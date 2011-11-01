package view.world;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import view.View;

public class WorldDragAndDropView extends JPanel implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3293576344832012683L;
	private static Logger logger = Logger.getLogger(WorldDragAndDropView.class);
	
	public WorldDragAndDropView()
	{
		setLayout(null);
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(getSize());
		System.out.println(getLocation());
		Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)));
		g2d.setColor(new Color(255, 0, 0));
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRect(0, 0, getWidth(), getHeight());
	}
}
