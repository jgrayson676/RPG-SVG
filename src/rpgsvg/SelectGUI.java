package rpgsvg;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random; 

import javax.imageio.ImageIO;
import javax.sound.midi.Sequencer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import network.NetworkObject;
import rpgsvg.moves.Move;
import rpgsvg.triggers.*;



public class SelectGUI {		//The beginning selection window for RPG-SVG.		
	
	private Random random;
	
	public static JFrame frmSelectGUI;
	JList<Pokemon> list1;
	JList<Pokemon> list2;
	JButton btnReady;
	JButton btnHelp;
	JButton btnSave;
	static JLabel lblP1Number;
	static JLabel lblP2Number;
	static Pokemon lastSelectedPokemon;
	
	static DefaultListModel<Pokemon> listModel1;
	static DefaultListModel<Pokemon> listModel2;
	
	static List<Pokemon> team1 = new ArrayList<Pokemon>();
	static List<Pokemon> team2 = new ArrayList<Pokemon>();
	
	
	Move earthquake;
	Move surf;
	Move fireblast;
	Move focusblast;
	Move icebeam;
	Move thunderbolt;
	Move crunch;
	Move stoneedge;
	Move psychic;
	Move airslash;		//Need flinch pls
	Move dynamicpunch;	//Needs confusion
	Move signalbeam;	//Needs confusion
	Move firepunch;
	Move bodyslam;
	Move thunderpunch;
	Move flamethrower;
	Move sludgebomb;
	Move extremespeed;
	Move splash;
	Move swag;
	Move rockslide;		//Need flinch pls			
	Move icepunch;
	Move hydropump;
	Move energyball;
	Move poisonjab;
	Move earthpower;
	Move aquatail;
	Move scald;
	Move xscissor;
	Move darkpulse;		//Need flinch pls
	Move crosspoison;
	Move triattack;
	Move leafstorm;
	Move iceshard;
	Move seedbomb;
	Move foulplay;
	Move thunder;
	Move eruption;
	Move waterfall;
	Move gigadrain;
	Move bravebird;
	Move bulletpunch;
	Move bugbite;
	Move closecombat;
	Move megahorn;
	Move discharge;
	Move skyuppercut;
	Move flareblitz;
	Move machpunch;
	Move bulletseed;
	Move facade;
	Move quickattack;
	Move watergun;
	Move shadowclaw;
	Move outrage;
	Move overheat;
	Move shadowball;
	Move bugbuzz;
	Move uturn;		//need switch :'(
	Move nightslash;
	Move leafblade;
	Move wildcharge;//NOT IMPLEMENTED YET!!!!
	Move hammerarm;
	Move explosion;	//NOT IMPLEMENTED YET!!!!!!!
	Move drainpunch; //need health draining
	Move superpower;
	Move flail;		//need implement
	Move blizzard;
	Move hyperfang;	//need flinch
	Move aquajet;
	
	Move calmmind;
	Move roost;
	Move nastyplot;
	Move bulkup;
	Move recover;
	Move synthesis;
	Move dragondance;
	Move swordsdance;
	Move coil;
	Move growth;
	Move rockpolish;
	Move agility;
	
	Move toxic;
	Move willowisp;
	Move thunderwave;
	Move spore;
	Move sleeppowder;
	
	Pokemon venusaur;
	Pokemon charizard;
	Pokemon blastoise;
	Pokemon raichu;
	Pokemon arcanine;
	Pokemon alakazam;
	Pokemon machamp;
	Pokemon starmie;
	Pokemon jolteon;
	Pokemon snorlax;
	Pokemon meganium;
	Pokemon typhlosion;
	Pokemon feraligatr;
	Pokemon crobat;
	Pokemon scizor;
	Pokemon heracross;
	Pokemon shuckle;
	Pokemon ampharos;
	Pokemon umbreon;
	Pokemon houndoom;
	Pokemon sceptile;
	Pokemon blaziken;
	Pokemon swampert;
	Pokemon breloom;
	Pokemon swellow;
	Pokemon milotic;
	Pokemon luvdisc;
	Pokemon shedinja;
	Pokemon cradily;
	Pokemon flygon;
	Pokemon torterra;
	Pokemon infernape;
	Pokemon empoleon;
	Pokemon drifblim;
	Pokemon gastrodon;
	Pokemon drapion;
	Pokemon porygonz;
	Pokemon yanmega;
	Pokemon serperior;
	Pokemon emboar;
	Pokemon samurott;
	Pokemon gigalith;
	Pokemon scrafty;
	Pokemon jellicent;
	Pokemon reuniclus;
	Pokemon chandelure;
	Pokemon eelektross;
	Pokemon stunfisk;
	Pokemon magikarp;
	
	
	Trigger overgrow = new Overgrow(venusaur);
	Trigger blaze = new Blaze(charizard);
	Trigger torrent = new Torrent(blastoise);

	static Sequencer sequencer;
	private JButton btnT1P1;
	private JButton btnT1P2;
	private JButton btnT1P3;
	private JButton btnT1P4;
	private JButton btnT1P5;
	private JButton btnT1P6;
	private JButton btnT2P1;
	private JButton btnT2P2;
	private JButton btnT2P3;
	private JButton btnT2P4;
	private JButton btnT2P5;
	private JButton btnT2P6;
	private JLabel lblP;
	private JLabel lblP_1;
	private JLabel label;
	private JLabel label_1;
	
	private int team;
	private boolean team1isReady;
	private boolean team2isReady;
	private NetworkObject network;
	

	
	public SelectGUI(int team, Random random, NetworkObject net) {
		this.random = random;
		this.team = team;
		this.network = net;
		populate();
		initialize();
	}

