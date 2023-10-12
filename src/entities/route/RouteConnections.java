package entities.route;

public class RouteConnections {
	int OriginNode;
	int DestinyNode;
	
	public RouteConnections(int originNode, int destinyNode) {
		OriginNode = originNode;
		DestinyNode = destinyNode;
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
}