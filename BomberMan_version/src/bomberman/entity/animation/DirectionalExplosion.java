package bomberman.entity.animation;

import bomberman.Board;
import bomberman.entity.Entity;
import bomberman.entity.animation.mob.Mob;
import bomberman.graphics.Screen;

public class DirectionalExplosion extends Entity {

	protected Board _board;
	protected int Dir;
	private int _radius;
	protected int xOrigin, yOrigin;
	protected Flame[] _explosions;
	
	public DirectionalExplosion(int x, int y, int direction, int radius, Board board) {
		xOrigin = x;
		yOrigin = y;
		_x = x;
		_y = y;
		Dir = direction;
		_radius = radius;
		_board = board;
		
		_explosions = new Flame[ calculatePermitedDistance() ];
		createExplosions();
		}
	// Xu li bom no
	private void createExplosions() {
		boolean last = false;
		
		int x = (int)_x;
		int y = (int)_y;
		for (int i = 0; i < _explosions.length; i++) {
			last = i == _explosions.length -1;
			
			switch (Dir) {
				case 0: y--; break;
				case 1: x++; break;
				case 2: y++; break;
				case 3: x--; break;
			}
			_explosions[i] = new Flame(x, y, Dir, last, _board);
		}
	}
	// Tinh chu vi map
	private int calculatePermitedDistance() {
		int radius = 0;
		int x = (int)_x;
		int y = (int)_y;
		while(radius < _radius) {
			if(Dir == 0) y--;
			if(Dir == 1) x++;
			if(Dir == 2) y++;
			if(Dir == 3) x--;
			
			Entity a = _board.getEntity(x, y, null);
			
			if(a instanceof Mob) ++radius; 
			
			if(a.collide(this) == false)
				break;
			
			++radius;
		}
		return radius;
	}
	// Bom no tai 5 o grass
	public Flame explosionAt(int x, int y) {
            for (Flame _explosion : _explosions) {
                if (_explosion.getX() == x && _explosion.getY() == y) {
                    return _explosion;
                }
            }
		return null;
	}

	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		
            for (Flame _explosion : _explosions) {
                _explosion.render(screen);
            }
	}

	@Override
	public boolean collide(Entity e) {
		return true;
	}
}
