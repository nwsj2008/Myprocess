/**
 * 
 */
package edu.opinion.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.apache.log4j.Logger;
import org.dom4j.io.DOMReader;
import org.w3c.dom.Document;

import edu.opinion.convert.DomConvert;
import edu.opinion.process.multiThread.BbsParserThread;

/**
 * @author hsr
 * 
 */
public class ParserPbserver implements Observer {


	private List<FileList> flist;
	private String path;
	private String url ;
	private String charset ;
	private String module;
	private static Logger logger = Logger.getLogger(ParserPbserver.class);

	@Override
	public void update(Observable o, Object arg) {

		if (arg == null) {
			return;
		}
		flist = (List<FileList>) arg;
		for (FileList fileList : flist) {
			try {
				path = fileList.getFilePath();
				url = fileList.getUrl();
				charset = fileList.getCharset();
				module = fileList.getModule();
				

				Document dom = DomConvert.ToDOM(path, charset);
				DOMReader drd = new DOMReader();
				org.dom4j.Document doc = drd.read(dom);
				
				if (module.startsWith("bbs")) {
					new Thread(new BbsParserThread(doc,url,module)).start();
				}
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	

}
