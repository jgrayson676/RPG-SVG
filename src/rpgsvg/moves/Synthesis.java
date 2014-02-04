package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Synthesis extends Move {

	private static final long serialVersionUID = 6616690808253316194L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.GRASS;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 5;
	private int critstage = 0;
	
	public Synthesis(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setHp(0.5);
			Battle.getAttacker().applyModifier(m);
		}
	}