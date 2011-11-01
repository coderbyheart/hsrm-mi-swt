package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.menu.MenuItemListModel;

import org.apache.log4j.Logger;

import config.AppConfig;
import data.DataFactory;
import data.dataobject.TextData;

public class MenuView extends JMenuBar implements View, ActionListener {
	private static final long serialVersionUID = -1539645841099968404L;
	JMenu datei = new JMenu("Datei");
	JMenu simulation = new JMenu("Simulation");
	JMenu hilfe = new JMenu("Hilfe");
	JMenuItem neu = new JMenuItem("Neu");
	public JMenuItem laden = new JMenuItem("Laden");
	JMenuItem speichern = new JMenuItem("Speichern");
	public JCheckBoxMenuItem simCntrl = new JCheckBoxMenuItem("Starten");
	public JMenuItem simNext = new JMenuItem("Einen Schritt weiter");
	public JCheckBoxMenuItem simDebug = new JCheckBoxMenuItem(
			"Debug-Layer anzeigen");
	JMenuItem beenden = new JMenuItem("Beenden");
	JMenuItem faq = new JMenuItem("FAQ");
	JMenuItem ueber = new JMenuItem("Ueber...");
	private DataFactory dataFactory;
	private AppConfig config;
	private static Logger logger = Logger.getLogger(MenuView.class);
	private MenuItemListModel model;

	/**
	 * Konstruktor, der Texte aus {@link MenuItemListModel} verwendet
	 * 
	 * @param simulationTimer
	 * @param model
	 */
	public MenuView(AppConfig config, DataFactory dataFactory) {
		this.dataFactory = dataFactory;
		this.config = config;
		init();
	}

	private void init() {
		logger.debug("Lade Texte");
		model = new MenuItemListModel();
		for (TextData textData : dataFactory.getTextGroup(config.getLanguage(),
				"menu")) {
			model.put(textData.getId(), textData.getText());
			logger.debug(String.format("%s: %s", textData.getId(),
					textData.getText()));
		}
		datei.setText(model.get("menu-file"));
		simulation.setText(model.get("menu-simulation"));
		hilfe.setText(model.get("menu-help"));
		neu.setText(model.get("menu-new"));
		laden.setText(model.get("menu-load"));
		speichern.setText(model.get("menu-save"));
		simCntrl.setText(model.get("menu-start"));
		simCntrl.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ev) {
				if (simCntrl.getState()) {
					simCntrl.setText(model.get("menu-stop"));
				} else {
					simCntrl.setText(model.get("menu-start"));
				}
				simNext.setEnabled(!simCntrl.getState());
			}
		});
		simNext.setText(model.get("menu-simnext"));
		simDebug.setText(model.get("menu-simdebug-on"));
		simDebug.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ev) {
				if (simDebug.getState()) {
					simDebug.setText(model.get("menu-simdebug-off"));
				} else {
					simDebug.setText(model.get("menu-simdebug-on"));
				}
			}
		});

		beenden.setText(model.get("menu-quit"));
		faq.setText(model.get("menu-faq"));
		ueber.setText(model.get("menu-about"));

		laden.addActionListener(this);
		faq.addActionListener(this);
		beenden.addActionListener(this);

		datei.add(neu);
		datei.add(laden);
		datei.add(speichern);
		simulation.add(simCntrl);
		simulation.add(simNext);
		simulation.add(simDebug);
		datei.add(beenden);
		hilfe.add(faq);
		hilfe.add(ueber);

		add(datei);
		add(simulation);
		add(hilfe);

	}

	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == ueber) {
			JFrame frame = new JFrame();
			UIManager.put("OptionPane.okButtonText", "Fenster schliessen");
			JOptionPane
					.showMessageDialog(
							frame,
							"Entwickelt und erstellt von: Simon Franzen, Sven Hesse, Markus Tacker, Ramona Vehabovic",
							"Verkehrsplaner-Simulation\nVersion 0.1",
							JOptionPane.INFORMATION_MESSAGE);
		}
		if (ev.getSource() == faq) {
			JFrame frame = new JFrame();
			frame.add(new JLabel(
					"<html><b>Hilfe – Hier finden Sie alle Informationen zur Bedienung des Programms.</b><p>"
							+ "<p><br/><b> Was macht dieses Programm?</b>"
							+ "<p>Das Programm simuliert einen Verkehrsplaner."
							+ "<p>Es zeigt an, wie sich Fahrzeuge im Straßenverkehr auf andere Fahrzeuge, Ampeln und Straßenverläufen verhalten."
							+ "<p><br/><b>Wie baue ich eine Simulation?</b>"
							+ "<p>Über DATEI -> NEU lässt sich eine Welt neu anlegen. Anschließend haben Sie die Möglichkeit, die Größe Ihrer Welt frei zu wählen oder eine der vorgegebenen Größen zu nutzen."
							+ "<p><br/><b>Wie kann ich Kacheln auf die Welt legen?</b>"
							+ "<p>Rechts befindet sich die Werkzeugleiste, die Straßenteile, Fahrzeuge und Ampeln beinhaltet. Mit Hilfe von Drag&Drop haben Sie die Möglichkeit Kacheln auf die Welt zu legen."
							+ "<p>Fahrzeuge lassen sich nur auf Straßenkacheln legen, Ampeln wiederum nur auf Kreuzungen und Abzweigungen."
							+ "<p>Während des Anlegens einer Kachel gibt es die Möglichkeit die gewählte Kachel zu drehen. Dazu drücken Sie während dem Halten bitte die TAB-Taste. Dadurch wird Ihre Kachel um 90° im Uhrzeigersinn gedreht. Für erneutes Drehen drücken Sie einfach nochmals die TAB-Taste"
							+ "<p><br/><b>Ich habe eine Kachel falsch angelegt, wie kann ich diese wieder löschen?</b>"
							+ "<p>Unter der Auswahl der Kacheln befindet sich ein Mülleimer. Wählen Sie die zu löschende Kachel und ziehen Sie diese auf den Mülleimer."
							+ "<p><br/><b>Wie starte/beende ich eine Simulation?</b>"
							+ "<p> Unter SIMULATION -> STARTEN/STOPPEN kann die Simulation gestartet/gestoppt werden."
							+ "<p><br/><b>Wie kann ich meine Simulation speichern/laden?</b>"
							+ "<p>Unter DATEI -> SPEICHERN/LADEN, können gebaute Simulationen gespeichert/geladen werden."
							+ "<p><br/><b>Wie kann ich den Vehrkehrsplaner beenden?</b>"
							+ "<p>Entweder durch DATEI -> BEENDEN oder über das Kreuz oben rechts in der Ecke</html>"));
			frame.setSize(800, 800);
			frame.setVisible(true);
		}

		else if (ev.getSource() == beenden) {
			System.exit(0);
		}
	}

}
