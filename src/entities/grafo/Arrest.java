package entities.grafo;

public class Arrest {
	
	private int OriginNode;
	private int DestinyNode;
	private Integer Weight;
	
	public Arrest(int pOriginNode, int pDestinyNode, int pWeight) {
		OriginNode = pOriginNode;
		DestinyNode = pDestinyNode;
		Weight = pWeight;
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
	public Integer getWeight() {
		return Weight;
	}
	public void setWeight(Integer weight) {
		Weight = weight;
	}
}