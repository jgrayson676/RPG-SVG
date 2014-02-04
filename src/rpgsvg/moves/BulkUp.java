package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class BulkUp extends Move {

	private static final long serialVersionUID = 8662641800650796978L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.FIGHTING;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstage = 0;
	
	public BulkUp(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.ATTACK, +1);
			m.setStatModifier(Modifier.DEFENSE, +1);
			Battle.getAttacker().applyModifier(m);
		}
	}