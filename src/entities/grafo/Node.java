package entities.grafo;

import java.util.List;

public class Node {
	private int nodeName;
	private int cost;
	private Node ancestorNode;
	private List<Node> NeighborNodes;
	private List<Node> NoNeighborNodes;
	
	public Node(int node, int cost) {
		this.nodeName = node;
		this.cost = cost;
	}
	
	public int getNodeName() {
		return nodeName;
	}
	public void setNodeName(int node) {
		this.nodeName = node;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Node getAncestorNode() {
		return ancestorNode;
	}
	public void setAncestorNode(Node ancestorNode) {
		this.ancestorNode = ancestorNode;
	}
	public List<Node> getNeighborNodes() {
		return NeighborNodes;
	}
	public void setNeighborNodes(List<Node> neighborNodes) {
		NeighborNodes = neighborNodes;
	}
	public List<Node> getNoNeighborNodes() {
		return NoNeighborNodes;
	}
	public void setNoNeighborNodes(List<Node> noNeighborNodes) {
		NoNeighborNodes = noNeighborNodes;
	}

	@Override
    public boolean equals(Object param) {
		if(param instanceof Node) {
			Node node = (Node) param;
			if(this.nodeName == node.nodeName) {
				return true;
			}
		}
        return false;
    }

	
}
