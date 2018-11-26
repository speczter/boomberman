package bomberman;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import bomberman.exceptions.BombermanException;
import bomberman.graphics.Screen;
import bomberman.GameScreen.Frame;
import bomberman.controller.EventController;
import gameSound.GameSound;

public class Game extends Canvas {
	
	public static final int TILES_SIZE = 16,WIDTH = TILES_SIZE * (int)(31 / 2),HEIGHT = 13 * TILES_SIZE;
        protected static int SCREENDELAY = 3;
	public static int SCALE = 3;
	
	protected static int bombRate = 1;
	protected static int bombRadius = 1;
	protected static double playerSpeed = 0.75;
        protected int _screenDelay = SCREENDELAY;
	private EventController _input;
	private boolean isRunning = false;
	private boolean isPaused = true;
	
	private Board _board;
	private Screen screen;
	private Frame _frame;
        
        private GameSound gameSound;
        
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game() throws BombermanException {
		
		screen = new Screen(WIDTH, HEIGHT);
		_input = new EventController();
                
		_board = new Board(this, _input, screen);
		addKeyListener(_input);
	}
	
	
	private void renderGame() { 
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
                
		screen.clear();
		
		_board.render(screen);
		
                System.arraycopy(screen._pixels, 0, pixels, 0, pixels.length);
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show(); 
	}
	
	private void renderScreen() { 
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		Graphics g = bs.getDrawGraphics();

		g.dispose();
		bs.show();
	}

	private void update() {
		_input.update();
		_board.update();
	}
	
	public void start() {
		isRunning = true;
		
		long  lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0; 
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
//                gameSound.getIstance().getAudio(gameSound.PLAYGAME).loop();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			
			if(isPaused) {
                            isPaused = false;
                            renderScreen();
			}else{
                            renderGame();
			}
				
			
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				updates = 0;
				frames = 0;
			}
		}
	}
	public static double getPlayerSpeed() {
		return playerSpeed;
	}
	
	public static int getBombRate() {
		return bombRate;
	}
	
	public static int getBombRadius() {
		return bombRadius;
	}
	
	public static void addPlayerSpeed(double i) {
		playerSpeed += i;
	}
	
	public static void addBombRadius(int i) {
		bombRadius += i;
	}
	
	public static void addBombRate(int i) {
		bombRate += i;
	}

	public EventController getInput() {
		return _input;
	}
	
	public Board getBoard() {
		return _board;
	}
	
	public void run() {
		isRunning = true;
		isPaused = false;
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public void pause() {
		isPaused = true;
	}
	
        public void resetScreenDelay() {
		_screenDelay = SCREENDELAY;
	}
}
