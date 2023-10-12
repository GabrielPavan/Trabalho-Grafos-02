package infra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import factories.MyThreadFactory;
import factories.RouterFactory;
import others.ConfigParameter;

public class ManageFile {

	private InputStream InputStream;
	private InputStreamReader InputStreamReader;
	private BufferedReader BufferedReader;

	private OutputStream OutputStream;
	private OutputStreamWriter OutputStreamWriter;
	private BufferedWriter BufferedWriter;

	public void BufferFileReader(String FilePath) throws IOException {
		InputStream = new FileInputStream(FilePath);
		InputStreamReader = new InputStreamReader(InputStream);
		BufferedReader = new BufferedReader(InputStreamReader);
	}
	public void BufferFileWriter(String FilePath) throws IOException {
		OutputStream = new FileOutputStream(FilePath);
		OutputStreamWriter = new OutputStreamWriter(OutputStream);
		BufferedWriter = new BufferedWriter(OutputStreamWriter);
	}

	public List<String> GetDataFromFile() throws IOException {
		String ReadLine;
		List<String> DataList = new ArrayList<String>();
		while ((ReadLine = BufferedReader.readLine()) != null) {
			DataList.add(ReadLine);
		}
		return DataList;
	}
	
	public void SetupConfig(String ConfigPath) throws IOException {
		BufferFileReader(ConfigPath);
		List<String> DataList = GetDataFromFile();
		closeFile();
		
		if (DataList.size() < ConfigParameter.NumberOfConfiguredParameters) {
			throw new IOException("O arquivo esta imcompleto ou esta vazio!");
		}
		
		for (int i = 0; i < DataList.size() ; i++) {
			if (!DataList.get(i).contains(ConfigParameter.DefaultSpliter)){
				throw new IOException("O arquivo de configuração esta incorrto!");
			}
			String[] ParameterAndValue = DataList.get(i).split(ConfigParameter.DefaultSpliter);
			switch (ParameterAndValue[ConfigParameter.GetParameter]) {
				case ConfigParameter.Processado:
				case ConfigParameter.NãoProcessado:
					CreateFolder(ParameterAndValue[ConfigParameter.GetValue]);
					break;
				default:
					throw new IOException("Parametro não definido nas configurações!!");
			}
		}
	}
	
	public void SetupRoutes(String RouterFilePath) throws IOException, InterruptedException {
		File Folder = new File(RouterFilePath);
		ThreadFactory threadFactory =  new MyThreadFactory();
		ExecutorService executor = Executors.newFixedThreadPool(4, threadFactory);
		
        if (Folder.exists() && Folder.isDirectory()) {
            File[] Files = Folder.listFiles();
            for (File File : Files) {
                if (File.isFile()) {
                	executor.submit(() -> {
                		new RouterFactory(File);
                	});
                }
            }
            executor.shutdown();
            if(!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            	throw new InterruptedException("O Tempo limete para o processamento das rotas Foi atingido!");
            }
        }
	}
	
	public void CreateFolder(String FolderPath) throws IOException {
		File Folder = new File(FolderPath);
		if (!Folder.exists()) {
			if (!Folder.mkdir()) {
				throw new IOException("Erro ao criar a pasta: " + FolderPath);
			}
		}
	}
	public void MoveFile(String OriginPath, String DestinyPath, CopyOption copyOption) {
		Path Origin = Path.of(OriginPath);
 	   	Path Destiny = Path.of(DestinyPath);
 	   	try {
			Files.move(Origin, Destiny, copyOption);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void closeFile() throws IOException {
		if (BufferedReader != null) BufferedReader.close();
		if (BufferedWriter != null) BufferedWriter.close();
	}
}