package bomberman.graphics;

public class Sprite {
	
	public final int SIZE;
	private int _x, _y;
	public int[] _pixels;
	protected int _realWidth;
	protected int _realHeight;
	private SpriteSheet _sheet;
	
	public static Sprite voidSprite = new Sprite(16, 0xffffff);
	
	public static Sprite grass = new Sprite(16, 6, 8, SpriteSheet.tile, 16, 16);
	public static Sprite box = new Sprite(16, 0, 3, SpriteSheet.tile, 16, 16);
	public static Sprite wall = new Sprite(16, 0, 6, SpriteSheet.tile, 16, 16);
	public static Sprite portal = new Sprite(16, 5, 6, SpriteSheet.tile, 16, 16);
	
        
        
	public static Sprite player_up = new Sprite(16, 0, 0, SpriteSheet.playerSheet1, 12, 16);
        public static Sprite player_up_1 = new Sprite(16, 0, 1, SpriteSheet.playerSheet1, 12, 16);
	public static Sprite player_up_2 = new Sprite(16, 0, 2, SpriteSheet.playerSheet1, 12, 15);
        
	public static Sprite player_down = new Sprite(16, 2, 0, SpriteSheet.playerSheet1, 12, 15);
        public static Sprite player_down_1 = new Sprite(16, 2, 1, SpriteSheet.playerSheet1, 12, 15);
	public static Sprite player_down_2 = new Sprite(16, 2, 2, SpriteSheet.playerSheet1, 12, 16);
        
	public static Sprite player_left = new Sprite(16, 3, 0, SpriteSheet.playerSheet1, 10, 15);
        public static Sprite player_left_1 = new Sprite(16, 3, 1, SpriteSheet.playerSheet1, 11, 16);
	public static Sprite player_left_2 = new Sprite(16, 3, 2, SpriteSheet.playerSheet1, 12 ,16);
        
	public static Sprite player_right = new Sprite(16, 1, 0, SpriteSheet.playerSheet1, 10, 16);
        public static Sprite player_right_1 = new Sprite(16, 1, 1, SpriteSheet.playerSheet1, 11, 16);
	public static Sprite player_right_2 = new Sprite(16, 1, 2, SpriteSheet.playerSheet1, 12, 16);
	
	public static Sprite player_die1 = new Sprite(16, 4, 2, SpriteSheet.playerSheet1, 14, 16);
	public static Sprite player_die2 = new Sprite(16, 5, 2, SpriteSheet.playerSheet1, 13, 15);
	public static Sprite player_die3 = new Sprite(16, 6, 2, SpriteSheet.playerSheet1, 16, 16);
	
        
	public static Sprite balloom_left1 = new Sprite(16, 6, 0, SpriteSheet.tile, 16, 16);
	public static Sprite balloom_left2 = new Sprite(16, 7, 0, SpriteSheet.tile, 16, 16);
	public static Sprite balloom_left3 = new Sprite(16, 8, 0, SpriteSheet.tile, 16, 16);
	public static Sprite balloom_die = new Sprite(16, 9, 0, SpriteSheet.tile, 16, 16);
	

