package entities.route;

public class RouteTrailer {
	private int NumberOfConnectionsLines;
	private int NumberOfWeightLines;
	private int Sum0fWeightsBetweenAllNodes;
	
	public RouteTrailer(int numberOfConnectionsLines, int numberOfWeightLines, int sum0fWeightsBetweenAllNodes) {
		NumberOfConnectionsLines = numberOfConnectionsLines;
		NumberOfWeightLines = numberOfWeightLines;
		Sum0fWeightsBetweenAllNodes = sum0fWeightsBetweenAllNodes;
	}
	
	public int getNumberOfConnectionsLines() {
		return NumberOfConnectionsLines;
	}
	public void setNumberOfConnectionsLines(int numberOfConnectionsLines) {
		NumberOfConnectionsLines = numberOfConnectionsLines;
	}
	public int getNumberOfWeightLines() {
		return NumberOfWeightLines;
	}
	public void setNumberOfWeightLines(int numberOfWeightLines) {
		NumberOfWeightLines = numberOfWeightLines;
	}
	public int getSum0fWeightsBetweenAllNodes() {
		return Sum0fWeightsBetweenAllNodes;
	}
	public void setSum0fWeightsBetweenAllNodes(int sum0fWeightsBetweenAllNodes) {
		Sum0fWeightsBetweenAllNodes = sum0fWeightsBetweenAllNodes;
	}
}
