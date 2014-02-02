package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Thunderbolt extends Move {

	private static final long serialVersionUID = 330631313869541163L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.ELECTRIC;
	private int damage = 90;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public Thunderbolt(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.PARALYSIS);
			Battle.getDefender().applyModifier(m);
		}
	}
}