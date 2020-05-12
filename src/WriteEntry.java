
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Date;

public class WriteEntry {

	public void writeEntry(String str, String caption) {

		String filePath = "lib\\db.bin";
		Date today = Calendar.getInstance().getTime();
		String outPut = "\n" + "<p><h1>" + caption + "</h1>" + str + "<create>" + today + "</create></p>";

		try {

			Files.write(Paths.get(filePath), outPut.getBytes(), StandardOpenOption.APPEND);

		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("Write Successful");

	}

	public static void main(String[] args) {

		WriteEntry we = new WriteEntry();
		we.writeEntry("Hello, my name is Maksim Mazur", "Entry MyName is");
	}
}
