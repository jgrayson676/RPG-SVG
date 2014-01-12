package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public class HammerArm extends Move {
	
	private boolean isPhysical = true;
	private Type type = Pokemon.Type.FIGHTING;
	private int damage = 100;
	private int accuracy = 90;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	private static final long serialVersionUID = -77977936958338150L;

	public HammerArm(){
		
	}
	
	public void run(){
		Modifier m = new Modifier();
		m.setStatModifier(Modifier.SPEED, -1);
		Battle.getAttacker().applyModifier(m);
	}
}