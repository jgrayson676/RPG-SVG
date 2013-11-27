package rpgsvg.moves;
import java.io.Serializable;

import rpgsvg.Modifier;
import rpgsvg.Pokemon;
import rpgsvg.Pokemon.Type;


public abstract class Move implements Serializable{		//Object specifications for moves to be used by Pokemon.
	
	
	private static final long serialVersionUID = 5;
	
	/*FIELDS*/
	private boolean isPhysical;
	private Type type;
	private int damage;
	private int accuracy;
	private int priority;
		
	private int critstage; //0 is normal, 1 is high critical hit ratio
	private int pp;
	
	/*CONSTRUCTORS*/
	
	public Move(){
		
	}
	
	/* METHODS */
	
	public String getGUIString()		//returns the name of the type of the move.
	{
		return this.type.toGUIString();
	}
	
	public Type getType() {
		return this.type;
	}
	
	public boolean getContact() {
		return this.isPhysical;
	}
	
	public int getAttackType(){
		return this.type.type();
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
	
	public int getCritStage(){
		return this.critstage;
	}
	
	public int getPP(){
		return this.pp;
	}
	
	public void run(){
		
	}
}
