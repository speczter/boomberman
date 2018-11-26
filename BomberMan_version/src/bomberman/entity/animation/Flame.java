package bomberman.entity.animation;

import bomberman.Board;
import bomberman.entity.Entity;
import bomberman.entity.animation.mob.Mob;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;

// Xu li vu no
public class Flame extends Entity {
	protected boolean _last = false;
	protected Board _board;
	protected Sprite _sprite1, _sprite2;
        protected int DIR;
	
	public Flame(int x, int y, int Dir, boolean last, Board board) {
		_x = x;
		_y = y;
                DIR = Dir;
		_last = last;
		_board = board;
	}
	
	@Override
	public void render(Screen screen) {
                if (DIR == 0){
                    if(_last == false) {
                        _sprite = Sprite.flame_vertical2;
                    } else {
                        _sprite = Sprite.flame_vertical_top_last2;
                    }
                }
                if (DIR == 1)
                    if(_last == false) {
                        _sprite = Sprite.flame_horizontal2;
                    } else {
                        _sprite = Sprite.flame_horizontal_right_last2;
                    }
                if (DIR == 2)
                    if(_last == false) {
                        _sprite = Sprite.flame_vertical2;
                    } else {
                        _sprite = Sprite.flame_vertical_down_last2;
                    }
                if (DIR == 3){
                    if(_last == false) {
                        _sprite = Sprite.flame_horizontal2;
                    } else {
                        _sprite = Sprite.flame_horizontal_left_last2;
                    }
		}
                
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntity(xt, yt , this);
	}
	
	@Override
	public void update() {
        }
        // Xu li va cham
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Mob) {
			((Mob)e).kill(); // Khi va cham player se tu chet
		}
		
		return true;
	}
	

}