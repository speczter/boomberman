package gameSound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;
//import javax.sound.sampled.LineUnavailableException;

public class GameSound {
        public static GameSound instance;

	public static final String MENU = "menu.wav";
	public static final String PLAYGAME = "playgame.mid";
	public static final String BOMB = "newbomb.wav";
	public static final String BOMBER_DIE = "bomber_die.wav";
	public static final String ENEMY_DIE = "Enemy_die.wav";
	public static final String BOMB_BANG = "bomb.wav";
	public static final String ITEM = "item.wav";
	public static final String WIN = "win.wav";
	public static final String LOSE = "lose.mid";
	private HashMap<String, AudioClip> audioMap;

	public GameSound(){
		audioMap = new HashMap<>();
		loadAllAudio();
	}

	public static GameSound getIstance() {
		if (instance == null) {
			instance = new GameSound();
		}

		return instance;
	}
        // Tai am thanh
        @SuppressWarnings("empty-statement")
	public void loadAllAudio() {
		putAudio(MENU);;
		putAudio(PLAYGAME);
		putAudio(BOMB);
		putAudio(ENEMY_DIE);
		putAudio(BOMBER_DIE);
		putAudio(BOMB_BANG);
		putAudio(ITEM);
		putAudio(WIN);
		putAudio(LOSE);
	}

        @SuppressWarnings("empty-statement")
	public void stop() {
		getAudio(MENU).stop();;
		getAudio(PLAYGAME).stop();
		getAudio(BOMB).stop();
		getAudio(BOMB_BANG).stop();
		getAudio(WIN).stop();
		getAudio(LOSE).stop();
	}

	public void putAudio(String name) {
            //File sound = new File(name);
            //Clip clip = AudioSystem.getClip();
            //clip.open(AudioSystem.getAudioInputStream(sound));
            AudioClip auClip = Applet.newAudioClip(GameSound.class.getResource(name));
            audioMap.put(name, auClip);
	}

	public AudioClip getAudio(String name) {
		return audioMap.get(name);
	}
}
