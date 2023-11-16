package entities.route;

public class RouteWeights {
	private int OriginNode;
	private int DestinyNode;
	private int Weight;
	
	public RouteWeights() {
		
	}
	public RouteWeights(int originNode, int destinyNode, int weight) {
		OriginNode = originNode;
		DestinyNode = destinyNode;
		Weight = weight;
	}
	
	public int getOriginNode() {
		return OriginNode;
	}
	public void setOriginNode(int originNode) {
		OriginNode = originNode;
	}
	public int getDestinyNode() {
		return DestinyNode;
	}
	public void setDestinyNode(int destinyNode) {
		DestinyNode = destinyNode;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
	}
}
