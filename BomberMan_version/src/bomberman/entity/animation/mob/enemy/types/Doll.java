package bomberman.entity.animation.mob.enemy.types;


import bomberman.Board;
import bomberman.Game;
import bomberman.entity.animation.mob.enemy.Enemy;
import bomberman.entity.animation.mob.enemy.ai.AIignor;
import bomberman.graphics.Sprite;

public class Doll extends Enemy {
	
	public Doll(int x, int y, Board board) {
            super(x, y, board, Sprite.doll_die, Game.getPlayerSpeed(), 400);	
            _sprite = Sprite.doll_left1;	
            _ai = new AIignor();
            Dir = _ai.calculateDirection();
	}
	
	@Override
	protected void chooseSprite() {
            if(isMoving)
		_sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, Animation, 60);
            else
		_sprite = Sprite.doll_left1;		
	}
}
