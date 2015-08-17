package com.arbitrium.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

import com.arbitrium.decisions.Decision;
import com.arbitrium.manager.HMDManager;

public class HelpMeDecideWizardPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton passbtn;
	private JToggleButton completedButton;
	private JLabel timesPassedValuelbl;
	private JLabel dateCompletedValuelbl;
	private JLabel currentDateValuelbl;
	private JTextArea decisionDesscriptiontxtArea;
	private JLabel decisionTitlelbl;
	private JButton askbtn;
	private HMDManager hmdmanager;
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private Decision currentDecision;

	/**
	 * Create the panel.
	 */
	public HelpMeDecideWizardPanel() {
		setSize(622,380);
		setLayout(null);

		try {
			hmdmanager = HMDManager.getManagerInstance();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		JLabel lblNewLabel = new JLabel("Having a hard time deciding what to do next?");
		lblNewLabel.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(165, 6, 291, 16);
		add(lblNewLabel);

		askbtn = new JButton("ASK H.M.D.");
		askbtn.addActionListener(this);
		askbtn.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 14));
		askbtn.setBounds(243, 34, 117, 29);
		add(askbtn);

		passbtn = new JButton("Pass");
		passbtn.setEnabled(false);
		passbtn.addActionListener(this);
		passbtn.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 14));
		passbtn.setBounds(6, 277, 161, 29);
		add(passbtn);

		completedButton = new JToggleButton("Completed?");
		completedButton.setEnabled(false);
		completedButton.addActionListener(this);
		completedButton.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		completedButton.setBounds(6, 305, 161, 29);
		add(completedButton);

		JLabel dateCompletedlbl = new JLabel("Date Completed:");
		dateCompletedlbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateCompletedlbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		dateCompletedlbl.setBounds(176, 310, 117, 16);
		add(dateCompletedlbl);

		JLabel timesPassedlbl = new JLabel("Times Passed:");
		timesPassedlbl.setHorizontalAlignment(SwingConstants.CENTER);
		timesPassedlbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		timesPassedlbl.setBounds(176, 282, 117, 16);
		add(timesPassedlbl);

		JLabel currentDatelbl = new JLabel("Current Date");
		currentDatelbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentDatelbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		currentDatelbl.setBounds(485, 6, 117, 16);
		add(currentDatelbl);

		currentDateValuelbl = new JLabel(dateFormat.format(Calendar.getInstance().getTime()));
		currentDateValuelbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentDateValuelbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		currentDateValuelbl.setBounds(476, 34, 140, 16);
		add(currentDateValuelbl);

		timesPassedValuelbl = new JLabel("#");
		timesPassedValuelbl.setHorizontalAlignment(SwingConstants.CENTER);
		timesPassedValuelbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		timesPassedValuelbl.setBounds(305, 282, 117, 16);
		add(timesPassedValuelbl);

		dateCompletedValuelbl = new JLabel("Not yet completed.");
		dateCompletedValuelbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateCompletedValuelbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		dateCompletedValuelbl.setBounds(305, 310, 117, 16);
		add(dateCompletedValuelbl);

		decisionTitlelbl = new JLabel("Ask HMD");
		decisionTitlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		decisionTitlelbl.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		decisionTitlelbl.setBounds(15, 75, 574, 16);
		add(decisionTitlelbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(28, 261, 561, 12);
		add(separator);

		decisionDesscriptiontxtArea = new JTextArea();
		decisionDesscriptiontxtArea.setEditable(false);
		decisionDesscriptiontxtArea.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		decisionDesscriptiontxtArea.setText("Ask HMD for description.");
		decisionDesscriptiontxtArea.setLineWrap(true);
		decisionDesscriptiontxtArea.setWrapStyleWord(true);
		decisionDesscriptiontxtArea.setBounds(15, 103, 574, 149);
		add(decisionDesscriptiontxtArea);

	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == askbtn) {
			if((currentDecision = hmdmanager.askHMD()) != null) {
				decisionTitlelbl.setText(currentDecision.getTitle());
				decisionDesscriptiontxtArea.setText(currentDecision.getDescription());
				timesPassedValuelbl.setText(currentDecision.getTimesPassed()+"");
				if(currentDecision.isCompleted()){
					completedButton.setSelected(true);
					dateCompletedValuelbl.setText(currentDecision.getDateCompleted());
				} else {
					completedButton.setSelected(false);
					dateCompletedValuelbl.setText("Not yet completed.");
				}
				askbtn.setEnabled(false);
				passbtn.setEnabled(true);
				completedButton.setEnabled(true);
			} else {
				currentDecision = hmdmanager.askHMDCompleted();
				decisionTitlelbl.setText(currentDecision.getTitle());
				decisionDesscriptiontxtArea.setText(currentDecision.getDescription());
				timesPassedValuelbl.setText(currentDecision.getTimesPassed()+"");
				if(currentDecision.isCompleted()){
					completedButton.setSelected(true);
					dateCompletedValuelbl.setText(currentDecision.getDateCompleted());
				} else {
					completedButton.setSelected(false);
					dateCompletedValuelbl.setText("Not yet completed.");
				}
				askbtn.setEnabled(true);
				passbtn.setEnabled(false);
				completedButton.setEnabled(false);
			}
		} else if(action.getSource() == completedButton){
			if(completedButton.isSelected()){
				dateCompletedValuelbl.setText(dateFormat.format(Calendar.getInstance().getTime()));
				currentDecision.setCompleted(true);
				passbtn.setEnabled(false);
			} else {
				dateCompletedValuelbl.setText("Not yet completed.");
				passbtn.setEnabled(true);
			}
			currentDecision.setDateCompleted(dateCompletedValuelbl.getText());
			if(!hmdmanager.updateDecision(currentDecision.getID(), currentDecision)){
				JOptionPane.showMessageDialog(this,"Could not update decision.");
			}
			askbtn.setEnabled(true);
		} else if(action.getSource() == passbtn){
			currentDecision.pass();
			if(!hmdmanager.updateDecision(currentDecision.getID(), currentDecision)){
				JOptionPane.showMessageDialog(this,"Could not update decision.");
			}
			decisionTitlelbl.setText("Ask HMD");
			decisionDesscriptiontxtArea.setText("Ask HMD for description.");
			timesPassedValuelbl.setText("#");
			dateCompletedValuelbl.setText("Not yet completed.");
			passbtn.setEnabled(false);
			completedButton.setEnabled(false);
			askbtn.setEnabled(true);
		}
		try {
			hmdmanager.saveDecisions();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
