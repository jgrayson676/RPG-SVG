package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Crunch extends Move {

	private static final long serialVersionUID = 2435446062822680190L;
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.DARK;
	private int damage = 80;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public Crunch(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.DEFENSE, -1);
			Battle.getDefender().applyModifier(m);
		}
	}
}
