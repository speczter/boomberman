package bomberman.entity.animation.mob.enemy.types;


import bomberman.Board;
import bomberman.Game;
import bomberman.entity.animation.mob.enemy.Enemy;
import bomberman.entity.animation.mob.enemy.ai.AImedium;
import bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
    
	public Kondoria(int x, int y, Board board) {
            super(x, y, board, Sprite.kondoria_die, Game.getPlayerSpeed() / 4, 1000);	
            _sprite = Sprite.kondoria_left1;
            _ai = new AImedium(_board.getPlayer(), this);
            Dir  = _ai.calculateDirection();
	}
        
	@Override
	protected void chooseSprite() {
            if(isMoving)
		_sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, Animation, 60);
            else
		_sprite = Sprite.kondoria_left1;
	}
}
