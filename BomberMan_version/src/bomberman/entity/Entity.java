package bomberman.entity;

import bomberman.graphics.IRender;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.DataLoaderLevel.Coordinates;
import gameSound.GameSound;

public abstract class Entity implements IRender {
        protected GameSound gameSound;
	protected double _x, _y;
	protected boolean isRemove = false;
	protected Sprite _sprite;
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(Screen screen);
	
	public void remove() {
		isRemove = true;
	}
	
	public boolean isRemoved() {
		return isRemove;
	}
	
	public Sprite getSprite() {
		return _sprite;
	}
	
	public abstract boolean collide(Entity e);
	
	public double getX() {
		return _x;
	}
	
	public double getY() {
		return _y;
	}
	
	public int getXTile() {
		return Coordinates.pixelToTile(_x + _sprite.SIZE / 2); 
	}
	
	public int getYTile() {
		return Coordinates.pixelToTile(_y - _sprite.SIZE / 2);
	}
}
