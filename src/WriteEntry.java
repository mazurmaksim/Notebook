
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class WriteEntry {

	private final static String FILEPATH = "lib\\db.bin";

	public void writeMap(Map<Integer, String> entrie) {
		String tmp = "";

		for (Map.Entry<Integer, String> str : entrie.entrySet()) {

			tmp += str.getValue();

		}

		try {
			Files.write(Paths.get(FILEPATH), tmp.getBytes());

		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println("Map Write Successful");

	}

	
	public void writeEntry(String str, String caption) {

		Date today = Calendar.getInstance().getTime();
		String outPut = "\n" + "<p><h1>" + caption + "</h1>" + str + "<create>" + today + "</create></p>";

		try {

			Files.write(Paths.get(FILEPATH), outPut.getBytes(), StandardOpenOption.APPEND);

		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println("Write Successful");

	}

	public static void main(String[] args) {

		WriteEntry we = new WriteEntry();
		we.writeEntry("Сегодня не забыть сделать с детьми уроки", "Уроки с детьми");
	}
}
