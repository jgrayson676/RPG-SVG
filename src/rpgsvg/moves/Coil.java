package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Coil extends Move {

	private static final long serialVersionUID = 8728582271049757554L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.POISON;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstage = 0;
	
	public Coil(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.ATTACK, +1);
			m.setStatModifier(Modifier.DEFENSE, +1);
			m.setStatModifier(Modifier.ACCURACY, +1);
			Battle.getAttacker().applyModifier(m);
		}
	}