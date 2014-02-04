package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Recover extends Move {
 
	private static final long serialVersionUID = 6087897105423876127L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.NORMAL;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public Recover(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setHp(0.5);
			Battle.getAttacker().applyModifier(m);
		}
	}