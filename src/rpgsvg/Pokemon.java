package rpgsvg;
import java.awt.Color;
import java.io.Serializable;
import javax.swing.ImageIcon;
import rpgsvg.triggers.Trigger;


public class Pokemon implements Serializable{		//Object specifications for individual Pokemon
	
	
	/* STATUS NUMBERS*/
	
    public static final int NO_STATUS 	= 0;
    public static final int BURN 		= 1;
    public static final int FREEZE 		= 2;
    public static final int PARALYSIS 	= 3;
    public static final int SLEEP 		= 4;
    public static final int POISON 		= 5;
    public static final int BAD_POISON 	= 6;
    
    /* TYPE COLORS */
    
    public enum Type {
    	
    	NORMAL		(1,	191,	185, 	139 )	,
    	FIRE		(2,	255, 	102, 	139 )	,	
    	WATER		(3,	0, 		191, 	235 )	,
    	ELECTRIC	(4,	241, 	189, 	0	)	,
    	GRASS		(5,	0, 		254, 	85	)	,
    	ICE			(6,	0, 		236, 	223 )	,
    	FIGHTING	(7,	255, 	0, 		0	)	,
    	POISON		(8,	228, 	1, 		236 )	,
    	GROUND		(9,	200, 	121, 	19	)	,
    	FLYING		(10,91, 	0, 		202 )	,
    	PSYCHIC		(11,255, 	56, 	104 )	,
    	BUG			(12,119, 	211, 	0	)	,
    	ROCK		(13,167, 	112, 	26	)	,
    	GHOST		(14,138, 	69, 	197 )	,
    	DRAGON		(15,0, 		180, 	255 )	,
    	DARK		(16,100, 	100, 	100 )	,
    	STEEL		(17,88, 	96, 	124 )	;
    	
    	private final int type;
    	private final int r;
    	private final int g;
    	private final int b;
    	
    	Type(int type, int r, int g, int b)
    	{
    		this.type = type;
    		this.r = r;
    		this.g = g;
    		this.b = b;
    	}
    	
    	public Color color()
    	{
    		return new Color(r, g, b);
    	}
    	
    	public int type()
    	{
    		return type;
    	}
    	
    	public String toGUIString()		
    	{
    		String x = this.toString().toLowerCase();
    		char c = Character.toUpperCase(x.charAt(0));
    		x = c + x.substring(1, x.length());
    		return x;
    	}
    }
    
   
    
	private static final long serialVersionUID = 1;
	
	/*FIELDS*/
	public String name;
	ImageIcon sprite1;				
	ImageIcon sprite2;
	ImageIcon minisprite;
	public Move[] moves = new Move[4];
	public int[] stats = new int[8]; 							//0-HP, 1-ATTACK, 2-DEFENSE, 3-SPECIAL, 4-SPECIAL DEFENSE, 5-SPEED, 6-TYPE1, 7-TYPE2
	
	public Type type1;
	public Type type2;
	
	public int currenthealth;
	public int[] statModifiers = {0, 0, 0, 0, 0, 0, 0, 1}; 	//0-ATTACK, 1-DEFENSE, 2-SPECIAL, 3-SPECIAL DEFENSE, 4-SPEED, 5-EVASION, 6-ACCURACY, 7-CRITSTAGE
	public int status;
	public int statusTimer = 0;
	public Trigger ability;
	public boolean team1;
	
	
	
	/*CONSTRUCTORS*/
	
	public Pokemon()
	{
		
	}
	
	public Pokemon(String pname, int hp, int attack, int defense, int special, int sdef, int speed, int t1, int t2, Move m1, Move m2, Move m3, Move m4, Trigger a)
	{
		name = pname;
		stats[0] = hp + 75;
		currenthealth = hp + 75;
		stats[1] = attack + 15;
		stats[2] = defense + 15;
		stats[3] = special + 15;
		stats[4] = sdef + 15;
		stats[5] = speed + 15;
		stats[6] = t1;
		stats[7] = t2;
		
		for(Type t : Type.values())
		{
			if (t.type() == t1)
				this.type1 = t;
			if (t.type() == t2)
				this.type2 = t;
		}
		
		ability = a;
		moves[0] = m1;
		moves[1] = m2;
		moves[2] = m3;
		moves[3] = m4;
		ability = a;
	}
	
	/*METHODS*/
	
	public void addImages()	//used when a user retrieves Pokemon information from SelectGUI, or when the MainGUI is initialized, to save file i/o time.
	{
		 try{
	          sprite1 = new ImageIcon(this.getClass().getResource("Media/Images/" + name.toLowerCase() + "flip.gif"));
	          sprite2 = new ImageIcon(this.getClass().getResource("Media/Images/" + name.toLowerCase() + ".gif"));
	          minisprite = new ImageIcon(this.getClass().getResource("Media/Images/" + name.toLowerCase() + "mini.png"));
	        } 
	        catch (Exception e) 
	        {
	        	e.printStackTrace();
	        }
	}
	
