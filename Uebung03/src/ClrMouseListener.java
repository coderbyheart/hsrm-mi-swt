import java.awt.event.MouseEvent;
import javax.swing.JTextField;


public class ClrMouseListener extends BaseMouseListener {
	
	public ClrMouseListener(JTextField input) {
		super(input);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.input.setText("");
	}

}
