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
import javax.swing.plaf.basic.BasicProgressBarUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TeamGUI { 	// The team status window for RPG-SVG. Used to obtain
						// information on the team and switch Pokemon.

	/* FIELDS */
	private JFrame frmTeamGUI;
	public int team;
	public int teamsize;
	static int j;
	static int k;
	static JPanel teampanel;
	
	JLabel lblP1 = null, lblP2 = null, lblP3 = null, lblP4 = null, lblP5 = null, lblP6 = null;
	JLabel[] lblNames = {lblP1, lblP2, lblP3, lblP4, lblP5, lblP6};
	
	JProgressBar healthBarP1 = null, healthBarP2 = null, healthBarP3 = null, healthBarP4 = null, healthBarP5 = null, healthBarP6 = null;
	JProgressBar[] healthBars = {healthBarP1, healthBarP2, healthBarP3, healthBarP4, healthBarP5, healthBarP6};
	
	JButton btnSwitchP2 = null, btnSwitchP3 = null, btnSwitchP4 = null, btnSwitchP5 = null, btnSwitchP6 = null;
	JButton[] btnSwitchers = {btnSwitchP2, btnSwitchP3, btnSwitchP4, btnSwitchP5, btnSwitchP6};
	
	JButton btnInfoP1 = null, btnInfoP2 = null, btnInfoP3 = null, btnInfoP4 = null, btnInfoP5 = null, btnInfoP6 = null;
	JButton[] btnInfos = {btnInfoP1, btnInfoP2, btnInfoP3, btnInfoP4, btnInfoP5, btnInfoP6};
	
	JLabel lblTypeP1 = null, lblTypeP2 = null, lblTypeP3 = null, lblTypeP4 = null, lblTypeP5 = null, lblTypeP6 = null;
	JLabel[] lblTypes = {lblTypeP1, lblTypeP2, lblTypeP3, lblTypeP4, lblTypeP5, lblTypeP6};
	
	JLabel lblP1Sprite = null, lblP2Sprite = null, lblP3Sprite = null, lblP4Sprite = null, lblP5Sprite = null, lblP6Sprite = null;
	JLabel[] lblSprites = {lblP1Sprite, lblP2Sprite, lblP3Sprite, lblP4Sprite, lblP5Sprite, lblP6Sprite};

	
	
	/* CONSTRUCTOR */
	public TeamGUI(int i) { // i = 1 displays Team 1, i == 2 displays Team 2
		team = i;
		if (team == 1)
			teamsize = MainGUI.team1.size();
		else if (team == 2)
			teamsize = MainGUI.team2.size();
		initialize();
		frmTeamGUI.setVisible(true);
	}

	/* METHODS */
	private void initialize() {

		
		ImageIcon l = new ImageIcon(this.getClass().getResource("Media/Images/" + MainGUI.theme.bgTeamGUI));
		Image image = l.getImage();
		
		teampanel = new BackImage(image);
		teampanel.setLayout(null);
		
		frmTeamGUI = new JFrame(); // The Window
		
		frmTeamGUI.setContentPane(teampanel);
		
		frmTeamGUI.setAlwaysOnTop(true);
		
		MainGUI.frmMainGUI.setFocusable(false);
		MainGUI.frmMainGUI.setEnabled(false);
		
		frmTeamGUI.setTitle("Pokemon Team Information");
		if (team == 1)
			frmTeamGUI.setTitle("Pokemon Team Information- PLAYER 1");
		if (team == 2)
			frmTeamGUI.setTitle("Pokemon Team Information- PLAYER 2");
		frmTeamGUI.setResizable(false);
		frmTeamGUI.setBounds(260, 375, 600, 390);
		frmTeamGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTeamGUI.getContentPane().setLayout(null);
		
		class WindowEventHandler extends WindowAdapter {
			  public void windowClosing(WindowEvent evt) {
			    MainGUI.frmMainGUI.setEnabled(true);
			  }
			}
		
		frmTeamGUI.addWindowListener(new WindowEventHandler());

		

		JLabel lblInBattle = new JLabel("In Battle");
		lblInBattle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInBattle.setBounds(6, 6, 119, 16);
		frmTeamGUI.getContentPane().add(lblInBattle);
		

		
		
		for(int i = 0; i < lblNames.length; i++)		//initialize name labels
		{
			lblNames[i] = new JLabel("------------");
			lblNames[i].setForeground(Color.BLACK);
			if (team == 1 && (teamsize > i || i == 0))
				lblNames[i].setText(MainGUI.team1.get(i).name);
			else if (team == 2 && (teamsize > i || i == 0))
				lblNames[i].setText(MainGUI.team2.get(i).name);
			lblNames[i].setFont(new Font("Helvetica", Font.BOLD, 18));
			if(i == 0)
				lblNames[i].setBounds(6, 34, 130, 20);
			else
				lblNames[i].setBounds(6, 80 + 50 * i, 130, 20);
			frmTeamGUI.getContentPane().add(lblNames[i]);
		}

		JLabel lblInParty = new JLabel("In Party");
		lblInParty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInParty.setBounds(6, 90, 119, 16);
		frmTeamGUI.getContentPane().add(lblInParty);

		
		
		
		
		for(int i = 0; i < healthBars.length; i++)		//initialize health bars
		{
			healthBars[i] = new JProgressBar();
			healthBars[i].setStringPainted(true);
			healthBars[i].setForeground(Color.GREEN);
			healthBars[i].setUI(new BasicProgressBarUI() {
			      protected Color getSelectionBackground() { return Color.black; }
			      protected Color getSelectionForeground() { return Color.black; }
			    });
			
			if(teamsize <= i)
				healthBars[i].setVisible(false);
			else if (team == 1)
				healthBars[i].setValue(MainGUI.team1.get(i).currenthealth * 100 / MainGUI.team1.get(i).stats[0]);
			else if (team == 2)
				healthBars[i].setValue(MainGUI.team2.get(i).currenthealth * 100 / MainGUI.team2.get(i).stats[0]);
			if(i == 0)
				healthBars[i].setBounds(140, 32, 146, 20);
			else
				healthBars[i].setBounds(140, 80 + 50 * i, 146, 20);
			frmTeamGUI.getContentPane().add(healthBars[i]);
		}
		
		
		
		
		for(int i = 0; i < btnSwitchers.length; i++)	//initialize switch buttons
		{

			btnSwitchers[i] = new JButton("Switch");
			btnSwitchers[i].setForeground(Color.BLACK);
			btnSwitchers[i].setBackground(MainGUI.theme.Color());
			btnSwitchers[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			btnSwitchers[i].setFont(new Font("Helvetica", Font.BOLD, 14));
			try{
				if(team == 1 && MainGUI.team1.get(i+1).currenthealth == 0 || team == 2 && MainGUI.team2.get(i+1).currenthealth == 0)
				{
					btnSwitchers[i].setEnabled(false);
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
			btnSwitchers[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainGUI.frmMainGUI.setEnabled(true);
					for(int i = 0; i < btnSwitchers.length; i++)
					{
						if(e.getSource().equals(btnSwitchers[i]))
							j = i;
					}
					if(team == 1) {
						if(MainGUI.team1.get(0).currenthealth == 0) {
							frmTeamGUI.dispose();
							MainGUI.switchIn(j+1, team);
							if(MainGUI.team2.get(0).currenthealth == 0) {
								new TeamGUI(2);
							}
						} else {
							frmTeamGUI.dispose();
							MainGUI.selectMove((-1 * j) - 2, 1);
						}
					}
					if(team == 2) {
						if(MainGUI.team2.get(0).currenthealth == 0) {
							frmTeamGUI.dispose();
							MainGUI.switchIn(j+1, team);
						} else {
							frmTeamGUI.dispose();
							MainGUI.selectMove((-1 * j) - 2, 2);
						}
					}
				}
			});
			
			btnSwitchers[i].setBounds(502, 126 + 50 * i, 92, 29);
			if(teamsize < (i + 2))
				btnSwitchers[i].setVisible(false);
			frmTeamGUI.getContentPane().add(btnSwitchers[i]);
		}
		
		for(int i = 0; i < btnInfos.length; i++)		//initialize info buttons
		{
			btnInfos[i] = new JButton("Info");
			btnInfos[i].setForeground(Color.BLACK);
			btnInfos[i].setBackground(MainGUI.theme.Color());
			btnInfos[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			btnInfos[i].setFont(new Font("Helvetica", Font.BOLD, 14));
			
			if(teamsize < i + 1)
				btnInfos[i].setVisible(false);
			
			btnInfos[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i = 0; i < btnInfos.length; i++)
					{
						if(e.getSource().equals(btnInfos[i]))
							k = i;
					}
					if(team == 1)
						JOptionPane.showMessageDialog(
								frmTeamGUI, 
								MainGUI.team1.get(k).getFormattedStats(), 
								MainGUI.team1.get(k).name, 2, 
								MainGUI.team1.get(k).sprite1);
					if(team == 2)
						JOptionPane.showMessageDialog(
								frmTeamGUI, 
								MainGUI.team2.get(k).getFormattedStats(), 
								MainGUI.team2.get(k).name, 2, 
								MainGUI.team2.get(k).sprite2);
				}
			});
			if(i == 0)
				btnInfos[i].setBounds(408, 31, 92, 29);
			else
				btnInfos[i].setBounds(408, 76 + 50 * i, 92, 29);
			frmTeamGUI.getContentPane().add(btnInfos[i]);
		}

		
		for(int i = 0; i < lblTypes.length; i++)		//initialize type labels
		{
			lblTypes[i] = new JLabel("Type/Type");
			lblTypes[i].setForeground(Color.BLACK);
			if (team == 1 && (teamsize > i || i == 0))
				lblTypes[i].setText(MainGUI.team1.get(i).getType1() + 
						(MainGUI.team1.get(i).getType2().equals("")?
						"":("/" + MainGUI.team1.get(i).getType2())));
			else if (team == 2 && (teamsize > i || i == 0))
				lblTypes[i].setText(MainGUI.team2.get(i).getType1() + 
						(MainGUI.team2.get(i).getType2().equals("")?
						"":("/" + MainGUI.team2.get(i).getType2())));
			else
				lblTypes[i].setVisible(false);
			lblTypes[i].setFont(new Font("Helvetica", Font.BOLD, 15));
			if(i == 0)
				lblTypes[i].setBounds(291, 34, 119, 20);
			else
				lblTypes[i].setBounds(291, 80 + 50 * i, 119, 20);
			frmTeamGUI.getContentPane().add(lblTypes[i]);
		}


		for(int i = 0; i < lblSprites.length; i++)		//initialize sprite labels
		{
			lblSprites[i] = new JLabel("-");
			if (team == 1 && (teamsize > i || i == 0))
				lblSprites[i] = new JLabel(MainGUI.team1.get(i).minisprite);
			else if (team == 2 && (teamsize > i || i == 0))
				lblSprites[i] = new JLabel(MainGUI.team2.get(i).minisprite);
			else
				lblSprites[i].setVisible(false);
			if(i == 0)
				lblSprites[i].setBounds(104, 22, 32, 32);
			else
				lblSprites[i].setBounds(104, 68 + 50 * i, 32, 32);
			frmTeamGUI.getContentPane().add(lblSprites[i]);
		}
	}
}
