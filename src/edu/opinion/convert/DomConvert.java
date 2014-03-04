/**
 * 
 */
package edu.opinion.convert;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * @author hsr
 * 
 */
public class DomConvert {

	public static Document ToDOM(String path, String charset)
			throws Exception {
		DOMParser parser = new DOMParser();
		parser.setFeature("http://xml.org/sax/features/namespaces", false);
		Document dom = null;

		if (path != null && !path.trim().equals("")) {

			FileInputStream input = new FileInputStream(path);
			InputStreamReader fr = new InputStreamReader(input, charset);
			InputSource is = new InputSource(fr);
			parser.parse(is);
			fr.close();
			dom = parser.getDocument();
			if (dom != null)
				dom.normalize();
			return dom;
		} else {
			return null;
		}

	}

}
