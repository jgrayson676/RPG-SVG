package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Spore extends Move {

	private static final long serialVersionUID = -1733934651238678111L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.GRASS;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public Spore(){
		
	}
	
	public void run(){
		
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.SLEEP);
			Battle.getDefender().applyModifier(m);
	}
}
