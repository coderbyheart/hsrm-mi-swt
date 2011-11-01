package data.datasource.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Basisklasse aller XML-Quellen
 */
public abstract class XMLSource {
	protected File xmlFile;
	protected Document xmlDoc;
	protected Element xmlRoot;
	private static Logger logger = Logger.getLogger(XMLSource.class);

	/**
	 * Leerer Konstruktor
	 */
	public XMLSource() {
	}

	/**
	 * Konstruktor, der eine XML-Datei übergeben bekommt
	 * 
	 * @param xmlFile
	 */
	public XMLSource(File xmlFile) {
		this.setXmlFile(xmlFile);
	}

	/**
	 * Parst ein XML-File
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @return Document
	 * @see <a
	 *      href="http://crumpling-rumblings.blogspot.com/2008/05/java-how-to-make-getelementbyid-work.html">Crumpling
	 *      Rumblings: Java: How to make getElementById() work using xml
	 *      schema</a>
	 */
	protected Document parseDocument(File xmlFile)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory domBuilderFactory = DocumentBuilderFactory
				.newInstance();
		domBuilderFactory.setIgnoringElementContentWhitespace(true);
		domBuilderFactory.setIgnoringComments(true);
		domBuilderFactory.setNamespaceAware(true);
		domBuilderFactory.setValidating(true);
		domBuilderFactory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");
		domBuilderFactory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaSource",
				getXmlSchema());

		logger.debug(String.format("XML: %s", xmlFile.getAbsoluteFile()));
		logger.debug(String.format("XML-Schema: %s", getXmlSchema()
				.getAbsoluteFile()));

		DocumentBuilder domBuilder = domBuilderFactory.newDocumentBuilder();
		Document xmlDom = domBuilder.parse(xmlFile);
		removeWhitespaceNodes(xmlDom.getDocumentElement());
		return xmlDom;
	}

	protected Document newDocument(File xsdFile) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory domBuilderFactory = DocumentBuilderFactory
				.newInstance();
		domBuilderFactory.setIgnoringElementContentWhitespace(true);
		domBuilderFactory.setIgnoringComments(true);
		domBuilderFactory.setNamespaceAware(true);
		domBuilderFactory.setValidating(true);
		domBuilderFactory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");
		domBuilderFactory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaSource",
				xsdFile);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		return doc;
	}

	/**
	 * Gibt das XML-Schema für diese Quelle zurück
	 * 
	 * @return XML-Schema
	 */
	protected abstract File getXmlSchema();

	/**
	 * Entfernt whitespace aus dem Document
	 * 
	 * @see <a href="http://forums.java.net/node/667186?#comment-684626">Java 6
	 *      parsers not ignoring whitespace | Java.net</a>
	 * @param e
	 */
	private static void removeWhitespaceNodes(Element e) {
		NodeList children = e.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--) {
			Node child = children.item(i);
			if (child instanceof Text
					&& ((Text) child).getData().trim().length() == 0) {
				e.removeChild(child);
			} else if (child instanceof Element) {
				removeWhitespaceNodes((Element) child);
			}
		}
	}

	/**
	 * @param xmlFile
	 *            the xmlFile to set
	 */
	public void setXmlFile(File xmlFile) {
		this.xmlFile = xmlFile;
		// Dokument öffnen
		try {
			xmlDoc = parseDocument(xmlFile);

		} catch (ParserConfigurationException e) {
			logger.error("Parser-Error", e);
		} catch (SAXException e) {
			logger.error("SAX-Error", e);
		} catch (IOException e) {
			logger.error("IO-Error", e);
		}

		xmlRoot = xmlDoc.getDocumentElement();
	}

}