import java.awt.event.MouseEvent;
import javax.swing.JTextField;


public class DelMouseListener extends BaseMouseListener {
	
	public DelMouseListener(JTextField input) {
		super(input);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int len = this.input.getText().length();
		if (len <= 0) return;
		this.input.setText(this.input.getText().substring(0, len - 1));
	}

}
