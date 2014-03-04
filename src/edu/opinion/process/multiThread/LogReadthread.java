package edu.opinion.process.multiThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import edu.opinion.model.FileList;
/**
 * 
 * @author hsr
 *
 */
public class LogReadthread extends Observable implements Runnable {
	
	private static  Logger LOGGER = Logger.getLogger(LogReadthread.class);
	
	/**
     * 是否停止线程
     */
	private boolean isCancle = false;
	
	/**
	 * 线程是否在运行
	 */
	private boolean isRunning = false;
	
	/**
	 * 爬虫日志文件file
	 */
	private File file = null;
	
	/**
	 * url、module、文件路径
	 */
	private List<FileList> list = new ArrayList<FileList>();
	
	/**
	 * url模板参数(正则表达式及确定解析模板参数的module)
	 */
	private Map<String, String> rxMap;

	/**
	 * 读取url—pattern.xml文件，将参数放入hashmap中
	 * @param file 爬虫日志文件
	 */
	public LogReadthread(File file) {
		this.file = file;
		rxMap = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		List<Element> list = null;
		File xmlfile = new File("Xmlpattern/url_pattern.xml");
		try {
			doc = saxReader.read(xmlfile);
			list = doc.selectNodes("//item");
			for (Element item : list) {
				String module = item.elementText("module");
				String regex = item.elementTextTrim("regex");
				rxMap.put(regex, module);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
/**
 * 
 * @return isRunning
 */
	public boolean isRunning() {
		return isRunning;
	}
	
/**
 * 停止监视线程
 */
	public void cancel() {
		isCancle = true;
	}
	
	@Override
	public void run() {

		isCancle = false;
		if (isCancle) {
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {

				String[] filed = line.split(";");
				for (Entry<String, String> entry : rxMap.entrySet()) {
					Matcher matcher = Pattern.compile(entry.getKey()).matcher(
							filed[1]);
					if (matcher.find()) {
						//LOGGER.info("網址"+filed[1]+"可以被解析");
						FileList fileList = new FileList();
						fileList.setFilePath(file.getParent() +"\\"+ filed[0]);
						fileList.setModule(entry.getValue());
						fileList.setUrl(filed[1]);
						fileList.setCharset(filed[2].replaceAll("charset=", ""));
						list.add(fileList);
					}
				}
			}
			setChangement(list);        //通知ParserThread，该日志读取线程已结束
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	/**
	 * 通知观察者已更新信息
	 * @param list
	 */
	private void setChangement(List<FileList> list) {
		setChanged();
		notifyObservers(list);             //将模板参数推给ParserThread
	}


}
