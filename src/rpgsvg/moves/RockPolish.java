package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class RockPolish extends Move {

	private static final long serialVersionUID = -6008137979458790857L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.ROCK;
	private int damage =0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstage = 0;
	
	public RockPolish(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPEED, +2);
			Battle.getAttacker().applyModifier(m);
	}
}