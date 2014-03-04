/**
 * 
 */
package edu.opinion.process.main;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import edu.opinion.process.multiThread.DirectoryReadThread;

/**
 * @author hsr
 * 
 */
public class PoolMain {

	/**
	 * @param args
	 */
	
	private final static String path = "E:/crawlcontent";
	public static void main(String[] args) {
		PoolMain.PoolStart();

	}

	private  static void PoolStart() {
		ThreadPoolExecutor TPE = new ThreadPoolExecutor(3, 10, 3,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
				new ThreadPoolExecutor.DiscardOldestPolicy());
		File file  = new File(path);
		File [] files = file.listFiles();
		for (File file2 : files) {
			TPE.execute(new DirectoryReadThread(file2.getAbsolutePath()));
		}
		TPE.shutdown();
	}
}
