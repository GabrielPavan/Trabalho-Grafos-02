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
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import entities.route.Route;
import factories.RouterFactory;
import others.ConfigParameter;
import others.FilesPath;

public class ManageFile {

	private InputStream InputStream;
	private InputStreamReader InputStreamReader;
	private BufferedReader BufferedReader;

	private OutputStream OutputStream;
	private OutputStreamWriter OutputStreamWriter;
	private BufferedWriter BufferedWriter;
	
	private ScheduledExecutorService executor;
	private ThreadPoolExecutor fileExecutor;

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

		for (int i = 0; i < DataList.size(); i++) {
			if (!DataList.get(i).contains(ConfigParameter.DefaultSpliter)) {
				throw new IOException("O arquivo de configuração esta incorreto!");
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

	public void MonitorRoutes(String RouterFilePath) throws IOException, InterruptedException {
		File Folder = new File(RouterFilePath);
		List<Route> Routes = new ArrayList<Route>();

		executor = Executors.newScheduledThreadPool(1);
		fileExecutor = new ThreadPoolExecutor(0,2,1000,TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(15));
		
		if (Folder.exists() && Folder.isDirectory()) {
			executor.scheduleAtFixedRate(() -> {
				File[] Files = Folder.listFiles(file -> file.isFile());
				for (File file : Files) {
					if (FilesPath.DefaultRouterFileName.matcher(file.getName()).matches()) {
						fileExecutor.submit(() -> {
							Route route = new RouterFactory(file).getRoute();
							if (route != null) {
								Routes.add(route);
								MoveFile(file, FilesPath.DefaultPathForProcessedFiles,
										StandardCopyOption.REPLACE_EXISTING);
							}
						});
					} else {
						MoveFile(file, FilesPath.DefaultPathForUnProcessedFiles, StandardCopyOption.REPLACE_EXISTING);
					}
				}
			}, 0, 5000, TimeUnit.MILLISECONDS);
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
	public void MoveFile(File file, String DestinyPath, CopyOption copyOption) {
		Path Origin = Path.of(file.getPath());
		Path Destiny = Path.of(DestinyPath + file.getName());
		try {
			Files.move(Origin, Destiny, copyOption);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void closeFile() throws IOException {
		if (BufferedReader != null)
			BufferedReader.close();
		if (BufferedWriter != null)
			BufferedWriter.close();
	}
}