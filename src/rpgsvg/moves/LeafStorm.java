package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public class LeafStorm extends Move{

	private static final long serialVersionUID = -8083150504892432277L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.GRASS;
	private int damage = 130;
	private int accuracy = 90;
	private int priority = 0;
	private int pp = 5;
	private int cristage = 0;
	
	public LeafStorm(){
		
	}
	
	public void run(){
		Modifier m = new Modifier();
		m.setStatModifier(Modifier.SPECIAL, -2);
		Battle.getAttacker().applyModifier(m);
	}
}
