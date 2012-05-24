package su.litvak.xonix.model;

public enum Tile {
	WATER,
	EARTH,
	HERO,
	ENEMY;
	
	@Override
	public String toString() {
		switch (this) {
			case WATER:
				return "I";
			case EARTH:
				return "O";
			case HERO:
				return "H";
			case ENEMY:
				return "X";
		}
		return "n/a";
	}
	
	public boolean isHero() {
		return this == HERO;
	}
	
	public boolean isEarth() {
		return this == EARTH;
	}
	
	public boolean isWater() {
		return this == WATER;
	}
}