
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class WriteEntry {

	private final static String FILEPATH = "lib\\db.bin";

	public void writeMap(Map<String, String> entrie) {
		
		String tmp = "";
		if(!entrie.isEmpty()) {
		for (Map.Entry<String, String> str : entrie.entrySet()) {
			
			tmp += coverText( str.getKey() ,str.getValue());

		}
		}
		else tmp = "";
		try {
			Files.write(Paths.get(FILEPATH), tmp.getBytes());

		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println("Map Write Successful");

	}

	public String coverText(String caption, String text) {
		
		Date today = Calendar.getInstance().getTime();
		
		return ("<p><h1>" + caption + "</h1>" + text + "<create>" + today + "</create></p>"+"\n");
		
	}
	
	public void writeEntry(String str, String caption) {

		Date today = Calendar.getInstance().getTime();
		String outPut ="<p><h1>" + caption + "</h1>" + str + "<create>" + today + "</create></p>" + "\n";

		try {

			Files.write(Paths.get(FILEPATH), outPut.getBytes(), StandardOpenOption.APPEND);

		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println("Write Successful");

	}

	public static void main(String[] args) {

		WriteEntry we = new WriteEntry();
		we.writeEntry("Убрать всю територию за гаражами", "День уборки");
		we.writeEntry("Забрать холодильник и отнести на 10 етаж", "Забрать холодильник");
		we.writeEntry("Ехать за город на рыбалку", "Если хорошая погода");
		we.writeEntry("Переделать все программы", "Куча дел");
		we.writeEntry("У лукоморья дуб зеленый", "Выучить стих");
		we.writeEntry("соль, сахар, хлеб", "Список покупок");
	
	}
}
