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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

import network.NetworkObject;
import rpgsvg.triggers.EndStatus;
import rpgsvg.triggers.Status;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*	
						 _____  _____   _____        _______      ________ 
						 |  __ \|  __ \ / ____|      / ____\ \    / / ____|
						 | |__) | |__) | |  __ _____| (___  \ \  / / |  __ 
						 |  _  /|  ___/| | |_ |______\___ \  \ \/ /| | |_ |
						 | | \ \| |    | |__| |      ____) |  \  / | |__| |
						 |_|  \_\_|     \_____|     |_____/    \/   \_____|
						
						 Random Pokemon Generator - Simulator Video Game
						 		Copyright 2013 The RPG-SVG Team
				-Steven Chen, Jonathan Grayson, Eric Yu, Raymond Chen, Steven Tran-
						 			networking by Shawn Wu
						 		
						Based on the Pokemon game series by Nintendo
						 		
						 				credit to:
			GAME FREAK- Satoshi Tajiri, Ken Sugimori, Junichi Masuda, Shota Kageyama
						 		Pokecheck Sprites Database
						 			Smogon University
						 			Pokemon Database
						 				Pokeparaiso
						 				Serebii
						 		

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
 * 		v6.0- improved interface, themes, 
 * 
 * 		
 * 		
 * 		
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
	
	static JButton btnP1M1 = new JButton();
	static JButton btnP1M2 = new JButton();
	static JButton btnP1M3 = new JButton();
	static JButton btnP1M4 = new JButton();
	static JButton[] p1Buttons = {btnP1M1, btnP1M2, btnP1M3, btnP1M4};
	
	static JButton btnP2M1 = new JButton();
	static JButton btnP2M2 = new JButton();
	static JButton btnP2M3 = new JButton();
	static JButton btnP2M4 = new JButton();
	
	static JButton[] p2Buttons = {btnP2M1, btnP2M2, btnP2M3, btnP2M4};
	
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
	static MediaPlayer normalPlayer;
	static MediaPlayer superPlayer;
	static MediaPlayer resistPlayer;

	static boolean playMusic = true;

	public enum Theme {
		
		GROUND	(0, "MainGUIground.jpeg", "TeamGUIground.jpeg", "gymbattle.mp3", new Color(255, 250, 245)),
		ROCK	(1, "MainGUIrock.jpeg", "TeamGUIrock.jpeg", "johtochampion.mp3", new Color(227, 227, 227)),
		WATER	(2, "MainGUIwater.jpeg", "TeamGUIwater.jpeg", "laketrio.mp3", new Color(202, 221, 251)),
		GRASS	(3, "MainGUIgrass.jpeg", "TeamGUIgrass.jpeg", "nbattle.mp3", new Color(215, 252, 191)),
		FIRE	(4, "MainGUIfire.jpeg", "TeamGUIfire.jpeg", "cynthia.mp3", new Color(254, 222, 0));
		
		private final int id;
		private final String bgMainGUI;
		final String bgTeamGUI;
		private final String music;
		private final Color color;
		
		Theme(int x, String a, String b, String c, Color i)
		{
			this.id = x;
			this.bgMainGUI = a;
			this.bgTeamGUI = b;
			this.music = c;
			this.color = i;
		}
		
		
		
		public int ID() {
			return this.id;
		}
		
		public String MainGUI() {
			return this.bgMainGUI;
		}
		
		public String TeamGUI() {
			return this.bgTeamGUI;
		}
		
		public String Music() {
			return this.music;
		}
		
		public Color Color() {
			return this.color;
		}
	}
	
	static Theme theme;
	

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
		
		if(playMusic)
		{
			Platform.runLater(new Runnable() {
			@Override public void run() {
				
		        final URL u = getClass().getResource("Media/Audio/" + theme.music);
		        final Media hit = new Media(u.toString());
		        mediaPlayer = new MediaPlayer(hit);
		        mediaPlayer.play();
		        mediaPlayer.setAutoPlay(true);
		        mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		      }
		});
		}
		
		
	}
	
	
	
	/* METHODS */
	private void initialize() {

		battle = new Battle(team1, team2, random);

		Image image = null;
		
		try{
			int j = (int)(Math.random() * 5);
			for(Theme x: Theme.values())
			{
				if(j == x.ID())
				{
					theme = x;
				}
			}
			
			ImageIcon i = new ImageIcon(this.getClass().getResource("Media/Images/" + theme.bgMainGUI));
			image = i.getImage();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		mainpanel = new BackImage(image);
		mainpanel.setLayout(null);
		
		frmMainGUI = new JFrame();
		
		
		if(playMusic)
		{
			@SuppressWarnings("unused")
		final JFXPanel fxPanel = new JFXPanel();	//initializes JavaFX for MediaPlayer function
		
		Platform.runLater(new Runnable() {
			@Override public void run() {
				
				final JFXPanel fxPanel = new JFXPanel();
				
		        final URL normal = getClass().getResource("Media/Audio/normalhit.mp3");
		        final Media hit = new Media(normal.toString());
		        normalPlayer = new MediaPlayer(hit);
		        
		        final URL supr = getClass().getResource("Media/Audio/superhit.mp3");
		        final Media hits = new Media(supr.toString());
		        superPlayer = new MediaPlayer(hits);
		        
		        final URL resist = getClass().getResource("Media/Audio/resisthit.mp3");
		        final Media hitr = new Media(resist.toString());
		        resistPlayer = new MediaPlayer(hitr);
		      }
		});
		}
		
		
		
		
		if(team == 1)
			frmMainGUI.setTitle("Random Pokemon Generator-Simulator Video Game");
		else
			frmMainGUI.setTitle("Random Pokemon Generator-Simulator Video Game");
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
		panelP1.setBounds(50  + (192-team1.get(0).sprite1.getIconWidth())/2,
						  23 + (192-team1.get(0).sprite1.getIconHeight())/2,
						  team1.get(0).sprite1.getIconWidth() + 10,
						  team1.get(0).sprite1.getIconHeight() + 10);
		lblPictureP1.setIcon(team1.get(0).sprite1);
		lblPictureP1.setToolTipText(team1.get(0).getFormattedHTMLStats());
		panelP1.add(lblPictureP1);
		mainpanel.add(panelP1);
		

		panelP2 = new JPanel(); // P2 Sprite Region
		panelP2.setOpaque(false);
		panelP2.setBounds(558, 23, 192, 192);
		panelP2.setPreferredSize(new Dimension(192, 192));
		lblPictureP2 = new JLabel();
		panelP2.setBounds(558 + (192-team2.get(0).sprite2.getIconWidth())/2, 
						  23 + (192-team2.get(0).sprite2.getIconHeight())/2, 
						  team2.get(0).sprite2.getIconWidth() + 10, 
						  team2.get(0).sprite2.getIconHeight() + 10);
		lblPictureP2.setIcon(team2.get(0).sprite2);
		lblPictureP2.setToolTipText(team2.get(0).getFormattedHTMLStats());
		panelP2.add(lblPictureP2);
		mainpanel.add(panelP2);
		
		
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
		txtInfo.setText	("-------BEGIN BATTLE!-------\n");
		appendText("\nPlayer 1 sent out " + team1.get(0).name + "!\n");
		appendText("\nPlayer 2 sent out " + team2.get(0).name + "!\n");
		appendText		("\n-----Select your actions.-----\n");
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
		healthBarP1.setStringPainted(true);
		healthBarP1.setForeground(Color.GREEN);
		healthBarP1.setBounds(50, 255, 225, 20);
		
		healthBarP1.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent evt) {
		    	if(healthBarP1.getValue() > 45)
		    		healthBarP1.setForeground(Color.GREEN);
		    	else if(healthBarP1.getValue() > 15)
		    		healthBarP1.setForeground(Color.YELLOW);
		    	else
		    		healthBarP1.setForeground(Color.RED);
		    }
		    });
		
		healthBarP1.setUI(new BasicProgressBarUI() {
		      protected Color getSelectionBackground() { return Color.BLACK; }
		      protected Color getSelectionForeground() { return Color.BLACK; }
		    });
		healthBarP1.setValue(100);
		mainpanel.add(healthBarP1);

		healthBarP2 = new JProgressBar(); // P2 Health Bar
		healthBarP2.setStringPainted(true);
		healthBarP2.setForeground(Color.GREEN);
		healthBarP2.setBounds(558, 255, 225, 20);
		
		healthBarP2.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent evt) {
		    	if(healthBarP2.getValue() > 45)
		    		healthBarP2.setForeground(Color.GREEN);
		    	else if(healthBarP2.getValue() > 15)
		    		healthBarP2.setForeground(Color.YELLOW);
		    	else
		    		healthBarP2.setForeground(Color.RED);
		    }
		    });
		
		healthBarP2.setUI(new BasicProgressBarUI() {
		      protected Color getSelectionBackground() { return Color.BLACK; }
		      protected Color getSelectionForeground() { return Color.BLACK; }
		    });
		healthBarP2.setValue(100);
		mainpanel.add(healthBarP2);
		
		
		lblHealthP1 = new JLabel(team1.get(0).stats[0] + "/"
				+ team1.get(0).stats[0]); // P1 Health Label
		lblHealthP1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblHealthP1.setForeground(Color.BLACK);
		lblHealthP1.setBounds(214, 283, 61, 16);
		mainpanel.add(lblHealthP1);

		lblHealthP2 = new JLabel(team2.get(0).stats[0] + "/"
				+ team2.get(0).stats[0]); // P2 Health Label
		lblHealthP2.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblHealthP2.setForeground(Color.BLACK);
		lblHealthP2.setBounds(722, 283, 61, 16);
		mainpanel.add(lblHealthP2);

		lblP1 = new JLabel(team1.get(0).name); // P1 Name Label
		lblP1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblP1.setForeground(Color.BLACK);
		lblP1.setBounds(22, 220, 220, 23);
		mainpanel.add(lblP1);

		lblP2 = new JLabel(team2.get(0).name); // P2 Name Label
		lblP2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblP2.setForeground(Color.BLACK);
		lblP2.setBounds(529, 220, 221, 23);
		mainpanel.add(lblP2);

		
		for(int i = 0; i < 4; i++)
		{
			p1Buttons[i].setText(team1.get(0).moves[i].name);
			p1Buttons[i].setFocusable(false);
			p1Buttons[i].setOpaque(true);
			p1Buttons[i].setBorderPainted(true);
			p1Buttons[i].setForeground(Color.BLACK);
			p1Buttons[i].setFont(new Font("Helvetica", Font.BOLD, 14));
			p1Buttons[i].setToolTipText("Type: " + team1.get(0).moves[i].getType()
					+ "  Accuracy: " + team1.get(0).moves[i].accuracy);
			
			p1Buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int target = 0;
					for(int j = 0; j < p1Buttons.length; j++)
					{
						if(e.getSource().equals(p1Buttons[j]))
						{
							target = j;
							break;
						}
							
					}
					selectMove(target, 1);
				}});
			
			switch(i)
			{
			case 0: p1Buttons[i].setBounds(6, 310, 150, 40); break;
			case 1: p1Buttons[i].setBounds(157, 310, 150, 40); break;
			case 2: p1Buttons[i].setBounds(6, 351, 150, 40); break;
			case 3: p1Buttons[i].setBounds(157, 351, 150, 40); 
			}
			
			mainpanel.add(p1Buttons[i]);
		}
		
		for(int i = 0; i < 4; i++)
		{
			p2Buttons[i].setText(team2.get(0).moves[i].name);
			p2Buttons[i].setFocusable(false);
			p2Buttons[i].setOpaque(true);
			p2Buttons[i].setBorderPainted(true);
			p2Buttons[i].setForeground(Color.BLACK);
			p2Buttons[i].setFont(new Font("Helvetica", Font.BOLD, 14));
			p2Buttons[i].setToolTipText("Type: " + team2.get(0).moves[i].getType()
					+ "  Accuracy: " + team2.get(0).moves[i].accuracy);
			
			p2Buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int target = 0;
					for(int j = 0; j < p2Buttons.length; j++)
					{
						if(e.getSource().equals(p2Buttons[j]))
						{
							target = j;
							break;
						}
					}
					selectMove(target, 2);
				}});
			
			switch(i)
			{
			case 0: p2Buttons[i].setBounds(493, 310, 150, 40); break;
			case 1: p2Buttons[i].setBounds(644, 310, 150, 40); break;
			case 2: p2Buttons[i].setBounds(493, 351, 150, 40); break;
			case 3: p2Buttons[i].setBounds(644, 351, 150, 40); 
			}
			
			mainpanel.add(p2Buttons[i]);
		}
		
		Color background = theme.Color();
		
		for(int i = 0; i < 4; i++)
		{
			p1Buttons[i].setBackground(background);
			p2Buttons[i].setBackground(background);
		}

		btnP1Team = new JButton("Team Status / Switch Pokemon"); 	// Team 1 TeamGUI Opener
		btnP1Team.setFont(new Font("Helvetica", Font.BOLD, 13));
		btnP1Team.setForeground(Color.BLACK);
		btnP1Team.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		btnP1Team.setBackground(background);
		btnP1Team.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TeamGUI(1);
			}});
		btnP1Team.setBounds(6, 430, 300, 29);
		mainpanel.add(btnP1Team);

		btnP2Team = new JButton("Team Status / Switch Pokemon"); 	// Team 2 TeamGUI Opener
		btnP2Team.setFont(new Font("Helvetica", Font.BOLD, 13));
		btnP2Team.setForeground(Color.BLACK);
		btnP2Team.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		btnP2Team.setBackground(background);
		btnP2Team.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TeamGUI(2);
			}});
		btnP2Team.setBounds(493, 430, 300, 29);
		mainpanel.add(btnP2Team);

		

		JButton btnSaveLoad = new JButton("SAVE / LOAD");	//Save/Load Utility Opener
		btnSaveLoad.setBounds(341, 430, 117, 29);
		btnSaveLoad.setForeground(Color.BLACK);
		btnSaveLoad.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		btnSaveLoad.setEnabled(true);
		btnSaveLoad.setFont(new Font("Helvetica", Font.BOLD, 12));
		btnSaveLoad.setBackground(background);
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
		
		
		//Enable the right buttons
		if(team == 1) {
			for(int i = 0; i < 4; i++)
			{
				p1Buttons[i].setEnabled(team1.get(0).moves[i].pp > 0);
			}
			btnP1Team.setEnabled(true);
			disableP2Buttons();
			btnP2Team.setEnabled(false);
		} else if(team == 2) {
			for(int i = 0; i < 4; i++)
			{
				p2Buttons[i].setEnabled(team2.get(0).moves[i].pp > 0);
			}
			btnP2Team.setEnabled(true);
			disableP1Buttons(); 
			btnP1Team.setEnabled(false);
		}
		refreshButtons();
	}
	
	public static void enableP1Buttons() {
		for(JButton b : p1Buttons)
		{
			b.setEnabled(true);
		}	
	}
	public static void disableP1Buttons() {
		for(JButton b : p1Buttons)
		{
			b.setEnabled(false);
		}
	}
	public static void enableP2Buttons() {
		for(JButton b : p2Buttons)
		{
			b.setEnabled(true);
		}
	}
	public static void disableP2Buttons() {
		for(JButton b : p2Buttons)
		{
			b.setEnabled(false);
		}
	}
	
	
	public static void refresh() 	// The light refresh method after a battle turn
									// has been completed.
	{

		if (team1.get(0).currenthealth > 0) {
			refreshButtons();
		}

		if (team1.get(0).currenthealth != 0
				&& team2.get(0).currenthealth != 0)
			appendText("\n-----Select your actions.-----\n");

	}
	
	
	// Refreshes the main window after a player switches Pokemon.
	public static void switchRefresh(Pokemon switchout, Pokemon switchin) 
	{
		battle.removeTriggers(switchout);
		battle.addTrigger(switchin.ability);

		battle.addTrigger(new Status(switchin));
		battle.addTrigger(new EndStatus(switchin));


		lblPictureP1.setIcon(team1.get(0).sprite1);
		lblPictureP2.setIcon(team2.get(0).sprite2);
		
		panelP1.setBounds(50  + (192-team1.get(0).sprite1.getIconWidth())/2, 
						  23 + (192-team1.get(0).sprite1.getIconHeight())/2, 
						  team1.get(0).sprite1.getIconWidth() + 10, 
						  team1.get(0).sprite1.getIconHeight() + 10);
		panelP2.setBounds(558 + (192-team2.get(0).sprite2.getIconWidth())/2, 
						  23 + (192-team2.get(0).sprite2.getIconHeight())/2, 
						  team2.get(0).sprite2.getIconWidth() + 10, 
						  team2.get(0).sprite2.getIconHeight() + 10);

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
		
		lblPictureP1.setToolTipText(team1.get(0).getFormattedHTMLStats());
		lblPictureP2.setToolTipText(team2.get(0).getFormattedHTMLStats());

		for(int i = 0; i < 4; i++)
		{
			p1Buttons[i].setText(team1.get(0).moves[i].name);
			p2Buttons[i].setText(team2.get(0).moves[i].name);
			
			p1Buttons[i].setToolTipText("Type: " + team1.get(0).moves[i].getType()
					+ "  Accuracy: " + team1.get(0).moves[i].accuracy);
			p2Buttons[i].setToolTipText("Type: " + team2.get(0).moves[i].getType()
				+ "  Accuracy: " + team2.get(0).moves[i].accuracy);
		}
		
		
		//Enable the right buttons
		refreshButtons();


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
	
	//Chooses the correct Color for a button, based on the move's type int
	public static Color getBtnBackgrounds(Move m)	
	{
		return m.mtype.color();
	}
	
	public static void refreshButtons() {		//Enable the right buttons
		if(team == 1) {
			for(int i = 0; i < 4; i++)
			{
				p1Buttons[i].setEnabled(team1.get(0).moves[i].pp > 0);
			}
			btnP1Team.setEnabled(true);
			disableP2Buttons();
			btnP2Team.setEnabled(false);
		} else if(team == 2) {
			for(int i = 0; i < 4; i++)
			{
				p2Buttons[i].setEnabled(team2.get(0).moves[i].pp > 0);
			}
			btnP2Team.setEnabled(true);
			disableP1Buttons();   
			btnP1Team.setEnabled(false);
		} else if(team == 0) {
			for(int i = 0; i < 4; i++)
			{
				p1Buttons[i].setEnabled(team1.get(0).moves[i].pp > 0);
			}
			btnP1Team.setEnabled(true);
			for(int i = 0; i < 4; i++)
			{
				p2Buttons[i].setEnabled(team2.get(0).moves[i].pp > 0);
			}
			btnP2Team.setEnabled(true);
		}
		
		for(int i = 0; i < 4; i++)
		{
			p1Buttons[i].setBorder(BorderFactory.createLineBorder(
					getBtnBackgrounds(team1.get(0).moves[i]), 3));
		}
		for(int i = 0; i < 4; i++)
		{
			p2Buttons[i].setBorder(BorderFactory.createLineBorder(
					getBtnBackgrounds(team2.get(0).moves[i]), 3));
		}
		
	}

	public static void loadRefresh() {


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



		lblPictureP1.setToolTipText(team1.get(0).getFormattedHTMLStats());
		lblPictureP2.setToolTipText(team2.get(0).getFormattedHTMLStats());

		for(int i = 0; i < 4; i++)
		{
			p1Buttons[i].setText(team1.get(0).moves[i].name);
			p2Buttons[i].setText(team2.get(0).moves[i].name);
			
			p1Buttons[i].setToolTipText("Type: " + team1.get(0).moves[i].getType()
					+ "  Accuracy: " + team1.get(0).moves[i].accuracy);
			p2Buttons[i].setToolTipText("Type: " + team2.get(0).moves[i].getType()
				+ "  Accuracy: " + team2.get(0).moves[i].accuracy);
		}

		team1.get(0).setStatusText();
		team2.get(0).setStatusText();

		frmMainGUI.repaint();

	}

	public static void selectMove(int action, int player) {
		if (player == 1) {
			actionSelectedP1 = action;
			disableP1Buttons();
			btnP1Team.setEnabled(false);
			p1isDone = true;
			appendText("\nPlayer 1 has selected! ");
			if(!p2isDone) appendText("Waiting for Player 2.\n");
			else appendText("\n");
		} else if (player == 2) {
			actionSelectedP2 = action;
			disableP2Buttons();
			btnP2Team.setEnabled(false);
			p2isDone = true;
			appendText("\nPlayer 2 has selected! ");
			if(!p2isDone) appendText("Waiting for Player 1.\n");
			else appendText("\n");
		} else {
			System.out.println("Invalid player.");
		}
		
		if(player == team) {
			network.sendMessage("MOVE " + action);
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
			network.sendMessage("SWITCH " + action);
		}
	}

	public static void begin() {
		appendText("\n-------ROUND BEGIN!-------\n");
		battle.run(actionSelectedP1, actionSelectedP2);
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
