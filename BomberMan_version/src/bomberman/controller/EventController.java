package bomberman.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Them class nhap input tu ban phim dieu khien boomber
public class EventController implements KeyListener {
	
	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right, space;
	// Ham di chuyen
	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

}
