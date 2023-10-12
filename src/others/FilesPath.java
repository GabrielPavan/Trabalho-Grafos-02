package others;

import java.util.regex.Pattern;

public class FilesPath {
	public static final String DefaultConfigFilePath = "C:\\Teste\\Configuracao\\config.txt";
	public static final String DefaultRoutesFilesPath = "C:\\Teste\\";
	
	public static final String DefaultPathForUnProcessedFiles = "C:\\Teste\\Não Processado\\";
	public static final String DefaultPathForProcessedFiles = "C:\\Teste\\Processado\\";
	
	public static final Pattern DefaultRouterFileName = Pattern.compile("rota\\d{2}\\.txt");
}