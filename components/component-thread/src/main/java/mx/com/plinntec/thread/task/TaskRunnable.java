/**
 * 
 */
package mx.com.plinntec.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author {user}
 *
 */
public class TaskRunnable implements Runnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskRunnable.class);
	
	private String taskName;
	
	
	/**
	 * 
	 */
	public TaskRunnable() {
		super();
	}
	
	/**
	 * @param taskName
	 */
	public TaskRunnable(String taskName) {
		super();
		this.taskName = taskName;
	}



	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			LOG.debug(this.taskName + ": is started.");
			Thread.sleep(10000);
			LOG.debug(this.taskName + ": is completed.");
		} catch (Exception e) {
			LOG.error(this.taskName + ": is not completed!");
			e.printStackTrace();
		}

	}

}
