import java.io.*;
import java.util.*;

public class ReadEntry {


	private final String MARKEREND = "</p>";
	private String entry = "";
	private int count = 0;
	private Map<Integer, String> entries = new TreeMap<>();
	private Map<Integer, String> entriesOutPut = new TreeMap<>();
	
	private Map<Integer, String> readEntry() {

		try (FileReader fr = new FileReader("lib\\db.bin")) {

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
	
	public String EntryOutput() {//return String With date format and caption
		
	return "";
		
	}
	

	public static void main(String[] args) {

		ReadEntry re = new ReadEntry();
		System.out.println(re.readEntry());

	}

}
