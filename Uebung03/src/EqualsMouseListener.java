import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import org.python.util.PythonInterpreter; 
import org.python.core.*; 

public class EqualsMouseListener extends BaseMouseListener {

	public EqualsMouseListener(JTextField input) {
		super(input);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		PythonInterpreter myPy = new PythonInterpreter();
		PyObject result = myPy.eval(input.getText());
		input.setText(String.valueOf(Py.py2float(result)));		
	}
}
