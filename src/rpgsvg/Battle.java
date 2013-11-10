package rpgsvg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import rpgsvg.moves.Move;
import rpgsvg.triggers.*;


public class Battle implements Serializable {	//The encapsulating class for all internal actions once selections have been made by both players.
	
	

	private static final long serialVersionUID = 2;
	public ArrayList<Pokemon> team1;
	public ArrayList<Pokemon> team2;
	public static Pokemon p1;
	public static Pokemon p2;
	public static int move1;
	public static int move2;
	
	public static double crit;
	public static double stab;
	public static double typemod;
	public static double accuracy;
	
	public ArrayList<Trigger> beginturn = new ArrayList<Trigger>();      
	public ArrayList<Trigger> beforeattack = new ArrayList<Trigger>();     
	public ArrayList<Trigger> beforedefend = new ArrayList<Trigger>();     
	public ArrayList<Trigger> afterdefend = new ArrayList<Trigger>();     
	public ArrayList<Trigger> endturn = new ArrayList<Trigger>();      
	
	public Trigger currentweather = null;
	public static double tempAttackMod = 1;
	public static double tempDefenseMod = 1;
	public static int oldhealth;
	public static Pokemon attacker;
	public static Pokemon defender;
	public static Move m;
    public static int finaldamage;
    public static boolean p1Faster;
    public static boolean pokemonAttacks = true;
	
    
	public static Timer timer1 = new Timer(10, new ActionListener() {		//decrements Player 1's health bar and health text display to the targethealth1 value.
        public void actionPerformed(ActionEvent evt) {
    		int currenthealthbar = MainGUI.healthBarP1.getValue();
        	if(currenthealthbar > targethealth1)
        	{
        		MainGUI.healthBarP1.setValue(currenthealthbar - 1);
        		int x = MainGUI.healthBarP1.getValue()  * p1.stats[0] / 100;
        		MainGUI.lblHealthP1.setText(x + "/" + p1.stats[0]);
        	}
        	else
        	{
        		MainGUI.lblHealthP1.setText(p1.currenthealth + "/" + p1.stats[0]);
        		timer1.stop();
        	}
        		
        }
    }
	);
	public static Timer timer2 = new Timer(10, new ActionListener() {		//decrements Player 2's health bar and health text display to the targethealth2 value.
        public void actionPerformed(ActionEvent evt) {
        	int currenthealthbar = MainGUI.healthBarP2.getValue();
        	if(currenthealthbar > targethealth2)
        	{
        		MainGUI.healthBarP2.setValue(currenthealthbar - 1);
        		int x = MainGUI.healthBarP2.getValue()  * p2.stats[0] / 100;
        		MainGUI.lblHealthP2.setText(x + "/" + p2.stats[0]);
        	}
        		
        	else
        	{
        		MainGUI.lblHealthP2.setText(p2.currenthealth + "/" + p2.stats[0]);
        		timer2.stop();
        	}
        		
        }
    }
	);
	public static Timer run1 = new Timer(1000, new ActionListener() {		//runs Player 1's attack on Player 2, with an initial delay of 1 second (altered as necessary)
		public void actionPerformed(ActionEvent e) {
			run1.setRepeats(false);
			runAttack(p1, p2, p1.moves[move1], false);
		}
	});
	public static Timer run2 = new Timer(1000, new ActionListener() {		//runs Player 2's attack on Player 1, with an initial delay of 1 second (altered as necessary)
		public void actionPerformed(ActionEvent e) {
			run2.setRepeats(false);
			runAttack(p2, p1, p2.moves[move2], false);
		}
	});
	
