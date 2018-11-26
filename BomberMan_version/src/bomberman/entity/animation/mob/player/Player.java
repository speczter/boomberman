package bomberman.entity.animation.mob.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bomberman.Board;
import bomberman.Game;
import bomberman.entity.Entity;
import bomberman.entity.animation.bomb.Bomb;
import bomberman.entity.animation.DirectionalExplosion;
import bomberman.entity.animation.mob.Mob;
import bomberman.entity.animation.mob.enemy.Enemy;
import bomberman.entity.tile.item.Item;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.controller.EventController;
import bomberman.level.DataLoaderLevel.Coordinates;
import gameSound.GameSound;

public class Player extends Mob {
	
	private List<Bomb> _bombs;
	protected EventController _input;
	protected int _timeBetweenPutBombs = 0;
        protected int TimerDie = 35;
	public static List<Item> _powerups = new ArrayList<Item>();
	
	
	public Player(int x, int y, Board board) {
		super(x, y, board);
		_bombs = _board.getBombs();
		_input = _board.getInput();
		_sprite = Sprite.player_right;
	}
	
	
	@Override
	public void update() {
		clearBombs();
		if(isAlive == false) {
			afterKill();
			return;
		}
		
		if(_timeBetweenPutBombs < -7500) _timeBetweenPutBombs = 0; else _timeBetweenPutBombs--;
		
		Animation();
		
		calculateMove();
		
		detectPlaceBomb();
	}
	
	@Override
	public void render(Screen screen) {
		calculateXOffset();
                
		if (isAlive){
                    AliveSprite();
                }
                if (isDying){
                    try {
                        diePlayer();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
		screen.renderEntity((int)_x, (int)_y - _sprite.SIZE, this);
	}
	
	public void calculateXOffset() {
		int xScroll = Screen.calculateXOffset(_board, this);
		Screen.setOffset(xScroll, 0);
	}

	private void detectPlaceBomb() {
		if(_input.space && Game.getBombRate() > 0 && _timeBetweenPutBombs < 0) {
			
			int xt = Coordinates.pixelToTile(_x + _sprite.getSize() / 2);
			int yt = Coordinates.pixelToTile( (_y + _sprite.getSize() / 2) - _sprite.getSize() );
			
			placeBomb(xt,yt);
			Game.addBombRate(-1);
			
			_timeBetweenPutBombs = 30;
		}
	}
	
	protected void placeBomb(int x, int y) {
		Bomb b = new Bomb(x, y, _board);
		_board.addBomb(b);
	}
	
	private void clearBombs() {
		Iterator<Bomb> bs = _bombs.iterator();
		
		Bomb b;
		while(bs.hasNext()) {
			b = bs.next();
			if(b.isRemoved())  {
				bs.remove();
				Game.addBombRate(1);
			}
		}
		
	}
	
	@Override
	public void kill() {
		if(!isAlive) return;
		
		isAlive = false;
                isDying = true;
	}
	
	@Override
	protected void afterKill() {
		if(Timer > 0) --Timer;
		else {
			if(_bombs.isEmpty()) {
                            _board.restartLevel();
                           //_board.endGame();
			}
		}
	}
	
	@Override
	protected void calculateMove() {
		int xa = 0, ya = 0;
		if(_input.up) ya--;
		if(_input.down) ya++;
		if(_input.left) xa--;
		if(_input.right) xa++;
		
		if(xa != 0 || ya != 0)  {
			move(xa * Game.getPlayerSpeed(), ya * Game.getPlayerSpeed());
			isMoving = true;
		} else {
			isMoving = false;
		}
		
	}
	
	@Override
	public boolean canMove(double x, double y) {
		for (int c = 0; c < 4; c++) { 
			double xt = ((_x + x) + c % 2 * 11) / Game.TILES_SIZE; 
			double yt = ((_y + y) + c / 2 * 12 - 13) / Game.TILES_SIZE;
			
			Entity a = _board.getEntity(xt, yt, this);
			
			if(!a.collide(this))
				return false;
		}
		
		return true;
	}
        
	@Override
	public void move(double xa, double ya) {
		if(xa > 0) Dir = 1;
		if(xa < 0) Dir = 3;
		if(ya > 0) Dir = 2;
		if(ya < 0) Dir = 0;
		
		if(canMove(0, ya)) { 
			_y += ya;
		}
		
		if(canMove(xa, 0)) {
			_x += xa;
		}
	}
	
	@Override
	public boolean collide(Entity e) {
		if(e instanceof DirectionalExplosion) {
			kill();
			return false;
		}
		
		if(e instanceof Enemy) {
			kill();
			return true;
		}
		
		return true;
	}
	
	public void addPowerup(Item i) {
		if(i.isRemoved()) return;
		
		_powerups.add(i);
		
		i.setValues();
	}
	
	public void clearUsedPowerups() {
		Item p;
		for (int i = 0; i < _powerups.size(); i++) {
			p = _powerups.get(i);
			if(p.isActive() == false)
				_powerups.remove(i);
		}
	}
	
	public void removePowerups() {
		for (int i = 0; i < _powerups.size(); i++) {
				_powerups.remove(i);
		}
	}
	
	private void AliveSprite() {
            if (isAlive){
                switch(Dir) {
		case 0:
			_sprite = Sprite.player_up;
			if(isMoving) {
				_sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, Animation, 20);
			}
			break;
		case 1:
			_sprite = Sprite.player_right;
			if(isMoving) {
				_sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, Animation, 20);
			}
			break;
		case 2:
			_sprite = Sprite.player_down;
			if(isMoving) {
				_sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, Animation, 20);
			}
			break;
		case 3:
			_sprite = Sprite.player_left;
			if(isMoving) {
				_sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, Animation, 20);
			}
			break;
		default:
			_sprite = Sprite.player_right;
			if(isMoving) {
				_sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, Animation, 20);
			}
			break;
		}
            }
                
	}
        private void diePlayer() throws InterruptedException{
            Thread.sleep(20);
            TimerDie--;
            if (TimerDie % 35 > 25){
                _sprite = Sprite.player_die1;
            }
            else if (TimerDie % 35 > 15){
                _sprite = Sprite.player_die2;
            }else if (TimerDie % 35 > 5){
                _sprite = Sprite.player_die3;
            }else{
                remove();
            }
            gameSound.getIstance().getAudio(gameSound.PLAYGAME).stop();
            gameSound.getIstance().getAudio(gameSound.BOMBER_DIE).play();
        }
}
