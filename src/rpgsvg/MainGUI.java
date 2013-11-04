package rpgsvg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javax.swing.JFrame;
import javax.sound.midi.*;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JTextArea;

import network.NetworkObject;
import rpgsvg.triggers.EndStatus;
import rpgsvg.triggers.Status;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*	
 _____  _____   _____        _______      _______ 
 |  __ \|  __ \ / ____|      / ____\ \    / / ____|
 | |__) | |__) | |  __ _____| (___  \ \  / / |  __ 
 |  _  /|  ___/| | |_ |______\___ \  \ \/ /| | |_ |
 | | \ \| |    | |__| |      ____) |  \  / | |__| |
 |_|  \_\_|     \_____|     |_____/    \/   \_____|

 Random Pokemon Generator - Simulator Video Game
 		Copyright 2013 The RPG-SVG Team
 	-Steven Chen, Jonathan Grayson, Eric Yu, Raymond Chen, Steven Tran-
 		with networking by Shawn Wu

 *NOTE: REQUIRES JAVA SE 7*
 */

/*
 * CHANGELOG:
 * 
 * Alpha 
 * 		v1.0- functional game, 10 Pokemon, some moves functional, basic mechanics, no abilities, no statuses, no effects (Apr 15, 2013)
 * 		v2.0- upgraded to Java SE 7, 50 Pokemon, updated JList mechanics for Java 7, midi lag issue fixed (Apr 20, 2013)
 * 		v2.1- added all attacking moves to Pokemon, improved victory screen (Apr 23, 2013)
 * 		v2.2- implemented saving and loading of battle .pkmn extension files (Apr 25, 2013)
 * 		v3.0- implemented stat modifying framework, fixed health bar display glitch, added new modifying moves (May 9, 2013)
 * 		v3.1- enabled word wrapping on info console, reorganized Pokemon information window, improved fonts/readability (May 11, 2013)
 * 		v3.2- added delays, timing, and health bar incrementation (May 12, 2013)
 * 		
 * 
 * Beta
 * 		v1.0- new framework for internal files, jar capability, added damage-dependent recoil and absorption, added keyboard input
 * 		v2.0- implemented ability Trigger framework, fixed text display order
 * 		v3.0- networking added
 * 		v4.0- animated sprites, colored type buttons, background, animation panel positioning
 * 		v5.0- transparent status panel, power points for moves, Generation 7 move and type changes, new background, graphical optimization, text enhancement
 * 
 * 		GIT Support: Woohoo! RPG-SVG is now in the cloud!
 */

public class MainGUI { // The main game window for RPG-SVG.

	/* FIELDS */

	public static ArrayList<Pokemon> team1;
	public static ArrayList<Pokemon> team2;

	static int actionSelectedP1;
	static int actionSelectedP2;
	static Battle battle;
	static JPanel mainpanel;
	static JLabel lblPictureP1;
	static JLabel lblPictureP2;
	static JPanel panelP1;
	static JPanel panelP2;
	static JProgressBar healthBarP1;
	static JProgressBar healthBarP2;
	static JLabel lblHealthP1;
	static JLabel lblHealthP2;
	static JLabel lblP1;
	static JLabel lblP2;
	static JButton btnP1M1;
	static JButton btnP1M2;
	static JButton btnP1M3;
	static JButton btnP1M4;
	static JButton btnP2M1;
	static JButton btnP2M2;
	static JButton btnP2M3;
	static JButton btnP2M4;
	static JButton btnP1Team;
	static JButton btnP2Team;
	static JButton btnBegin;
	static JFrame frmMainGUI;
	static Sequencer sequencer;
	public static JTextArea txtInfo;
	static JLabel lblStatusP1;
	static JLabel lblStatusP2;

	static boolean p1isDone;
	static boolean p2isDone;
	static int team;
	public static NetworkObject network;
	private static Random random;
	
	static MediaPlayer mediaPlayer;



	/**
	 * @wbp.parser.entryPoint
	 */

	/* CONSTRUCTOR */
	public MainGUI(List<Pokemon> a, List<Pokemon> b, int team, Random random, NetworkObject net) { 
		 try {
			    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
			 } catch (Exception e) {
			            e.printStackTrace();
			 }		
		team1 = (ArrayList<Pokemon>) a;
		team2 = (ArrayList<Pokemon>) b;
		MainGUI.team = team;
		MainGUI.random = random;
		network = net;
		System.out.println("Team 1: " + team1.toString());
		System.out.println("Team 2: " + team2.toString());
		
		initialize();
		
		playMusic();
		
		
	}

	
	
