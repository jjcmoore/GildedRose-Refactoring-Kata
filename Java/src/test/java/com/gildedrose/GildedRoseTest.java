package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

	@Test
	void foo() {
		Item[] items = new Item[] { new Item("foo", 0, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("foo", app.items[0].name);
	}

	@Test
	void updateQuality_decrementQuality_whenQualityIsOne() {
		Item[] items = new Item[] { new Item("foo", 1, 1) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("foo", app.items[0].name);
		assertEquals(0, app.items[0].quality);
	}

	@Test
	void updateQuality_incrementQuality_whenAgedBrie() {
		Item[] items = new Item[] { new Item("Aged Brie", 1, 1) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Aged Brie, 0, 2", app.items[0].toString());
	}

	@Test
	void updateQuality_doNotIncreaseQuality_whenAgedBrieIs50() {
		Item[] items = new Item[] { new Item("Aged Brie", 1, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Aged Brie, 0, 50", app.items[0].toString());
	}
	
	@Test
	void updateQuality_increaseQualityBy1_whenBackstagePasseSellinGreaterThan10() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Backstage passes to a TAFKAL80ETC concert, 10, 1", app.items[0].toString());
	}
	
	@Test
	void updateQuality_increaseQualityBy2_whenBackstagePasseSellInLessThan11AndGreaterThan5() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Backstage passes to a TAFKAL80ETC concert, 9, 2", app.items[0].toString());
	}
	
	@Test
	void updateQuality_dontIncreaseQualityBy2_whenBackstagePasseSellInLessThan11AndGreaterThan5() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Backstage passes to a TAFKAL80ETC concert, 9, 50", app.items[0].toString());
	}
	
	@Test
	void updateQuality_increaseQualityBy3_whenBackstagePasseSellInLessThan6() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Backstage passes to a TAFKAL80ETC concert, 4, 13", app.items[0].toString());
	}
	
	@Test
	void updateQuality_setQualityToO_whenBackstagePasseAreExpired() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Backstage passes to a TAFKAL80ETC concert, -1, 0", app.items[0].toString());
	}
	


	
}
