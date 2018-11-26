package bomberman.entity.tile.item.flame;

import bomberman.Game;
import bomberman.entity.Entity;
import bomberman.entity.animation.mob.player.Player;
import bomberman.entity.tile.item.Item;
import bomberman.graphics.Sprite;
import gameSound.GameSound;

public class ItemFlame extends Item {

	public ItemFlame(int x, int y, int level, Sprite sprite) {
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
		Game.addBombRadius(1);
	}
	


}
