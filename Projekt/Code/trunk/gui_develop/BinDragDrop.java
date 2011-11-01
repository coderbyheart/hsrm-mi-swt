package gui_develop;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.dnd.DragSource;
import java.awt.event.MouseEvent;

import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class BinDragDrop extends AbstractDragDrop {

	GuiTestApp parent;
	
	public BinDragDrop(GuiTestApp parent) {
		this.parent = parent;
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}


	@Override
	public void mouseExited(MouseEvent arg0) {}

	boolean dragging = false;
	TileView originalTile;
	TileView draggedTile;
	TileView draggedOverTile = null;
	Point offset;
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		dragging = true;
		offset = new Point(arg0.getX(),arg0.getY());
		originalTile = (TileView) arg0.getSource();
		draggedTile = originalTile.clone();
		
		// Drag tile size
		draggedTile.setSize(originalTile.getSize());
		
		parent.layeredPane.add(draggedTile, parent.layeredPane.getComponentCountInLayer(JLayeredPane.DRAG_LAYER) );
		
		setDragTileLocation(new Point(arg0.getXOnScreen(), arg0.getYOnScreen()));

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(dragging) {
			setDragTileLocation(new Point(arg0.getXOnScreen(), arg0.getYOnScreen()));
			
			
			// find drop target on world
			Point os = new Point(arg0.getXOnScreen(), arg0.getYOnScreen());
			SwingUtilities.convertPointFromScreen(os, parent.bin);
			
			Component component = 
                SwingUtilities.getDeepestComponentAt(
                		parent.bin,
                		(int) os.getX(), 
                		(int) os.getY()  );
			
			if ( component instanceof TileView ) {
				// available drop target reached
				draggedOverTile =  ((TileView) component);
				
				// change cursor symbol to drop target symbol
				Cursor cursor = DragSource.DefaultCopyDrop;
				draggedTile.setCursor(cursor);
			}
			else {
				draggedOverTile = null;
				
				// reset cursor symbol
				draggedTile.setCursor(null);
			}
		}
	}
	
	private void setDragTileLocation(Point mouseLocation) {
		Point frameOffset = parent.getLocation();
		
		draggedTile.setLocation(
				(int) ( mouseLocation.getX() - frameOffset.getX() - offset.getX() - 5 ), 
				(int) ( mouseLocation.getY() - frameOffset.getY() - offset.getY() - 30) );
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		dragging = false;
		
		// reset cursor symbol
		draggedTile.setCursor(null);
		
		parent.layeredPane.remove(draggedTile);
		parent.layeredPane.repaint();
		
		if ( draggedOverTile != null ) {
			originalTile.reset();
			draggedOverTile.setTile(draggedTile.getTile());
			parent.repaint();
			draggedOverTile = null;
		}
	}

	
}
