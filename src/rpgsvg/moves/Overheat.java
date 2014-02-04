package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class Overheat extends Move {

	private static final long serialVersionUID = 4075986620715929737L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.FIRE;
	private int damage = 130;
	private int accuracy = 90;
	private int priority = 0;
	private int pp = 5;
	private int critstage = 0;
	
	public Overheat(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPECIAL, -2);
			Battle.getDefender().applyModifier(m);
		}
	}
