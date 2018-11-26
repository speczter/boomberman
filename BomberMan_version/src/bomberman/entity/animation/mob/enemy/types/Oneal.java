package bomberman.entity.animation.mob.enemy.types;


import bomberman.Board;
import bomberman.Game;
import bomberman.entity.animation.mob.enemy.Enemy;
import bomberman.entity.animation.mob.enemy.ai.AImedium;
import bomberman.graphics.Sprite;

public class Oneal extends Enemy {
	
	public Oneal(int x, int y, Board board) {
            super(x, y, board, Sprite.oneal_die, Game.getPlayerSpeed(), 200);
            _sprite = Sprite.oneal_left1;
            _ai = new AImedium(_board.getPlayer(), this);
            Dir  = _ai.calculateDirection();
	}
	
	@Override
	protected void chooseSprite() {
            if(isMoving)
                _sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, Animation, 60);
            else
                _sprite = Sprite.oneal_left1;	
	}
}
