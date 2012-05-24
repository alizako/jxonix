package su.litvak.xonix.model;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class BattleField {
	private Tile[][] tiles;
	// TODO remember previous battle-field state, or just path to revert it back when enemy kills hero
	private Set<Point> pathOfHero;
	private int score;
	
	public BattleField(int n, int m) {
		tiles = new Tile[n + 2][m + 2];
		
		for (int i = 0; i < n + 2; i++) {
			for (int j = 0; j < m + 2; j++) {
				// default hero position
				if (i == 0 && j == 0) {
					tiles[i][j] = Tile.HERO;
				} else if (i == 0 || j == 0 || i == n + 1 || j == m + 1) {
					tiles[i][j] = Tile.EARTH;
				} else {
					tiles[i][j] = Tile.WATER;
				}
			}
		}
	}
	
	public void moveHero(int dx, int dy) {
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				if (tiles[y][x].isHero()) {
					int newX = x + dx;
					int newY = y + dy;
					
					if (newX >= 0 && newX < tiles[y].length &&
						newY >= 0 && newY < tiles.length) {
						// check maybe we need to clean up (in case we entered the clean cell)
						if (tiles[newY][newX].isEarth()) {
							// TODO clean-field
						} else {
							if (pathOfHero == null) {
								pathOfHero = new HashSet<Point>();
							}
							
							pathOfHero.add(new Point(x, y));
						}
						
						tiles[y][x] = Tile.EARTH;
						// update score
						if (tiles[newY][newX].isWater()) {
							score++;
						}
						// update state of next cell
						tiles[newY][newX] = Tile.HERO;
					}
					
					return;
				}
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				result.append(tiles[y][x]).append(' ');
			}
			result.append('\n');
		}
		
		return result.toString();
	}
	
	public Tile getTile(int x, int y) {
		return tiles[y][x];
	}
	
	public int getScore() {
		return score;
	}
}