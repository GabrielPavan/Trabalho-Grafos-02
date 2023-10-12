package entities.route;

public class RouteHeader {
	private int NumberOfNodes;
	private int SumOfEdgeWeights;
	
	public RouteHeader(int numberOfNodes, int sumOfEdgeWeights) {
		NumberOfNodes = numberOfNodes;
		SumOfEdgeWeights = sumOfEdgeWeights;
	}
	public int getNumberOfNodes() {
		return NumberOfNodes;
	}
	public void setNumberOfNodes(int numberOfNodes) {
		NumberOfNodes = numberOfNodes;
	}
	public int getSumOfEdgeWeights() {
		return SumOfEdgeWeights;
	}
	public void setSumOfEdgeWeights(int sumOfEdgeWeights) {
		SumOfEdgeWeights = sumOfEdgeWeights;
	}
}
