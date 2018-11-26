package bomberman.entity.tile.destroyable.box;

import bomberman.entity.Entity;
import bomberman.entity.animation.DirectionalExplosion;
import bomberman.entity.animation.mob.enemy.types.Kondoria;
import bomberman.entity.tile.destroyable.DestroyableTile;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.DataLoaderLevel.Coordinates;

public class BoxTile extends DestroyableTile {
	
	public BoxTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(Screen screen) {
		int x = Coordinates.tileToPixel(_x);
		int y = Coordinates.tileToPixel(_y);
		
		if(isDestroyed) {
			_sprite = movingSprite(Sprite.box_destroy, Sprite.box_destroy1, Sprite.box_destroy2, Sprite.box_destroy3, Sprite.box_destroy4);
			
			screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
		}
		else
			screen.renderEntity( x, y, this);
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof DirectionalExplosion)
			destroy();
		
		if(e instanceof Kondoria)
			return true;
			
		return false;
	}
	
	
}
