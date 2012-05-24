package su.litvak.xonix.test;

import org.junit.Before;
import org.junit.Test;

import su.litvak.xonix.controller.Controller;
import su.litvak.xonix.model.BattleField;
import su.litvak.xonix.model.Tile;

import static junit.framework.Assert.*;

public class Tests {
	BattleField field;
	Controller controller;
	
	@Before
	public void init() {
		this.field = new BattleField(5, 5);
		this.controller = new Controller(field);
	}
	
	@Test
	public void testHeroPosition() {
		assertTrue("Hero is in upper left corner", field.getTile(0, 0).isHero());
	}
	
	@Test
	public void testHeroPathErasure() {
		controller.moveHero(1, 0);
		controller.moveHero(1, 0);
		
		assertEquals("Hero moved right two times", Tile.HERO, field.getTile(2, 0));
		
		for (int y = 1; y < field.getHeight(); y++) {
			controller.moveHero(0, 1);
			assertEquals("Hero erased cell #" + y, Tile.EARTH, field.getTile(2, y - 1));
		}
		
		assertEquals("Score", field.getHeight() - 2, field.getScore());
	}
}
