package rpgsvg.moves;

import rpgsvg.Battle;
import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;

public class NastyPlot extends Move {

	private static final long serialVersionUID = -2484733662024174068L;
	private boolean isPhysical = false;
	private Type type = Pokemon.Type.DARK;
	private int damage = 0;
	private int accuracy = 100;
	private int priority = 0;
	private int pp = 20;
	private int critstage = 0;
	
	public NastyPlot(){
		
	}
	
	public void run(){
			Modifier m = new Modifier();
			m.setStatModifier(Modifier.SPECIAL, +2);
			Battle.getAttacker().applyModifier(m);
		}
	}