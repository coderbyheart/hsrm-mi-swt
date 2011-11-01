package util;

/**
 * Exception für {@link SVGConverter}
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: SVGConverterException.java 156 2011-01-08 13:56:59Z mtack001 $
 */
public class SVGConverterException extends Exception {
	public SVGConverterException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 5751667141130248149L;
}
