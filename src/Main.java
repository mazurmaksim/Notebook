import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
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
	private JList list;
	private DefaultListModel dynList;
	private Vector<ListItem> items;
	private JTextArea textPane;
	private JButton saveNote;

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

		initialize();

	}

	private void initialize() {
		frame = new JFrame();
		items = new Vector<>();
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 700, 527);
		frame.setSize(700, 527);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Note");
		dynList = new DefaultListModel();
		textPane = new JTextArea();
		textPane.setBounds(226, 36, 448, 441);
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true);
		textPane.getLineWrap();
		rdEntr = new ReadEntry();
		wrEntr = new WriteEntry();
		list = new JList();
		list.setBounds(24, 35, 178, 301);
		list.setModel(dynList);
		addItems(rdEntr.getAllentries());
		list.setSelectedIndex(0);

		if (!rdEntr.getAllentries().isEmpty()) {

			textPane.setText(rdEntr.getAllentries().get(dynList.elementAt(0).toString()));

		}

		if (rdEntr.getAllentries().isEmpty()) {
			textPane.setBackground(UIManager.getColor("Button.disabledForeground"));
			textPane.setEnabled(false);
		} else
			textPane.setEnabled(true);

		ActionListener actAdd = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String result = JOptionPane.showInputDialog(frame, "Please enter entry title",
						"Please enter entry title", JOptionPane.DEFAULT_OPTION);

				try {
					if (!result.isEmpty()) {
						addItems(result);
						rdEntr.setEntries(result, "");
						textPane.setBackground(Color.WHITE);
						textPane.setEnabled(true);
						saveNote.setEnabled(true);
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
					System.out.println("Size map: " + rdEntr.getAllentries());

					try {
						captionLb.setText(list.getSelectedValue().toString());// " created: " +
																				// rdEntr.getCreateDate(list.getSelectedValue().toString()));
						textPane.setText(rdEntr.getAllentries().get(list.getSelectedValue().toString()));
					} catch (NullPointerException e) {
						captionLb.setText("");
						textPane.setText("");
					}
				}
			}
		});
		// list.setFocusTraversalPolicyProvider(true);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(list);

		saveNote = new JButton("Save Note");
		saveNote.setBounds(24, 394, 178, 36);
		// saveNote.setEnabled(false);

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
					
					rdEntr.setEntries(dynList.get(list.getSelectedIndex()).toString(), textPane.getText());
					wrEntr.writeMap(rdEntr.getAllentries());
					// saveNote.setEnabled(false);

				} catch (NullPointerException f) {
					captionLb.setText("No one note select");

				} catch ( ArrayIndexOutOfBoundsException fg) {
					wrEntr.writeMap(rdEntr.getAllentries());
				}
			}
		};

		ActionListener actRemove = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					captionLb.setText(dynList.get(list.getSelectedIndex()).toString() + " Removed saccesfully ");
					rdEntr.getAllentries().remove(dynList.get(list.getSelectedIndex()).toString());
					dynList.remove(list.getSelectedIndex());
					list.setSelectedIndex(list.getSelectedIndex() + 1);
					textPane.setText("");

				} catch (ArrayIndexOutOfBoundsException f) {
					captionLb.setText("No one note select");
					textPane.setText("");
					textPane.setEnabled(false);
				}

			}
		};

		rmNote.addActionListener(actRemove);
		saveNote.addActionListener(actSave);
		addNoteBtn.addActionListener(actAdd);
	}

	private void addItems(Map<String, String> map) {

		for (Map.Entry<String, String> entry : map.entrySet()) {
			ListItem lsi = new ListItem(entry.getKey());
			dynList.addElement(lsi.toString());
		}

	}

	private void addItems(String str) {

		dynList.add(dynList.size(), str);

	}

}