package bomberman.entity.animation.mob;

import bomberman.Board;
import bomberman.Game;
import bomberman.entity.animation.Animation;
import bomberman.graphics.Screen;

public abstract class Mob extends Animation {
	
	protected Board _board;
	protected int Dir = -1;
	protected boolean isAlive = true;
        protected boolean isDying = false;
	protected boolean isMoving = false;
	public int Timer = 80;
	
	public Mob(int x, int y, Board board) {
		_x = x;
		_y = y;
		_board = board;
	}
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(Screen screen);
	
	protected abstract void calculateMove();
	
	protected abstract void move(double xa, double ya);
	
	public abstract void kill();
	
	protected abstract void afterKill();
	
	protected abstract boolean canMove(double x, double y);
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public boolean isMoving() {
		return isMoving;
	}
        
        public boolean isDying(){
            return isDying;
        }
	
	public int getDirection() {
		return Dir;
	}
	
	protected double getXMessage() {
		return (_x * Game.SCALE) + (_sprite.SIZE / 2 * Game.SCALE);
	}
	
	protected double getYMessage() {
		return (_y* Game.SCALE) - (_sprite.SIZE / 2 * Game.SCALE);
	}
	
}
