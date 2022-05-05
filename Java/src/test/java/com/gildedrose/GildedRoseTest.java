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
	void updateQuality_onlyIncreaseTo50_whenBackstagePasseSellInBetween5and10Days() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49) };
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
	void updateQuality_onlyIncreaseTo50_whenBackstagePasseSellInLessThan6() {
		String name = "Backstage passes to a TAFKAL80ETC concert";
		int sellIn = 4;
		int quality = 49;
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Backstage passes to a TAFKAL80ETC concert, 3, 50", app.items[0].toString());
	}
	
	@Test
	void updateQuality_setQualityToO_whenBackstagePasseAreExpired() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Backstage passes to a TAFKAL80ETC concert, -1, 0", app.items[0].toString());
	}
	
	@Test
	void updateQuality_qualitySame_whenSulfuras() {
		String name = "Sulfuras, Hand of Ragnaros";
		int sellIn = -1;
		int quality = 666;
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Sulfuras, Hand of Ragnaros, -1, 666", app.items[0].toString());
	}

	@Test
	void updateQuality_doubleDecreasedQuality_whenSellInLessThanZero() {
		String name = "Bananas";
		int sellIn = -1;
		int quality = 49;
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Bananas, -2, 47", app.items[0].toString());
	}
	
	@Test
	void updateQuality_AgedBrie_WhenSellInLessThan0_increaseQualityBy1() {
		String name = "Aged Brie";
		int sellIn = -1;
		int quality = 48;
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Aged Brie, -2, 50", app.items[0].toString());
	}
	
	@Test
	void updateQuality_AgedBrie_WhenSellInLessThan0_increaseQualityBy1ButNotMoreThan50() {
		String name = "Aged Brie";
		int sellIn = -1;
		int quality = 50;
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Aged Brie, -2, 50", app.items[0].toString());
	}
	
	@Test
	void updateQuality_AgedBrie_WhenSellInGreaterThan1_increaseQualityBy1ButNotMoreThan50() {
		String name = "Aged Brie";
		int sellIn = 25;
		int quality = 50;
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Aged Brie, 24, 50", app.items[0].toString());
	}
	
	@Test
	void updateQuality_AgedBrie_WhenSellInIs25_increaseQualityBy1ButNotMoreThan50() {
		String name = "Aged Brie";
		int sellIn = 25;
		int quality = 48;
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Aged Brie, 24, 49", app.items[0].toString());
	}
	
}
