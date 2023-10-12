package entities;

import java.util.List;

public class Route {
	private String Header;
	private List<String> ConnectionSummary;
	private List<String> WeightSummary;
	private String Trailer;
	
	public String getHeader() {
		return Header;
	}
	public void setHeader(String header) {
		Header = header;
	}
	public List<String> getConnectionSummary() {
		return ConnectionSummary;
	}
	public void setConnectionSummary(List<String> connectionSummary) {
		ConnectionSummary = connectionSummary;
	}
	public List<String> getWeightSummary() {
		return WeightSummary;
	}
	public void setWeightSummary(List<String> weightSummary) {
		WeightSummary = weightSummary;
	}
	public String getTrailer() {
		return Trailer;
	}
	public void setTrailer(String trailer) {
		Trailer = trailer;
	}
}
