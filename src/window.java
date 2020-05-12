import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.JTextPane;

public class window {

	private JFrame frame;
	private ReadEntry rdEntr;
	private WriteEntry wrEntr;
	private RemoveEntry rmEntr;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public window() {
		rdEntr = new ReadEntry();
		rdEntr.readEntry();
		wrEntr = new WriteEntry();
		rmEntr = new RemoveEntry();
		initialize();

	}

	private void initialize() {
		int listIndex = -1;
		frame = new JFrame();
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Note");

		Vector<ListItem> items = new Vector<>();
		for (Map.Entry<Integer, String> entry : rdEntr.getEntries().entrySet()) {
			items.add(new ListItem(entry.getKey(), entry.getValue()));
		}

		JTextArea textPane = new JTextArea();
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true);
		textPane.getLineWrap();
		
		JList list = new JList(items);
		list.setBackground(UIManager.getColor("Button.disabledForeground"));
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();

				if (evt.getClickCount() == 1) {
					rdEntr.readEntry();
					// Double-click detected
					int index = list.locationToIndex(evt.getPoint());
					textPane.setText(rdEntr.getNormalText(index));
				}
			}
		});

		frame.getContentPane().add(list, BorderLayout.WEST);

		JButton saveNote = new JButton("Save Note");
		saveNote.setBounds(100, 100, 100, 100);

		frame.getContentPane().add(saveNote);
		frame.getContentPane().add(saveNote, BorderLayout.SOUTH);
		frame.getContentPane().add(textPane, BorderLayout.CENTER);

		JButton addNoteBtn = new JButton("Add Note");
		frame.getContentPane().add(addNoteBtn, BorderLayout.NORTH);

		ActionListener actSave = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rmEntr.removeEntry(list.getSelectedIndex());
				String p = wrEntr.coverText(list.getSelectedValue().toString(), textPane.getText());
				rdEntr.setEntries(list.getSelectedIndex(), p);
				wrEntr.writeMap(rdEntr.getEntries());
			}
		};

		saveNote.addActionListener(actSave);
	}

}
