import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadEntry {

	private static final String MARKEREND = "</p>";
	private static final String PATH_TO_DB = "lib\\db.bin";
	private String entry = "";
	private Map<Integer, String> entries = new TreeMap<>();
	private int count;
	
	public Map<Integer, String> readEntry() {
		 
		count = 0;
		
		try (FileReader fr = new FileReader(PATH_TO_DB)) {

			int c;

			while ((c = fr.read()) != -1) {

				entry += (char) c;

				if (entry.equals(MARKEREND)) {
					entry += (char) c;

				}
				if (entry.endsWith(MARKEREND)) {

					entries.put(count++, entry);
					entry = "";

				}

			}

		} catch (IOException e) {
			System.out.print("Error read entry" + e);

		}

		System.out.println("Read " + count + " Entries");
		return entries;

	}

	public Map<Integer, String> getEntries() {

		return entries;

	}
	
	public void setEntries(int index, String value) {
		
		entries.put(index, value);
		
	}

	public String getNormalText(int index) {

		String gentry = entries.get(index);
		String tmp = "";
		final Pattern pattern = Pattern.compile("</h1>(.+?)<create>");
		final Matcher matcher = pattern.matcher(gentry);
		matcher.find();

		tmp = matcher.group(1);

		return tmp;

	}

	public String getCreateDate(int index) {

		String gentry = entries.get(index);
		String tmp = "";
		final Pattern pattern = Pattern.compile("<create>(.+?)</create>");
		final Matcher matcher = pattern.matcher(gentry);
		matcher.find();

		tmp = matcher.group(1);

		return tmp;

	}

	public static void main(String[] args) {

		ReadEntry re = new ReadEntry();
		System.out.println(re.readEntry());

	}

}
