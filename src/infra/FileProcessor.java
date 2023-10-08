package infra;

import java.io.File;

public class FileProcessor implements Runnable {
	private final File File;
	
	public FileProcessor(File File) {
		this.File = File;
	}
	
	@Override
	public void run() {
        System.out.println("Thread terminou de processar arquivo: " + File.getName());
	}
}