	public static Timer end = new Timer(2000, new ActionListener() {		//runs ending mechanics and refreshes, and creates new TeamGUI windows as needed.
		public void actionPerformed(ActionEvent e) {						//uses an initial delay of 2 seconds (altered as necessary)
			end.setRepeats(false);
			delayTime = 0;
			
			
			
			for(Trigger t: MainGUI.battle.endturn)
			{
				t.run();
			}
			
			if(p2.currenthealth != 0 && p1.currenthealth != 0)
				MainGUI.refresh();
			/* BEGIN EDIT Steven T. fixed victory 11-3-13*/
			if(p2.currenthealth == 0)
			{
				boolean c = true;
				for (int j = 0; j < MainGUI.team2.size(); j++) {
					if (MainGUI.team2.get(j).currenthealth > 0)
						c = false;
				}
				if (c) {
					JOptionPane.showMessageDialog(MainGUI.frmMainGUI, "P1 Victory!", "VICTORY",
							2, MainGUI.team1.get(0).sprite1);
					System.exit(0);
				}
				else if(MainGUI.team == 2 || MainGUI.team == 0) new TeamGUI(2);
			}
			
			if(p1.currenthealth == 0)
			{
				boolean b = true;
				for (int j = 0; j < MainGUI.team1.size(); j++) {
					if (MainGUI.team1.get(j).currenthealth > 0)
						b = false;
				}
				if (b) {
					JOptionPane.showMessageDialog(MainGUI.frmMainGUI, "P2 Victory!", "VICTORY",
							2, MainGUI.team2.get(0).sprite2);
					System.exit(0);
				}
				else if(MainGUI.team == 1 || MainGUI.team == 0) new TeamGUI(1);
			}
			/* END EDIT Steven T. end victory fix 11-3-13*/
		}
	});
	
	
	
	public static int targethealth1;
	public static int targethealth2;
	public static int delayTime;
	public static int nextDelayTime;
	
	public static Random random;
	

	
	
	/*CONSTRUCTOR*/
	@SuppressWarnings("static-access")
	public Battle(ArrayList<Pokemon> a, ArrayList<Pokemon> b, Random random)	//The battle object where all battle components and the current state of the Pokemon are stored.
	{															//Used as an encapsulating structure, and for saving and loading of games.
		team1 = a;
		team2 = b;
		
		this.random = random;
		
		p1 = team1.get(0);
		p2 = team2.get(0);
		
		addTrigger(p1.ability);
		addTrigger(p2.ability);
		
		addTrigger(new Status(p1));
		addTrigger(new EndStatus(p1));
		addTrigger(new Status(p2));
		addTrigger(new EndStatus(p2));
	
	}
	
	
	
