
public class RemoveEntry {

	private WriteEntry wrEntry;
	private ReadEntry rdEntry;

	public RemoveEntry() {

		wrEntry = new WriteEntry();
		rdEntry = new ReadEntry();

	}

	public void removeEntry(int index) {

		rdEntry.readEntry();

		if (!rdEntry.getEntries().isEmpty()) {

			rdEntry.getEntries().remove(index);
			wrEntry.writeMap(rdEntry.getEntries());

		}

	}

}
