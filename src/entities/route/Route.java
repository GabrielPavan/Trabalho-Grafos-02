package entities.route;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private RouteHeader Header;
	private List<RouteConnections> Connections = new ArrayList<>();
	private List<RouteWeights> Weights = new ArrayList<>();
	private RouteTrailer Trailer;
	
	private int sumOfWeights;
	
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
	
	public Integer getTotalNodes() {
		if(Connections.size() == 0)
			return 0;
		
		List<Integer> Nodes = new ArrayList<Integer>();
		
		Connections.forEach(x -> {
			if(!Nodes.contains(x.OriginNode)) {
				Nodes.add(x.OriginNode);
			}
			if(!Nodes.contains(x.DestinyNode)) {
				Nodes.add(x.DestinyNode);
			}
		});
		
		return Nodes.size();
	}
	
	public int getTotalSumOfWeights() {
		if(Weights.size() == 0)
			return 0;
		
		Weights.forEach(x -> {
			sumOfWeights += x.getWeight();
		});
		
		return sumOfWeights;
	}
}