	public void run(int bmove1, int bmove2)
	{
		move1 = bmove1;
		move2 = bmove2;
				
		if(move1 < 0)		//P1 switch Pokemon
		{
			int i = 6;
			switch(move1)
			{
				case -2: i = 1; break;
				case -3: i = 2; break;
				case -4: i = 3; break;
				case -5: i = 4; break;
				case -6: i = 5; break;
			}
			Pokemon p = team1.remove(i);
			
			int[] a = {0, 0, 0, 0, 0, 0, 0, 1};
			team1.get(0).statModifiers = a;
			
			Pokemon temp = team1.remove(0);
			
			MainGUI.appendText("\nPlayer 1 withdrew " + temp.name + "!\n");
			MainGUI.appendText("\nPlayer 1 sent out " + p.name + "!\n");
			
			team1.add(0,p);
			team1.add(i,temp);
			MainGUI.team1 = team1;
			p1 = team1.get(0);
			MainGUI.switchRefresh(temp, p);
		}
		
		if(move2 < 0)		//P2 Switch Pokemon
		{
			int i = 6;
			switch(move2)
			{
				case -2: i = 1; break;
				case -3: i = 2; break;
				case -4: i = 3; break;
				case -5: i = 4; break;
				case -6: i = 5; break;
			}
			Pokemon p = team2.remove(i);
			
			int[] b = {0, 0, 0, 0, 0, 0, 0, 1};
			team2.get(0).statModifiers = b;
			
			Pokemon temp = team2.remove(0);
			
			MainGUI.appendText("\nPlayer 2 withdrew " + temp.name + "!\n");
			MainGUI.appendText("\nPlayer 2 sent out " + p.name + "!\n");
			
			team2.add(0,p);
			team2.add(i, temp);
			MainGUI.team2 = team2;
			p2 = team2.get(0);
			MainGUI.switchRefresh(temp, p);
		}

		
		
		
		
		for(Trigger t: MainGUI.battle.beginturn)
		{
			t.run();
		}
		
		
		
		if(move1 > -1 && move2 > -1)	//Check possible cases for attacking
		{
			if((((p1.stats[5] * Modifier.getStatMultiplier(p1.statModifiers[4]) * (p1.status == Pokemon.PARALYSIS?.25:1)) > (p2.stats[5] * Modifier.getStatMultiplier(p2.statModifiers[4]) * (p2.status == Pokemon.PARALYSIS?.25:1)) && p1.moves[move1].getPriority() == p2.moves[move2].getPriority()) || p1.moves[move1].getPriority() > p2.moves[move2].getPriority()) && move1 > -1)
			{
				boolean b = runAttack(p1, p2, p1.moves[move1], true);
				if(!b && move2 > -1)
				{
					run2.setRepeats(false);
					run2.start();
					end.setRepeats(false);
					end.setInitialDelay(2200);
					end.start();				//END TIMER CONTAINS THE ENDTURN TRIGGER MECHANISM
				}
				else
				{
					end.setRepeats(false);
					end.setInitialDelay(1100);
					end.start();
				}
			}
			else
			{
				boolean b = runAttack(p2, p1, p2.moves[move2], true);
				if(!b && move1 > -1)
				{
					run1.setRepeats(false);
					run1.start();
					end.setRepeats(false);
					end.setInitialDelay(2200);
					end.start();
				}
				else
				{
					end.setRepeats(false);
					end.setInitialDelay(1100);
					end.start();
				}
					
		//			runAttack(p1, p2, p1.moves[move1], false);
			}
		}
		else if(move1 > -1)
		{
			runAttack(p1, p2, p1.moves[move1], true);
			end.setRepeats(false);
			end.setInitialDelay(1100);
			end.start();
		}
		else if(move2 > -1)
		{
			runAttack(p2, p1, p2.moves[move2], true);
			end.setRepeats(false);
			end.setInitialDelay(1100);
			end.start();
		}
		else
		{
			end.setRepeats(false);
			end.setInitialDelay(500);
			end.start();
		}
		

		
		
	}
					//Runs an attack by the attacker on the defender, using the move m
	public static boolean runAttack(Pokemon battacker, Pokemon bdefender, Move bm, boolean isFirst)		//TRUE=defender has fainted, FALSE=defender can attack
	{
		attacker = battacker;
		defender = bdefender;
		m = bm;
		MainGUI.appendText("\n" + attacker.name + " used " + m.getClass().getName() + "!\n");
		
		
		int atkstat;
		int defstat;
		
		
		crit = 1;
		if(random.nextDouble() < Modifier.getCritChance(attacker.statModifiers[7] + m.critstage))
		{
			crit = 2;
		}
		
		if(m.getContact())
		{
			atkstat = attacker.stats[1];
			defstat = defender.stats[2];
		}
		else
		{
			atkstat = attacker.stats[3];
			defstat = defender.stats[4];
		}
		
		
		typemod = typeCheck(m.getAttackType(), defender.stats[6]);
		if(defender.stats[7] != 0)
		{
			typemod = typemod * typeCheck(m.getAttackType(), defender.stats[7]);
		}

		
		stab = 1;
		if(attacker.stats[6] == m.getAttackType() || attacker.stats[7] == m.getAttackType())
		{
			stab = 1.5;
		}
		accuracy = m.getAccuracy() * Modifier.getStatMultiplier(attacker.statModifiers[6]);
		
		//APPLY BEFOREATTACK AND BEFOREDEFEND
		for(Trigger t : MainGUI.battle.beforeattack)
		{	
			if(t.parent.name == attacker.name)
			{
				t.run();
			}
		}
		for(Trigger t : MainGUI.battle.beforedefend)
		{
			if(t.parent.name == defender.name)
			{
				t.run();
			}
		}
		
		if(!pokemonAttacks)
		{
			pokemonAttacks = true;
			return false;
		}
		
		double rand = random.nextDouble() * Modifier.getStatMultiplier(defender.statModifiers[5]);
		
		accuracy = accuracy / 100;
		if(rand > accuracy)			//Quits out if the move misses.
		{
			MainGUI.appendText("\n" + attacker.name + " missed!\n");
			return false;
		}
		
		double randomizer = 1;
		randomizer = randomizer - random.nextDouble()	/ (100 / 15);
		oldhealth = defender.currenthealth;
		
		finaldamage = 0;
        if(crit > 1){
            double damage = ((((((22 * m.getDamage() * (atkstat * Math.max(Modifier.getStatMultiplier(m.getContact()?attacker.statModifiers[0]:attacker.statModifiers[2]), 1)) / 50) / (defstat * Math.min(Modifier.getStatMultiplier(m.getContact()?defender.statModifiers[1]:defender.statModifiers[3]), 1))) + 2) * crit * randomizer) * stab * typemod)) * tempAttackMod * tempDefenseMod;
            finaldamage = (int)Math.round(damage);
        }else{
            double damage = ((((((22 * m.getDamage() * (atkstat * Modifier.getStatMultiplier(m.getContact()?attacker.statModifiers[0]:attacker.statModifiers[2])) / 50) / (defstat * Modifier.getStatMultiplier(m.getContact()?defender.statModifiers[1]:defender.statModifiers[3]))) + 2) * crit * randomizer) * stab * typemod)) * tempAttackMod * tempDefenseMod;
            finaldamage = (int) Math.round(damage);
        }
        
        if(m.getDamage() == 0)
		  finaldamage = 0;
		if(finaldamage > defender.currenthealth)
		{
			finaldamage = defender.currenthealth;
		}
        
        //AFTERDEFEND
        for(Trigger t : MainGUI.battle.afterdefend)
        {
        	if(defender.name == t.parent.name)
        	{
        		t.run();
        	}
        }
        
        bm.pp--;
        
        if(m.getDamage() != 0)
        {
        	if(crit > 1)
        		MainGUI.appendText("\nCritical Hit!\n");
        	if(typemod == 0)
				MainGUI.appendText("\nIt doesn't affect " + defender.name + "!\n");
			else if(typemod < 1)
				MainGUI.appendText("\nIt's not very effective.\n");
			else if(typemod > 1)
				MainGUI.appendText("\nIt's super effective!\n");
        }
        
		
		if(finaldamage != 0)
			MainGUI.appendText("\n" + m.getClass().getName() + " hit for " + finaldamage + " HP!\n");
		
		
		
		if(defender.team1)
		{
			targethealth1 = 0;
			if(defender.currenthealth != 0)
			{
				defender.currenthealth = defender.currenthealth - finaldamage;
				targethealth1 = (int)(defender.currenthealth * 100 / defender.stats[0]);
			}
		}
		if(!defender.team1)
		{
			targethealth2 = 0;
			if(defender.currenthealth != 0)
			{
				defender.currenthealth = defender.currenthealth - finaldamage;
				targethealth2 = (int)(defender.currenthealth * 100 / defender.stats[0]);
			}
		}
		

		
		if(defender.team1)
		{
			timer1.start();
		}
		else if(!defender.team1)
		{
			timer2.start();
		}
		       //Does not apply modifier if no damage was dealt by a damage dealing move ex: absorbed discharge by volt absorb 
		if((random.nextDouble() < m.effectChance && (typemod != 0 || (typemod == 0 && (m.getDamage() == 0 || m.getDamage() > 150)))) && !(finaldamage == 0 && m.getDamage() > 0))		//APPLY STAT MODIFIERS
		{
			attacker.applyModifier(m.self);
			if(typemod > 0)
			{
				defender.applyModifier(m.enemy);
			}
		}
		
		tempAttackMod = 1;
		tempDefenseMod = 1;
		if(defender.currenthealth == 0)
		{
			MainGUI.appendText("\n" + defender.name + " fainted!\n");
			return true;
		}
		else
			return false;
		
	}
	
	
	/*	Types: Normal(1), Fire(2), Water(3), Electric(4), Grass(5), Ice(6), Fighting(7), Poison(8),
	 *  Ground(9), Flying(10), Psychic(11), Bug(12), Rock(13), Ghost(14), Dragon(15), Dark(16), Steel(17)
	 */
	
