import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;

public class Main {

	private JFrame frame;
	private ReadEntry rdEntr;
	private WriteEntry wrEntr;
	private RemoveEntry rmEntr;
	private JList list;
	private DefaultListModel dynList;
	private Vector<ListItem> items;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		rdEntr = new ReadEntry();
		rdEntr.readEntry();
		wrEntr = new WriteEntry();
		rmEntr = new RemoveEntry();
		initialize();

	}

	private void initialize() {
		int listIndex = -1;
		frame = new JFrame();
		items = new Vector<>();
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 700, 527);
		frame.setSize(700, 527);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Note");
		dynList = new DefaultListModel();
		JTextArea textPane = new JTextArea();
		textPane.setBounds(226, 36, 448, 441);
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true);
		textPane.getLineWrap();

		list = new JList();
		list.setBounds(24, 35, 178, 301);
		list.setModel(dynList);
		addItems(rdEntr.getEntries());
		JScrollPane listScrollPane = new JScrollPane(list);

		frame.add(listScrollPane, BorderLayout.CENTER);
		list.setSelectedIndex(0);
		ActionListener actAdd = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String result = JOptionPane.showInputDialog(frame, "Please enter entry title",
						"Please enter entry title", JOptionPane.DEFAULT_OPTION);

				try {
					if (!result.isEmpty()) {
						addItems(result);
					}
				} catch (NullPointerException ef) {

				}
				System.out.println(result);

			}

		};

		JLabel captionLb = new JLabel("Select the notes");
		captionLb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		captionLb.setBounds(226, 0, 448, 34);
		frame.getContentPane().add(captionLb);

		list.setBackground(UIManager.getColor("Button.disabledForeground"));
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();

				if (evt.getClickCount() == 1) {
					rdEntr.readEntry();
					int index = list.locationToIndex(evt.getPoint());
					try {
						captionLb.setText(dynList.get(index).toString() + " created: " + rdEntr.getCreateDate(index));
						textPane.setText(rdEntr.getNormalText(index));
					} catch (NullPointerException e) {
						captionLb.setText("");
						textPane.setText("");
					}
				}
			}
		});
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(list);

		JButton saveNote = new JButton("Save Note");
		saveNote.setBounds(24, 394, 178, 36);

		frame.getContentPane().add(saveNote);
		frame.getContentPane().add(saveNote, BorderLayout.SOUTH);
		frame.getContentPane().add(textPane);

		JButton addNoteBtn = new JButton("Add Note");
		addNoteBtn.setBounds(24, 347, 178, 36);
		frame.getContentPane().add(addNoteBtn);

		JButton rmNote = new JButton("Remove selected note");
		rmNote.setBounds(24, 441, 178, 36);
		frame.getContentPane().add(rmNote);

		JLabel lblNewLabel = new JLabel("List of notes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(70, 10, 91, 14);
		frame.getContentPane().add(lblNewLabel);

		ActionListener actSave = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					rmEntr.removeEntry(list.getSelectedIndex());
					String p = wrEntr.coverText(list.getSelectedValue().toString(), textPane.getText());
					rdEntr.setEntries(list.getSelectedIndex(), p);
					wrEntr.writeMap(rdEntr.getEntries());
				} catch (NullPointerException f) {
					captionLb.setText("No one note select");

				}
			}
		};

		ActionListener actRemove = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					captionLb.setText(dynList.get(list.getSelectedIndex()).toString() + " Removed saccesfully ");
					rmEntr.removeEntry(list.getSelectedIndex());
					dynList.remove(list.getSelectedIndex());
					textPane.setText("");
				} catch (ArrayIndexOutOfBoundsException f) {
					captionLb.setText("No one note select");

				}

			}
		};

		rmNote.addActionListener(actRemove);
		saveNote.addActionListener(actSave);
		addNoteBtn.addActionListener(actAdd);
	}

	private void addItems(Map<Integer, String> map) {

		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			ListItem lsi = new ListItem(entry.getKey(), entry.getValue());
			dynList.add(lsi.getKey(), lsi.toString());
		}

	}

	private void addItems(String str) {

		dynList.add(dynList.size(), str);

	}
}