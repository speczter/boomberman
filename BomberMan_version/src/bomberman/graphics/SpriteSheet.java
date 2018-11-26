package bomberman.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String _path;
	public int SIZE;
	public int[] _pixels;
	
	public static SpriteSheet tile = new SpriteSheet("/textures/sprite.png", 256);
        public static SpriteSheet playerSheet1 = new SpriteSheet("/textures/player1.png", 112);
        public static SpriteSheet playerSheet2 = new SpriteSheet("/textures/player2.png", 112);
	
	public SpriteSheet(String path, int size) {
		_path = path;
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			URL a = SpriteSheet.class.getResource(_path);
			BufferedImage image = ImageIO.read(a);
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, _pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
