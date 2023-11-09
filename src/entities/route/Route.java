package entities.route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Route {
	
	private RouteHeader Header;
	private List<RouteConnections> Connections = new ArrayList<>();
	private List<RouteWeights> Weights = new ArrayList<>();
	private RouteTrailer Trailer;
	
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
		if(Connections.size() == 0)
			return 0;
		
		Set<Integer> connIDs = new HashSet<Integer>();
		
		for (RouteConnections conn : Connections) {
			connIDs.add(conn.DestinyNode);
			connIDs.add(conn.OriginNode);
		}
		return connIDs.size();
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