	private void playMusic() {
		Platform.runLater(new Runnable() {
			@Override public void run() {
				
		        
		        final URL u = getClass().getResource("Media/Audio/Poke.mp3");
		        final Media hit = new Media(u.toString());
		        mediaPlayer = new MediaPlayer(hit);
		        mediaPlayer.play();
		        mediaPlayer.setAutoPlay(true);
		        mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		      }
		});
	}
	
	/* METHODS */
	private void initialize() {

		battle = new Battle(team1, team2, random);

		

		
		Image image = null;
		
		try{
			ImageIcon i = new ImageIcon(this.getClass().getResource("Media/Images/background.jpeg"));
			image = i.getImage();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		mainpanel = new BackImage(image);
		mainpanel.setLayout(null);
		
		frmMainGUI = new JFrame();
		
		@SuppressWarnings("unused")
		final JFXPanel fxPanel = new JFXPanel();
		
		
		
		
		
		if(team == 1)
			frmMainGUI.setTitle("Random Pokemon Generator-Simulator Video Game SERVER");
		else
			frmMainGUI.setTitle("Random Pokemon Generator-Simulator Video Game CLIENT");
		frmMainGUI.setBounds(150, 50, 800, 500);
		frmMainGUI.setResizable(false);
		frmMainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainGUI.getContentPane().setLayout(null);
		frmMainGUI.setContentPane(mainpanel);

		mainpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("1"), "m1p1");
		mainpanel.getActionMap().put("m1p1", new SelectAttack(0, 1));
		mainpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("2"), "m2p1");
		mainpanel.getActionMap().put("m2p1", new SelectAttack(1, 1));
		mainpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("3"), "m3p1");
		mainpanel.getActionMap().put("m3p1", new SelectAttack(2, 1));
		mainpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("4"), "m4p1");
		mainpanel.getActionMap().put("m4p1", new SelectAttack(3, 1));
		mainpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("7"), "m1p2");
		mainpanel.getActionMap().put("m1p2", new SelectAttack(0, 2));
		mainpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("8"), "m2p2");
		mainpanel.getActionMap().put("m2p2", new SelectAttack(1, 2));
		mainpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("9"), "m3p2");
		mainpanel.getActionMap().put("m3p2", new SelectAttack(2, 2));
		mainpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("0"), "m4p2");
		mainpanel.getActionMap().put("m4p2", new SelectAttack(3, 2));
		mainpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("B"), "begin");
		mainpanel.getActionMap().put("begin", new BeginTurn());

		panelP1 = new JPanel(); // P1 Sprite Region
		panelP1.setOpaque(false);
		panelP1.setBounds(50, 23, 192, 192);
		panelP1.setPreferredSize(new Dimension(192, 192));
		lblPictureP1 = new JLabel();
		panelP1.setBounds(50  + (192-team1.get(0).sprite1.getIconWidth())/2, 23 + (192-team1.get(0).sprite1.getIconHeight())/2, team1.get(0).sprite1.getIconWidth() + 10, team1.get(0).sprite1.getIconHeight() + 10);
		lblPictureP1.setIcon(team1.get(0).sprite1);
		lblPictureP1.setToolTipText(team1.get(0).getFormattedHTMLStats());
		panelP1.add(lblPictureP1);
		mainpanel.add(panelP1);
		

		panelP2 = new JPanel(); // P2 Sprite Region
		panelP2.setOpaque(false);
		panelP2.setBounds(558, 23, 192, 192);
		panelP2.setPreferredSize(new Dimension(192, 192));
		lblPictureP2 = new JLabel();
		panelP2.setBounds(558 + (192-team2.get(0).sprite2.getIconWidth())/2, 23 + (192-team2.get(0).sprite2.getIconHeight())/2, team2.get(0).sprite2.getIconWidth() + 10, team2.get(0).sprite2.getIconHeight() + 10);
		lblPictureP2.setIcon(team2.get(0).sprite2);
		lblPictureP2.setToolTipText(team2.get(0).getFormattedHTMLStats());
		panelP2.add(lblPictureP2);
		mainpanel.add(panelP2);
		
		/* Steven T. made statusPanel font bold and centered some base text */
		JScrollPane infoPane = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		infoPane.setBounds(310, 130, 200, 166);
		infoPane.setOpaque(false);
		infoPane.setBorder(null);
		mainpanel.add(infoPane);

		txtInfo = new JTextArea(); // Info Console
		txtInfo.setOpaque(false);
		txtInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfo.setEditable(false);
		txtInfo.setLineWrap(true);
		txtInfo.setWrapStyleWord(true);
		txtInfo.setText("-------BEGIN BATTLE!-------\n");
		appendText("\nPlayer 1 sent out " + team1.get(0).name + "!\n");
		appendText("\nPlayer 2 sent out " + team2.get(0).name + "!\n");
		appendText("\n----- Select your actions -----\n");
		infoPane.setViewportView(txtInfo);
		infoPane.getViewport().setOpaque(false);

		JLabel lblHP1 = new JLabel("HP");
		lblHP1.setFont(new Font("Britannic Bold", Font.BOLD, 15));
		lblHP1.setBounds(22, 255, 30, 16);
		mainpanel.add(lblHP1);

		JLabel lblHP2 = new JLabel("HP");
		lblHP2.setFont(new Font("Britannic Bold", Font.BOLD, 15));
		lblHP2.setBounds(529, 255, 30, 16);
		mainpanel.add(lblHP2);

		healthBarP1 = new JProgressBar(); // P1 Health Bar
		healthBarP1.setBounds(50, 255, 225, 20);
		healthBarP1.setValue(100);
		mainpanel.add(healthBarP1);

		healthBarP2 = new JProgressBar(); // P2 Health Bar
		healthBarP2.setBounds(558, 255, 225, 20);
		healthBarP2.setValue(100);
		mainpanel.add(healthBarP2);

		lblHealthP1 = new JLabel(team1.get(0).stats[0] + "/"
				+ team1.get(0).stats[0]); // P1 Health Label
		lblHealthP1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblHealthP1.setBounds(214, 283, 61, 16);
		mainpanel.add(lblHealthP1);

		lblHealthP2 = new JLabel(team2.get(0).stats[0] + "/"
				+ team2.get(0).stats[0]); // P2 Health Label
		lblHealthP2.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblHealthP2.setBounds(722, 283, 61, 16);
		mainpanel.add(lblHealthP2);

		lblP1 = new JLabel(team1.get(0).name); // P1 Name Label
		lblP1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblP1.setBounds(22, 220, 220, 23);
		mainpanel.add(lblP1);

		lblP2 = new JLabel(team2.get(0).name); // P2 Name Label
		lblP2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblP2.setBounds(529, 220, 221, 23);
		mainpanel.add(lblP2);

		/* BEGIN CHANGES STEVENZC 10-31-2013 */
		
		btnP1M1 = new JButton(team1.get(0).moves[0].name); // P1 Move1
		btnP1M1.setFocusable(false);
		btnP1M1.setOpaque(true);
		btnP1M1.setBorderPainted(true);
		btnP1M1.setForeground(Color.BLACK);
		btnP1M1.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnP1M1.setToolTipText("Type: " + team1.get(0).moves[0].getType()
				+ "  Accuracy: " + team1.get(0).moves[0].accuracy);
		btnP1M1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectMove(0, 1);
			}});
		btnP1M1.setBounds(6, 310, 150, 40);
		mainpanel.add(btnP1M1);

		btnP1M2 = new JButton(team1.get(0).moves[1].name); // P1 Move2
		btnP1M2.setFocusable(false);
		btnP1M2.setOpaque(true);
		btnP1M2.setBorderPainted(true);
		btnP1M2.setForeground(Color.BLACK);
		btnP1M2.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnP1M2.setToolTipText("Type: " + team1.get(0).moves[1].getType()
				+ "  Accuracy: " + team1.get(0).moves[1].accuracy);
		btnP1M2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectMove(1, 1);
			}});
		btnP1M2.setBounds(157, 310, 150, 40);
		mainpanel.add(btnP1M2);

		btnP1M3 = new JButton(team1.get(0).moves[2].name); // P1 Move3
		btnP1M3.setFocusable(false);
		btnP1M3.setOpaque(true);
		btnP1M3.setBorderPainted(true);
		btnP1M3.setForeground(Color.BLACK);
		btnP1M3.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnP1M3.setToolTipText("Type: " + team1.get(0).moves[2].getType()
				+ "  Accuracy: " + team1.get(0).moves[2].accuracy);
		btnP1M3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectMove(2, 1);
			}});
		btnP1M3.setBounds(6, 351, 150, 40);
		mainpanel.add(btnP1M3);

		btnP1M4 = new JButton(team1.get(0).moves[3].name); // P1 Move4
		btnP1M4.setFocusable(false);
		btnP1M4.setOpaque(true);
		btnP1M4.setBorderPainted(true);
		btnP1M4.setForeground(Color.BLACK);
		btnP1M4.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnP1M4.setToolTipText("Type: " + team1.get(0).moves[3].getType()
				+ "  Accuracy: " + team1.get(0).moves[3].accuracy);
		btnP1M4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectMove(3, 1);
			}});
		btnP1M4.setBounds(157, 351, 150, 40);
		mainpanel.add(btnP1M4);

		btnP2M1 = new JButton(team2.get(0).moves[0].name); // P2 Move1
		btnP2M1.setFocusable(false);
		btnP2M1.setOpaque(true);
		btnP2M1.setBorderPainted(true);
		btnP2M1.setForeground(Color.BLACK);
		btnP2M1.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnP2M1.setToolTipText("Type: " + team2.get(0).moves[0].getType()
				+ "  Accuracy: " + team2.get(0).moves[0].accuracy);
		btnP2M1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectMove(0, 2);
			}});
		btnP2M1.setBounds(493, 310, 150, 40);
		mainpanel.add(btnP2M1);

		btnP2M2 = new JButton(team2.get(0).moves[1].name); // P2 Move2
		btnP2M2.setFocusable(false);
		btnP2M2.setOpaque(true);
		btnP2M2.setBorderPainted(true);
		btnP2M2.setForeground(Color.BLACK);
		btnP2M2.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnP2M2.setToolTipText("Type: " + team2.get(0).moves[1].getType()
				+ "  Accuracy: " + team2.get(0).moves[1].accuracy);
		btnP2M2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectMove(1, 2);
			}});
		btnP2M2.setBounds(644, 310, 150, 40);
		mainpanel.add(btnP2M2);

		btnP2M3 = new JButton(team2.get(0).moves[2].name); // P2 Move3
		btnP2M3.setFocusable(false);
		btnP2M3.setOpaque(true);
		btnP2M3.setBorderPainted(true);
		btnP2M3.setForeground(Color.BLACK);
		btnP2M3.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnP2M3.setToolTipText("Type: " + team2.get(0).moves[2].getType()
				+ "  Accuracy: " + team2.get(0).moves[2].accuracy);
		btnP2M3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectMove(2, 2);
			}});
		btnP2M3.setBounds(493, 351, 150, 40);
		mainpanel.add(btnP2M3);

		btnP2M4 = new JButton(team2.get(0).moves[3].name); // P2 Move4
		btnP2M4.setFocusable(false);
		btnP2M4.setOpaque(true);
		btnP2M4.setBorderPainted(true);
		btnP2M4.setForeground(Color.BLACK);
		btnP2M4.setFont(new Font("Helvetica", Font.BOLD, 14));
		btnP2M4.setToolTipText("Type: " + team2.get(0).moves[3].getType()
				+ "  Accuracy: " + team2.get(0).moves[3].accuracy);
		btnP2M4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectMove(3, 2);
			}});
		btnP2M4.setBounds(644, 351, 150, 40);
		mainpanel.add(btnP2M4);
		
		
		btnP1M1.setBackground(new Color(255, 250, 245));
		btnP1M2.setBackground(new Color(255, 250, 245));
		btnP1M3.setBackground(new Color(255, 250, 245));
		btnP1M4.setBackground(new Color(255, 250, 245));
		btnP2M1.setBackground(new Color(255, 250, 245));
		btnP2M2.setBackground(new Color(255, 250, 245));
		btnP2M3.setBackground(new Color(255, 250, 245));
		btnP2M4.setBackground(new Color(255, 250, 245));

		btnP1Team = new JButton("Team Status / Switch Pokemon"); 	// Team 1 TeamGUI Opener
		btnP1Team.setFont(new Font("Helvetica", Font.BOLD, 13));
		btnP1Team.setBackground(new Color(255, 250, 245));
		btnP1Team.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TeamGUI(1);
			}});
		btnP1Team.setBounds(6, 430, 300, 29);
		mainpanel.add(btnP1Team);

		btnP2Team = new JButton("Team Status / Switch Pokemon"); 	// Team 2 TeamGUI Opener
		btnP2Team.setFont(new Font("Helvetica", Font.BOLD, 13));
		btnP2Team.setBackground(new Color(255, 250, 245));
		btnP2Team.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TeamGUI(2);
			}});
		btnP2Team.setBounds(493, 430, 300, 29);
		mainpanel.add(btnP2Team);

		

		JButton btnSaveLoad = new JButton("SAVE / LOAD");
		btnSaveLoad.setBounds(341, 430, 117, 29);
		btnSaveLoad.setEnabled(true);
		btnSaveLoad.setFont(new Font("Helvetica", Font.BOLD, 12));
		btnSaveLoad.setBackground(new Color(255, 250, 245));
		btnSaveLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaveGUI(1);
			}
		});
		mainpanel.add(btnSaveLoad);

		lblStatusP1 = new JLabel("");
		lblStatusP1.setBounds(245, 227, 30, 16);
		mainpanel.add(lblStatusP1);

		lblStatusP2 = new JLabel("");
		lblStatusP2.setBounds(753, 227, 30, 16);
		mainpanel.add(lblStatusP2);
		
		/* END EDIT STEVENZC 10-31-13*/
		
		
		//Enable the right buttons
		if(team == 1) {
			btnP1M1.setEnabled(team1.get(0).moves[0].pp > 0);
			btnP1M2.setEnabled(team1.get(0).moves[1].pp > 0);
			btnP1M3.setEnabled(team1.get(0).moves[2].pp > 0);
			btnP1M4.setEnabled(team1.get(0).moves[3].pp > 0);
			btnP1Team.setEnabled(true);
			btnP2M1.setEnabled(false);
			btnP2M2.setEnabled(false);
			btnP2M3.setEnabled(false);
			btnP2M4.setEnabled(false);
			btnP2Team.setEnabled(false);
		} else if(team == 2) {
			btnP2M1.setEnabled(team2.get(0).moves[0].pp > 0);
			btnP2M2.setEnabled(team2.get(0).moves[1].pp > 0);
			btnP2M3.setEnabled(team2.get(0).moves[2].pp > 0);
			btnP2M4.setEnabled(team2.get(0).moves[3].pp > 0);
			btnP2Team.setEnabled(true);
			btnP1M1.setEnabled(false);    
			btnP1M2.setEnabled(false);    
			btnP1M3.setEnabled(false);    
			btnP1M4.setEnabled(false);    
			btnP1Team.setEnabled(false);
		}
		refreshButtons();
		
		
	}
	/* BEGIN EDIT Steven T. victory fix, see Battle class 11-3-13 */
	public static void refresh() // The light refresh method after a battle turn
									// has been completed.
	{

		if (team1.get(0).currenthealth > 0) {
			refreshButtons();
		}

		if (team1.get(0).currenthealth != 0
				&& team2.get(0).currenthealth != 0)
			appendText("\n----- Select your actions -----\n");

	}

	public static void switchRefresh(Pokemon switchout, Pokemon switchin) // Refreshes the main window after a player switches Pokemon.
	{
		battle.removeTriggers(switchout);
		battle.addTrigger(switchin.ability);

		battle.addTrigger(new Status(switchin));
		battle.addTrigger(new EndStatus(switchin));

		System.out.println(team1 + "   " + team2);

		lblPictureP1.setIcon(team1.get(0).sprite1);
		lblPictureP2.setIcon(team2.get(0).sprite2);
		
		panelP1.setBounds(50  + (192-team1.get(0).sprite1.getIconWidth())/2, 23 + (192-team1.get(0).sprite1.getIconHeight())/2, team1.get(0).sprite1.getIconWidth() + 10, team1.get(0).sprite1.getIconHeight() + 10);
		panelP2.setBounds(558 + (192-team2.get(0).sprite2.getIconWidth())/2, 23 + (192-team2.get(0).sprite2.getIconHeight())/2, team2.get(0).sprite2.getIconWidth() + 10, team2.get(0).sprite2.getIconHeight() + 10);

		lblP1.setText(team1.get(0).name);
		lblP2.setText(team2.get(0).name);

		lblHealthP1.setText(team1.get(0).currenthealth + "/"
				+ team1.get(0).stats[0]);
		lblHealthP2.setText(team2.get(0).currenthealth + "/"
				+ team2.get(0).stats[0]);
		int x = team1.get(0).currenthealth * 100 / team1.get(0).stats[0];
		int y = team2.get(0).currenthealth * 100 / team2.get(0).stats[0];

		healthBarP1.setValue(x);
		healthBarP1.setEnabled(false);
		healthBarP1.setEnabled(true);
		healthBarP2.setValue(y);
		healthBarP2.setEnabled(false);
		healthBarP2.setEnabled(true);

		System.out.println("New Pokemon Information:"
				+ team1.get(0).getFormattedStats());

		lblPictureP1.setToolTipText(team1.get(0).getFormattedHTMLStats());
		lblPictureP2.setToolTipText(team2.get(0).getFormattedHTMLStats());

		btnP1M1.setText(team1.get(0).moves[0].name);
		btnP1M2.setText(team1.get(0).moves[1].name);
		btnP1M3.setText(team1.get(0).moves[2].name);
		btnP1M4.setText(team1.get(0).moves[3].name);

		btnP2M1.setText(team2.get(0).moves[0].name);
		btnP2M2.setText(team2.get(0).moves[1].name);
		btnP2M3.setText(team2.get(0).moves[2].name);
		btnP2M4.setText(team2.get(0).moves[3].name);
		
		//Enable the right buttons
		refreshButtons();

		btnP1M1.setToolTipText("Type: " + team1.get(0).moves[0].getType()
				+ "  Accuracy: " + team1.get(0).moves[0].accuracy);
		btnP1M2.setToolTipText("Type: " + team1.get(0).moves[1].getType()
				+ "  Accuracy: " + team1.get(0).moves[1].accuracy);
		btnP1M3.setToolTipText("Type: " + team1.get(0).moves[2].getType()
				+ "  Accuracy: " + team1.get(0).moves[2].accuracy);
		btnP1M4.setToolTipText("Type: " + team1.get(0).moves[3].getType()
				+ "  Accuracy: " + team1.get(0).moves[3].accuracy);

		btnP2M1.setToolTipText("Type: " + team2.get(0).moves[0].getType()
				+ "  Accuracy: " + team2.get(0).moves[0].accuracy);
		btnP2M2.setToolTipText("Type: " + team2.get(0).moves[1].getType()
				+ "  Accuracy: " + team2.get(0).moves[1].accuracy);
		btnP2M3.setToolTipText("Type: " + team2.get(0).moves[2].getType()
				+ "  Accuracy: " + team2.get(0).moves[2].accuracy);
		btnP2M4.setToolTipText("Type: " + team2.get(0).moves[3].getType()
				+ "  Accuracy: " + team2.get(0).moves[3].accuracy);

		team1.get(0).setStatusText();
		team2.get(0).setStatusText();

		frmMainGUI.repaint();
	}

	public static void save(String s) {
		try {
			FileOutputStream saveFile = new FileOutputStream(s + ".pkmn");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(battle);
			save.close();
		} catch (Exception e) {
		}
	}

	public static void load(String s) {
		try {
			FileInputStream saveFile = new FileInputStream(s + ".pkmn");
			ObjectInputStream save = new ObjectInputStream(saveFile);
			battle = (Battle) save.readObject();
			save.close();
			loadRefresh();
			refresh();
		} catch (Exception ef) {
		}
	}
	
	public static Color getBtnBackgrounds(Move m)	//Chooses the correct Color for a button, based on the move's type int
	{
		return m.mtype.color();
	}
	
	public static void refreshButtons() {		//Enable the right buttons
		if(team == 1) {
			btnP1M1.setEnabled(team1.get(0).moves[0].pp > 0);
			btnP1M2.setEnabled(team1.get(0).moves[1].pp > 0);
			btnP1M3.setEnabled(team1.get(0).moves[2].pp > 0);
			btnP1M4.setEnabled(team1.get(0).moves[3].pp > 0);
			btnP1Team.setEnabled(true);
			btnP2M1.setEnabled(false);
			btnP2M2.setEnabled(false);
			btnP2M3.setEnabled(false);
			btnP2M4.setEnabled(false);
			btnP2Team.setEnabled(false);
		} else if(team == 2) {
			btnP2M1.setEnabled(team2.get(0).moves[0].pp > 0);
			btnP2M2.setEnabled(team2.get(0).moves[1].pp > 0);
			btnP2M3.setEnabled(team2.get(0).moves[2].pp > 0);
			btnP2M4.setEnabled(team2.get(0).moves[3].pp > 0);
			btnP2Team.setEnabled(true);
			btnP1M1.setEnabled(false);    
			btnP1M2.setEnabled(false);    
			btnP1M3.setEnabled(false);    
			btnP1M4.setEnabled(false);    
			btnP1Team.setEnabled(false);
		} else if(team == 0) {
			btnP1M1.setEnabled(team1.get(0).moves[0].pp > 0);
			btnP1M2.setEnabled(team1.get(0).moves[1].pp > 0);
			btnP1M3.setEnabled(team1.get(0).moves[2].pp > 0);
			btnP1M4.setEnabled(team1.get(0).moves[3].pp > 0);
			btnP1Team.setEnabled(true);
			btnP2M1.setEnabled(team2.get(0).moves[0].pp > 0);
			btnP2M2.setEnabled(team2.get(0).moves[1].pp > 0);
			btnP2M3.setEnabled(team2.get(0).moves[2].pp > 0);
			btnP2M4.setEnabled(team2.get(0).moves[3].pp > 0);
			btnP2Team.setEnabled(true);
		}
		
		btnP1M1.setBorder(BorderFactory.createLineBorder(getBtnBackgrounds(team1.get(0).moves[0]), 3));
		btnP1M2.setBorder(BorderFactory.createLineBorder(getBtnBackgrounds(team1.get(0).moves[1]), 3));
		btnP1M3.setBorder(BorderFactory.createLineBorder(getBtnBackgrounds(team1.get(0).moves[2]), 3));
		btnP1M4.setBorder(BorderFactory.createLineBorder(getBtnBackgrounds(team1.get(0).moves[3]), 3));
		btnP2M1.setBorder(BorderFactory.createLineBorder(getBtnBackgrounds(team2.get(0).moves[0]), 3));
		btnP2M2.setBorder(BorderFactory.createLineBorder(getBtnBackgrounds(team2.get(0).moves[1]), 3));
		btnP2M3.setBorder(BorderFactory.createLineBorder(getBtnBackgrounds(team2.get(0).moves[2]), 3));
		btnP2M4.setBorder(BorderFactory.createLineBorder(getBtnBackgrounds(team2.get(0).moves[3]), 3));
		
		
		
	}

	public static void loadRefresh() {
		System.out.println(team1 + "   " + team2);

		lblPictureP1.setIcon(team1.get(0).sprite1);
		lblPictureP2.setIcon(team2.get(0).sprite2);

		lblP1.setText(team1.get(0).name);
		lblP2.setText(team2.get(0).name);

		lblHealthP1.setText(team1.get(0).currenthealth + "/"
				+ team1.get(0).stats[0]);
		lblHealthP2.setText(team2.get(0).currenthealth + "/"
				+ team2.get(0).stats[0]);
		int x = team1.get(0).currenthealth * 100 / team1.get(0).stats[0];
		int y = team2.get(0).currenthealth * 100 / team2.get(0).stats[0];

		healthBarP1.setValue(x);
		healthBarP1.setEnabled(false);
		healthBarP1.setEnabled(true);
		healthBarP2.setValue(y);
		healthBarP2.setEnabled(false);
		healthBarP2.setEnabled(true);

		System.out.println("New Pokemon information:"
				+ team1.get(0).getFormattedStats());

		lblPictureP1.setToolTipText(team1.get(0).getFormattedHTMLStats());
		lblPictureP2.setToolTipText(team2.get(0).getFormattedHTMLStats());

		btnP1M1.setText(team1.get(0).moves[0].name);
		btnP1M2.setText(team1.get(0).moves[1].name);
		btnP1M3.setText(team1.get(0).moves[2].name);
		btnP1M4.setText(team1.get(0).moves[3].name);

		btnP2M1.setText(team2.get(0).moves[0].name);
		btnP2M2.setText(team2.get(0).moves[1].name);
		btnP2M3.setText(team2.get(0).moves[2].name);
		btnP2M4.setText(team2.get(0).moves[3].name);

		btnP1M1.setToolTipText("Type: " + team1.get(0).moves[0].getType()
				+ "  Accuracy: " + team1.get(0).moves[0].accuracy);
		btnP1M2.setToolTipText("Type: " + team1.get(0).moves[1].getType()
				+ "  Accuracy: " + team1.get(0).moves[1].accuracy);
		btnP1M3.setToolTipText("Type: " + team1.get(0).moves[2].getType()
				+ "  Accuracy: " + team1.get(0).moves[2].accuracy);
		btnP1M4.setToolTipText("Type: " + team1.get(0).moves[3].getType()
				+ "  Accuracy: " + team1.get(0).moves[3].accuracy);

		btnP2M1.setToolTipText("Type: " + team2.get(0).moves[0].getType()
				+ "  Accuracy: " + team2.get(0).moves[0].accuracy);
		btnP2M2.setToolTipText("Type: " + team2.get(0).moves[1].getType()
				+ "  Accuracy: " + team2.get(0).moves[1].accuracy);
		btnP2M3.setToolTipText("Type: " + team2.get(0).moves[2].getType()
				+ "  Accuracy: " + team2.get(0).moves[2].accuracy);
		btnP2M4.setToolTipText("Type: " + team2.get(0).moves[3].getType()
				+ "  Accuracy: " + team2.get(0).moves[3].accuracy);

		team1.get(0).setStatusText();
		team2.get(0).setStatusText();

		frmMainGUI.repaint();

	}

	public static void selectMove(int action, int player) {
		if (player == 1) {
			actionSelectedP1 = action;
			btnP1M1.setEnabled(false);
			btnP1M2.setEnabled(false);
			btnP1M3.setEnabled(false);
			btnP1M4.setEnabled(false);
			btnP1Team.setEnabled(false);
			p1isDone = true;
			appendText("\nPlayer 1 has selected! ");
			if(!p2isDone) appendText("Waiting for Player 2.\n");
			else appendText("\n");
		} else if (player == 2) {
			actionSelectedP2 = action;
			btnP2M1.setEnabled(false);
			btnP2M2.setEnabled(false);
			btnP2M3.setEnabled(false);
			btnP2M4.setEnabled(false);
			btnP2Team.setEnabled(false);
			p2isDone = true;
			appendText("\nPlayer 2 has selected! ");
			if(!p2isDone) appendText("Waiting for Player 1.\n");
			else appendText("\n");
		} else {
			System.out.println("Invalid player.");
		}
		
		if(player == team) {
			network.sendMessage("MOVE "+action);
		}
		
		if(p1isDone && p2isDone) {
			begin();
		}
		
	}
	
	public static void switchIn(int action, int player) {
		if(player == 1) {
			Pokemon p = MainGUI.team1.remove(action);
			Pokemon temp = MainGUI.team1.remove(0);
			team1.add(0, p);
			team1.add(action, temp);
			battle.refresh(player, p);
			appendText("\nPlayer 1 withdrew " + temp.name + "!\n");
			appendText("\nPlayer 1 sent out " + p.name + "!\n");
			switchRefresh(temp, p);
			refresh();
		} else if(player == 2) {
			Pokemon p = MainGUI.team2.remove(action);
			Pokemon temp = MainGUI.team2.remove(0);
			team2.add(0, p);
			team2.add(action, temp);
			battle.refresh(player, p);
			appendText("\nPlayer 2 withdrew " + temp.name + "!\n");
			appendText("\nPlayer 2 sent out " + p.name + "!\n");
			switchRefresh(temp, p);
			refresh();
		}
		
		if(player == team) {
			network.sendMessage("SWITCH "+action);
		}
	}

	public static void begin() {
		appendText("\n-------ROUND BEGIN!-------\n");
		//btnBegin.setEnabled(false);
		battle.run(actionSelectedP1, actionSelectedP2);
		System.out.println("P1: " + actionSelectedP1 + "  P2: "
				+ actionSelectedP2);
		p1isDone = false;
		p2isDone = false;
	}
	
	public static void appendText(String text) {
		txtInfo.append(text);
		int x;
		txtInfo.selectAll();
		x = txtInfo.getSelectionEnd();
		txtInfo.select(x,x);
	}

	public class SelectAttack extends AbstractAction implements Serializable {
		private static final long serialVersionUID = 1L;
		private int a;
		private int p;

		public SelectAttack(int attack, int player) {
			a = attack;
			p = player;
		}

		public void actionPerformed(ActionEvent e) {
			selectMove(a, p);
		}
	}

	public class BeginTurn extends AbstractAction implements Serializable {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			if (btnBegin.isEnabled())
				begin();
		}
	}
}
