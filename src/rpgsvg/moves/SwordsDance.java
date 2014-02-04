package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class SwordsDance extends Move {

	private static final long serialVersionUID = 770463059353155486L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.NORMAL;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstage = 0;
	
	public SwordsDance(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.ATTACK, +2);
			Battle.getAttacker().applyModifier(m);
		}
	}