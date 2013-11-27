package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon.Type;

public class FocusBlast extends Move {

	private static final long serialVersionUID = -960887830590624391L;
	private boolean isPhysical = false;
	private Type type = Type.FIGHTING;
	private int damage = 120;
	private int accuracy = 70;
	private int priority = 0;
	private int pp = 5;
	
	public FocusBlast(){
		
	}
	
	public void run() {
		if(Battle.random.nextDouble() < 0.1){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPECIALDEFENSE, -1);
			Battle.getDefender().applyModifier(m);
		}
	}
	
}
