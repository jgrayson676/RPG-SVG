package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public class Roost extends Move {

	private static final long serialVersionUID = 114768340954067300L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.FLYING;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public Roost(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setHp(0.5);
			Battle.getAttacker().applyModifier(m);
		}
	}