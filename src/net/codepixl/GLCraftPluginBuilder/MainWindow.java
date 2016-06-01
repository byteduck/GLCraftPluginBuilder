package net.codepixl.GLCraftPluginBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class MainWindow extends JFrame {
	private JTextField nameField;
	private JTextField verField;
	private JTextField mainField;
	private JTextField descField;
	private JTextField locField;
	private JTextField outField;
	public MainWindow() {
		this.setSize(481, 362);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("GLCraft Plugin Builder");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton BrowseButton = new JButton("Browse");
		BrowseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    locField.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		JLabel lblOutputLocation = new JLabel("Output Location");
		panel_2.add(lblOutputLocation, "2, 4");
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    outField.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		panel_2.add(btnBrowse, "4, 4");
		
		outField = new JTextField();
		panel_2.add(outField, "6, 4, fill, default");
		outField.setColumns(10);
		
		JLabel lblProjectLocation = new JLabel("Bin Location");
		panel_2.add(lblProjectLocation, "2, 6");
		panel_2.add(BrowseButton, "4, 6");
		
		locField = new JTextField();
		panel_2.add(locField, "6, 6, fill, default");
		locField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JButton buildButton = new JButton("Build");
		buildButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.build(new BuildOptions(outField.getText(),locField.getText()));
			}
		});
		panel_1.add(buildButton);
	}

}
