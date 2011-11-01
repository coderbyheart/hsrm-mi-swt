package gui_develop;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.dnd.DragSource;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class ToolbarDragDrop extends AbstractDragDrop {

	GuiTestApp parent;

	boolean dragging = false;
	TileView draggedTile;
	TileView draggedOverTile = null;
	Point offset;

	public ToolbarDragDrop(GuiTestApp parent) {
		this.parent = parent;

		KeyboardFocusManager fm = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		fm.addKeyEventDispatcher(new KeyEventDispatcher() {
			public boolean dispatchKeyEvent(KeyEvent e) {

				if (e.getID() == KeyEvent.KEY_RELEASED
						&& e.getKeyCode() == KeyEvent.VK_TAB) {

					if (dragging) {

						Thread t = new Thread() {
							public void run() {
								draggedTile.rotate();
							}
						};

						t.start();

					}

				}

				return false;
			}
		});
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		dragging = true;
		offset = new Point(arg0.getX(), arg0.getY());
		TileView orig = (TileView) arg0.getSource();
		draggedTile = orig.clone();

		// Drag tile size
		draggedTile.setSize(orig.getSize());

		parent.layeredPane.add(draggedTile, parent.layeredPane
				.getComponentCountInLayer(JLayeredPane.DRAG_LAYER));

		setDragTileLocation(new Point(arg0.getXOnScreen(), arg0.getYOnScreen()));

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (dragging) {
			setDragTileLocation(new Point(arg0.getXOnScreen(),
					arg0.getYOnScreen()));

			// find drop target on world
			Point os = new Point(arg0.getXOnScreen(), arg0.getYOnScreen());
			SwingUtilities.convertPointFromScreen(os, parent.world);

			Component component = SwingUtilities.getDeepestComponentAt(
					parent.world, (int) os.getX(), (int) os.getY());

			if (component instanceof TileView) {
				// available drop target reached
				draggedOverTile = ((TileView) component);

				// change cursor symbol to drop target symbol
				Cursor cursor = DragSource.DefaultCopyDrop;
				draggedTile.setCursor(cursor);
			} else {
				draggedOverTile = null;

				// reset cursor symbol
				draggedTile.setCursor(null);
			}
		}
	}

	private void setDragTileLocation(Point mouseLocation) {
		Point frameOffset = parent.getLocation();

		draggedTile.setLocation(
				(int) (mouseLocation.getX() - frameOffset.getX()
						- offset.getX() - 5), (int) (mouseLocation.getY()
						- frameOffset.getY() - offset.getY() - 30));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		dragging = false;

		// reset cursor symbol
		draggedTile.setCursor(null);

		parent.layeredPane.remove(draggedTile);
		parent.layeredPane.repaint();

		if (draggedOverTile != null) {
			draggedOverTile.setTile(draggedTile.getTile());
			parent.repaint();
		}
	}

}
