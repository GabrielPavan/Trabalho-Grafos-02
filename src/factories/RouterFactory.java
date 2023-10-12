package factories;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import entities.route.Route;
import entities.route.RouteConnections;
import entities.route.RouteHeader;
import entities.route.RouteTrailer;
import entities.route.RouteWeights;
import infra.ManageFile;
import others.FilesPath;
import others.RouteParameter;

public class RouterFactory {
	private List<String> Data = new ArrayList<String>();
	private ManageFile _ManageFile = new ManageFile();

	Route _Route = new Route();

	public Route getRoute() {
		return _Route;
	}

	public RouterFactory(File pRouterFile) {
		try {
			ReadFileRoute(pRouterFile);
			CreateRoute(Data);
			
		} catch (Exception e) {
			_Route = null;
			_ManageFile.MoveFile(pRouterFile, FilesPath.DefaultPathForUnProcessedFiles,
					StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Falha na criação da Rota: " + pRouterFile.getName());
			System.out.println(e.getMessage());
		}
	}

	private void ReadFileRoute(File pRouterFile) throws Exception {
		_ManageFile.BufferFileReader(pRouterFile.getPath());
		Data = _ManageFile.GetDataFromFile();
		_ManageFile.closeFile();
	}

	private void CreateRoute(List<String> pData) throws Exception {
		for (int i = 0; i < pData.size(); i++) {
			String Row = pData.get(i);
			if(RouteParameter.TrailerPattern.matcher(Row).matches()) {
				_Route.setTrailer(CreateTrailer(Row));
			}
		}
		if (_Route.getTrailer() == null) {
			throw new Exception("Trailer inválido");
		}
		for (int i = 0; i < pData.size(); i++) {
			String Row = pData.get(i);
			if(RouteParameter.ConnectionPattern.matcher(Row).matches()) {
				_Route.getConnections().add(CreateConnection(Row));
			}
			if(RouteParameter.WeightPattern.matcher(Row).matches()) {
				_Route.getWeights().add(CreateRouteWeights(Row));
			}
		}
		if (_Route.getConnections().size() != _Route.getTrailer().getNumberOfConnectionsLines()) {
			throw new Exception("Resumo de conexões inválido.");
		}
		if (_Route.getWeights().size() != _Route.getTrailer().getNumberOfWeightLines()) {
			throw new Exception("Número de linhas diferentes");
		}
		for (int i = 0; i < pData.size(); i++) {
			String Row = pData.get(i);
			if(RouteParameter.HeaderPattern.matcher(Row).matches()) {
				_Route.setHeader(CreateHeader(Row));
			}
		}
		if (_Route.getHeader() == null) {
			throw new Exception("Header inválido");
		}
	}

	private RouteHeader CreateHeader(String HeaderRow) throws Exception {
		// 00NNSP

		Integer NumberOfNodes = Integer.parseInt(HeaderRow.substring(0, 4));
		if (NumberOfNodes == 0)
			throw new Exception("Falha! -> Número total de nós inválido.");
		if (NumberOfNodes != _Route.getTotalNodes()) {
			String NewHeader = "0";
			NewHeader = NewHeader.concat(HeaderRow);
			return CreateHeader(NewHeader);
		}
		Integer SumOfEdgeWeights = Integer.parseInt(HeaderRow.substring(4));
		if (SumOfEdgeWeights != _Route.getTrailer().getSum0fWeightsBetweenAllNodes()) {
			throw new Exception(
					"Falha! -> Soma dos pesos difere (Valor do Registro HEADER = NN e Soma dos Pesos = NN)");
		}

		return new RouteHeader(NumberOfNodes, SumOfEdgeWeights);
	}

	private RouteConnections CreateConnection(String ConnectionRow) {
		// 01NO=ND

		String[] Data = ConnectionRow.split(RouteParameter.DefaultValueSpliter);

		Integer OriginNode = Integer.parseInt(Data[0].substring(2));
		Integer DestinyNode = Integer.parseInt(Data[1]);

		return new RouteConnections(OriginNode, DestinyNode);
	}

	private RouteWeights CreateRouteWeights(String WeightRow) {
		// 02NO;ND=P
		// [0] [1]
		String[] Data = WeightRow.split(RouteParameter.DefaultDataSlipter);

		Integer OriginNode = Integer.parseInt(Data[0].substring(2));
		Integer DestinyNode = Integer.parseInt(Data[1].split(RouteParameter.DefaultValueSpliter)[0]);
		Integer Weight = Integer.parseInt(Data[1].split(RouteParameter.DefaultValueSpliter)[1]);

		return new RouteWeights(OriginNode, DestinyNode, Weight);
	}

	private RouteTrailer CreateTrailer(String TrailerRow) {
		// 09RC=NN;RP=NN;NNNN
		// [0] [1] [2]
		String[] Data = TrailerRow.split(RouteParameter.DefaultDataSlipter);

		Integer NumberOfConnectionsLines = Integer.parseInt(Data[0].split(RouteParameter.DefaultValueSpliter)[1]);
		Integer NumberOfWeightLines = Integer.parseInt(Data[1].split(RouteParameter.DefaultValueSpliter)[1]);
		Integer Sum0fWeightsBetweenAllNodes = Integer.parseInt(Data[2]);

		return new RouteTrailer(NumberOfConnectionsLines, NumberOfWeightLines, Sum0fWeightsBetweenAllNodes);
	}
}
