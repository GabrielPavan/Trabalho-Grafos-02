package entities.grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Grafo {

	private Set<Node> nodes;
	private List<Arrest> arrests;
	
	public Set<Node> getNodes() {
		return nodes;
	}
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Arrest> getArrests() {
		return arrests;
	}
	public void setArrests(List<Arrest> arrests) {
		this.arrests = arrests;
	}

	private Set<Node> nodesNotVisited;

	public List<Node> caminhoMinimo(Node noOrigin, Node noDestiny) {

		nodesNotVisited = new HashSet<Node>();
		
		for (Node node : nodes) {
			if (!node.equals(noOrigin)) {
				node.setCost(Integer.MAX_VALUE);
			} else {
				node.setCost(0);
			}
			node.setAncestorNode(null);
			nodesNotVisited.add(node);
		}

		while (!nodesNotVisited.isEmpty()) {
			Node ClosestNode = getClosestNode(nodesNotVisited);
			
			nodesNotVisited.remove(ClosestNode);
			
			if (ClosestNode == null) {
				break;
			} else if(ClosestNode.equals(noDestiny)) {
				return criandoMenorCaminho(noOrigin, noDestiny);
			}
			ClosestNode.setNeighborNodes(getNeighbors(ClosestNode));
			UpdateNodeList(ClosestNode);
		}
		return criandoMenorCaminho(noOrigin, noDestiny);
	}

	private Node getClosestNode(Set<Node> nodeNotVisited) {
		int ClosestNodeCost = Integer.MAX_VALUE;
		Node ClosestNode = null;
		for (Node node : nodeNotVisited) {
			if (node.getCost() < ClosestNodeCost) {
				ClosestNodeCost = node.getCost();
				ClosestNode = node;
			}
		}
		return ClosestNode;
	}
	
	private List<Node> getNeighbors(Node pNode) {
		List<Node> NeighborsNodes = arrests.stream()
											.filter(arrest -> pNode.getNodeName() == arrest.getOriginNode())
											.map(arrest -> new Node(arrest.getDestinyNode(), Integer.MAX_VALUE))
											.collect(Collectors.toList());

		for (Node node : NeighborsNodes) {
			arrests.stream()
						.filter(weight -> (pNode.getNodeName() == weight.getOriginNode()))
						.forEach(weight -> {
							if(node.getNodeName() == weight.getDestinyNode()) {
								node.setCost(pNode.getCost() + weight.getWeight());
								node.setAncestorNode(pNode);
							}
						});
		}
		return NeighborsNodes;
	}
	
	private void UpdateNodeList(Node closestNode) {
		for(Node Node : closestNode.getNeighborNodes()) {
			for(Node node2 : nodes) {
				if (Node.equals(node2)) {
					 if(Node.getCost() <= node2.getCost()) {
						 node2.setCost(Node.getCost());
						 node2.setAncestorNode(closestNode);
					 }
		         }
			}
		}
	}
	
	private List<Node> criandoMenorCaminho(Node noOrigin, Node noDestiny){
		List<Node> MenorCaminho = new ArrayList<Node>();
		
		for (Node node : nodes) {
			if(node.equals(noDestiny)) {
				Node antecessor = node;
				while(antecessor.getAncestorNode() != null) {
					MenorCaminho.add(antecessor);
					antecessor = antecessor.getAncestorNode();
				}
				if(MenorCaminho.size() > 0) {
					MenorCaminho.add(noOrigin);
				}
			}
		}
		Collections.reverse(MenorCaminho);
		return MenorCaminho;
	}
}