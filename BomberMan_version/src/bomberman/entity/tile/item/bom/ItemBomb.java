package bomberman.entity.tile.item.bom;

import bomberman.Game;
import bomberman.entity.Entity;
import bomberman.entity.animation.mob.player.Player;
import bomberman.entity.tile.item.Item;
import bomberman.graphics.Sprite;

public class ItemBomb extends Item {

	public ItemBomb(int x, int y, int level, Sprite sprite) {
		super(x, y, level, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Player) {
			((Player) e).addPowerup(this);
			remove();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void setValues() {
		_active = true;
                gameSound.getIstance().getAudio(gameSound.ITEM).play();
		Game.addBombRate(1);
	}
	


}
