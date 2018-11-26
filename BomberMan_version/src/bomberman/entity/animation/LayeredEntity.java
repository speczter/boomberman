package bomberman.entity.animation;

import bomberman.entity.Entity;
import java.util.LinkedList;
import bomberman.entity.tile.destroyable.DestroyableTile;
import bomberman.graphics.Screen;

public class LayeredEntity extends Entity {
	
	protected LinkedList<Entity> Entity = new LinkedList<Entity>();
	
	public LayeredEntity(int x, int y, Entity ... entity) {
		_x = x;
		_y = y;
		
		for (int i = 0; i < entity.length; i++) {
			Entity.add(entity[i]); 
			
			if(i > 1) {
				if(entity[i] instanceof DestroyableTile)
					((DestroyableTile)entity[i]).addBelowSprite(entity[i-1].getSprite());
			}
		}
	}
	
	@Override
	public void update() {
		clearRemoved();
		getTopEntity().update();
	}
	
	@Override
	public void render(Screen screen) {
		getTopEntity().render(screen);
	}
	
	public Entity getTopEntity() {
		
		return Entity.getLast();
	}
	
	private void clearRemoved() {
		Entity top  = getTopEntity();
		
		if(top.isRemoved())  {
			Entity.removeLast();
		}
	}
	
	public void addBeforeTop(Entity e) {
		Entity.add(Entity.size() - 1, e);
	}
	
	@Override
	public boolean collide(Entity e) {
		return getTopEntity().collide(e);
	}

}
