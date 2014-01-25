/**Version 0.4
 * 
 * License:
 * 
 * Obey the following when stealing/ borrowing/ selling/ commandeering/ legitimately using this code.
 * pretty please with a cherry on top.
 * 
 * In response to the new legal guidelines surrounding this glorious medium of code,
 * I hereby proclaim that I am not allowing anyone to touch my work with their filthy hands.
 * Please wash them before handling any of my intellectual property, or at the very least use hand sanitizer.
 * 
 * Compiled by the Legal Team at Tomunchi Co. without their knowledge or concent.
 * Yes, this is an unlicensed license.
 * Coppyright 2014
 */
package mp3Engine;

import java.util.ArrayList;

public class MP3Sound {
	
	ArrayList<MP3Clip> clips;
	ArrayList<Integer> playing;
	/**
	 * Initalizes a new MP3 playing sound system.
	 * You do not need to worry about putting it in a new thread, because MP3Sound does this on its own.
	 */
	public MP3Sound(){
		clips = new ArrayList<>();
		playing = new ArrayList<>();
	}
	/**
	 * Adds the clip to the sound system
	 * @param url is the URL of the clip to be added. On macs, it looks like "/Users/Name/Desktop/Sounds/FileName.mp3" or wherever you have it
	 * @return the int of the clip added. It is used in playClip()
	 */
	public int addClip(String url){
			try {
				MP3Clip clip = new MP3Clip(false, url);
				clips.add(clip);
				playing.add(-1);
				return clips.indexOf(clip);
			} catch (Exception e) {e.printStackTrace();}
		return -1;
	}
	/**
	 * Adds the clip to the sound system
	 * @param url is the relitive url to the jar or whatever. It looks like "/res/FileName.mp3"
	 * @return the int of the clip added. It is used in playClip()
	 */
	public int addClipRelative(String url){
		try{
			MP3Clip clip = new MP3Clip(true, url);
			clips.add(clip);
			playing.add(-1);
			return clips.indexOf(clip);
		}catch(Exception e){e.printStackTrace();}
		return -1;
	}
	/**
	 * Plays the clip. Clips can overlap, be played a couple times at once, etc.
	 * @param clipNumber the number  of the clip that you would like to play
	 * @return the int instance of the clip played. It is used in stopClip()
	 */
	public int playClip(int clipNumber){
		if(clipNumber<0||clipNumber>clips.size()){
			return -1;}
		playing.set(clipNumber,playing.get(clipNumber)+1);
		return clips.get(clipNumber).play();
	}
	/**
	 * Plays the clip over and over and over and over and over and over. forever. until you stop it.
	 * @param clipNumber the number of the clip that you would like to play
	 * @return the int instance of the clip played. It is used in stopClip()
	 */
	public int loopClip(int clipNumber){
		if(clipNumber<0||clipNumber>clips.size()){
			return -1;}
		playing.set(clipNumber,playing.get(clipNumber)+1);
		return clips.get(clipNumber).loop();
	}
	/**
	 * Stops the specified clip instance from playing
	 * @param clipNumber the clip number of the clip you want to stop
	 * @param instanceNumber the clip instance number of the clip you want to stop
	 */
	public void stopClip(int clipNumber, int instanceNumber){
		clips.get(clipNumber).stop(instanceNumber);
	}
}
