/**License:
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

import java.io.InputStream;
import java.util.ArrayList;

public class MP3Clip {
	String filePath;
	InputStream is;
	ArrayList<MP3Thread> clips;
	Boolean relitiveURL;
	
	public MP3Clip(Boolean relitive, String FilePath){
		relitiveURL = relitive;
		filePath = FilePath;
		System.out.println(filePath);
		clips = new ArrayList<>();
	}
	public int play() {
        MP3Thread thread = new MP3Thread(relitiveURL, filePath,false);
        clips.add(thread);
		return clips.indexOf(thread);
	}
	public void stop(int instanceNumber) {
		clips.get(instanceNumber).stop();
	}
	public int loop() {
		MP3Thread thread = new MP3Thread(relitiveURL, filePath,true);
        clips.add(thread);
		return clips.indexOf(thread);
	}
}
