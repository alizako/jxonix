package su.litvak.xonix.test;

import org.junit.Before;
import org.junit.Test;

import su.litvak.xonix.model.BattleField;
import su.litvak.xonix.model.Tile;

import static junit.framework.Assert.*;

public class Tests {
	
	private final static int FIELD_WIDTH = 5;
	private final static int FIELD_HEIGHT = 5;
	
	BattleField field;
	
	@Before
	public void init() {
		this.field = new BattleField(FIELD_WIDTH, FIELD_HEIGHT);
	}
	
	@Test
	public void testHeroPosition() {
		assertTrue("Hero is in upper left corner", field.getTile(0, 0).isHero());
	}
	
	@Test
	public void testHeroPathErasure() {
		field.moveHero(1, 0);
		field.moveHero(1, 0);
		
		assertEquals("Hero moved right two times", Tile.HERO, field.getTile(2, 0));
		
		for (int y = 1; y < FIELD_HEIGHT + 2; y++) {
			field.moveHero(0, 1);
			assertEquals("Hero erased cell #" + y, Tile.EARTH, field.getTile(2, y - 1));
		}
		
		assertEquals("Score", FIELD_HEIGHT, field.getScore());
	}
}
