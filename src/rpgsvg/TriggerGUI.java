package rpgsvg;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TriggerGUI {	//Debugger tool for RPG-SVG
							//Displays information about triggers running during moves
	
	//Fields
	private JFrame frmTriggerGUI;
	private JPanel triggerPanel;
	private boolean triggerOpen;
	
	//Constructor
	public TriggerGUI() {
		initialize();
	}

	//Methods
	private void initialize() {
		
		triggerPanel.setLayout(null);
		
		frmTriggerGUI = new JFrame();
		frmTriggerGUI.setContentPane(triggerPanel);
		frmTriggerGUI.setAlwaysOnTop(true);
		
		frmTriggerGUI.setTitle("Pokemon Trigger Debugger");
		
		frmTriggerGUI.setResizable(false);
		frmTriggerGUI.setBounds(120, 175, 300, 200);
		frmTriggerGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTriggerGUI.getContentPane().setLayout(null);
	}
}
