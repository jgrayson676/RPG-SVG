package rpgsvg;
import java.io.Serializable;

import rpgsvg.Pokemon.Type;


public class Move implements Serializable{		//Object specifications for moves to be used by Pokemon.
	
	
	private static final long serialVersionUID = 5;
	
	/*FIELDS*/
	public String name;
	public boolean isPhysical;
	public int type;
	public int damage;
	public int accuracy;
	public Modifier self;
	public Modifier enemy;
	public double effectChance;
	public int priority;
	
	public Type mtype;
	
	public int critstage; //0 is normal, 1 is high critical hit ratio
	public int pp;
	
	/*CONSTRUCTORS*/
	
	public Move(String mname, boolean physical, int mt, int mdamage, int maccuracy, Modifier mp1status, Modifier mp2status, double meffectChance, int mpriority, int mcritstage, int powerpoints)
	{
		name = mname;
		isPhysical = physical;
		type = mt;
		
		for(Type t : Type.values())
		{
			if(t.type() == mt)
				mtype = t;
		}
		
		damage = mdamage;
		accuracy = maccuracy;
		self = mp1status;
		enemy = mp2status;
		effectChance = meffectChance;
		priority = mpriority;
		critstage = mcritstage;
		pp = powerpoints;
		
	}
	
	
	
	/* METHODS */
	
	public String getType()		//returns the name of the type of the move.
	{
		return this.mtype.toGUIString();
	}
	

}
