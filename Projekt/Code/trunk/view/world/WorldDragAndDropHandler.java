package view.world;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class WorldDragAndDropHandler extends WorldDragAndDropHandlerBase {

	/**
	 * Hierhin zeichnen wir die gehaltenen Objekte
	 */
	private ArrayList<WorldTileView> tiles;
	private int screenStartX;
	private int screenStartY;
	private boolean dragging;
	private WorldTileView draggedTile;
	private int draggedTileStartY;
	private int draggedTileStartX;
	private WorldView world;
	private static Logger logger = Logger
			.getLogger(WorldDragAndDropHandler.class);

	public WorldDragAndDropHandler(WorldView world) {
		this.world = world;
		tiles = new ArrayList<WorldTileView>();
	}

	public void addTile(WorldTileView tileView) {
		if (tiles.contains(tileView))
			return;
		tiles.add(tileView);
		tileView.addMouseListener(this);
		tileView.addMouseMotionListener(this);
		tileView.addKeyListener(this);
	}

	@Override
	public void mousePressed(MouseEvent ev) {
		WorldTileView view = (WorldTileView) ev.getSource();
		if (view.getModel() == null)
			return;
		draggedTile = (WorldTileView) ev.getSource();
		// Speichere start
		draggedTileStartX = draggedTile.getX();
		draggedTileStartY = draggedTile.getY();
		screenStartX = ev.getXOnScreen();
		screenStartY = ev.getYOnScreen();
		dragging = true;
		showView(true);
		updateView(ev);
	}

	@Override
	public void mouseReleased(MouseEvent ev) {
		if (!dragging)
			return;
		dragging = false;
		showView(false);
		// Neue Position der Kachel setzen
		// draggedTile.getModel().set
		
		int newLeft = world.getModel().getLeftIndex((draggedTile.getLocation().x + world.getZoomedTileSize() / 2) * (1 / world.getZoom()));
		int newTop = world.getModel().getTopIndex((draggedTile.getLocation().y + world.getZoomedTileSize() / 2) * (1 / world.getZoom()));
		draggedTile.getModel().setPosition(newTop, newLeft);
	}

	private void showView(boolean b) {
		if(b) {
			draggedTile.requestFocusInWindow();
		}
		world.getLayer().setLayer(draggedTile, b ? world.getDragAndDropLevel().intValue() : world.getTileLevel()
				.intValue(), 0);
	}

	@Override
	public void mouseDragged(MouseEvent ev) {
		if (!dragging)
			return;
		updateView(ev);
	}

	private void updateView(MouseEvent ev) {

		draggedTile.setLocation(draggedTileStartX + ev.getXOnScreen() - screenStartX,
				draggedTileStartY + ev.getYOnScreen() - screenStartY);
	}

	@Override
	public void keyReleased(KeyEvent ev) {
		if (!dragging)
			return;
		if (ev.getKeyCode() != 32)
			return;
		draggedTile.getModel().rotateRight();
	}
}
