/**
 * 
 */
package mx.com.plinntec.thread.service.impl;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.com.plinntec.thread.service.IThreadPoolMonitorService;

/**
 * @author user-admin
 *
 */
public class ThreadPoolMonitorService implements IThreadPoolMonitorService {

	private static Logger LOG = LoggerFactory.getLogger(ThreadPoolMonitorService.class);

	ThreadPoolExecutor executor;
	private long monitoringPeriod;

	public void run() {
		try {
			while (true) {
				monitorThreadPool();
				Thread.sleep(monitoringPeriod * 1000);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	public void monitorThreadPool() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("CurrentPoolSize : ").append(executor.getPoolSize());
		strBuff.append(" - CorePoolSize : ").append(executor.getCorePoolSize());
		strBuff.append(" - MaximumPoolSize : ").append(executor.getMaximumPoolSize());
		strBuff.append(" - ActiveTaskCount : ").append(executor.getActiveCount());
		strBuff.append(" - CompletedTaskCount : ").append(executor.getCompletedTaskCount());
		strBuff.append(" - TotalTaskCount : ").append(executor.getTaskCount());
		strBuff.append(" - isTerminated : ").append(executor.isTerminated());

		LOG.debug(strBuff.toString());
	}

	public ThreadPoolExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(ThreadPoolExecutor executor) {
		this.executor = executor;
	}

	public long getMonitoringPeriod() {
		return monitoringPeriod;
	}

	public void setMonitoringPeriod(long monitoringPeriod) {
		this.monitoringPeriod = monitoringPeriod;
	}
}
