package bomberman.graphics;

import bomberman.Board;
import bomberman.Game;
import bomberman.entity.Entity;
import bomberman.entity.animation.mob.player.Player;

public class Screen {
	protected int _width, _height;
	public int[] _pixels;
	private int _transparentColor = 0xffff00ff;
	
	public static int xOffset = 0, yOffset = 0;
	
	public Screen(int width, int height) {
		_width = width;
		_height = height;
		
		_pixels = new int[width * height];
		
	}
	
	public void clear() {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = 0;
		}
	}
	
	public void renderEntity(int xp, int yp, Entity entity) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < entity.getSprite().getSize(); y++) {
			int ya = y + yp; 
			for (int x = 0; x < entity.getSprite().getSize(); x++) {
				int xa = x + xp;
				if(xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break;
				if(xa < 0) xa = 0;
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
				if(color != _transparentColor) _pixels[xa + ya * _width] = color;
			}
		}
	}
	
	public void renderEntityWithBelowSprite(int xp, int yp, Entity entity, Sprite below) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < entity.getSprite().getSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < entity.getSprite().getSize(); x++) {
				int xa = x + xp;
				if(xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break; 
				if(xa < 0) xa = 0;
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
				if(color != _transparentColor) 
					_pixels[xa + ya * _width] = color;
				else
					_pixels[xa + ya * _width] = below.getPixel(x + y * below.getSize());
			}
		}
	}
	
	public static void setOffset(int xO, int yO) {
		xOffset = xO;
		yOffset = yO;
	}
	
	public static int calculateXOffset(Board board, Player player) {
		if(player == null) return 0;
		int temp = xOffset;
		
		double playerX = player.getX() / 16;
		double complement = 0.5;
		int firstBreakpoint = board.getWidth() / 4;
		int lastBreakpoint = board.getWidth() - firstBreakpoint;
		
		if( playerX > firstBreakpoint + complement && playerX < lastBreakpoint - complement) {
			temp = (int)player.getX()  - (Game.WIDTH / 2);
		}
		
		return temp;
	}
        
        public void renderFlame(int xp, int yp, Sprite sprite, int flip) {
		xp = xp << 6;
		yp = yp << 6;
		
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = yp + y;
			int ys = y;
			if(flip == 0 || flip == 2) ys = (sprite.SIZE - 1) - ys;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = xp + x;
				int xs = x;
				if(flip == 1 || flip == 3) xs = (sprite.SIZE - 1) - xs;
				if(x < -sprite.SIZE || x >= _width || y < 0 || y >= _height) break; 
				if(x < 0) x = 0;
				int col = sprite._pixels[xs + ys * sprite.SIZE];
				if(col == 0xFF870087) continue;
				_pixels[xa + ya * _width] = col;
			}
		}
	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public int getRealWidth() {
		return _width * Game.SCALE;
	}
	
	public int getRealHeight() {
		return _height * Game.SCALE;
	}
}