	public static double typeCheck(int attType, int defType){						//Checks for attack multipliers
	                       //NorFirWatEleGraIceFigPoiGroFlyPsyBugRocGhoDraDarSte
	  double[][] matchups = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,.5, 0, 1, 1,.5}, /* Normal */		//columns are defensive, rows are offensive
	                         {1,.5,.5, 1, 2, 2, 1, 1, 1, 1, 1, 2,.5, 1,.5, 1, 2}, /* Fire */
	                         {1, 2,.5, 1,.5, 1, 1, 1, 2, 1, 1, 1, 2, 1,.5, 1, 1}, /* Water */
	                         {1, 1, 2,.5,.5, 1, 1, 1, 0, 2, 1, 1, 1, 1,.5, 1, 1}, /* Electric */
	                         {1,.5, 2, 1,.5, 1, 1,.5, 2,.5, 1,.5, 2, 1,.5, 1,.5}, /* Grass */
	                         {1,.5,.5, 1, 2,.5, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1,.5}, /* Ice */
	                         {2, 1, 1, 1, 1, 2, 1,.5, 1,.5,.5,.5, 2, 0, 1, 2, 2}, /* Fighting */
	                         {1, 1, 1, 1, 2, 1, 1,.5,.5, 1, 1, 1,.5,.5, 1, 1, 0}, /* Poison */
	                         {1, 2, 1, 2,.5, 1, 1, 2, 1, 0, 1,.5, 1, 1, 1, 1, 2}, /* Ground */
	                         {1, 1, 1,.5, 2, 1, 2, 1, 1, 1, 1, 2,.5, 1, 1, 1,.5}, /* Flying */
	                         {1, 1, 1, 1, 1, 1, 2, 2, 1, 1,.5, 1, 1, 1, 1, 0,.5}, /* Psychic */
	                         {1,.5, 1, 1, 2, 1,.5,.5, 1,.5, 2, 1, 1,.5, 1, 2,.5}, /* Bug */
	                         {1, 2, 1, 1, 1, 2,.5, 1,.5, 2, 1, 2, 1, 1, 1, 1,.5}, /* Rock */
	                         {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1,.5, 1}, /* Ghost */
	                         {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1,.5}, /* Dragon */
	                         {1, 1, 1, 1, 1, 1,.5, 1, 1, 1, 2, 1, 1, 2, 1,.5, 1}, /* Dark */
	                         {1,.5,.5,.5, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1,.5}};/* Steel */
	  if((attType > 0 && attType < 18) && (defType > 0 && defType < 18))
	    return matchups[attType -1][defType -1];
	  return -1;
	  }
	
	
	public void refresh(int i, Pokemon p)	//keeps the p1 and p2 fields current, as necessary.
	{
		if(i == 1)
		{
			p1 = p;
		}
		else if(i == 2)
		{
			p2 = p;
		}
	}
	
	
	public void runTriggers(ArrayList<Trigger> a){
	    if(a.size() == 0)
	      return;
	    for(Trigger i: a){
	        i.run();
	    }
	}
	
	public void removeTriggers(Pokemon parent){
		for(int i = 0; i < beginturn.size(); i++){
			if(beginturn.get(i).parent.equals(parent))
	    	   beginturn.remove(i);
	        }
		for(int i = 0; i < beforeattack.size(); i++){
			if(beforeattack.get(i).parent.equals(parent))
	    	   beforeattack.remove(i);
	        }
		for(int i = 0; i < beforedefend.size(); i++){
			if(beforedefend.get(i).parent.equals(parent))
	    	   beforedefend.remove(i);
	        }
		for(int i = 0; i < afterdefend.size(); i++){
			if(afterdefend.get(i).parent.equals(parent))
	    	   afterdefend.remove(i);
	        }
		for(int i = 0; i < endturn.size(); i++){
			if(endturn.get(i).parent.equals(parent))
	    	   endturn.remove(i);
	        }
	}
	
	public void addTrigger(Trigger t)
	{
		if(t == null)
			return;
		int i = t.checkSlot();
		switch(i)
		{
		case 1: beginturn.add(t); 		return;
		case 2: beforeattack.add(t); 	return;
		case 3: beforedefend.add(t);  	return;
		case 4: afterdefend.add(t); 	return;
		case 5: endturn.add(t);  		return;
		}
	}

}