	public static Sprite oneal_left1 = new Sprite(16, 6, 1, SpriteSheet.tile, 16, 16);
	public static Sprite oneal_left2 = new Sprite(16, 7, 1, SpriteSheet.tile, 16, 16);
	public static Sprite oneal_left3 = new Sprite(16, 8, 1, SpriteSheet.tile, 16, 16);
	public static Sprite oneal_die = new Sprite(16, 9, 1, SpriteSheet.tile, 16, 16);
	
        
	public static Sprite doll_left1 = new Sprite(16, 10, 3, SpriteSheet.tile, 16, 16);
	public static Sprite doll_left2 = new Sprite(16, 11, 3, SpriteSheet.tile, 16, 16);
	public static Sprite doll_left3 = new Sprite(16, 12, 3, SpriteSheet.tile, 16, 16);
	public static Sprite doll_die = new Sprite(16, 13, 3, SpriteSheet.tile, 16, 16);
	
	
	public static Sprite minvo_left1 = new Sprite(16, 6, 5, SpriteSheet.tile, 16, 16);
	public static Sprite minvo_left2 = new Sprite(16, 7, 5, SpriteSheet.tile, 16, 16);
	public static Sprite minvo_left3 = new Sprite(16, 8, 5, SpriteSheet.tile, 16, 16);
	public static Sprite minvo_die = new Sprite(16, 9, 5, SpriteSheet.tile, 16, 16);
	
	
	public static Sprite kondoria_left1 = new Sprite(16, 10, 0, SpriteSheet.tile, 16, 16);
	public static Sprite kondoria_left2 = new Sprite(16, 11, 0, SpriteSheet.tile, 16, 16);
	public static Sprite kondoria_left3 = new Sprite(16, 12, 0, SpriteSheet.tile, 16, 16);
	public static Sprite kondoria_die = new Sprite(16, 13, 0, SpriteSheet.tile, 16, 16);
	
        
	public static Sprite enemy_die1 = new Sprite(16, 9, 6, SpriteSheet.tile, 16, 16);
	public static Sprite enemy_die2 = new Sprite(16, 10, 6, SpriteSheet.tile, 16, 16);
	public static Sprite enemy_die3 = new Sprite(16, 11, 6, SpriteSheet.tile, 16, 16);
        public static Sprite enemy_die4 = new Sprite(16, 12, 6, SpriteSheet.tile, 16, 16);
        public static Sprite enemy_die5 = new Sprite(16, 13, 6, SpriteSheet.tile, 16, 16);
	
	
	public static Sprite bomb = new Sprite(16, 5, 7, SpriteSheet.tile, 16, 16);
	public static Sprite bomb_1 = new Sprite(16, 5, 8, SpriteSheet.tile, 16, 16);
	public static Sprite bomb_2 = new Sprite(16, 5, 9, SpriteSheet.tile, 16, 16);
	
	
	public static Sprite flame_middle = new Sprite(16, 0, 11, SpriteSheet.tile, 16, 16);
	public static Sprite flame_middle1 = new Sprite(16, 0, 10, SpriteSheet.tile, 16, 16);
	public static Sprite flame_middle2 = new Sprite(16, 0, 9, SpriteSheet.tile, 16, 16);
        public static Sprite flame_middle3 = new Sprite(16, 0, 8, SpriteSheet.tile, 16, 16);
        public static Sprite flame_middle4 = new Sprite(16, 0, 7, SpriteSheet.tile, 16, 16);
	
	public static Sprite flame_vertical = new Sprite(16, 3, 11, SpriteSheet.tile, 16, 16);
	public static Sprite flame_vertical1 = new Sprite(16, 3, 10, SpriteSheet.tile, 16, 16);
	public static Sprite flame_vertical2 = new Sprite(16, 3, 9, SpriteSheet.tile, 16, 16);
        public static Sprite flame_vertical3 = new Sprite(16, 3, 8, SpriteSheet.tile, 16, 16);
        public static Sprite flame_vertical4 = new Sprite(16, 3, 7, SpriteSheet.tile, 16, 16);
	
	public static Sprite flame_horizontal = new Sprite(16, 1, 11, SpriteSheet.tile, 16, 16);
	public static Sprite flame_horizontal1 = new Sprite(16, 1, 10, SpriteSheet.tile, 16, 16);
	public static Sprite flame_horizontal2 = new Sprite(16, 1, 9, SpriteSheet.tile, 16, 16);
        public static Sprite flame_horizontal3 = new Sprite(16, 1, 8, SpriteSheet.tile, 16, 16);
        public static Sprite flame_horizontal4 = new Sprite(16, 1, 7, SpriteSheet.tile, 16, 16);
	
	public static Sprite flame_horizontal_left_last = new Sprite(16, 8, 8, SpriteSheet.tile, 16, 16);
	public static Sprite flame_horizontal_left_last1 = new Sprite(16, 8, 9, SpriteSheet.tile, 16, 16);
	public static Sprite flame_horizontal_left_last2 = new Sprite(16, 8, 10, SpriteSheet.tile, 16, 16);
        public static Sprite flame_horizontal_left_last3 = new Sprite(16, 8, 11, SpriteSheet.tile, 16, 16);
        public static Sprite flame_horizontal_left_last4 = new Sprite(16, 8, 12, SpriteSheet.tile, 16, 16);
	
