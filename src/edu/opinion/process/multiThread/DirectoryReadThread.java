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
	 * 所需解析文件地址
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
	 * 日志读取线程
	 */
	private LogReadthread lrd;
	
	/**
	 * 解析线程
	 */
	private ParserPbserver pp;

	/**
	 * 递归读取爬虫文件夹
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
					lrd.addObserver(pp); // 將解析線程放入讀取線程觀察者中
					new Thread(lrd).start();// 啟動讀取線程
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
