
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.beans.*; //property change stuff
import java.awt.*;
import java.awt.event.*;

public class AddEntryDialog extends JDialog implements ActionListener, PropertyChangeListener {
	private String typedText = null;
	private JTextField textField;

	private String magicWord;
	private JOptionPane optionPane;

	private String btnString1 = "Enter";
	private String btnString2 = "Cancel";

	/**
	 * Returns null if the typed string was invalid; otherwise, returns the string
	 * as the user entered it.
	 */
	public String getValidatedText() {
		return typedText;
	}

	{

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}