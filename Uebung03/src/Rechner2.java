import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Rechner2 extends JFrame {
	
	private int width = 300;

	private String[][] buttons = { { "CLR", "(", ")", "+" },
			{ "7", "8", "9", "-" }, { "4", "5", "6", "*" },
			{ "1", "2", "3", "/" }, { "0", ".", "<-", "=" } };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Rechner2();
	}

	public Rechner2() {
		initSwing();
	}

	private void initSwing() {
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());

		// Eingabefeld
		JTextField input = new JTextField();
		input.setPreferredSize(new Dimension(width, 50));
		pane.add(input, BorderLayout.PAGE_START);

		// Frame für Grid
		JPanel buttonGrid = new JPanel();
		buttonGrid.setPreferredSize(new Dimension(width, 400));
		GridLayout buttonLayout = new GridLayout(buttons.length,
				buttons[0].length);
		buttonGrid.setLayout(buttonLayout);
		pane.add(buttonGrid, BorderLayout.PAGE_END);
		
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length; j++) {
				JButton b = new JButton(buttons[i][j]);
				buttonGrid.add(b);
				if (buttons[i][j].equals("=")) {
					b.addMouseListener(new EqualsMouseListener(input));
				} else if (buttons[i][j].equals("<-")) {
					b.addMouseListener(new DelMouseListener(input));
				} else if (buttons[i][j].equals("CLR")) {
					b.addMouseListener(new ClrMouseListener(input));
				} else {
					b.addMouseListener(new NumberMouseListener(buttons[i][j], input));
				}
				
			}
		}

		this.setSize(300, 500);
		// pack();
		setVisible(true);
	}
}