package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Toxic extends Move {

	private static final long serialVersionUID = -8412519470416970797L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.POISON;
	private int damage = 0;
	private int accuracy = 90;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public Toxic(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.BAD_POISON);
			Battle.getDefender().applyModifier(m);
	}
}