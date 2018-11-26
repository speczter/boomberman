package bomberman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bomberman.entity.Entity;
import bomberman.entity.animation.Message;
import bomberman.entity.animation.bomb.Bomb;
import bomberman.entity.animation.Flame;
import bomberman.entity.animation.mob.Mob;
import bomberman.entity.animation.mob.player.Player;
import bomberman.entity.tile.item.Item;
import bomberman.exceptions.LoadLevelException;
import bomberman.graphics.IRender;
import bomberman.graphics.Screen;
import bomberman.controller.EventController;
import bomberman.level.DataLoaderLevel.FileLevel;
import bomberman.level.Level;

public class Board implements IRender {

	public int _width, _height;
	protected Level _level;
	protected Game _game;
	protected EventController _input;
	protected Screen _screen;
	
	public Entity[] Entity;
	public List<Mob> _mobs = new ArrayList<Mob>();
	protected List<Bomb> _bombs = new ArrayList<Bomb>();
	private List<Message> _messages = new ArrayList<Message>();
	
	private int _screenToShow = -1;
        
	public Board(Game game, EventController input, Screen screen) {
		_game = game;
		_input = input;
		_screen = screen;
		
		changeLevel(1);
	}

	@Override
	public void update() {
		if( _game.isPaused() ) return;
		
		updateEntity();
		updateMobs();
		updateBombs();
		updateMessages();
		
		for (int i = 0; i < _mobs.size(); i++) {
			Mob a = _mobs.get(i);
			if(((Entity)a).isRemoved()) _mobs.remove(i);
		}
	}


