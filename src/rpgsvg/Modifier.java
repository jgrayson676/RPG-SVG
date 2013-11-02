package rpgsvg;
import java.io.Serializable;


public class Modifier implements Serializable{		//A storage object used to help Triggers convey changes in the Battle framework.
	

	
	private static final long serialVersionUID = 3;
	public double attack;
	public double defense;
	public int addstatus;
	public int[] statModifiers = {0, 0, 0, 0, 0, 0, 0, 0};	//0-ATTACK, 1-DEFENSE, 2-SPECIAL, 3-SPECIAL DEFENSE, 4-SPEED, 5-EVASION, 6-ACCURACY, 7-CRIT
	public double hp;
	public String dialog;
	public boolean deleteTrigger;
	public boolean isCrit;
	public double recoil;
	
	
	public Modifier()
	{
		attack = 1;
		defense = 1;
		addstatus = Pokemon.NO_STATUS;
		hp = 0;
		dialog = "";
		deleteTrigger = false;
		isCrit = false;
		recoil = 0;
	}
	
			//										1		2	  3		  4		 5		6	   7	   8       9
	public Modifier(double a, double b,    int c, int d, int e, int f, int g, int h, int i, int j, int k, double l, String m, boolean n)
	{		//		ATTACK	  DEFENSE	 STATUS		ATK	   DEF	  SPC	SPDEF  SPEED   EVAD   ACC	 CRIT	  HP      DIALOG	 DELTRG		
		attack = a;
		defense = b;
		addstatus = c;
		statModifiers[0] = d;
		statModifiers[1] = e;
		statModifiers[2] = f;
		statModifiers[3] = g;
		statModifiers[4] = h;
		statModifiers[5] = i;
		statModifiers[6] = j;
		statModifiers[7] = k;
		hp = l;
		dialog = m;
		deleteTrigger = n;
        recoil = 0;
	}
    
    //Extra constructor for moves with recoil that I made because I was lazy
    public Modifier(double a, double b,    int c, int d, int e, int f, int g, int h, int i, int j, int k, double l, String m, boolean n, double o)
	{		//		ATTACK	  DEFENSE	 STATUS		ATK	   DEF	  SPC	SPDEF  SPEED   EVAD   ACC	 CRIT	  HP      DIALOG	 DELTRG  RECOIL
		attack = a;
		defense = b;
		addstatus = c;
		statModifiers[0] = d;
		statModifiers[1] = e;
		statModifiers[2] = f;
		statModifiers[3] = g;
		statModifiers[4] = h;
		statModifiers[5] = i;
		statModifiers[6] = j;
		statModifiers[7] = k;
		hp = l;
		dialog = m;
		deleteTrigger = n;
        recoil = o;
	}
	
	public static double getStatMultiplier(int n) //generic calculation for stat multipliers
	{
		switch(n)
		{
		case -6: return 0.25;
		case -5: return 0.29;
		case -4: return 0.33;
		case -3: return 0.40;
		case -2: return 0.50;
		case -1: return 0.67;
		case 0: return 1;
		case 1: return 1.5;
		case 2: return 2;
		case 3: return 2.5;
		case 4: return 3;
		case 5: return 3.5;
		case 6: return 4;
		}
		return -1;
	}
	
	public static double getEvasionMultiplier(int n)	//calculation for evasion only
	{
		switch(n)
		{
		case -6: return 0.33;
		case -5: return 0.38;
		case -4: return 0.43;
		case -3: return 0.50;
		case -2: return 0.60;
		case -1: return 0.75;
		case 0: return 1;
		case 1: return 1.33;
		case 2: return 1.67;
		case 3: return 2;
		case 4: return 2.33;
		case 5: return 2.67;
		case 6: return 3;
		}
		return -1;
	}
	
	public static double getCritChance(int stage){	//calculation for critical hit chances
	    switch(stage)
	    {
	    case 1: return 0.0625;
	    case 2: return 0.1250;
	    case 3: return 0.2500;
	    case 4: return 0.3333;
	    case 5: return 0.5000;
	    }
	    System.out.println("Critical hit stage out of bounds: " + stage);
	    return 0;
	}
	
	public static String getStatusText(int i){
		switch(i)
		{
		case Pokemon.BURN: return "burned";
		case Pokemon.FREEZE: return "frozen";
		case Pokemon.BAD_POISON: return "badly poisoned";
		case Pokemon.PARALYSIS: return "paralyzed";
		case Pokemon.SLEEP: return "put to sleep";
		case Pokemon.POISON: return "poisoned";
		}
		return "unaffected";
	}
	
	
	public static void print(int stat, int lvl, Pokemon p)	//updates the console of MainGUI with the effects of a level change of a certain statistic of a certain Pokemon.
	{
		String name = getStat(stat);
		
		switch(lvl)
		{
		case -3: MainGUI.txtInfo.append("\n" + p.name + "\'s " + name + " fell drastically!\n");	break;
		case -2: MainGUI.txtInfo.append("\n" + p.name + "\'s " + name + " sharply fell!\n");        break;
		case -1: MainGUI.txtInfo.append("\n" + p.name + "\'s " + name + " fell!\n");                break;
		case  1: MainGUI.txtInfo.append("\n" + p.name + "\'s " + name + " rose!\n");                break;
		case  2: MainGUI.txtInfo.append("\n" + p.name + "\'s " + name + " sharply rose!\n");        break;
		case  3: MainGUI.txtInfo.append("\n" + p.name + "\'s " + name + " rose drastically!\n");    break;
		case  6: MainGUI.txtInfo.append("\n" + p.name + " maximized its " + name + "!\n");          break;
		}                                   
	}
	
	public static String getStat(int i)		//returns the name of the stat referred to by i.
	{
		switch(i)
		{
		case 0:	return "attack";			
		case 1: return "defense";			
		case 2: return "special attack";	
		case 3: return "special defense";	
		case 4: return "speed";				
		case 5: return "evasion"; 			
		}
		return null;
	}

}
