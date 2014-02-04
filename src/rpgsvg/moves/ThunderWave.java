package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class ThunderWave extends Move {

	private static final long serialVersionUID = 7084670981533026352L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.ELECTRIC;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstage = 0;
	
	public ThunderWave(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.PARALYSIS);
			Battle.getDefender().applyModifier(m);
	}
}
