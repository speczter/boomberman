package bomberman.entity.animation.bomb;

import bomberman.entity.animation.DirectionalExplosion;
import bomberman.entity.animation.Flame;
import bomberman.Board;
import bomberman.Game;
import bomberman.entity.animation.Animation;
import bomberman.entity.Entity;
import bomberman.entity.animation.mob.Mob;
import bomberman.entity.animation.mob.player.Player;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.DataLoaderLevel.Coordinates;

public class Bomb extends Animation {

	protected double _timeToExplode = 120;
	public int _timeAfter = 20;
	
	protected Board _board;
	protected boolean _isPass = true;
	protected DirectionalExplosion[] _explosions = null;
	protected boolean _exploded = false;
	
	public Bomb(int x, int y,Board board) {
		_x = x;
		_y = y;
		_board = board;
		_sprite = Sprite.bomb;
	}
	
	@Override
	public void update() {
		if(_timeToExplode > 0) 
			_timeToExplode--;
		else {
			if(!_exploded) 
				explosion();
			else
				updateExplosions();
			
			if(_timeAfter > 0) 
				_timeAfter--;
			else
				remove();
		}
			
		Animation();
	}
	
	@Override
	public void render(Screen screen) {
		if(_exploded) {
			_sprite =  Sprite.flame_middle2;
			renderExplosions(screen);
		} else{
                    _sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, Animation, 60);
                    
                }
                        
		
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntity(xt, yt , this);
	}
	
	public void renderExplosions(Screen screen) {
            for (DirectionalExplosion _explosion : _explosions) {
                _explosion.render(screen);
            }
	}
	
	public void updateExplosions() {
            for (DirectionalExplosion _explosion : _explosions) {
                _explosion.update();
            }
	}
	
	public void explode() {
		_timeToExplode = 0;
	}
	
	protected void explosion() {
		_isPass = true;
		_exploded = true;
		
		Mob a = _board.getMobAt(_x, _y);
		if(a != null)  {
			a.kill();
		}
		
		_explosions = new DirectionalExplosion[4];
		
		for (int i = 0; i < _explosions.length; i++) {
			_explosions[i] = new DirectionalExplosion((int)_x, (int)_y, i, Game.getBombRadius(), _board);
		}
	}
	
	public Flame explosionAt(int x, int y) {
		if(!_exploded) return null;
		
            for (DirectionalExplosion _explosion : _explosions) {
                if (_explosion == null) {
                    return null;
                }
                Flame e = _explosion.explosionAt(x, y);
                if(e != null) return e;
            }
		
		return null;
	}
	
	public boolean isExploded() {
		return _exploded;
	}
	

	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Player) {
			double diffX = e.getX() - Coordinates.tileToPixel(getX());
			double diffY = e.getY() - Coordinates.tileToPixel(getY());
			
			if(!(diffX >= -11 && diffX < 16 && diffY >= 1 && diffY <= 28)) {
				_isPass = false;
			}
			
			return _isPass;
		}
		
		if(e instanceof DirectionalExplosion) {
			explode();
			return true;
		}
		
		return false;
	}
}
