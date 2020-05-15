import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListItem {
	//private String index;
	private String entry;

	ListItem(String entry) {

		//this.index = index;
		this.entry = entry;

	}

	@Override
	public String toString() {

		return entry;

	}

}