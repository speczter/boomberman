package bomberman.entity.animation.mob.enemy;

import bomberman.Board;
import bomberman.Game;
import bomberman.entity.Entity;
import bomberman.entity.animation.DirectionalExplosion;
import bomberman.entity.animation.mob.Mob;
import bomberman.entity.animation.mob.player.Player;
import bomberman.entity.animation.mob.enemy.ai.AI;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.DataLoaderLevel.Coordinates;
import gameSound.GameSound;

public abstract class Enemy extends Mob {

	protected int _points;
	
	protected double _speed; 
	protected AI _ai;
	

	protected final double MAX_STEPS;
	protected final double rest;
	protected double _steps;
	
	protected int _finalAnimation = 50;
	protected Sprite _dieSprite;
	
	public Enemy(int x, int y, Board board, Sprite die, double speed, int points) {
		super(x, y, board);
		
		_points = points;
		_speed = speed;
		
		MAX_STEPS = Game.TILES_SIZE / _speed;
		rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
		_steps = MAX_STEPS;
		
		Timer = 20;
		_dieSprite = die;
	}
	
	@Override
	public void update() {
		Animation();
		
		if(isAlive == false) {
			afterKill();
			return;
		}
		
		if(isAlive)
			calculateMove();
	}
	
	@Override
	public void render(Screen screen) {
		
		if(isAlive)
			chooseSprite();
		else {
			if(Timer > 0) {
				_sprite = _dieSprite;
				Animation = 0;
			} else {
                                gameSound.getIstance().getAudio(gameSound.ENEMY_DIE).play();
				_sprite = Sprite.SpriteLoader(Sprite.enemy_die1, Sprite.enemy_die2, Sprite.enemy_die3, Sprite.enemy_die4, Sprite.enemy_die5, Animation, 100);
			}
				
		}
			
		screen.renderEntity((int)_x, (int)_y - _sprite.SIZE, this);
	}
	
	@Override
	public void calculateMove() {
		int xa = 0, ya = 0;
		if(_steps <= 0){
			Dir = _ai.calculateDirection();
			_steps = MAX_STEPS;
		}
			
		if(Dir == 0) ya--; 
		if(Dir == 2) ya++;
		if(Dir == 3) xa--;
		if(Dir == 1) xa++;
		
		if(canMove(xa, ya)) {
			_steps -= 1 + rest;
			move(xa * _speed, ya * _speed);
			isMoving = true;
		} else {
			_steps = 0;
			isMoving = false;
		}
	}
	
	@Override
	public void move(double xa, double ya) {
		if(!isAlive) return;

			_y += ya;
			_x += xa;
	}
	
	@Override
	public boolean canMove(double x, double y) {
		
		double xr = _x, yr = _y - 16; 
		
		if(Dir == 0) { yr += _sprite.getSize() -1 ; xr += _sprite.getSize()/2; } 
		if(Dir == 1) {yr += _sprite.getSize()/2; xr += 1;}
		if(Dir == 2) { xr += _sprite.getSize()/2; yr += 1;}
		if(Dir == 3) { xr += _sprite.getSize() -1; yr += _sprite.getSize()/2;}
		
		int xx = Coordinates.pixelToTile(xr) +(int)x;
		int yy = Coordinates.pixelToTile(yr) +(int)y;
		
		Entity a = _board.getEntity(xx, yy, this); 
		
		return a.collide(this);
	}
	
	@Override
	public boolean collide(Entity e) {
		if(e instanceof DirectionalExplosion) {
			kill();
			return false;
		}
		
		if(e instanceof Player) {
			((Player) e).kill();
			return false;
		}
		
		return true;
	}
	
	@Override
	public void kill() {
		if(isAlive == false) return;
		isAlive = false;
	}
	
	
	@Override
	protected void afterKill() {
		if(Timer > 0) --Timer;
		else {
			
			if(_finalAnimation > 0) --_finalAnimation;
			else
				remove();
		}
	}
	
	protected abstract void chooseSprite();
}
