package window;
import java.awt.Rectangle;

import manage.Card;
import manage.CardManager;

import javax.swing.*;

import org.json.simple.parser.ParseException;

import java.awt.event.*;
import java.io.IOException;

import exeptions.BadCardFormatExeption;
import exeptions.CardManagerExeptions;

public class AplicationView {
	
	private JFrame mainFrame;
	
	private JTable dataTable;
	private JScrollPane scrollPane;
	
	private JMenuBar menuBar;
	private JMenu menuAction;
//	private JMenu menuProperties;
	private JMenuItem menuItem;
	
	public AplicationView() throws BadCardFormatExeption {
		prepareGUI();
	}

	private void prepareGUI() throws BadCardFormatExeption {
		//Frame
		mainFrame = new JFrame("Wizytownik");
		mainFrame.setSize(400,400);
//		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
		
		//MenuBar
		menuBar = new JMenuBar();
		menuBar.setFocusable(false);
		
		menuAction = new JMenu("Action");
//		menuProperties = new JMenu("Properties");
		menuBar.add(menuAction);
//		menuBar.add(menuProperties);
	
		menuItem = new JMenuItem("Import");
		menuItem.addActionListener(e -> showImportQuestion());
		menuItem.setActionCommand("Import");
		menuAction.add(menuItem);
		
		menuItem = new JMenuItem("Export");
		menuItem.addActionListener(e -> showExportQuestion());
		menuItem.setActionCommand("Export");
		menuAction.add(menuItem);
		
		menuAction.insertSeparator(2);
		
		menuItem = new JMenuItem("ADD");
		menuItem.addActionListener(e -> showDataForm(false));
		menuAction.add(menuItem);
		
		menuItem = new JMenuItem("DELETE");
		menuItem.addActionListener(e -> showDeleteWarning());
		menuAction.add(menuItem);
		
		menuItem = new JMenuItem("CHANGE");
		menuItem.addActionListener(e -> showDataForm(true));
		menuAction.add(menuItem);
		
		menuAction.insertSeparator(5);
		
		menuItem = new JMenuItem("FIND");
		menuItem.addActionListener(e -> showFindMessage());
		menuAction.add(menuItem);
		
		menuItem = new JMenuItem("SORT");
		menuItem.addActionListener(e -> showSortQuestion());
		menuAction.add(menuItem);
	
		//DataTable
		dataTable = new JTable(new DataTableModel());
		//Set DataTable properties
		dataTable.getTableHeader().setReorderingAllowed(false);
		dataTable.getTableHeader().setResizingAllowed(false);
		dataTable.setFillsViewportHeight(true);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		
		
		scrollPane = new JScrollPane(dataTable);
		mainFrame.setJMenuBar(menuBar);
		mainFrame.add(scrollPane);
		mainFrame.setVisible(true);
	}

	public void updateTable() {
		try {
			dataTable.setModel(new DataTableModel());
		} catch (BadCardFormatExeption e) {
			//TODO? Never happens
		}
	}
	
