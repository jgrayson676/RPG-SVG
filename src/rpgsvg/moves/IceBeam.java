package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class IceBeam extends Move {

	private static final long serialVersionUID = -6910382536037166989L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.ICE;
	private int damage = 90;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public IceBeam(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setAddstatus(Pokemon.FREEZE);
			Battle.getDefender().applyModifier(m);
		}
	}
}
