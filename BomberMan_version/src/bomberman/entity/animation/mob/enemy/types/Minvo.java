package bomberman.entity.animation.mob.enemy.types;


import bomberman.Board;
import bomberman.Game;
import bomberman.entity.animation.mob.enemy.Enemy;
import bomberman.entity.animation.mob.enemy.ai.AImedium;
import bomberman.graphics.Sprite;

public class Minvo extends Enemy {
	
	
	public Minvo(int x, int y, Board board) {
            super(x, y, board, Sprite.minvo_die, Game.getPlayerSpeed() * 2, 800);
            _sprite = Sprite.minvo_left1;
            _ai = new AImedium(_board.getPlayer(), this);
            Dir  = _ai.calculateDirection();
        }
        
	@Override
	protected void chooseSprite() {
            if(isMoving)
		_sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, Animation, 60);
            else
		_sprite = Sprite.minvo_left1;
	}
}
