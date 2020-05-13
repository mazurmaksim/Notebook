import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListItem {
	private int index;
	private String entry;

	ListItem(int index, String entry) {

		this.index = index;
		this.entry = entry;

	}
	
	public int getKey() {
		
		return index;
	}
	@Override
	public String toString() {

		String tmp = "";
		final Pattern pattern = Pattern.compile("<h1>(.+?)</h1>");
		final Matcher matcher = pattern.matcher(entry);
		matcher.find();

		tmp = matcher.group(1);

		return tmp;

	}

}