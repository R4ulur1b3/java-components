/**
 * 
 */
package mx.com.plinntec.thread.service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author user-admin
 *
 */
public interface IThreadPoolMonitorService extends Runnable {

	public void monitorThreadPool();

	public ThreadPoolExecutor getExecutor();

	public void setExecutor(ThreadPoolExecutor executor);

}
