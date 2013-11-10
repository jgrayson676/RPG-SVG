package rpgsvg.moves;
import java.io.Serializable;

import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public abstract class Move implements Serializable{		//Object specifications for moves to be used by Pokemon.
	
	
	private static final long serialVersionUID = 5;
	
	/*FIELDS*/
	private boolean isPhysical;
	private int type;
	private int damage;
	private int accuracy;
	private int priority;
	
	public Type mtype;
	
	public int critstage; //0 is normal, 1 is high critical hit ratio
	public int pp;
	
	/*CONSTRUCTORS*/
	
	public Move(){
		
	}
	
	/* METHODS */
	
	public String getType()		//returns the name of the type of the move.
	{
		return this.mtype.toGUIString();
	}
	
	public boolean getContact() {
		return this.isPhysical;
	}
	
	public int getAttackType(){
		return this.type;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	public int getAccuracy(){
		return this.accuracy;
	}
	
	public int getPriority(){
		return this.priority;
	}
	
	public void run(){
		
	}
}