	public String getFormattedHTMLStats()
	{
		
		String s = "<html>Name: " + name + "  <p><p>HP: " + stats[0] + "  <p>Attack: " + stats[1] + "     Defense: " + stats[2] + "  <p>Sp. Attack: " + stats[3] + "     Sp. Defense: " + stats[4] + "  <p>Speed: " + stats[5] + "<p><p>Type 1: ";
		
		s = s + this.type1.toGUIString();
		
		if(this.type2 != null)
		{
			s = s + "     Type 2: " + this.type2.toGUIString();
		}
		
		s = s + "  <p><p>Moveset: " + moves[0].name + ", " + moves[1].name + ", " + moves[2].name + ", " + moves[3].name +  (ability!=null?("<p><p>Ability: " + ability.getClass().getSimpleName()):"") + "</html>";
		
		return s;
	}
	
	public String getFormattedStats()	//returns a formatted String block of a Pokemon's complete information.
	{
		String s;
		
		s = "Name: " + name + "  \n\nHP: " + stats[0] + "  \nAttack: " + stats[1] + "  Defense: " + stats[2] + "  \nSp. Attack: " + stats[3] + "  Sp. Defense: " + stats[4] + "  \nSpeed: " + stats[5] + "\n\nType 1: ";
		
		s = s + this.type1.toGUIString();
		
		if(this.type2 != null)
		{
			s = s + "     Type 2: " + this.type2.toGUIString();
		}
		
		s = s + "  \n\nMoveset: " + moves[0].name + ", " + moves[1].name + ", " + moves[2].name + ", " + moves[3].name;
		
		return s;
	}
	
	public String getType1()	//returns the String type 1 of the Pokemon.
	{
		return type1.toGUIString();
	}
	
	public String getType2()	//returns the String type 2 of the Pokemon.
	{
		if(type2 != null)
			return type2.toGUIString();
		return "";
	}
	
	public void setStatusText()
	{
		if(team1)		
		{
			switch(status)
			{
			case NO_STATUS: MainGUI.lblStatusP1.setText(""); MainGUI.lblStatusP1.setForeground(null); break;
			case BURN: MainGUI.lblStatusP1.setText("<html><font color=#F00803>BRN</font></html>"); MainGUI.lblStatusP1.setForeground(new Color(240, 128, 48)); 		 	break;
			case POISON: MainGUI.lblStatusP1.setText("<html><font color=#A004A0>PSN</font></html>"); MainGUI.lblStatusP1.setForeground(new Color(160, 64, 160)); 	 	break;
			case SLEEP: MainGUI.lblStatusP1.setText("<html><font color=#8C888C>SLP</font></html>"); MainGUI.lblStatusP1.setForeground(new Color(140, 136, 140)); 	 	break;
			case PARALYSIS: MainGUI.lblStatusP1.setText("<html><font color=#F8D003>PAR</font></html>"); MainGUI.lblStatusP1.setForeground(new Color(248, 208, 48));  	break;
			case FREEZE: MainGUI.lblStatusP1.setText("<html><font color=#98D8D8>FRZ</font></html>"); MainGUI.lblStatusP1.setForeground(new Color(152, 216, 216)); 	 	break;
			case BAD_POISON: MainGUI.lblStatusP1.setText("<html><font color=#A004A0>PSN</font></html>"); MainGUI.lblStatusP1.setForeground(new Color(160, 64, 160));  	break;
			}
		}
		else
		{
			switch(status)
			{
			case NO_STATUS: MainGUI.lblStatusP2.setText(""); MainGUI.lblStatusP1.setForeground(null); break;
			case BURN: MainGUI.lblStatusP2.setText("<html><font color=#F00803>BRN</font></html>"); MainGUI.lblStatusP2.setForeground(new Color(240, 128, 48)); 		 	break;
			case POISON: MainGUI.lblStatusP2.setText("<html><font color=#A004A0>PSN</font></html>"); MainGUI.lblStatusP2.setForeground(new Color(160, 64, 160)); 		break;
			case SLEEP: MainGUI.lblStatusP2.setText("<html><font color=#8C888C>SLP</font></html>"); MainGUI.lblStatusP2.setForeground(new Color(140, 136, 140)); 		break;
			case PARALYSIS: MainGUI.lblStatusP2.setText("<html><font color=#F8D003>PAR</font></html>"); MainGUI.lblStatusP2.setForeground(new Color(248, 208, 48));  	break;
			case FREEZE: MainGUI.lblStatusP2.setText("<html><font color=#98D8D8>FRZ</font></html>"); MainGUI.lblStatusP2.setForeground(new Color(152, 216, 216)); 	 	break;
			case BAD_POISON: MainGUI.lblStatusP2.setText("<html><font color=#A004A0>PSN</font></html>"); MainGUI.lblStatusP2.setForeground(new Color(160, 64, 160)); 	break;
			}
		}
	}
	
	public String toString()	//returns the Pokemon's name, when printed.
	{
		return name;
	}
	
