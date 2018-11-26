package bomberman.level.DataLoaderLevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import bomberman.Board;
import bomberman.Game;
import bomberman.entity.animation.LayeredEntity;
import bomberman.entity.animation.mob.player.Player;
import bomberman.entity.animation.mob.enemy.types.Balloom;
import bomberman.entity.animation.mob.enemy.types.Doll;
import bomberman.entity.animation.mob.enemy.types.Kondoria;
import bomberman.entity.animation.mob.enemy.types.Minvo;
import bomberman.entity.animation.mob.enemy.types.Oneal;
import bomberman.entity.tile.map.GrassTile;
import bomberman.entity.tile.map.PortalTile;
import bomberman.entity.tile.map.WallTile;
import bomberman.entity.tile.destroyable.box.BoxTile;
import bomberman.entity.tile.item.bom.ItemBomb;
import bomberman.entity.tile.item.flame.ItemFlame;
import bomberman.entity.tile.item.speed.ItemSpeed;
import bomberman.exceptions.LoadLevelException;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.Level;

public class FileLevel extends Level {
	
	public FileLevel(String path, Board board) throws LoadLevelException {
		super(path, board);
	}
	
	@Override
	public void loadLevel(String path) throws LoadLevelException {
		try {
			URL absPath = FileLevel.class.getResource("/" + path);
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(absPath.openStream()));

			String data = in.readLine();
			StringTokenizer tokens = new StringTokenizer(data);
			
			_level = Integer.parseInt(tokens.nextToken());
			_height = Integer.parseInt(tokens.nextToken());
			_width = Integer.parseInt(tokens.nextToken());

			_lineTiles = new String[_height];
			
			for(int i = 0; i < _height; ++i) {
				_lineTiles[i] = in.readLine().substring(0, _width);
 			}
			
			in.close();
		} catch (IOException e) {
			throw new LoadLevelException("Error loading level " + path, e);
		}
	}
	
	@Override
	public void createEntity() {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				addLevelEntity( _lineTiles[y].charAt(x), x, y );
			}
		}
	}
	
	public void addLevelEntity(char c, int x, int y) {
		int pos = x + y * getWidth();
		
		switch(c) { 
			case '#': 
				_board.addEntitie(pos, new WallTile(x, y, Sprite.wall));  
				break;
			case 'b': 
				LayeredEntity layer = new LayeredEntity(x, y, 
						new GrassTile(x ,y, Sprite.grass), 
						new BoxTile(x ,y, Sprite.box));
				
				if(_board.isPowerupUsed(x, y, _level) == false) {
					layer.addBeforeTop(new ItemBomb(x, y, _level, Sprite.item_bomb));
				}
				
				_board.addEntitie(pos, layer);
				break;
			case 's':
				layer = new LayeredEntity(x, y, 
						new GrassTile(x ,y, Sprite.grass), 
						new BoxTile(x ,y, Sprite.box));
				
				if(_board.isPowerupUsed(x, y, _level) == false) {
					layer.addBeforeTop(new ItemSpeed(x, y, _level, Sprite.item_speed));
				}
				
				_board.addEntitie(pos, layer);
				break;
			case 'f': 
				layer = new LayeredEntity(x, y, 
						new GrassTile(x ,y, Sprite.grass), 
						new BoxTile(x ,y, Sprite.box));
				
				if(_board.isPowerupUsed(x, y, _level) == false) {
					layer.addBeforeTop(new ItemFlame(x, y, _level, Sprite.item_flame));
				}
				
				_board.addEntitie(pos, layer);
				break;
			case '*': 
				_board.addEntitie(pos, new LayeredEntity(x, y, 
						new GrassTile(x ,y, Sprite.grass), 
						new BoxTile(x ,y, Sprite.box)) );
				break;
			case 'x': 
				_board.addEntitie(pos, new LayeredEntity(x, y, 
						new GrassTile(x ,y, Sprite.grass), 
						new PortalTile(x ,y, _board, Sprite.portal), 
						new BoxTile(x ,y, Sprite.box)) );
				break;
			case ' ': 
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
			case 'p': 
				_board.addMob( new Player(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board) );
				Screen.setOffset(0, 0);
				
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
			case '1':
				_board.addMob( new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
			case '2':
				_board.addMob( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
			case '3':
				_board.addMob( new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
			case '4':
				_board.addMob( new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
			case '5':
				_board.addMob( new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
			default: 
				_board.addEntitie(pos, new GrassTile(x, y, Sprite.grass) );
				break;
			}
	}
	
}
