/**
 * 
 */
package mx.com.plinntec.thread.handler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author user-admin
 *
 */
public class TestRejectedExecutionHandler implements RejectedExecutionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestRejectedExecutionHandler.class);
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
	 */
	@Override
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
		LOG.debug(runnable.toString() + " : has been rejected");
	}

}
