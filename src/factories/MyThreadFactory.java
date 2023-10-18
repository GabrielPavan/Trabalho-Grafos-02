package factories;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory{
	public static final int MaxNumberOfThreads = 4;
	
	@Override
	public Thread newThread(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.setPriority(Thread.NORM_PRIORITY);
		return thread;
	}
}