package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class WillOWisp extends Move {

	private static final long serialVersionUID = -1174330626685697403L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.FIRE;
	private int damage = 0;
	private int accuracy = 85;
	private int priority = 0;
	private int pp = 15;
	private int critstage = 0;
	
	public WillOWisp(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.BURN);
			Battle.getDefender().applyModifier(m);
	}
}
