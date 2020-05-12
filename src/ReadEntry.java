import java.io.*;
import java.util.*;

public class ReadEntry {

	private static final String MARKEREND = "</p>";
	private static final String PATH_TO_DB = "lib\\db.bin";
	private String entry = "";
	private int count = 0;
	private Map<Integer, String> entries = new TreeMap<>();
	// private Map<Integer, String> entriesOutPut = new TreeMap<>();

	public Map<Integer, String> readEntry() {

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

	public String EntryOutput() {// return String With date format and caption

		return "";

	}

	public Map<Integer, String> getEntries() {

		return entries;

	}

	public static void main(String[] args) {

		ReadEntry re = new ReadEntry();
		System.out.println(re.readEntry());

	}

}
