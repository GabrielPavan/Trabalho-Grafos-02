package entities.route;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private RouteHeader Header;
	private List<RouteConnections> Connections = new ArrayList<>();
	private List<RouteWeights> Weights = new ArrayList<>();
	private RouteTrailer Trailer;
	
	private int NumberOfNodes;
	
	public RouteHeader getHeader() {
		return Header;
	}
	public void setHeader(RouteHeader header) {
		Header = header;
	}
	public List<RouteConnections> getConnections() {
		return Connections;
	}
	public void setConnections(List<RouteConnections> connections) {
		Connections = connections;
	}
	public List<RouteWeights> getWeights() {
		return Weights;
	}
	public void setWeights(List<RouteWeights> weights) {
		Weights = weights;
	}
	public RouteTrailer getTrailer() {
		return Trailer;
	}
	public void setTrailer(RouteTrailer trailer) {
		Trailer = trailer;
	}
	
	public int getTotalNodes() {
		if(Connections.size() == 0) {
			return 0;
		}
		
		NumberOfNodes = 1;
		int ValueOfFirstNode = Connections.get(0).OriginNode;
		Connections.forEach(x -> {
			if(ValueOfFirstNode != x.OriginNode) {
				NumberOfNodes++;
			}
		});
		
		return NumberOfNodes;
	}
}
