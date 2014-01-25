package rpgsvg;

import java.awt.Color;

public enum Theme {
	
	GROUND	(0, "MainGUIground.jpeg", "TeamGUIground.jpeg", "gymbattle.mp3", new Color(255, 250, 245)),
	ROCK	(1, "MainGUIrock.jpeg", "TeamGUIrock.jpeg", "johtochampion.mp3", new Color(227, 227, 227)),
	WATER	(2, "MainGUIwater.jpeg", "TeamGUIwater.jpeg", "laketrio.mp3", new Color(202, 221, 251)),
	GRASS	(3, "MainGUIgrass.jpeg", "TeamGUIgrass.jpeg", "nbattle.mp3", new Color(215, 252, 191)),
	FIRE	(4, "MainGUIfire.jpeg", "TeamGUIfire.jpeg", "ghetsis.mp3", new Color(255, 222, 90)),
	STEEL	(5, "MainGUIsteel.jpeg", "TeamGUIsteel.jpeg", "cynthia.mp3", new Color(231,231,231));
	
	private final int id;
	private final String bgMainGUI;
	final String bgTeamGUI;
	private final String music;
	private final Color color;
	
	Theme(int x, String a, String b, String c, Color i)
	{
		this.id = x;
		this.bgMainGUI = a;
		this.bgTeamGUI = b;
		this.music = c;
		this.color = i;
	}
	
	
	
	public int ID() {
		return this.id;
	}
	
	public String MainGUI() {
		return this.getBgMainGUI();
	}
	
	public String TeamGUI() {
		return this.bgTeamGUI;
	}
	
	public String Music() {
		return this.getMusic();
	}
	
	public Color Color() {
		return this.color;
	}



	public String getMusic() {
		return music;
	}



	public String getBgMainGUI() {
		return bgMainGUI;
	}
}
