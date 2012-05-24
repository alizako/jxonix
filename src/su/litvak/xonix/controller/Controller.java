package su.litvak.xonix.controller;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import su.litvak.xonix.model.BattleField;
import su.litvak.xonix.model.Tile;

public class Controller {
	private BattleField field;
	
	// TODO remember previous battle-field state, or just path to revert it back when enemy kills hero
	private Set<Point> pathOfHero;

	public Controller(BattleField field) {
		super();
		this.field = field;
	}
	
	public void moveHero(int dx, int dy) {
		for (int x = 0; x < field.getWidth(); x++) {
			for (int y = 0; y < field.getWidth(); y++) {
				if (field.getTile(x, y).isHero()) {
					int newX = x + dx;
					int newY = y + dy;
					
					if (newX >= 0 && newX < field.getWidth() &&
						newY >= 0 && newY < field.getHeight()) {
						// check maybe we need to clean up (in case we entered the clean cell)
						if (field.getTile(newX, newY).isEarth()) {
							// TODO clean-field
						} else {
							if (pathOfHero == null) {
								pathOfHero = new HashSet<Point>();
							}
							
							pathOfHero.add(new Point(x, y));
						}
						
						field.setTile(x, y, Tile.EARTH);
						// update score
						if (field.getTile(newX, newY).isWater()) {
							field.setScore(field.getScore() + 1);
						}
						// update state of next cell
						field.setTile(newX, newY, Tile.HERO);
					}
					
					return;
				}
			}
		}
	}
}