	public static Sprite flame_horizontal_right_last = new Sprite(16, 2, 11, SpriteSheet.tile, 16, 16);
	public static Sprite flame_horizontal_right_last1 = new Sprite(16, 2, 10, SpriteSheet.tile, 16, 16);
	public static Sprite flame_horizontal_right_last2 = new Sprite(16, 2, 9, SpriteSheet.tile, 16, 16);
        public static Sprite flame_horizontal_right_last3 = new Sprite(16, 2, 8, SpriteSheet.tile, 16, 16);
        public static Sprite flame_horizontal_right_last4 = new Sprite(16, 2, 7, SpriteSheet.tile, 16, 16);
	
	public static Sprite flame_vertical_top_last = new Sprite(16, 4, 11, SpriteSheet.tile, 16, 16);
	public static Sprite flame_vertical_top_last1 = new Sprite(16, 4, 10, SpriteSheet.tile, 16, 16);
	public static Sprite flame_vertical_top_last2 = new Sprite(16, 4, 9, SpriteSheet.tile, 16, 16);
        public static Sprite flame_vertical_top_last3 = new Sprite(16, 4, 8, SpriteSheet.tile, 16, 16);
        public static Sprite flame_vertical_top_last4 = new Sprite(16, 4, 7, SpriteSheet.tile, 16, 16);
	
	public static Sprite flame_vertical_down_last = new Sprite(16, 10, 8, SpriteSheet.tile, 16, 16);
	public static Sprite flame_vertical_down_last1 = new Sprite(16, 10, 9, SpriteSheet.tile, 16, 16);
	public static Sprite flame_vertical_down_last2 = new Sprite(16, 10, 10, SpriteSheet.tile, 16, 16);
        public static Sprite flame_vertical_down_last3 = new Sprite(16, 10, 11, SpriteSheet.tile, 16, 16);
        public static Sprite flame_vertical_down_last4 = new Sprite(16, 10, 12, SpriteSheet.tile, 16, 16);
	
	
	public static Sprite box_destroy = new Sprite(16, 1, 3, SpriteSheet.tile, 16, 16);
	public static Sprite box_destroy1 = new Sprite(16, 2, 3, SpriteSheet.tile, 16, 16);
	public static Sprite box_destroy2 = new Sprite(16, 3, 3, SpriteSheet.tile, 16, 16);
        public static Sprite box_destroy3 = new Sprite(16, 4, 3, SpriteSheet.tile, 16, 16);
        public static Sprite box_destroy4 = new Sprite(16, 5, 3, SpriteSheet.tile, 16, 16);
	
	public static Sprite item_bomb = new Sprite(16, 6, 7, SpriteSheet.tile, 16, 16);
	public static Sprite item_flame = new Sprite(16, 7, 7, SpriteSheet.tile, 16, 16);
	public static Sprite item_speed = new Sprite(16, 8, 7, SpriteSheet.tile, 16, 16);
	public static Sprite item_wallpass = new Sprite(16, 9, 7, SpriteSheet.tile, 16, 16);
	public static Sprite item_timebomb = new Sprite(16, 12, 7, SpriteSheet.tile, 16, 16);
	public static Sprite item_bombpass = new Sprite(16, 10, 7, SpriteSheet.tile, 16, 16);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		_x = x * SIZE;
		_y = y * SIZE;
		_sheet = sheet;
		_realWidth = rw;
		_realHeight = rh;
                
		load();
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				_pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
			}
		}
	}
	
	public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animation, int time) {
		int calc = animation % time;
		int diff = time / 3;
		if(calc < diff) {
			return normal;
		}	
		if(calc < diff * 2) {
			return x1;
		}	
               return x2;
	}
        
        public static Sprite SpriteLoader(Sprite x1, Sprite x2, Sprite x3, Sprite x4, Sprite x5, int animation, int time) {
		int calc = animation % time;
		int diff = time / 5;
		if(calc < diff) {
			return x1;
		}	
		if(calc < diff * 2) {
			return x2;
		}
                if(calc < diff * 3) {
			return x3;
		}
                if(calc < diff * 4) {
			return x4;
		}
               return x5;
	}
	
	public static Sprite movingSprite(Sprite x1, Sprite x2, int animation, int time) {
		int diff = time / 2;
		return (animation % time > diff) ? x1 : x2; 
	}
	
	public int getSize() {
		return SIZE;
	}
	
	public int[] getPixels() {
		return _pixels;
	}
	
	public int getPixel(int i) {
		return _pixels[i];
	}
	
	public int getRealWidth() {
		return _realWidth;
	}
	
	public int getRealHeight() {
		return _realHeight;
	}

}
