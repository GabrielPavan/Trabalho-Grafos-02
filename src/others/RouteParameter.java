package others;

import java.util.regex.Pattern;

public class RouteParameter {
	public static final Pattern HeaderPattern = Pattern.compile("00[0-9]{1,2}[0-9]{1,5}");
	public static final Pattern ConnectionPattern = Pattern.compile("01[0-9]{1,2}=[0-9]{1,2}");
	public static final Pattern WeightPattern = Pattern.compile("02[0-9]{1,2};[0-9]{1,2}=[0-9]{1,4}");
	public static final Pattern TrailerPattern = Pattern.compile("09RC=[0-9]{1,2};RP=[0-9]{1,2};[0-9]{1,4}");
	
	public static final String DefaultValueSpliter = "=";
	public static final String DefaultDataSlipter = ";";
}
