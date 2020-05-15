import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadEntry {

	private static final String MARKEREND = "</p>";
	private static final String PATH_TO_DB = "lib\\db.bin";
	private String entry = "";
	private Map<Integer, String> entries = new TreeMap<>();
	private Map<String, String> allentries = new TreeMap<>();
	private int count;

	public ReadEntry() {

		readEntry();

		if (!entries.isEmpty()) {

			SplitUpStr();

		}

	}

	public void readEntry() {

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

	}

	public void setEntries(String key, String value) {
		
			allentries.put(key, value);
			System.out.println(allentries);
	}

	public void SplitUpStr() {

		for (Map.Entry<Integer, String> str : entries.entrySet()) {

			allentries.put( getTitle(str.getValue()), getNormalText(str.getKey()) );

		}

	}

	public Map<String, String> getAllentries() {

		return allentries;

	}

	public String getTitle(String str) {

		String tmp = "";
		final Pattern pattern = Pattern.compile("<h1>(.+?)</h1>");
		final Matcher matcher = pattern.matcher(str);
		matcher.find();
		tmp = matcher.group(1);

		return tmp;

	}


	public String getNormalText(int index) {

		String gentry = entries.get(index);
		String tmp = "";
		final Pattern pattern = Pattern.compile("(?<=<\\/[Hh][1]>)([\\s\\S]*?)(?=<[Cc][Rr][Ee][Aa][Tt][Ee]>)");
		final Matcher matcher = pattern.matcher(gentry);

		if (matcher.find()) {
			tmp = matcher.group(1);
		}

		return tmp;

	}

	public String getCreateDate(String title) {
		String gentry = allentries.get(title);
		String tmp = "";
		final Pattern pattern = Pattern.compile("<create>(.+?)</create>");
		final Matcher matcher = pattern.matcher(gentry);
		matcher.find();

		tmp = matcher.group(1);

		return tmp;

	}

	public static void main(String[] args) {

		ReadEntry re = new ReadEntry();
		System.out.println(re.getAllentries());

	}

}
