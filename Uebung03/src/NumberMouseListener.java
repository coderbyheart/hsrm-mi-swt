import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;


public class NumberMouseListener implements MouseListener {
	
	private JTextField input;
	private String number;

	public NumberMouseListener(String number, JTextField input) {
		this.input = input;
		this.number = number;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.input.setText(this.input.getText() + this.number);
	}

}
