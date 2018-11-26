package bomberman.entity.tile.map;


import bomberman.entity.Entity;
import bomberman.entity.tile.Tile;
import bomberman.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		return true;
	}
}
