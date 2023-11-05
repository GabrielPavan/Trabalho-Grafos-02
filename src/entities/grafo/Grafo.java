package entities.grafo;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.route.Route;

public class Grafo {

	private Route route;
	private List<Node> nodes;
	
	public Grafo(Route pRoute) {
		route = pRoute;
		nodes = pRoute.getNodes();
	}

	public List<Integer> caminhoMinimo(Node noOrigem, Node noDestino){
		
		Set<Node> nodesNotVisited = new HashSet<Node>();
		
		nodes.forEach(node -> {
			if(!node.equals(noOrigem)) {
				node.setCost(Integer.MAX_VALUE);
			}
			node.setAncestor(null);
			nodesNotVisited.add(node);
		});
		
		while(!nodesNotVisited.isEmpty()) {
			Node ClosestNode = getClosestNode(nodesNotVisited);
	
			nodesNotVisited.remove(ClosestNode);
			
			
		}
		
		return Collections.emptyList();
	}
	
	private Node getClosestNode(Set<Node> nodeNotVisited) {
		Node ClosestNode = null;
		for (Node node : nodeNotVisited) {
			if(node.getCost() < Integer.MAX_VALUE) {
				ClosestNode = node;
			}
		}
		return ClosestNode;
	}
}