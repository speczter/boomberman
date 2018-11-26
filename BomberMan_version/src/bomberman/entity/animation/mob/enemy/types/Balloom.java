package bomberman.entity.animation.mob.enemy.types;


import bomberman.Board;
import bomberman.Game;
import bomberman.entity.animation.mob.enemy.Enemy;
import bomberman.entity.animation.mob.enemy.ai.AIignor;
import bomberman.graphics.Sprite;

public class Balloom extends Enemy {
	
	public Balloom(int x, int y, Board board) {
            super(x, y, board, Sprite.balloom_die, Game.getPlayerSpeed() / 2, 100);	
            _sprite = Sprite.balloom_left1;	
            _ai = new AIignor();
            Dir = _ai.calculateDirection();
	}
	
	@Override
	protected void chooseSprite() {
		if(isMoving)
                    _sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Animation, 60);
	}
}
