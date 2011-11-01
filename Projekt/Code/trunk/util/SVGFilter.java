package util;

import java.io.File;
import java.io.FilenameFilter;

class SVGFilter implements FilenameFilter {
	public SVGFilter() {
		super();
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".svg");
	}

}