	public void showDataForm(boolean change){
		String[] fields = Card.getDataFormat();
		int row = -1;
		if(change) {
			row = dataTable.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(
					mainFrame,
					"No data to changle selected",
					"Change Buisness Card",
					JOptionPane.ERROR_MESSAGE);	
				return;
			}
		}
		Object[] input = new Object[2*fields.length];
		for(int i=0;i<fields.length;i++){
			input[2*i] = fields[i];
			if(change) input[2*i + 1] = new JTextField(CardManager.getCard(row)[i]);
			else input[2*i + 1] = new JTextField();
		}
		int result = JOptionPane.showConfirmDialog(
			mainFrame,
			input,
			"Buisnes Card:",
			JOptionPane.OK_CANCEL_OPTION);	
		if(result == JOptionPane.OK_OPTION) {
			try {
				String[] data = new String[fields.length];
				for(int i=0; i<fields.length; i++){
					data[i] = ((JTextField)(input[2*i+1])).getText();
				}
				if(change)	CardManager.setRow(data, row);
				else CardManager.addRow(data);
				updateTable();
			}
			catch (CardManagerExeptions e) {
				JOptionPane.showMessageDialog(
					mainFrame,
					e.getExeptionMessage(),
					"Buisnes Card",
					JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(
					mainFrame,
					"Error occured when trying to work with file",
					"Buisnes Card",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void showDeleteWarning() {
		int row = dataTable.getSelectedRow();
		if(row == -1) 
			JOptionPane.showMessageDialog(
				mainFrame,
				"No data to delete selected",
				"Delete Buisness Card",
				JOptionPane.ERROR_MESSAGE);		
		else {
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
					mainFrame,
					"Do you realy wont to delete that card?",
					"Delete Buisness Card",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.ERROR_MESSAGE)) {
				try {
					CardManager.deleteRow(row);
					dataTable.clearSelection();
				} catch (CardManagerExeptions e) {
					JOptionPane.showMessageDialog(
					mainFrame,
					e.getExeptionMessage(),
					"Buisnes Card",
					JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(
					mainFrame,
					"Error occured when trying to work with file",
					"Buisnes Card",
					JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public void showFindMessage() {
		JComboBox<String> findByColumn = new JComboBox<String>(Card.getDataFormat());
		JTextField toFind = new JTextField();	
		
		Object[] input = {
				"Find:", toFind,
				"in column:", findByColumn};
		int result = JOptionPane.showConfirmDialog(
				mainFrame,
				input,
				"Find Card",
				JOptionPane.OK_CANCEL_OPTION);	
		if(result == JOptionPane.OK_OPTION) {
			int row = CardManager.findData(toFind.getText(),findByColumn.getSelectedIndex());
			if(row == -1) {
				JOptionPane.showMessageDialog(
						mainFrame,
						"CARD NOT FOUND",
						"Find Buisness Card",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				dataTable.setRowSelectionInterval(row, row);	
				dataTable.scrollRectToVisible(new Rectangle(dataTable.getCellRect(row, 0, true)));
			}
		}
	}
	
	public void showSortQuestion() {
		JComboBox<String> sortByColumn = new JComboBox<String>(Card.getDataFormat());
		JComboBox<String> sortIncreasing = new JComboBox<String>( new String[] {"Increasing","Decreasing"});
		
		Object[] input = {
				"Sort by Column:", sortByColumn,
				"Increasing/Decreasing", sortIncreasing};
		int result = JOptionPane.showConfirmDialog(
				mainFrame,
				input,
				"Sort Buisnes Cards:",
				JOptionPane.OK_CANCEL_OPTION);	
		if(result == JOptionPane.OK_OPTION) {
			CardManager.setSorting(sortByColumn.getSelectedIndex(), 0 == sortIncreasing.getSelectedIndex());
			}
		mainFrame.getContentPane().invalidate();
		mainFrame.getContentPane().validate();
		mainFrame.getContentPane().repaint();
	}
	
	public void showImportQuestion() {
		JFileChooser fileChooser = new JFileChooser();
		
		if(fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION){
			String file = fileChooser.getSelectedFile().getAbsolutePath();
			String extension = file.substring(file.lastIndexOf('.') + 1);

			try {
				CardManager.importData(file, extension);
				updateTable();
			} catch (CardManagerExeptions e) {
				JOptionPane.showMessageDialog(
					mainFrame,
					e.getExeptionMessage(),
					"Buisnes Card",
					JOptionPane.ERROR_MESSAGE);
			} catch (IOException e){
				JOptionPane.showMessageDialog(
					mainFrame,
					"Error occured when trying to work with file",
					 "Buisnes Card",
					  JOptionPane.ERROR_MESSAGE);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(
					mainFrame,
					"File cannot be interpereted as JSON file",
					 "Buisnes Card",
					 JOptionPane.ERROR_MESSAGE);
			}
		}		
	}

	public void showExportQuestion()  {
		JFileChooser fileChooser = new JFileChooser();

		if(fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION){
			String file = fileChooser.getSelectedFile().getAbsolutePath();
			String extension = file.substring(file.lastIndexOf('.') + 1);
			try {
				CardManager.exportData(file,extension);
			} catch (CardManagerExeptions e) {
				JOptionPane.showMessageDialog(
					mainFrame,
					e.getExeptionMessage(),
					"Buisnes Card",
					JOptionPane.ERROR_MESSAGE);
			} catch (IOException e){
				JOptionPane.showMessageDialog(
					mainFrame,
					"Error occured when trying to work with file",
					"Buisnes Card",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}