/**
 * 
 */
package mx.com.plinntec.thread.start;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.plinntec.thread.service.ITestThreadPoolExecutorService;
import mx.com.plinntec.thread.service.IThreadPoolMonitorService;
import mx.com.plinntec.thread.task.TaskRunnable;

/**
 * @author user-admin
 *
 */

public class Starter {

	  private static Logger LOG = LoggerFactory.getLogger(Starter.class);
	  
	    IThreadPoolMonitorService threadPoolMonitorService;
	    ITestThreadPoolExecutorService testThreadPoolExecutorService;
	 
	    public void start() {
	 
	        // A new thread pool is created...
	        ThreadPoolExecutor executor = testThreadPoolExecutorService.createNewThreadPool();
	        executor.allowCoreThreadTimeOut(true);
	 
	        // Created executor is set to ThreadPoolMonitorService...
	        threadPoolMonitorService.setExecutor(executor);
	 
	        // ThreadPoolMonitorService is started...
	        Thread monitor = new Thread(threadPoolMonitorService);
	        monitor.start();
	 
	        // New tasks are executed...
	       executor.execute(new TaskRunnable("Task"));
	 
	        // executor is shutdown...
	        executor.shutdown();
	    }   
	 
	    public IThreadPoolMonitorService getThreadPoolMonitorService() {
	        return threadPoolMonitorService;
	    }
	 
	    public void setThreadPoolMonitorService(IThreadPoolMonitorService threadPoolMonitorService) {
	        this.threadPoolMonitorService = threadPoolMonitorService;
	    }
	 
	    public ITestThreadPoolExecutorService getTestThreadPoolExecutorService() {
	        return testThreadPoolExecutorService;
	    }
	 
	    public void setTestThreadPoolExecutorService(ITestThreadPoolExecutorService testThreadPoolExecutorService) {
	        this.testThreadPoolExecutorService = testThreadPoolExecutorService;
	    }
}