	@Override
	public void render(Screen screen) {
		if( _game.isPaused() ) return;

		int x0 = Screen.xOffset >> 4; 
		int x1 = (Screen.xOffset + screen.getWidth() + Game.TILES_SIZE) / Game.TILES_SIZE; 
		int y0 = Screen.yOffset >> 4;
		int y1 = (Screen.yOffset + screen.getHeight()) / Game.TILES_SIZE; 
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				Entity[x + y * _level.getWidth()].render(screen);
			}
		}
		
		renderBombs(screen);
		renderMobs(screen);
		
	}
	public void newGame() {
		resetProperties();
		changeLevel(1);
	}
	
	@SuppressWarnings("static-access")
	private void resetProperties() {
		Player._powerups.clear();
		
		_game.playerSpeed = 1.0;
		_game.bombRadius = 1;
		_game.bombRate = 1;
		
	}

	public void restartLevel() {
		changeLevel(_level.getLevel());
	}
	
	public void nextLevel() {
		changeLevel(_level.getLevel() + 1);
	}
	
	public void changeLevel(int level) {
		_screenToShow = 2;
		_game.pause();
		_mobs.clear();
		_bombs.clear();
		_messages.clear();
		
		try {
			_level = new FileLevel("levels/Level" + level + ".txt", this);
			Entity = new Entity[_level.getHeight() * _level.getWidth()];
			
			_level.createEntity();
		} catch (LoadLevelException e) {
			endGame(); 
		}
	}
	// Phuong thuc su dung item
	public boolean isPowerupUsed(int x, int y, int level) {
		Item p;
		for (int i = 0; i < Player._powerups.size(); i++) {
			p = Player._powerups.get(i);
			if(p.getX() == x && p.getY() == y && level == p.getLevel())
				return true;
		}
		
		return false;
	}
	
	public void endGame() {
		_screenToShow = 1;
                //_game.stop();
                _game.resetScreenDelay();
                _game.pause();
	}
	
	public boolean detectNoEnemies() {
		int total = 0;
		for (int i = 0; i < _mobs.size(); i++) {
			if(_mobs.get(i) instanceof Player == false)
				++total;
		}
		
		return total == 0;
	}
	
	public Entity getEntity(double x, double y, Mob m) {
		
		Entity res = null;
		
		res = getExplosionAt((int)x, (int)y);
		if( res != null) return res;
		
		res = getBombAt(x, y);
		if( res != null) return res;
		
		res = getMobAtExcluding((int)x, (int)y, m);
		if( res != null) return res;
		
		res = getEntityAt((int)x, (int)y);
		
		return res;
	}
	
	public List<Bomb> getBombs() {
		return _bombs;
	}
	
	public Bomb getBombAt(double x, double y) {
		Iterator<Bomb> bs = _bombs.iterator();
		Bomb b;
		while(bs.hasNext()) {
			b = bs.next();
			if(b.getX() == (int)x && b.getY() == (int)y)
				return b;
		}
		
		return null;
	}
	// Bo sung phuong thuc getMobAt
	public Mob getMobAt(double x, double y) {
		Iterator<Mob> itr = _mobs.iterator();
		
		Mob cur;
		while(itr.hasNext()) {
			cur = itr.next();
			
			if(cur.getXTile() == x && cur.getYTile() == y)
				return cur;
		}
		
		return null;
	}
	// Bo sung phuong thuc getPlayer
	public Player getPlayer() {
		Iterator<Mob> itr = _mobs.iterator();
		
		Mob cur;
		while(itr.hasNext()) {
			cur = itr.next();
			
			if(cur instanceof Player)
				return (Player) cur;
		}
		
		return null;
	}
	
	public Mob getMobAtExcluding(int x, int y, Mob a) {
		Iterator<Mob> itr = _mobs.iterator();
		
		Mob cur;
		while(itr.hasNext()) {
			cur = itr.next();
			if(cur == a) {
				continue;
			}
			
			if(cur.getXTile() == x && cur.getYTile() == y) {
				return cur;
			}
				
		}
		
		return null;
	}
	// Xu ly bom no
	public Flame getExplosionAt(int x, int y) {
		Iterator<Bomb> bs = _bombs.iterator();
		Bomb b;
		while(bs.hasNext()) {
			b = bs.next();
			
			Flame e = b.explosionAt(x, y);
			if(e != null) {
				return e;
			}
				
		}
		
		return null;
	}
	
	public Entity getEntityAt(double x, double y) {
		return Entity[(int)x + (int)y * _level.getWidth()];
	}
	
	public void addEntitie(int pos, Entity e) {
		Entity[pos] = e;
	}
	
	public void addMob(Mob e) {
		_mobs.add(e);
	}
	
	public void addBomb(Bomb e) {
		_bombs.add(e);
	}
	
	public void addMessage(Message e) {
		_messages.add(e);
	}

	protected void renderEntity(Screen screen) {
            for (Entity Entity1 : Entity) {
                Entity1.render(screen);
            }
	}
	
	protected void renderMobs(Screen screen) {
		Iterator<Mob> itr = _mobs.iterator();
		
		while(itr.hasNext())
			itr.next().render(screen);
	}
	
	protected void renderBombs(Screen screen) {
		Iterator<Bomb> itr = _bombs.iterator();
		
		while(itr.hasNext())
			itr.next().render(screen);
	}

	protected void updateEntity() {
		if( _game.isPaused() ) return;
            for (Entity Entity1 : Entity) {
                Entity1.update();
            }
	}
	
	protected void updateMobs() {
		if( _game.isPaused() ) return;
		Iterator<Mob> itr = _mobs.iterator();
		
		while(itr.hasNext() && !_game.isPaused())
			itr.next().update();
	}
	
	protected void updateBombs() {
		if( _game.isPaused() ) return;
		Iterator<Bomb> itr = _bombs.iterator();
		
		while(itr.hasNext())
			itr.next().update();
	}
	
	protected void updateMessages() {
		if( _game.isPaused() ) return;
		Message m;
		int left = 0;
		for (int i = 0; i < _messages.size(); i++) {
			m = _messages.get(i);
			left = m.getDuration();
			
			if(left > 0) 
				m.setDuration(--left);
			else
				_messages.remove(i);
		}
	}
	
	public EventController getInput() {
		return _input;
	}
	
	public Level getLevel() {
		return _level;
	}
	
	public Game getGame() {
		return _game;
	}
	
	public int getShow() {
		return _screenToShow;
	}
	
	public void setShow(int i) {
		_screenToShow = i;
	}
	
	public int getWidth() {
		return _level.getWidth();
	}

	public int getHeight() {
		return _level.getHeight();
	}
	
}
