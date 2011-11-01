package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import config.AppConfig;

/**
 * Convertiert SVGs zu PNGs
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: SVGConverter.java 157 2011-01-08 15:32:17Z mtack001 $
 */
public class SVGConverter {

	private static Logger logger = Logger.getLogger(SVGConverter.class);

	/**
	 * Gibt die PNG-Datei zu einer SVG-Datei zurück
	 * Ist die PNG-Datei nicht vorhanden, wird diese erstellt.
	 * Siehe auch {@link SVGConverter#main(String[])}
	 * 
	 * @param svgFile
	 * @return PNG-Datei
	 * @throws SVGConverterException 
	 */
	public static File getPNG(File svgFile) throws SVGConverterException {
		File pngFile = getPngFilename(svgFile);
		if (!pngFile.isFile()) {
			convert(svgFile, pngFile);
		}
		return pngFile;
	}

	/**
	 * Konvertiert die SVG-Datei svgFile zur PNG-Datei pngFile
	 * 
	 * @param svgFile
	 * @param pngFile
	 * @throws SVGConverterException 
	 */
	protected static void convert(File svgFile, File pngFile) throws SVGConverterException {
		logger.debug(String.format("Konvertiere %s nach %s.", svgFile, pngFile));
		// Create a JPEG transcoder
		PNGTranscoder t = new PNGTranscoder();

		// Create the transcoder input.
		TranscoderInput input = new TranscoderInput(svgFile.toURI().toString());

		// Create the transcoder output.
		OutputStream ostream;
		try {
			ostream = new FileOutputStream(pngFile);
		} catch (FileNotFoundException e) {
			throw new SVGConverterException(String.format("Konnte Ziel %s nicht öffnen: %s.", pngFile, e.getMessage()));
		}
		TranscoderOutput output = new TranscoderOutput(ostream);
		
		// Set width
		t.addTranscodingHint(PNGTranscoder.KEY_WIDTH, new Float(200));

		// Save the image.
		try {
			t.transcode(input, output);
		} catch (TranscoderException e) {
			throw new SVGConverterException(String.format("Konnte %s nicht nach %s konvertieren: %s", svgFile, pngFile, e.getMessage()));
		}

		// Flush and close the stream.
		try {
			ostream.flush();
			ostream.close();
		} catch (IOException e) {
			throw new SVGConverterException(String.format("Konnte Stream nicht schließen: %s", e.getMessage()));
		}		
	}

	/**
	 * @param file SVG-Dateiname
	 * @return PNG-Dateiname
	 */
	private static File getPngFilename(File file) {
		return new File(file.getParent(), file.getName().concat(".png"));
	}

	/**
	 * Konvertiert alle SVG-Dateien, auch wenn diese bereits als PNG vorliegen
	 */
	public static void main(String[] argv) {
		// Set up logging
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);

		AppConfig config = new AppConfig();

		for (File svgFile : config.getGfxDir().listFiles(new SVGFilter())) {
			try {
				convert(svgFile, getPngFilename(svgFile));
			} catch (SVGConverterException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
