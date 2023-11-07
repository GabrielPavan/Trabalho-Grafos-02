package entities.route;

import java.util.ArrayList;
import java.util.List;
import entities.grafo.Node;

public class Route {
	private RouteHeader Header;
	private List<RouteConnections> Connections = new ArrayList<>();
	private List<RouteWeights> Weights = new ArrayList<>();
	private RouteTrailer Trailer;
	
	private List<Node> Nodes = new ArrayList<Node>();
	
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
	public List<Node> getNodes() {
		return Nodes;
	}
	public void setNodes(List<Node> nodes) {
		Nodes = nodes;
	}
	public RouteTrailer getTrailer() {
		return Trailer;
	}
	public void setTrailer(RouteTrailer trailer) {
		Trailer = trailer;
	}

	public int getTotalNodes() {
		if(Connections.size() == 0)
			return 0;
		
		Connections.forEach(x -> {
			Node OriginNode = new Node(x.OriginNode, 0);
			Node DestinyNode = new Node(x.DestinyNode, 0);
			
			if(!Nodes.contains(OriginNode)) {
				Nodes.add(OriginNode);
			}
			if(!Nodes.contains(DestinyNode)) {
				Nodes.add(DestinyNode);
			}
		});
		
		return Nodes.size();
	}
	
	public int getTotalSumOfWeights() {
		if(Weights.size() == 0)
			return 0;
		
		int sumOfWeights = 0;
		for (RouteWeights Weight : Weights) {
			sumOfWeights += Weight.getWeight();
		}
		
		return sumOfWeights;
	}
}