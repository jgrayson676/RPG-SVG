package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class BugBuzz extends Move {

	private static final long serialVersionUID = 3830923790162925847L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.BUG;
	private int damage = 90;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 10;
	private int critstage = 0;
	
	public BugBuzz(){
		
	}
	
	public void run(){
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPECIALDEFENSE, -1);
			Battle.getDefender().applyModifier(m);
		}
	}
}