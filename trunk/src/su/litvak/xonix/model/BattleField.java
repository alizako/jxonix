package su.litvak.xonix.model;


public class BattleField {
	private Tile[][] tiles;
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
	
	public void setTile(int x, int y, Tile tile) {
		tiles[y][x] = tile;
	}
	
	public int getWidth() {
		return tiles[0].length;
	}
	
	public int getHeight() {
		return tiles.length;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}