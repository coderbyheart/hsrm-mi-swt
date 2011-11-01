package data.datasource.xml;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import data.dataobject.TextData;

/**
 * XML-Quelle zur Internationalisierung
 * 
 * @version $Id: LanguageXMLSource.java 214 2011-01-16 11:46:29Z mtack001 $
 * @author Markus Tacker <m@tacker.org>
 */
public class LanguageXMLSource extends XMLSource
{
	ArrayList<TextData> texts;
	private static Logger logger = Logger.getLogger(LanguageXMLSource.class);

	public LanguageXMLSource(File xmlDir) {
		super(xmlDir);
	}

	/**
	 * @see XMLSource#getXmlSchema()
	 */
	@Override
	protected File getXmlSchema() {
		return new File(xmlFile.getParent(), "xliff-core-1.2-strict.xsd");
	}

	/**
	 * Gibt die Texte aus dem XML-File zurück
	 */
	public ArrayList<TextData> getTexts() {
		if (texts == null) {
			logger.debug(String.format("Lade Texte aus %s", xmlFile
					.getAbsolutePath()));

			texts = new ArrayList<TextData>();
			// Infos zur Übersetzung
			Element file = (Element) xmlRoot.getChildNodes().item(0);
			String targetLanguage = file.getAttribute("target-language");
			String sourceLanguage = file.getAttribute("source-language");
			// Texte laden
			NodeList transUnits = xmlRoot.getElementsByTagName("trans-unit");
			for (int i = 0; i < transUnits.getLength(); i++) {
				Element transUnit = (Element) transUnits.item(i);
				TextData textData = new TextData();
				TextData textOriginal = new TextData();
				textData.setOriginal(textOriginal);				
				// ID
				textData.setId(transUnit.getAttribute("id"));
				textOriginal.setId(transUnit.getAttribute("id"));
				// Group
				textData.setGroup(((Element) transUnit.getParentNode()).getAttribute("id"));
				textOriginal.setGroup(((Element) transUnit.getParentNode()).getAttribute("id"));
				// Sprache
				textData.setLanguage(targetLanguage);
				textOriginal.setLanguage(sourceLanguage);
				// Texte
				NodeList transTexts = transUnit.getChildNodes();
				for (int j = 0; j < transTexts.getLength(); j++) {
					Element transText = (Element) transTexts.item(j);
					if (transText.getNodeName().equals("target")) {
						textData.setText(transText.getTextContent());
					} else if (transText.getNodeName().equals("source")) {
						textOriginal.setText(transText.getTextContent());
					}
				}
				logger.debug(textData);
				texts.add(textData);
			}
			logger.debug(String.format("%d Texte geladen.", texts.size()));
		}
		return texts;
	}
}