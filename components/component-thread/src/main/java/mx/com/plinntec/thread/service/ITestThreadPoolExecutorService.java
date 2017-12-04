package mx.com.plinntec.thread.service;

import java.util.concurrent.ThreadPoolExecutor;

import mx.com.plinntec.thread.handler.TestRejectedExecutionHandler;

public interface ITestThreadPoolExecutorService {

	public ThreadPoolExecutor createNewThreadPool();

	public int getCorePoolSize();

	public void setCorePoolSize(int corePoolSize);

	public int getMaxPoolSize();

	public void setMaxPoolSize(int maximumPoolSize);

	public long getKeepAliveTime();

	public void setKeepAliveTime(long keepAliveTime);

	public int getQueueCapacity();

	public void setQueueCapacity(int queueCapacity);

	public TestRejectedExecutionHandler getTestRejectedExecutionHandler();

	public void setTestRejectedExecutionHandler(TestRejectedExecutionHandler testRejectedExecutionHandler);

}
