package bomberman.entity.tile.destroyable;

import bomberman.entity.Entity;
import bomberman.entity.animation.DirectionalExplosion;
import bomberman.entity.tile.Tile;
import bomberman.graphics.Sprite;

public class DestroyableTile extends Tile {

	private final int MAX_ANIMATE = 7500;
	private int anim = 0;
	protected boolean isDestroyed = false;
	protected int _timeToDisapear = 40;
	protected Sprite _belowSprite = Sprite.grass;
	
	public DestroyableTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		if(isDestroyed) {
			if(anim < MAX_ANIMATE) anim++; else anim = 0;
			if(_timeToDisapear > 0) 
				_timeToDisapear--;
			else
				remove();
		}
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}
	
	public void destroy() {
		isDestroyed = true;
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof DirectionalExplosion)
			destroy();
			
		return false;
	}
	
	public void addBelowSprite(Sprite sprite) {
		_belowSprite = sprite;
	}
	
	protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, Sprite x3, Sprite x4) {
		int calc = anim % 50;
		
		if(calc < 10) {
			return normal;
		}
			
		if(calc < 20) {
			return x1;
		}
		
                if(calc < 30) {
			return x2;
		}
                
                if(calc < 40) {
			return x3;
		}
                
		return x4;
	}
	
}
