/**
 * 
 */
package edu.opinion.process.multiThread;

import java.io.File;

import edu.opinion.model.ParserPbserver;



/**
 * @author hsr
 * 
 */
public class DirectoryReadThread implements Runnable {

	/**
	 * ��������ļ���ַ
	 */
	private String path;

	/**
	 * 
	 * @param path
	 */
	public DirectoryReadThread(String path) {
		this.path = path;
	}

	/**
	 * ��־��ȡ�߳�
	 */
	private LogReadthread lrd;
	
	/**
	 * �����߳�
	 */
	private ParserPbserver pp;

	/**
	 * �ݹ��ȡ�����ļ���
	 * 
	 * @param path
	 */
	private void fileRecursion(String path) {

		File file = new File(path);
		File[] childFiles = file.listFiles();
		for (File file2 : childFiles) {
			if (file2.isDirectory()) {
				fileRecursion(file2.getAbsolutePath());
			} else {
				if (file2.getName().matches("log.txt")) {
					lrd = new LogReadthread(new File(file2.getAbsolutePath()));
					pp = new ParserPbserver();
					lrd.addObserver(pp); // ���������̷����xȡ�����^������
					new Thread(lrd).start();// �����xȡ����
				}
			}
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			fileRecursion(path);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