	public void populate() {		
		
		/*	Types: Normal(1), Fire(2), Water(3), Electric(4), Grass(5), Ice(6), Fighting(7), Poison(8),
		 *  Ground(9), Flying(10), Psychic(11), Bug(12), Rock(13), Ghost(14), Dragon(15), Dark(16), Steel(17)
		 */
		
		/*MOVE LIST*/
		
		Modifier lowerspecialdef = 	new Modifier(1, 1, Pokemon.NO_STATUS, 	0, 0, 0, -1, 0, 0, 0, 0, 0, null, true);
		Modifier burn = 			new Modifier(1, 1, Pokemon.BURN, 		0, 0, 0, 0, 0, 0, 0, 0, 0, null, true);
	    Modifier freeze = 			new Modifier(1, 1, Pokemon.FREEZE, 		0, 0, 0, 0, 0, 0, 0, 0, 0, null, true);
	    Modifier paralysis = 		new Modifier(1, 1, Pokemon.PARALYSIS, 	0, 0, 0, 0, 0, 0, 0, 0, 0, null, true);
	    Modifier poison = 			new Modifier(1, 1, Pokemon.POISON, 		0, 0, 0, 0, 0, 0, 0, 0 ,0, null, true);
		
	    earthquake 		= new Move("Earthquake", 	true, 9, 100, 100, null, null, 0, 0, 0, 10);
	    surf 			= new Move("Surf", 			false, 3, 95, 100, null, null, 0, 0, 0, 15);
	    fireblast 		= new Move("Fire Blast", 	false, 2, 120, 85, null, burn, 0.1, 0, 0, 5);
	    focusblast 		= new Move("Focus Blast", 	false, 7, 120, 70, null, lowerspecialdef, 0.1, 0, 0, 5);
	    icebeam 		= new Move("Ice Beam", 		false, 6, 90, 100, null, freeze, 0.1, 0, 0, 10);
	    thunderbolt 	= new Move("Thunderbolt", 	false, 4, 90, 100, null, paralysis, 0.1, 0, 0, 15);
	    crunch 			= new Move("Crunch", 		true, 16, 80, 100, null, new Modifier(1, 1, Pokemon.NO_STATUS, 0, -1, 0, 0, 0, 0, 0, 0, 0, null, true), 0.2, 0, 0, 15);
	    stoneedge 		= new Move("Stone Edge", 	true, 13, 100, 80, null, null, 0, 0, 1, 5);
	    psychic 		= new Move("Psychic", 		false, 11, 90, 100, null, lowerspecialdef, 0.1, 0, 0, 10);
	    airslash 		= new Move("Air Slash", 	false, 10, 75, 95, null, null, 0.3, 0, 0, 20);
	    dynamicpunch 	= new Move("DynamicPunch", 	true, 7, 100, 100, null, null, 1, 0, 0, 5);
	    signalbeam 		= new Move("Signal Beam", 	false, 12, 75, 100, null, null, 0.1, 0, 0, 15);
	    firepunch 		= new Move("Fire Punch", 	true, 2, 75, 100, null, burn, 0.1, 0, 0, 15);
	    bodyslam 		= new Move("Body Slam", 	true, 1, 85, 100, null, paralysis, 0.3, 0, 0, 15);
	    thunderpunch 	= new Move("Thunder Punch", true, 4, 75, 100, null, paralysis, 0.1, 0, 0, 15);
	    flamethrower 	= new Move("Flamethrower", 	false, 2, 95, 100, null, burn, 0.1, 0, 0, 15);  
	    sludgebomb 		= new Move("Sludge Bomb", 	false, 8, 90, 100, null, poison, 0.3, 0, 0, 10);
	    extremespeed 	= new Move("ExtremeSpeed", 	true, 1, 90, 100, null, null, 0, 2, 0, 5);
	    splash 			= new Move("Splash", 		false, 3, 0, 100, null, null, 0, 0, 0, 40);
	    swag 			= new Move("SWAG", 			false, 15, 1000, 10, new Modifier(1, 1, Pokemon.NO_STATUS, 3, 3, 3, 3, 0, 0, 0, 0, 0, null, true), null, 1, 2, 0, 5);
	    rockslide 		= new Move("Rock Slide", 	true, 13, 75, 90, null, null, 0.3, 0, 0, 10);
	    icepunch 		= new Move("Ice Punch", 	true, 6, 75, 100, null, freeze, 0.1, 0, 0, 15);
	    hydropump 		= new Move("Hydro Pump", 	false, 3, 110, 80, null, null, 0, 0, 0, 5);
	    energyball 		= new Move("Energy Ball", 	false, 5, 80, 100, null, lowerspecialdef, 0.1, 0, 0, 10);
	    poisonjab 		= new Move("Poison Jab", 	true, 8, 80, 100, null, poison, 0.3, 0, 0, 20);
	    earthpower 		= new Move("Earth Power", 	false, 9, 90, 100, null, lowerspecialdef, 0.1, 0, 0, 10);
	    aquatail 		= new Move("Aqua Tail", 	true, 3, 90, 90, null, null, 0, 0, 0, 10);
	    scald 			= new Move("Scald", 		false, 3, 80, 100, null, burn, 0.3, 0, 0, 15);
	    xscissor 		= new Move("X-Scissor", 	true, 12, 80, 100, null, null, 0, 0, 0, 15);
	    darkpulse 		= new Move("Dark Pulse", 	false, 16, 80, 100, null, null, 0.2, 0, 0, 15);
	    crosspoison 	= new Move("Cross Poison", 	true, 8, 70, 100, null, poison, 0.1, 0, 1, 20);
	    triattack 		= new Move("Tri Attack", 	false, 1, 80, 100, null, null, 0, 0, 0, 10);
	    leafstorm 		= new Move("Leaf Storm", 	false, 5, 140, 90, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, -2, 0, 0, 0, 0, 0, 0, null, true), null, 1, 0, 0, 5);
	    iceshard 		= new Move("Ice Shard", 	true, 6, 40, 100, null, null, 0, 0, 0, 30);
	    seedbomb 		= new Move("Seed Bomb", 	true, 5, 80, 100, null, null, 0, 0, 0, 15);
	    foulplay 		= new Move("Foul Play", 	true, 16, 95, 100, null, null, 0, 0, 0, 15);
	    thunder 		= new Move("Thunder,", 		false, 4, 110, 70, null, paralysis, 0.3, 0, 0, 10);
	    eruption 		= new Move("Eruption", 		false, 2, 90, 100, null, null, 0, 0, 0, 5);
	    waterfall 		= new Move("Waterfall", 	true, 3, 80, 100, null, null, 0.2, 0, 0, 15);
	    gigadrain 		= new Move("Giga Drain", 	false, 5, 75, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, true, -.5), null, 1, 0, 0, 10);
	    bravebird 		= new Move("Brave Bird", 	true, 10, 120, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, true, .33), null, 1, 0, 0, 15);
	    bulletpunch 	= new Move("Bullet Punch", 	true, 17, 40, 100, null, null, 0, 1, 0, 30);
	    bugbite 		= new Move("Bug Bite", 		true, 12, 60, 100, null, null, 0, 0, 0, 20);
	    closecombat 	= new Move("Close Combat", 	true, 7, 120, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, -1, 0, -1, 0, 0, 0, 0, 0, null, true), null, 1, 0, 0, 5);
	    megahorn 		= new Move("Megahorn", 		true, 12, 120, 85, null, null, 0, 0, 0, 10);
	    discharge 		= new Move("Discharge", 	false, 4, 80, 100, null, paralysis, 0.3, 0, 0, 15);
	    skyuppercut 	= new Move("Sky Uppercut", 	true, 7, 85, 90, null, null, 0, 0, 0, 15);
	    flareblitz 		= new Move("Flare Blitz", 	true, 2, 120, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, true, .33), null, 1, 0, 0, 15);
	    machpunch 		= new Move("Mach Punch", 	true, 7, 40, 100, null, null, 0, 1, 0, 30);
	    bulletseed 		= new Move("Bullet Seed", 	true, 5, 75, 100, null, null, 0, 0, 0, 30);
	    facade 			= new Move("Facade", 		true, 1, 70, 100, null, null, 0, 0, 0, 20);
	    quickattack 	= new Move("Quick Attack", 	true, 1, 40, 100, null, null, 0, 1, 0, 30);
	    aquajet 		= new Move("Aqua Jet", 		true, 3, 40, 100, null, null, 0, 1, 0, 20);
	    watergun 		= new Move("Water Gun", 	false, 3, 40, 100, null, null, 0, 0, 0, 25);
	    shadowclaw 		= new Move("Shadow Claw", 	true, 14, 70, 100, null, null, 0, 0, 1, 15);
	    outrage 		= new Move("Outrage", 		true, 15, 120, 100, null, null, 0, 0, 0, 10);
	    overheat 		= new Move("Overheat", 		false, 2, 140, 90, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, -2, 0, 0, 0, 0, 0, 0, null, true), null, 1, 0, 0, 5);
	    shadowball 		= new Move("Shadow Ball", 	false, 14, 80, 100, null, lowerspecialdef, 0.2, 0, 0, 15);
	    bugbuzz 		= new Move("Bug Buzz", 		false, 12, 90, 100, null, lowerspecialdef, 0.1, 0, 0, 10);
	    uturn 			= new Move("U-Turn", 		true, 12, 70, 100, null, null, 0, 0, 0, 20);
	    nightslash 		= new Move("Night Slash", 	true, 16, 70, 100, null, null, 0, 0, 1, 15);
	    leafblade 		= new Move("Leaf Blade", 	true, 5, 90, 100, null, null, 0, 0, 1, 15);
	    wildcharge 		= new Move("Wild Charge", 	true, 4, 90, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, true, .25), null, 1, 0, 0, 15);
	    hammerarm 		= new Move("Hammer Arm", 	true, 7, 100, 90, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, -1, 0, 0, 0, 0, null, true), null, 1, 0, 0, 10);
	    explosion 		= new Move("Explosion", 	true, 1, 250, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 0, 0, 0, 0, -1, null, true), null, 1, 0, 0, 5);
	    drainpunch 		= new Move("Drain Punch", 	true, 7, 70, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, true, -.5), null, 1, 0, 0, 10);
	    superpower 		= new Move("Superpower", 	true, 7, 120, 100, null, null, 0, 0, 0, 5);
	    flail 			= new Move("Flail", 		true, 1, 200, 100, null, null, 0, 0, 0, 15);
	    blizzard 		= new Move("Blizzard", 		false, 6, 110, 70, null, freeze, 0.1, 0, 0, 5);
	    hyperfang 		= new Move("Hyper Fang", 	true, 1, 80, 90, null, null, 0.1, 0, 0, 15);
	    calmmind 		= new Move("Calm Mind", 	false, 11, 0, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 1, 1, 0, 0, 0, 0, 0, null, true), null, 1, 0, 0, 20);
	    
	    toxic 			= new Move("Toxic", false, 8, 0, 85, null, new Modifier(1, 1, Pokemon.BAD_POISON, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, true), 1, 0, 0, 10);
	    willowisp 		= new Move("Will-o-Wisp", false, 2, 0, 85, null, new Modifier(1, 1, Pokemon.BURN, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, true), 1, 0, 0, 15);
	    thunderwave 	= new Move("Thunder Wave", false, 4, 0, 100, null, new Modifier(1, 1, Pokemon.PARALYSIS, 0,0,0,0,0,0,0,0,0, null, true), 1, 0, 0, 20);
	    spore 			= new Move("Spore", false, 5, 0, 100, null, new Modifier(1, 1, Pokemon.SLEEP, 0,0,0,0,0,0,0,0,0,null, true), 1, 0,0, 15);
	    sleeppowder 	= new Move("Sleep Powder", false, 5, 0, 75, null, new Modifier(1, 1, Pokemon.SLEEP, 0,0,0,0,0,0,0,0,0,null, true), 1, 0,0, 15);

	        
	    Modifier halfhealth = new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 0, 0, 0, 0, 0.5, null, true);
	    
	    roost 			= new Move("Roost", false, 10, 0, 100, halfhealth, null, 1, 0, 0, 10);
	    nastyplot 		= new Move("Nasty Plot", false, 16, 0, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 2, 0, 0, 0, 0, 0, 0, null, true), null, 1, 0, 0, 20);
	    bulkup 			= new Move("Bulk Up", false, 7, 0, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 1, 1, 0, 0, 0, 0, 0, 0, 0, null, true), null, 1, 0, 0, 20);
	    recover 		= new Move("Recover", false, 1, 0, 100, halfhealth, null, 1, 0, 0, 10);
	    synthesis 		= new Move("Synthesis", false, 5, 0, 100, halfhealth, null, 1, 0, 0, 5);
	    dragondance 	= new Move("Dragon Dance", false, 15, 0, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 1, 0, 0, 0, 1, 0, 0, 0, 0, null, true), null, 1, 0, 0, 20);
	    swordsdance 	= new Move("Swords Dance", false, 1, 0, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 2, 0, 0, 0, 0, 0, 0, 0, 0, null, true), null, 1, 0, 0, 20);
	    coil 			= new Move("Coil", false, 8, 0, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 1, 1, 0, 0, 0, 0, 1, 0, 0, null, true), null, 1, 0, 0, 20);
	    growth 			= new Move("Growth", false, 5, 0, 100, new Modifier(1,1, Pokemon.NO_STATUS, 1, 1, 0, 0, 0, 0, 0, 0, 0, null, true), null, 1, 0, 0, 40);
	    rockpolish 		= new Move("Rock Polish", false, 13, 0, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 2, 0, 0, 0, 0, null, true), null, 1, 0, 0, 20);
	    agility 		= new Move("Agility", false, 1, 0, 100, new Modifier(1, 1, Pokemon.NO_STATUS, 0, 0, 0, 0, 2, 0, 0, 0, 0, null, true), null, 1, 0, 0, 30);
	      
		
		/*POKEMON LIST*/
		venusaur = new Pokemon("Venusaur", 80, 82, 83, 100, 100, 80, 5, 8, earthquake, sludgebomb, sleeppowder, growth, null);
		venusaur.ability = new Overgrow(venusaur);
		charizard = new Pokemon("Charizard", 78, 84, 78, 109, 85, 100, 2, 10, fireblast, focusblast, stoneedge, airslash, null);
		charizard.ability = new Blaze(charizard);
		blastoise = new Pokemon("Blastoise", 79, 83, 100, 85, 105, 78, 3, 0, earthquake, surf, icebeam, hydropump, null);
		blastoise.ability = new Torrent(blastoise);
		raichu = new Pokemon("Raichu", 60, 90, 55, 90, 80, 100, 4, 0, thunderwave, nastyplot, thunderbolt, focusblast, null);
		raichu.ability = new Lightningrod(raichu);
		arcanine = new Pokemon("Arcanine", 90, 110, 80, 100, 80, 95, 2, 0, flareblitz, wildcharge, crunch, extremespeed, null);
		arcanine.ability = new Flashfire(arcanine);
		alakazam = new Pokemon("Alakazam", 55, 50, 45, 135, 85, 120, 11, 0, focusblast, shadowball, psychic, calmmind, null);
		machamp = new Pokemon("Machamp", 90, 130, 80, 65, 85, 55, 7, 0, thunderpunch, dynamicpunch, stoneedge, bulkup, null);
		machamp.ability = new Noguard(machamp);
		starmie = new Pokemon("Starmie", 60, 75, 85, 100, 85, 115, 3, 11, icebeam, surf, psychic, thunderbolt, null);
		jolteon = new Pokemon("Jolteon", 65, 65, 60, 100, 95, 130, 4, 0, earthquake, surf, thunderbolt, signalbeam, null);
		jolteon.ability = new Voltabsorb(jolteon);
		snorlax = new Pokemon("Snorlax", 160, 100, 65, 65, 110, 30, 1, 0, firepunch, crunch, earthquake, bodyslam, null);
		snorlax.ability = new Thickfat(snorlax);
		meganium = new Pokemon("Meganium", 80, 82, 100, 83, 100, 80, 5, 0, synthesis, gigadrain, toxic, bodyslam, null);
		meganium.ability = new Overgrow(meganium);
		typhlosion = new Pokemon("Typhlosion", 78, 84, 78, 109, 85, 100, 2, 0, fireblast, focusblast, eruption, quickattack, null);
		feraligatr = new Pokemon("Feraligatr", 85, 105, 100, 79, 83, 78, 3, 0, crunch, waterfall, icepunch, dragondance, null);
		feraligatr.ability = new Sheerforce(feraligatr);
		crobat = new Pokemon("Crobat", 85, 90, 80, 70, 80, 130, 8, 10, bravebird, crosspoison, roost, toxic, null);
		scizor = new Pokemon("Scizor", 70, 130, 100, 55, 80, 65, 12, 17, bulletpunch, bugbite, swordsdance, roost, null);
		scizor.ability = new Technician(scizor);
		heracross = new Pokemon("Heracross", 80, 125, 75, 40, 95, 85, 12, 7, stoneedge, earthquake, closecombat, megahorn, null);
		heracross.ability = new Guts(heracross);
		shuckle = new Pokemon("Shuckle", 20, 10, 230, 10, 230, 5, 12, 13, rockslide, stoneedge, bugbite, toxic, null);
		shuckle.ability = new Sturdy(shuckle);
		ampharos = new Pokemon("Ampharos", 90, 75, 75, 115, 90, 55, 4, 0, focusblast, discharge, bodyslam, agility, null);
		ampharos.ability = new Static(ampharos);
		umbreon = new Pokemon("Umbreon", 95, 65, 110, 60, 130, 65, 16, 0, foulplay, recover, quickattack, toxic, null);
		houndoom = new Pokemon("Houndoom", 75, 90, 50, 110, 80, 96, 16, 2, fireblast, darkpulse, nastyplot, willowisp, null);
		sceptile = new Pokemon("Sceptile", 70, 85, 65, 105, 85, 120, 5, 0, focusblast, leafstorm, gigadrain, toxic, null);
		sceptile.ability = new Overgrow(sceptile);
		blaziken = new Pokemon("Blaziken", 80, 120, 70, 110, 70, 80, 2, 7, skyuppercut, rockslide, flareblitz, swordsdance, null);
		blaziken.ability = new Speedboost(blaziken);
		swampert = new Pokemon("Swampert", 100, 110, 90, 85, 90, 60, 3, 9, earthquake, scald, icepunch, toxic, null);
		swampert.ability = new Torrent(swampert);
		breloom = new Pokemon("Breloom", 60, 130, 80, 60, 60, 70, 5, 7, machpunch, bulletseed, swordsdance, spore, null);
		breloom.ability = new Technician(breloom);
		swellow = new Pokemon("Swellow", 60, 85, 60, 50, 50, 125, 1, 10, facade, bravebird, quickattack, roost, null);
		swellow.ability = new Guts(swellow);
		milotic = new Pokemon("Milotic", 95, 60, 79, 100, 125, 81, 3, 0, scald, icebeam, toxic, recover, null);
		milotic.ability = new Marvelscale(milotic);
		luvdisc = new Pokemon("Luvdisc", 43, 30, 55, 40, 65, 97, 3, 0, swag, watergun, hydropump, dragondance, null);
		shedinja = new Pokemon("Shedinja", -74, 90, 45, 30, 30, 40, 12, 14,  xscissor, shadowclaw, toxic, shadowball, null);
		shedinja.ability = new Wonderguard(shedinja);
		cradily = new Pokemon("Cradily", 86, 81, 97, 81, 107, 43, 5, 13, energyball, rockslide, recover, gigadrain, null);
		cradily.ability = new Stormdrain(cradily);
		flygon = new Pokemon("Flygon", 80, 100, 80, 80, 80, 100, 15, 9, earthquake, firepunch, stoneedge, outrage, null);
		flygon.ability = new Levitate(flygon);
		torterra = new Pokemon("Torterra", 95, 109, 105, 75, 85, 56, 5, 9, earthquake, stoneedge, seedbomb, rockpolish, null);
		torterra.ability = new Overgrow(torterra);
		infernape = new Pokemon("Infernape", 76, 104, 71, 104, 71, 108, 2, 7, stoneedge, overheat, closecombat, machpunch, null);
		infernape.ability = new Blaze(infernape);
		empoleon = new Pokemon("Empoleon", 84, 86, 88, 111, 101, 60, 3, 17, icebeam, scald, toxic, aquajet, null);
		empoleon.ability = new Torrent(empoleon);
		drifblim = new Pokemon("Drifblim", 150, 80, 44, 90, 54, 80, 14, 10, shadowball, thunderbolt, flamethrower, calmmind, null);
		gastrodon = new Pokemon("Gastrodon", 111, 83, 68, 92, 82, 39, 3, 9, scald, earthpower, recover, toxic, null);
		gastrodon.ability = new Stormdrain(gastrodon);
		drapion = new Pokemon("Drapion", 70, 90, 110, 60, 75, 95, 9, 16, crunch, earthquake, poisonjab, swordsdance, null);
		drapion.ability = new Sniper(drapion);
		porygonz = new Pokemon("Porygon-Z", 85, 80, 70, 135, 75, 90, 1, 0, icebeam, triattack, nastyplot, agility, null);
		porygonz.ability = new Adaptability(porygonz);
		yanmega = new Pokemon("Yanmega", 85, 76, 86, 116, 56, 95, 12, 10, bugbuzz, airslash, uturn, nightslash, null);
		yanmega.ability = new Speedboost(yanmega);
		// weavile = new Pokemon("Weavile", 70, 120, 65, 45, 85, 125, 16, 6, tackle, tackle, tackle, tackle, null);
		serperior = new Pokemon("Serperior", 75, 75, 95, 75, 95, 113, 5, 0, leafblade, aquatail, uturn, coil, null);
		serperior.ability = new Overgrow(serperior);
		emboar = new Pokemon("Emboar", 110, 123, 65, 100, 65, 65, 2, 7, flareblitz, wildcharge, hammerarm, bulkup, null);
		emboar.ability = new Reckless(emboar);
		samurott = new Pokemon("Samurott", 95, 100, 85, 108, 70, 70, 3, 0, icebeam, hydropump, megahorn, aquajet, null);
		samurott.ability = new Torrent(samurott);
		gigalith = new Pokemon("Gigalith", 85, 135, 130, 60, 70, 25, 13, 0, earthquake, rockslide, explosion, toxic, null);
		gigalith.ability = new Sturdy(gigalith);
		scrafty = new Pokemon("Scrafty", 65, 90, 115, 45, 115, 58, 7, 16, crunch, drainpunch, icepunch, dragondance, null);
		jellicent = new Pokemon("Jellicent", 100, 60, 70, 85, 105, 60, 3, 14, icebeam, scald, shadowball, toxic, null);
		jellicent.ability = new Waterabsorb(jellicent);
		reuniclus = new Pokemon("Reuniclus", 110, 65, 75, 125, 85, 30, 11, 0, recover, calmmind, psychic, focusblast, null);
		chandelure = new Pokemon("Chandelure", 60, 55, 90, 145, 90, 80, 14, 2, flamethrower, shadowball, energyball, calmmind, null);
		eelektross = new Pokemon("Eelektross", 85, 115, 80, 105, 80, 50, 4, 0, thunderbolt, superpower, flamethrower, gigadrain, null);
		eelektross.ability = new Levitate(eelektross);
		stunfisk = new Pokemon("Stunfisk", 109, 66, 84, 81, 99, 32, 4, 9, scald, discharge, earthpower, thunderwave, null);
		stunfisk.ability = new Static(stunfisk);
		magikarp = new Pokemon("Magikarp", 20, 10, 55, 15, 20, 80, 3, 0, splash, flail, swag, hydropump, null);
		magikarp.ability = new Sturdy(magikarp);
		
	}
	
	
	
	private void initialize() {
		
		
		
		frmSelectGUI = new JFrame();
		if(team == 1)
			frmSelectGUI.setTitle("RPG-SVG Pokemon Selector SERVER");
		else
			frmSelectGUI.setTitle("RPG-SVG Pokemon Selector CLIENT");
		frmSelectGUI.setBounds(150, 150, 440, 415);
		frmSelectGUI.setResizable(false);
		frmSelectGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		
		
		btnReady = new JButton("Ready!");
		btnReady.setBounds(298, 236, 118, 42);
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		//receive information from the two lists.							
				List<Pokemon> teamone = team1;
				for(Pokemon x : teamone)
				{
					x.team1 = true;
				}
				List<Pokemon> teamtwo = team2;
				for(Pokemon x : teamtwo)
				{
					x.team1 = false;
				}
				if(teamone.size() < 7 && teamtwo.size() < 7 && teamone.size() != 0 && teamtwo.size() != 0)
				{
					if(team == 1) {
						team1isReady = true;
						network.sendMessage("READY");
					} else if(team == 2) {
						team2isReady = true;
						network.sendMessage("READY");
					} else if(team == 0) {
						team1isReady = true;
						team2isReady = true;
					}
					btnReady.setEnabled(false);
					if(team1isReady && team2isReady) startGame();
				}
				else
				{
					JOptionPane.showMessageDialog(frmSelectGUI, "Make sure each player has selected between 1 and 6 Pokemon.");
				}

			}
		});
		btnReady.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		
		JLabel lblNewLabel = new JLabel("PLAYER 1\n");
		lblNewLabel.setBounds(35, 108, 70, 19);
		lblNewLabel.setFont(new Font("Futura", Font.PLAIN, 14));
		
		JLabel lblPlayer = new JLabel("PLAYER 2\n");
		lblPlayer.setBounds(168, 108, 70, 19);
		lblPlayer.setFont(new Font("Futura", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 131, 117, 238);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(141, 131, 117, 238);
		
		JLabel lblNewLabel_1 = new JLabel("Each player can select");
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(264, 131, 180, 16);
		
		JLabel lblUpToSix = new JLabel("up to six Pokemon from");
		lblUpToSix.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblUpToSix.setBounds(264, 151, 168, 16);
		
		JLabel lblTheirListWhen = new JLabel("their list. When ready, click");
		lblTheirListWhen.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblTheirListWhen.setBounds(264, 172, 180, 16);
		
		JLabel lblreadyToBegin = new JLabel("\"Ready!\" to begin battle!");
		lblreadyToBegin.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblreadyToBegin.setBounds(264, 193, 180, 16);
		
		btnHelp = new JButton("HELP");
		btnHelp.setFont(new Font("Futura", Font.PLAIN, 13));
		btnHelp.setBounds(310, 290, 95, 29);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//Displays individual Pokemon information if only one Pokemon is selected from a list, or
																//shows general game information and instructions when more/less Pokemon are selected.
					BufferedImage pokeball = null;
					try
					{
						pokeball = ImageIO.read(this.getClass().getResource("Media/Images/pokeball.png"));
					}
					catch(Exception ef)
					{	
						ef.printStackTrace();
					}
					JOptionPane.showMessageDialog(frmSelectGUI, "	Random Pokemon Generator-Simulator Video Game	-Copyright 2013 THE RPG-SVG TEAM\n\n This is a Pokemon simulator game for two players. Each player should select up to six Pokemon from their\nrandomly generated list by simply clicking. Do not worry about order: this will be randomized as well.\n Click Help again after selecting a Pokemon to learn more about it.\n Click on Ready!!! to begin battle!\n\n\nVersion: Beta 3.0", "Help", 2, new ImageIcon(pokeball));
				}
			
		});
		
		lblP1Number = new JLabel("0");
		lblP1Number.setBounds(61, 371, 14, 16);
		frmSelectGUI.getContentPane().add(lblP1Number);
		
		lblP2Number = new JLabel("0");
		lblP2Number.setBounds(195, 371, 14, 16);
		frmSelectGUI.getContentPane().add(lblP2Number);
		
		
		Pokemon[] pokemon1 = {venusaur, charizard, blastoise, raichu, arcanine, alakazam, machamp, starmie, jolteon, snorlax, meganium, typhlosion, feraligatr, crobat, scizor, heracross, shuckle, ampharos, umbreon, houndoom, sceptile, blaziken, swampert, breloom, swellow, milotic, luvdisc, shedinja, cradily, flygon, torterra, infernape, empoleon, drifblim, gastrodon, drapion, yanmega, serperior, emboar, samurott, gigalith, jellicent, reuniclus, chandelure, eelektross, stunfisk, magikarp};
		List<Pokemon> shuffled =Arrays.asList(pokemon1);
		Collections.shuffle(shuffled, random);
		int mid = shuffled.size() / 2;
		
		listModel1 = new DefaultListModel<Pokemon>();
		listModel2 = new DefaultListModel<Pokemon>();
		
		for(int i = 0; i < mid; i++)
		{
			listModel1.addElement(shuffled.get(i));
		}
		for(int i = mid; i < shuffled.size(); i ++)
		{
			listModel2.addElement(shuffled.get(i));
		}
		
		
		list1 = new JList<Pokemon>(listModel1);
		list1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				boolean a = e.getValueIsAdjusting();
				int index = list1.getSelectedIndex();
				addPokemon(a, index, 1);
			}
		});
		list1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		
		scrollPane.setViewportView(list1);
		
		
		list2 = new JList<Pokemon>(listModel2);
		list2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				boolean a = e.getValueIsAdjusting();
				int index = list2.getSelectedIndex();
				addPokemon(a, index, 2);
			}
		});
		list2.setFont(new Font("Helvetica", Font.PLAIN, 14));
		
		if(team == 0) {
			list1.setEnabled(true);
			list2.setEnabled(true);
		} else if(team == 1) {
			list1.setEnabled(true);
			list2.setEnabled(false);
		} else if(team == 2) {
			list1.setEnabled(false);
			list2.setEnabled(true);
		}
		scrollPane_1.setViewportView(list2);
		
		btnSave = new JButton("LOAD BATTLE");
		btnSave.setFont(new Font("Futura", Font.PLAIN, 13));
		btnSave.setBounds(298, 340, 117, 29);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new SaveGUI(2);
			}
		});
		
		frmSelectGUI.getContentPane().setLayout(null);
		frmSelectGUI.getContentPane().add(scrollPane);
		frmSelectGUI.getContentPane().add(scrollPane_1);
		frmSelectGUI.getContentPane().add(lblUpToSix);
		frmSelectGUI.getContentPane().add(lblTheirListWhen);
		frmSelectGUI.getContentPane().add(lblNewLabel_1);
		frmSelectGUI.getContentPane().add(lblreadyToBegin);
		frmSelectGUI.getContentPane().add(btnHelp);
		frmSelectGUI.getContentPane().add(btnReady);
		frmSelectGUI.getContentPane().add(lblNewLabel);
		frmSelectGUI.getContentPane().add(lblPlayer);
		frmSelectGUI.getContentPane().add(btnSave);
		
		btnT1P1 = new JButton("");
		btnT1P1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = team1.size() - 1;
				lblP1Number.setText("" + x);
				btnT1P1.setEnabled(false);
				btnT1P1.setToolTipText(null);
				btnT1P1.setIcon(null);
				listModel1.addElement(team1.remove(0));
				refresh();
			}
		});
		btnT1P1.setBounds(50, 6, 40, 40);
		btnT1P1.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT1P1);
		
		btnT1P2 = new JButton("");
		btnT1P2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(1, 1);
			}
		});
		btnT1P2.setBounds(110, 6, 40, 40);
		btnT1P2.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT1P2);
		
		btnT1P3 = new JButton("");
		btnT1P3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(2, 1);
			}
		});
		btnT1P3.setBounds(170, 6, 40, 40);
		btnT1P3.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT1P3);
		
		btnT1P4 = new JButton("");
		btnT1P4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(3, 1);
			}
		});
		btnT1P4.setBounds(230, 6, 40, 40);
		btnT1P4.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT1P4);
		
		btnT1P5 = new JButton("");
		btnT1P5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(4, 1);
			}
		});
		btnT1P5.setBounds(290, 6, 40, 40);
		btnT1P5.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT1P5);
		
		btnT1P6 = new JButton("");
		btnT1P6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(5, 1);
			}
		});
		btnT1P6.setBounds(350, 6, 40, 40);
		btnT1P6.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT1P6);
		
		btnT2P1 = new JButton("");
		btnT2P1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = team2.size() - 1;
				lblP2Number.setText("" + x);
				btnT2P1.setEnabled(false);
				btnT2P1.setToolTipText(null);
				btnT2P1.setIcon(null);
				listModel2.addElement(team2.remove(0));
				refresh();
			}
		});
		btnT2P1.setBounds(50, 58, 40, 40);
		btnT2P1.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT2P1);
		
		btnT2P2 = new JButton("");
		btnT2P2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(1, 2);
			}
		});
		btnT2P2.setBounds(110, 58, 40, 40);
		btnT2P2.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT2P2);
		
		btnT2P3 = new JButton("");
		btnT2P3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(2, 2);
			}
		});
		btnT2P3.setBounds(168, 58, 40, 40);
		btnT2P3.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT2P3);
		
		btnT2P4 = new JButton("");
		btnT2P4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(3, 2);
			}
		});
		btnT2P4.setBounds(230, 58, 40, 40);
		btnT2P4.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT2P4);
		
		btnT2P5 = new JButton("");
		btnT2P5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(4, 2);
			}
		});
		btnT2P5.setBounds(290, 58, 40, 40);
		btnT2P5.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT2P5);
		
		btnT2P6 = new JButton("");
		btnT2P6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePokemon(5, 2);
			}
		});
		btnT2P6.setBounds(350, 58, 40, 40);
		btnT2P6.setEnabled(false);
		frmSelectGUI.getContentPane().add(btnT2P6);
		
		lblP = new JLabel("P1");
		lblP.setFont(new Font("Futura", Font.PLAIN, 16));
		lblP.setBounds(14, 17, 61, 16);
		frmSelectGUI.getContentPane().add(lblP);
		
		lblP_1 = new JLabel("P2");
		lblP_1.setFont(new Font("Futura", Font.PLAIN, 16));
		lblP_1.setBounds(14, 68, 61, 16);
		frmSelectGUI.getContentPane().add(lblP_1);
		
		label = new JLabel("P1");
		label.setFont(new Font("Futura", Font.PLAIN, 16));
		label.setBounds(409, 18, 38, 16);
		frmSelectGUI.getContentPane().add(label);
		
		label_1 = new JLabel("P2");
		label_1.setFont(new Font("Futura", Font.PLAIN, 16));
		label_1.setBounds(409, 69, 38, 16);
		frmSelectGUI.getContentPane().add(label_1);
		
	}
	
	public void enableButton(int i, int j)
	{
		if(i == 1)
		{
			if(j == 1)
			{
				btnT1P1.setToolTipText(team1.get(0).getFormattedHTMLStats());
				team1.get(0).addImages();
				btnT1P1.setIcon(team1.get(0).minisprite);
				if(team == 1 || team == 0) btnT1P1.setEnabled(true);
			}
			if(j == 2)
			{
				btnT1P2.setToolTipText(team1.get(1).getFormattedHTMLStats());
				team1.get(1).addImages();
				btnT1P2.setIcon(team1.get(1).minisprite);
				if(team == 1 || team == 0) btnT1P2.setEnabled(true);
			}
			if(j == 3)
			{
				btnT1P3.setToolTipText(team1.get(2).getFormattedHTMLStats());
				team1.get(2).addImages();
				btnT1P3.setIcon(team1.get(2).minisprite);
				if(team == 1 || team == 0) btnT1P3.setEnabled(true);
			}
			if(j == 4)
			{
				btnT1P4.setToolTipText(team1.get(3).getFormattedHTMLStats());
				team1.get(3).addImages();
				btnT1P4.setIcon(team1.get(3).minisprite);
				if(team == 1 || team == 0) btnT1P4.setEnabled(true);
			}
			if(j == 5)
			{
				btnT1P5.setToolTipText(team1.get(4).getFormattedHTMLStats());
				team1.get(4).addImages();
				btnT1P5.setIcon(team1.get(4).minisprite);
				if(team == 1 || team == 0) btnT1P5.setEnabled(true);
			}
			if(j == 6)
			{
				btnT1P6.setToolTipText(team1.get(5).getFormattedHTMLStats());
				team1.get(5).addImages();
				btnT1P6.setIcon(team1.get(5).minisprite);
				if(team == 1 || team == 0) btnT1P6.setEnabled(true);
			}
		}
		if(i == 2)
		{
			if(j == 1)
			{
				btnT2P1.setToolTipText(team2.get(0).getFormattedHTMLStats());
				team2.get(0).addImages();
				btnT2P1.setIcon(team2.get(0).minisprite);
				if(team == 2 || team == 0) btnT2P1.setEnabled(true);
			}
			if(j == 2)
			{
				btnT2P2.setToolTipText(team2.get(1).getFormattedHTMLStats());
				team2.get(1).addImages();
				btnT2P2.setIcon(team2.get(1).minisprite);
				if(team == 2 || team == 0) btnT2P2.setEnabled(true);
			}
			if(j == 3)
			{
				btnT2P3.setToolTipText(team2.get(2).getFormattedHTMLStats());
				team2.get(2).addImages();
				btnT2P3.setIcon(team2.get(2).minisprite);
				if(team == 2 || team == 0) btnT2P3.setEnabled(true);
			}
			if(j == 4)
			{
				btnT2P4.setToolTipText(team2.get(3).getFormattedHTMLStats());
				team2.get(3).addImages();
				btnT2P4.setIcon(team2.get(3).minisprite);
				if(team == 2 || team == 0) btnT2P4.setEnabled(true);
			}
			if(j == 5)
			{
				btnT2P5.setToolTipText(team2.get(4).getFormattedHTMLStats());
				team2.get(4).addImages();
				btnT2P5.setIcon(team2.get(4).minisprite);
				if(team == 2 || team == 0) btnT2P5.setEnabled(true);
			}
			if(j == 6)
			{
				btnT2P6.setToolTipText(team2.get(5).getFormattedHTMLStats());
				team2.get(5).addImages();
				btnT2P6.setIcon(team2.get(5).minisprite);
				if(team == 2 || team == 0) btnT2P6.setEnabled(true);
			}
		}
	}
	
	public void disableButton(int i, int j)
	{
		if(i == 1)
		{
			if(j == 1)
			{
				btnT1P1.setEnabled(false);
				btnT1P1.setToolTipText(null);
				btnT1P1.setIcon(null);
			}
			if(j == 2)
			{
				btnT1P2.setEnabled(false);
				btnT1P2.setToolTipText(null);
				btnT1P2.setIcon(null);
			}
			if(j == 3)
			{
				btnT1P3.setEnabled(false);
				btnT1P3.setToolTipText(null);
				btnT1P3.setIcon(null);
			}
			if(j == 4)
			{
				btnT1P4.setEnabled(false);
				btnT1P4.setToolTipText(null);
				btnT1P4.setIcon(null);
			}
			if(j == 5)
			{
				btnT1P5.setEnabled(false);
				btnT1P5.setToolTipText(null);
				btnT1P5.setIcon(null);
			}
			if(j == 6)
			{
				btnT1P6.setEnabled(false);
				btnT1P6.setToolTipText(null);
				btnT1P6.setIcon(null);
			}
		}
		if(i == 2)
		{
			if(j == 1)
			{
				btnT2P1.setEnabled(false);
				btnT2P1.setToolTipText(null);
				btnT2P1.setIcon(null);
			}
			if(j == 2)
			{
				btnT2P2.setEnabled(false);
				btnT2P2.setToolTipText(null);
				btnT2P2.setIcon(null);
			}
			if(j == 3)
			{
				btnT2P3.setEnabled(false);
				btnT2P3.setToolTipText(null);
				btnT2P3.setIcon(null);
			}
			if(j == 4)
			{
				btnT2P4.setEnabled(false);
				btnT2P4.setToolTipText(null);
				btnT2P4.setIcon(null);
			}
			if(j == 5)
			{
				btnT2P5.setEnabled(false);
				btnT2P5.setToolTipText(null);
				btnT2P5.setIcon(null);
			}
			if(j == 6)
			{
				btnT2P6.setEnabled(false);
				btnT2P6.setToolTipText(null);
				btnT2P6.setIcon(null);
			}
		}
	}
	
	public void refresh()
	{
		if(team1.size() == 1)
		{
			enableButton(1, 1);
			disableButton(1, 2);
		}
		if(team1.size() == 2)
		{
			enableButton(1, 1);
			enableButton(1, 2);
			disableButton(1, 3);
		}
		if(team1.size() == 3)
		{
			enableButton(1, 1);
			enableButton(1, 2);
			enableButton(1, 3);
			disableButton(1, 4);
		}
		if(team1.size() == 4)
		{
			enableButton(1, 1);
			enableButton(1, 2);
			enableButton(1, 3);
			enableButton(1, 4);
			disableButton(1, 5);
		}
		if(team1.size() == 5)
		{
			enableButton(1, 1);
			enableButton(1, 2);
			enableButton(1, 3);
			enableButton(1, 4);
			enableButton(1, 5);
			disableButton(1, 6);
		}
		if(team1.size() == 6)
		{
			enableButton(1, 1);
			enableButton(1, 2);
			enableButton(1, 3);
			enableButton(1, 4);
			enableButton(1, 5);
			enableButton(1, 6);
		}
		if(team2.size() == 1)
		{
			enableButton(2, 1);
			disableButton(2, 2);
		}
		if(team2.size() == 2)
		{
			enableButton(2, 1);
			enableButton(2, 2);
			disableButton(2, 3);
		}
		if(team2.size() == 3)
		{
			enableButton(2, 1);
			enableButton(2, 2);
			enableButton(2, 3);
			disableButton(2, 4);
		}
		if(team2.size() == 4)
		{
			enableButton(2, 1);
			enableButton(2, 2);
			enableButton(2, 3);
			enableButton(2, 4);
			disableButton(2, 5);
		}
		if(team2.size() == 5)
		{
			enableButton(2, 1);
			enableButton(2, 2);
			enableButton(2, 3);
			enableButton(2, 4);
			enableButton(2, 5);
			disableButton(2, 6);
		}
		if(team2.size() == 6)
		{
			enableButton(2, 1);
			enableButton(2, 2);
			enableButton(2, 3);
			enableButton(2, 4);
			enableButton(2, 5);
			enableButton(2, 6);
		}
	}
	
	public void addPokemon(boolean a, int index, int team) {
		if(team == 1) {
			if(!a && index > -1 && team1.size() < 6)
			{
				int x = team1.size() + 1;
				lblP1Number.setText("" + x);
				
				team1.add((Pokemon) listModel1.remove(index));
				
				if(network != null && this.team == team) {
					network.sendMessage("TEAM " + index);
				}
				
				refresh();
			}
		} else if(team == 2) {
			if(!a && index > -1 && team2.size() < 6)
			{
				int x = team2.size() + 1;
				lblP2Number.setText("" + x);
				team2.add((Pokemon) listModel2.remove(index));
				
				if(network != null && this.team == team) {
					network.sendMessage("TEAM " + index);
				}
				
				refresh();
			}
		}
	}
	
	public void removePokemon(int index, int team) {
		if(team == 1) {
			int x = team1.size() - 1;
			lblP1Number.setText("" + x);
			listModel1.addElement(team1.remove(index));
			
			if(network != null && this.team == team) {
				network.sendMessage("DROP " + index);
			}
			
			refresh();
		} else if(team == 2) {
			int x = team2.size() - 1;
			lblP2Number.setText("" + x);
			listModel2.addElement(team2.remove(index));
			
			if(network != null && this.team == team) {
				network.sendMessage("DROP " + index);
			}
			
			refresh();
		}
	}
	
	public void setReady(int i) {
		if(i==1) team1isReady = true;
		if(i==2) team2isReady = true;
		
		if(team1isReady && team2isReady) startGame();
	}
	
	private void startGame() {
		
		List<Pokemon> teamone = team1;
		for(Pokemon x : teamone)
		{
			x.team1 = true;
		}
		List<Pokemon> teamtwo = team2;
		for(Pokemon x : teamtwo)
		{
			x.team1 = false;
		}
		
		
		frmSelectGUI.setVisible(false);
		frmSelectGUI.dispose();
		Collections.shuffle(teamone, random);
		Collections.shuffle(teamtwo, random);
		new MainGUI(teamone, teamtwo, team, random, network);
		MainGUI.frmMainGUI.setVisible(true);
	}
}