	public int applyModifier(Modifier m)	//applies a modifier's aspects to the Pokemon object, and makes necessary changes to the MainGUI and the Battle.
	{
	    int beforehealth = currenthealth;
		if(m == null)
	    	return 0;
		Battle.tempAttackMod *= m.attack;
	    Battle.tempDefenseMod *= m.defense;
	    
	    statModifiers[0] += m.statModifiers[0];
	    statModifiers[1] += m.statModifiers[1];
        statModifiers[2] += m.statModifiers[2];
        statModifiers[3] += m.statModifiers[3];
        statModifiers[4] += m.statModifiers[4];
        statModifiers[5] += m.statModifiers[5];
        statModifiers[6] += m.statModifiers[6];
        statModifiers[7] += m.statModifiers[7];
        
        int oldhealth = currenthealth;
        currenthealth += m.hp * stats[0];
        currenthealth -= m.recoil * Battle.finaldamage;
        
        if(currenthealth < 0)
        		currenthealth = 0;
        
        if(currenthealth > stats[0])
            currenthealth = stats[0];
        
        if(m.addstatus != NO_STATUS && this.status == NO_STATUS)
        {
        	MainGUI.txtInfo.append("\n" + this.name + " was " + Modifier.getStatusText(m.addstatus) + "!\n");
            this.status = m.addstatus;
        	this.setStatusText();
            if(this.status == SLEEP){
                statusTimer = (int)(Battle.random.nextDouble() * 4) + 1; //Using the 4th gen sleep (5th gen is weird http://bulbapedia.bulbagarden.net/wiki/Sleep_(status_ailment)#Generation_IV)             
            }
        }
        
        if(team1 && (m.hp != 0 || (m.recoil != 0 && Battle.finaldamage != 0)))
        {
        	MainGUI.healthBarP1.setValue((int)(currenthealth * 100 / stats[0]));
        	MainGUI.lblHealthP1.setText(currenthealth + "/" + stats[0]);
        	if(m.hp > 0)
        	{
        		int x = currenthealth - oldhealth;
        		MainGUI.txtInfo.append("\n" + name + " gained " + x + " HP!\n");
        	}
        	if(m.hp < 0)
        	{
        		int x = oldhealth - currenthealth;
        		MainGUI.txtInfo.append("\n" + name + " lost " + x + " HP!\n");
        	}
            if(m.recoil > 0)
            {
                MainGUI.txtInfo.append("\n" + name + " was hit with recoil!\n");
            }
            if(m.recoil < 0)
            {
                MainGUI.txtInfo.append("\n" + Battle.defender.name + " had its energy drained!\n");
            }
        		
        }
        
        if(!team1 && (m.hp != 0 || (m.recoil != 0 && Battle.finaldamage != 0)))
        {
        	MainGUI.healthBarP2.setValue((int)(currenthealth * 100 / stats[0]));
        	MainGUI.lblHealthP2.setText(currenthealth + "/" + stats[0]);
        	if(m.hp > 0)
        	{
        		int x = currenthealth - oldhealth;
        		MainGUI.txtInfo.append("\n" + name + " gained " + x + " HP!\n");
        	}
        	if(m.hp < 0)
        	{
        		int x = oldhealth - currenthealth;
        		MainGUI.txtInfo.append("\n" + name + " lost " + x + " HP!\n");
        	}
        	if(m.recoil > 0)
            {
                MainGUI.txtInfo.append("\n" + name + " was hit with recoil!\n");
            }
            if(m.recoil < 0)
            {
                MainGUI.txtInfo.append("\n" + Battle.defender.name + " had its energy drained!\n");
            }
        }
        
        if(currenthealth == 0 && beforehealth != 0)
        	MainGUI.txtInfo.append("\n" + name + " fainted!\n");
        
        for(int i = 0; i < 6; i++)
        {
        	if(statModifiers[i] < -6)
        	{
        		statModifiers[i] = -6;
        		MainGUI.txtInfo.append("\n" + name + "'s " + Modifier.getStat(i) + " cannot go any lower!\n");
        	}
        	else if(statModifiers[i] > 6)
        	{
        		statModifiers[i] = 6;
        		MainGUI.txtInfo.append("\n" + name + "'s " + Modifier.getStat(i) + " cannot go any higher!\n");
        	}
        	else if(m.statModifiers[i] != 0)
        		Modifier.print(i, m.statModifiers[i], this);
        }
        if(statModifiers[7] > 5)
          statModifiers[7] = 5;
        if(m.hp == 0)
        	return 0;
        else
        	return (int)Math.abs(m.hp) * 1000;
	}
	
	public int calculateModTime(Modifier m)		//calculates the maximum amount of time it will take to run applyModifier for the selected modifier.
	{
		if(m.hp == 0)
        	return 0;
        else
        	return (int)Math.abs(m.hp) * 1000;
	}
	
	public void deleteImages()
	{
		sprite1 = null;
		sprite2 = null;
		minisprite = null;
	}

}
