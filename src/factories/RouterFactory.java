package factories;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import infra.ManageFile;
import others.FilesPath;

public class RouterFactory {
	private List<String> Data = new ArrayList<String>();

	public RouterFactory(File pRouterFile) {
		ReadFileRoute(pRouterFile);
	}

	private void ReadFileRoute(File pRouterFile) {
		ManageFile _ManageFile = new ManageFile();

		try {
			_ManageFile.BufferFileReader(pRouterFile.getPath());
			Data = _ManageFile.GetDataFromFile();
			_ManageFile.closeFile();
			
			
		} catch (IOException e1) {
			_ManageFile.MoveFile(pRouterFile.getPath(), FilesPath.DefaultPathForUnProcessedFiles + pRouterFile.getName(), StandardCopyOption.REPLACE_EXISTING);
		} 
	}
}