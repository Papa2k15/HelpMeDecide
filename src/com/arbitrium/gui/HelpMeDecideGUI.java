package com.arbitrium.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.arbitrium.gui.panels.HMDManagerPanel;
import com.arbitrium.gui.panels.HelpMeDecideWizardPanel;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class HelpMeDecideGUI implements ActionListener {

	private JFrame frame;
	private HMDManagerPanel managerPanel;
	private HelpMeDecideWizardPanel HMDWizardPanel;
	private JTabbedPane mainTabbedPane;
	private JButton quitbtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpMeDecideGUI window = new HelpMeDecideGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public HelpMeDecideGUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		frame = new JFrame();
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame.setBounds(100, 100, 640, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		//Initialize panels
		managerPanel = new HMDManagerPanel();
		HMDWizardPanel = new HelpMeDecideWizardPanel();
		
		mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainTabbedPane.setBounds(6, 6, 628, 386);
		mainTabbedPane.addTab("Decision Content Manager", null, managerPanel);
		mainTabbedPane.addTab("Help Me Decide Wizard", null, HMDWizardPanel);
		frame.getContentPane().add(mainTabbedPane);
		
		quitbtn = new JButton("Quit");
		quitbtn.addActionListener(this);
		quitbtn.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 13));
		quitbtn.setBounds(262, 393, 117, 29);
		frame.getContentPane().add(quitbtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == quitbtn){
			if(!managerPanel.FINAL_SAVE){
				try {
					managerPanel.forceSave();
					System.exit(1);
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(this.frame, e1.getMessage());
				}
			} else {
				System.exit(0);
			}
		}
	}
}
