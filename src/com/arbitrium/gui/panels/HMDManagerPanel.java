package com.arbitrium.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.arbitrium.decisions.Decision;
import com.arbitrium.manager.HMDManager;
import com.arbitrium.tables.DecisionTable;

public class HMDManagerPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField titletxtfld;
	private JButton addbtn;
	private JTextArea descriptiontxtarea;
	private JScrollPane decisionscltbl;
	private JButton editbtn;
	private JButton removebtn;
	private JButton savebtn;
	private HMDManager decisionManager;
	private JTable decisionTable;
	private JLabel idValuelbl;
	private boolean titleReady = false;
	private boolean descriptionReady = false;
	public boolean FINAL_SAVE = true;

	/**
	 * Create the panel.
	 */
	public HMDManagerPanel() {

		try {
			decisionManager = HMDManager.getManagerInstance();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		setSize(622,380);
		setLayout(null);

		JLabel decisionEditorlbl = new JLabel("Decision Editor");
		decisionEditorlbl.setHorizontalAlignment(SwingConstants.CENTER);
		decisionEditorlbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 15));
		decisionEditorlbl.setBounds(58, 6, 118, 16);
		add(decisionEditorlbl);

		JLabel idlbl = new JLabel("ID:");
		idlbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 15));
		idlbl.setBounds(6, 34, 35, 16);
		add(idlbl);

		idValuelbl = new JLabel(""+decisionManager.getUniqueID());
		idValuelbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 15));
		idValuelbl.setBounds(58, 34, 35, 16);
		add(idValuelbl);

		JLabel titlelbl = new JLabel("Title:");
		titlelbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 15));
		titlelbl.setBounds(6, 62, 41, 16);
		add(titlelbl);

		titletxtfld = new JTextField();
		titletxtfld.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(e.getDocument().getLength() >= 3){
					titleReady = true;
				} else {
					titleReady = false;
				}
				if(titleReady && descriptionReady){
					addbtn.setEnabled(true);
				} else {
					addbtn.setEnabled(false);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(e.getDocument().getLength() >= 3){
					titleReady = true;
				} else {
					titleReady = false;
				}
				if(titleReady && descriptionReady){
					addbtn.setEnabled(true);
				} else {
					addbtn.setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(e.getDocument().getLength() >= 3){
					titleReady = true;
				} else {
					titleReady = false;
				}
				if(titleReady && descriptionReady){
					addbtn.setEnabled(true);
				} else {
					addbtn.setEnabled(false);
				}
			}
		});
		titletxtfld.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 12));
		titletxtfld.setBounds(58, 56, 185, 28);
		add(titletxtfld);
		titletxtfld.setColumns(10);

		JLabel descriptionlbl = new JLabel("Description:");
		descriptionlbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 15));
		descriptionlbl.setBounds(6, 90, 81, 16);
		add(descriptionlbl);

		descriptiontxtarea = new JTextArea();
		descriptiontxtarea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(e.getDocument().getLength() >= 3){
					descriptionReady = true;
				} else {
					descriptionReady = false;
				}
				if(titleReady && descriptionReady){
					addbtn.setEnabled(true);
				} else {
					addbtn.setEnabled(false);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(e.getDocument().getLength() >= 3){
					descriptionReady = true;
				} else {
					descriptionReady = false;
				}
				if(titleReady && descriptionReady){
					addbtn.setEnabled(true);
				} else {
					addbtn.setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(e.getDocument().getLength() >= 3){
					descriptionReady = true;
				} else {
					descriptionReady = false;
				}
				if(titleReady && descriptionReady){
					addbtn.setEnabled(true);
				} else {
					addbtn.setEnabled(false);
				}
			}
		});
		descriptiontxtarea.setWrapStyleWord(true);
		descriptiontxtarea.setLineWrap(true);
		descriptiontxtarea.setTabSize(4);
		descriptiontxtarea.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 12));
		descriptiontxtarea.setBounds(6, 118, 235, 169);
		add(descriptiontxtarea);

		addbtn = new JButton("Add Decision");
		addbtn.setEnabled(false);
		addbtn.addActionListener(this);
		addbtn.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 12));
		addbtn.setBounds(59, 299, 117, 29);
		add(addbtn);

		if(decisionManager.getAllDecisions().size()==0){
			decisionTable = new JTable(new DecisionTable());
		}else {
			decisionTable = new JTable(new DecisionTable(decisionManager.getAllDecisionsMultiDimensionArray()));
		}
		decisionTable.setPreferredScrollableViewportSize(new Dimension(200, 70));
		decisionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		decisionTable.setFillsViewportHeight(true);

		decisionscltbl = new JScrollPane(decisionTable);
		decisionscltbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 12));
		decisionscltbl.setBounds(266, 17, 332, 225);
		add(decisionscltbl);

		removebtn = new JButton("Delete Selected Decision");
		removebtn.addActionListener(this);
		removebtn.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		removebtn.setBounds(429, 254, 169, 29);
		add(removebtn);

		editbtn = new JButton("Edit Selected Decision");
		editbtn.addActionListener(this);
		editbtn.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		editbtn.setBounds(266, 254, 163, 29);
		add(editbtn);

		savebtn = new JButton("Save Changes");
		savebtn.setEnabled(false);
		savebtn.addActionListener(this);
		savebtn.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 12));
		savebtn.setBounds(352, 299, 143, 29);
		add(savebtn);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == addbtn){
			if(!addbtn.getText().equals("Update Decision")) {
				String title = titletxtfld.getText();
				String description = descriptiontxtarea.getText();
				if(title.length() > 3 && description.length() > 3){
					decisionManager.newDecision(new Decision(decisionManager.getUniqueID(), title, description));
					if(decisionManager.getAllDecisions().size()==0){
						decisionTable = new JTable(new DecisionTable());
					}else {
						decisionTable = new JTable(new DecisionTable(decisionManager.getAllDecisionsMultiDimensionArray()));
					}
					decisionTable.setPreferredScrollableViewportSize(new Dimension(200, 70));
					decisionTable.setFillsViewportHeight(true);
					decisionscltbl.setViewportView(decisionTable);
					titletxtfld.setText("");
					descriptiontxtarea.setText("");
					idValuelbl.setText(""+decisionManager.getUniqueID());
					savebtn.setEnabled(true);
					this.revalidate();
					this.repaint();
					this.updateUI();
				} else {
					JOptionPane.showMessageDialog(this, "Minimum character length for the title and decsription is 3.");
				} 
			} else {
				Decision newDecision = new Decision(Integer.parseInt(idValuelbl.getText()), 
						titletxtfld.getText(), descriptiontxtarea.getText());
				if(!decisionManager.updateDecision(Integer.parseInt(idValuelbl.getText()), newDecision)){
					JOptionPane.showMessageDialog(this, "Unable to update decision.");
				} 
				if(decisionManager.getAllDecisions().size()==0){
					decisionTable = new JTable(new DecisionTable());
				}else {
					decisionTable = new JTable(new DecisionTable(decisionManager.getAllDecisionsMultiDimensionArray()));
				}
				decisionTable.setPreferredScrollableViewportSize(new Dimension(200, 70));
				decisionTable.setFillsViewportHeight(true);
				decisionscltbl.setViewportView(decisionTable);
				titletxtfld.setText("");
				descriptiontxtarea.setText("");
				idValuelbl.setText(""+decisionManager.getUniqueID());
				savebtn.setEnabled(true);
				addbtn.setText("Add Decision");
			}
			FINAL_SAVE = false;
		} else if (action.getSource() == savebtn){
			try {
				decisionManager.saveDecisions();
				savebtn.setEnabled(false);
				FINAL_SAVE = true;
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		} else if (action.getSource() == editbtn){
			if(decisionTable.getSelectedRow() != -1){
				int IDTOEDIT = (Integer) decisionTable.getModel().getValueAt(decisionTable.getSelectedRow(), 0);
				Decision d = null;
				try {
					d = decisionManager.getDecision(IDTOEDIT);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
				idValuelbl.setText(d.getID()+"");
				titletxtfld.setText(d.getTitle());
				descriptiontxtarea.setText(d.getDescription());
				addbtn.setText("Update Decision");
			} else {
				JOptionPane.showMessageDialog(this, "Please select a decision to edit.");
			}
			FINAL_SAVE = false;
		} else if (action.getSource() == removebtn){
			if(decisionTable.getSelectedRow() != -1){
				int IDTOEDIT = (Integer) decisionTable.getModel().getValueAt(decisionTable.getSelectedRow(), 0);
				if(!decisionManager.removeDecision(IDTOEDIT)){
					JOptionPane.showMessageDialog(this, "Error with removing decision.");
				}
				
				//UPDATE TABLE
				if(decisionManager.getAllDecisions().size()==0){
					decisionTable = new JTable(new DecisionTable());
				}else {
					decisionTable = new JTable(new DecisionTable(decisionManager.getAllDecisionsMultiDimensionArray()));
				}
				decisionTable.setPreferredScrollableViewportSize(new Dimension(200, 70));
				decisionTable.setFillsViewportHeight(true);
				decisionscltbl.setViewportView(decisionTable);
				titletxtfld.setText("");
				descriptiontxtarea.setText("");
				idValuelbl.setText(""+decisionManager.getUniqueID());
				savebtn.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(this, "Please select a decision to remove.");
			}
			FINAL_SAVE = false;
		}
	}
	
	public void forceSave() throws FileNotFoundException{
		decisionManager.saveDecisions();
	}
}
