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
     * �Ƿ�ֹͣ�߳�
     */
	private boolean isCancle = false;
	
	/**
	 * �߳��Ƿ�������
	 */
	private boolean isRunning = false;
	
	/**
	 * ������־�ļ�file
	 */
	private File file = null;
	
	/**
	 * url��module���ļ�·��
	 */
	private List<FileList> list = new ArrayList<FileList>();
	
	/**
	 * urlģ�����(������ʽ��ȷ������ģ�������module)
	 */
	private Map<String, String> rxMap;

	/**
	 * ��ȡurl��pattern.xml�ļ�������������hashmap��
	 * @param file ������־�ļ�
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
 * ֹͣ�����߳�
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
						//LOGGER.info("�Wַ"+filed[1]+"���Ա�����");
						FileList fileList = new FileList();
						fileList.setFilePath(file.getParent() +"\\"+ filed[0]);
						fileList.setModule(entry.getValue());
						fileList.setUrl(filed[1]);
						fileList.setCharset(filed[2].replaceAll("charset=", ""));
						list.add(fileList);
					}
				}
			}
			setChangement(list);        //֪ͨParserThread������־��ȡ�߳��ѽ���
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	/**
	 * ֪ͨ�۲����Ѹ�����Ϣ
	 * @param list
	 */
	private void setChangement(List<FileList> list) {
		setChanged();
		notifyObservers(list);             //��ģ������Ƹ�ParserThread
	}


}
