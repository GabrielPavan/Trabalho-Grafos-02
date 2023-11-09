package factories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.grafo.Arrest;
import entities.grafo.Grafo;
import entities.grafo.Node;
import entities.route.Route;
import entities.route.RouteConnections;
import entities.route.RouteWeights;

public class GrafoFactory {
	
	private Route route;
	
	public GrafoFactory(Route pRoute) {
		this.route = pRoute;
	}
	
	public Grafo getGrafo() {
		Grafo grafo = new Grafo();
		
		Set<Node> Nodes = new HashSet<Node>();
		for (RouteConnections routeConn : route.getConnections()) {
		    Nodes.add(new Node(routeConn.getOriginNode(), Integer.MAX_VALUE));
		    Nodes.add(new Node(routeConn.getDestinyNode(), Integer.MAX_VALUE));
		}
		grafo.setNodes(Nodes);
		
		List<Arrest> Arrests = new ArrayList<Arrest>();
		for(RouteWeights routeWeight : route.getWeights()) {
			Arrests.add(new Arrest(routeWeight.getOriginNode(), routeWeight.getDestinyNode(), routeWeight.getWeight()));
		}
		grafo.setArrests(Arrests);
		
		return grafo;
	}
	
}
