package trekking.ui;

import javax.swing.JFrame;
import trekking.controller.MyController;
import trekking.model.Difficulty;
import trekking.model.MyItinerary;
import trekking.model.Trail;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	GridBagLayout gridBagLayout;
	JComboBox comboBox;
	JComboBox comboBox_1;
	JLabel lblNewLabel;
	JTextArea TextArea;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3;
	JButton btnNewButton;
	JButton btnNewButton_1;
	JButton btnNewButton_2;
	JButton btnNewButton_3;
	private FileWriter fileWriter;

	SwingMessageManager M1 = new SwingMessageManager();
	double DislivelloMin = 0;

	public void TextAreaWriter(MyController Cont) {
		TextArea.setText("\nITINERARIO ATTUALE\n");
		for (Trail L2 : Cont.getItinerary().getTrails()) {
			TextArea.append(L2 + "\n");
		}
		DislivelloMin = Cont.getItinerary().getTrails().get(0).getMinAltitude();

		for (Trail i : Cont.getItinerary().getTrails()) {
			if (DislivelloMin > i.getMinAltitude()) {
				DislivelloMin = i.getMinAltitude();
			}
		}

		TextArea.append("\nDislivello max: " + (Cont.getItinerary().calcMaxAltitudeDifference() - DislivelloMin)
				+ "\nLunghezza totale: " + Cont.getItinerary().calcTotalLength() + "\nDifficoltà Max: "
				+ (Cont.getItinerary().calcMaxDifficulty()) + "\n\n" + "Difficoltà media: "
				+ Cont.getItinerary().calcAverageDifficulty()
				+ "\n-----------------------------------------------------------------------------------------------------"
				+ "\n-----------------------------------------------------------------------------------------------------");
		DislivelloMin = 0;
	}

	public MainFrame(MyController Controller) {
		DrawGui();
		LogicGUI(Controller);
	}

	public void LogicGUI(final MyController Cont) {

		for (Difficulty D2 : Difficulty.values()) {
			comboBox_1.addItem(D2.toString());
		}

		for (Trail Trail : Cont.getAllTrails()) {
			comboBox.addItem(Trail);
		}
		// bottone Aggiungi
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyItinerary Itinerario = new MyItinerary();
				List<Trail> L1 = new ArrayList<>();
				L1 = Cont.getAllTrails();
				try {
					Cont.addTrail(L1.get((comboBox.getSelectedIndex())));
				} catch (IllegalArgumentException a) {
					M1.showMessage("Il sentiero da aggiungere non inizia dove termina il sentiero precedente.");
				}
				TextAreaWriter(Cont);

			}
		});
		// bottone controlla
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Difficulty D = Difficulty.values()[comboBox_1.getSelectedIndex()];
				if (Cont.checkItinerary(Double.parseDouble(textField.getText()),
						Double.parseDouble(textField_1.getText()), D,
						Double.parseDouble(textField_2.getText())) == true) {
					M1.showMessage("Controllo positivo");
				} else {
					M1.showMessage("Controllo negativo");
				}
			}
		});
		// dislivello
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty() == false && textField_1.getText().isEmpty() == false
						&& Cont.getItinerary().getTrails().size() != 0) {

					Difficulty D = Difficulty.values()[comboBox_1.getSelectedIndex()];
					if (Cont.checkItinerary(Double.parseDouble(textField.getText()),
							Double.parseDouble(textField_1.getText()), D,
							Double.parseDouble(textField_2.getText())) == true) {
						M1.showMessage("Controllo positivo");
					} else {
						M1.showMessage("Controllo negativo");

					}

					TextAreaWriter(Cont);
				}
			}
		});
		// lunghezza
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty() == false && textField_2.getText().isEmpty() == false
						&& Cont.getItinerary().getTrails().size() != 0) {
					Difficulty D = Difficulty.values()[comboBox_1.getSelectedIndex()];
					if (Cont.checkItinerary(Double.parseDouble(textField.getText()),
							Double.parseDouble(textField_1.getText()), D,
							Double.parseDouble(textField_2.getText())) == true) {
						M1.showMessage("Controllo positivo");
					} else {
						M1.showMessage("Controllo negativo");

					}

					TextAreaWriter(Cont);
				}
			}
		});
		// difficoltà media
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty() == false && textField_1.getText().isEmpty() == false
						&& Cont.getItinerary().getTrails().size() != 0) {
					Difficulty D = Difficulty.values()[comboBox_1.getSelectedIndex()];
					if (Cont.checkItinerary(Double.parseDouble(textField.getText()),
							Double.parseDouble(textField_1.getText()), D,
							Double.parseDouble(textField_2.getText())) == true) {
						M1.showMessage("Controllo positivo");
					} else {
						M1.showMessage("Controllo negativo");

					}

					TextAreaWriter(Cont);
				}
			}
		});
		// difficoltà
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().isEmpty() == false && textField_1.getText().isEmpty() == false
						&& textField_2.getText().isEmpty() == false && Cont.getItinerary().getTrails().size() != 0) {
					Difficulty D = Difficulty.values()[comboBox_1.getSelectedIndex()];
					if (Cont.checkItinerary(Double.parseDouble(textField.getText()),
							Double.parseDouble(textField_1.getText()), D,
							Double.parseDouble(textField_2.getText())) == true) {
						M1.showMessage("Controllo positivo");
					} else {
						M1.showMessage("Controllo negativo");
					}
					TextAreaWriter(Cont);
				}

			}
		});
		// reset
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cont.reset();
				TextArea.setText("nessun sentiero inserito");
			}
		});
		// stampa
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileWriter = new FileWriter("/Users/pietroarcidiacono/Downloads/PIETRO ARCIDIACONO/Itinerari.txt",
							true);
					fileWriter.append("ITINERARIO ATTUALE\n");
					for (Trail L2 : Cont.getItinerary().getTrails()) {
						fileWriter.append(L2 + "\n");
					}
					DislivelloMin = Cont.getItinerary().getTrails().get(0).getMinAltitude();

					for (Trail i : Cont.getItinerary().getTrails()) {
						if (DislivelloMin > i.getMinAltitude()) {
							DislivelloMin = i.getMinAltitude();
						}
					}

					fileWriter.append("\nDislivello max: "
							+ (Cont.getItinerary().calcMaxAltitudeDifference() - DislivelloMin) + "\nLunghezza totale: "
							+ Cont.getItinerary().calcTotalLength() + "\nDifficoltà Max: "
							+ (Cont.getItinerary().calcMaxDifficulty()) + "\n\n" + "Difficoltà media: "
							+ Cont.getItinerary().calcAverageDifficulty()
							+ "\n-----------------------------------------------------------------------------------------------------"
							+ "\n-----------------------------------------------------------------------------------------------------");
					DislivelloMin = 0;
					M1.showMessage("File creato con successo");
					fileWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void DrawGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1, 1, 1.0, 1.0, 1.0, 1.1, 2, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridwidth = 8;
		gbc_comboBox.gridheight = 1;
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.weightx = 0;
		gbc_comboBox.weighty = 0;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		getContentPane().add(comboBox, gbc_comboBox);

		lblNewLabel = new JLabel("Diificoltà Max");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 1;
		gbc_lblNewLabel.gridheight = 1;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.weightx = 0;
		gbc_lblNewLabel.weighty = 0;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.gridwidth = 1;
		gbc_comboBox_1.gridheight = 1;
		gbc_comboBox_1.fill = GridBagConstraints.BOTH;
		gbc_comboBox_1.weightx = 0;
		gbc_comboBox_1.weighty = 0;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 1;
		getContentPane().add(comboBox_1, gbc_comboBox_1);

		TextArea = new JTextArea();
		TextArea.setText("Nessun sentiero inserito");
		GridBagConstraints gbc_TextArea = new GridBagConstraints();
		gbc_TextArea.gridwidth = 6;
		gbc_TextArea.gridheight = 6;
		gbc_TextArea.insets = new Insets(0, 0, 5, 5);
		gbc_TextArea.fill = GridBagConstraints.BOTH;
		gbc_TextArea.gridx = 2;
		gbc_TextArea.gridy = 1;
		getContentPane().add(TextArea, gbc_TextArea);

		lblNewLabel_1 = new JLabel("Dislivello Max");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridwidth = 1;
		gbc_lblNewLabel_1.gridheight = 1;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.weightx = 0;
		gbc_lblNewLabel_1.weighty = 0;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridwidth = 1;
		gbc_textField.gridheight = 1;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.weightx = 0;
		gbc_textField.weighty = 0;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		lblNewLabel_2 = new JLabel("Lunghezza Max");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridwidth = 1;
		gbc_lblNewLabel_2.gridheight = 1;
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.weightx = 0;
		gbc_lblNewLabel_2.weighty = 0;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridwidth = 1;
		gbc_textField_1.gridheight = 1;
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.weightx = 0;
		gbc_textField_1.weighty = 0;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		lblNewLabel_3 = new JLabel("Difficoltà Media");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridwidth = 1;
		gbc_lblNewLabel_3.gridheight = 1;
		gbc_lblNewLabel_3.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_3.weightx = 0;
		gbc_lblNewLabel_3.weighty = 0;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridwidth = 1;
		gbc_textField_2.gridheight = 1;
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.weightx = 0;
		gbc_textField_2.weighty = 0;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 4;
		getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		btnNewButton = new JButton("Aggiungi");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridwidth = 1;
		gbc_btnNewButton.gridheight = 1;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.weightx = 0;
		gbc_btnNewButton.weighty = 0;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 5;
		getContentPane().add(btnNewButton, gbc_btnNewButton);

		btnNewButton_1 = new JButton("Controlla");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridwidth = 1;
		gbc_btnNewButton_1.gridheight = 1;
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.weightx = 0;
		gbc_btnNewButton_1.weighty = 0;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 5;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		btnNewButton_2 = new JButton("Reset");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridwidth = 1;
		gbc_btnNewButton_2.gridheight = 1;
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.weightx = 0;
		gbc_btnNewButton_2.weighty = 0;
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 6;
		getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);

		btnNewButton_3 = new JButton("Stampa");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridwidth = 1;
		gbc_btnNewButton_3.gridheight = 1;
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.weightx = 0;
		gbc_btnNewButton_3.weighty = 0;
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 6;
		getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
	}
}
