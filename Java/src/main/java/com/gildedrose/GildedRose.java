package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int item = 0; item < items.length; item++) {

			switch (getName(item)) {

			case "Sulfuras, Hand of Ragnaros":
				break;

			case "Aged Brie":
				updateAgedBrie(item);
				break;

			case "Backstage passes to a TAFKAL80ETC concert":
				updateTAFKAL80ETC(item);
				break;
			
			case "Conjured":
				updateConjured(item);
				break;

			default:
				updateAllOther(item);
			}
		}
	}

	private String getName(int item) {
		return items[item].name.contains("Conjured") ? "Conjured" : items[item].name;
	}

	private void updateConjured(int item) {
		decrementQuality(item);
		decrementQuality(item);
		age(item);
		if (expired(item)) {
			decrementQuality(item);
			decrementQuality(item);
		}
		
		
	}

	private void updateAllOther(int item) {
		decrementQuality(item);
		age(item);
		if (expired(item)) {
			decrementQuality(item);
		}
	}

	private void updateTAFKAL80ETC(int item) {
		incrementQuality(item);
		if (items[item].sellIn < 11) {
			incrementQuality(item);
		}
		if (items[item].sellIn < 6) {
			incrementQuality(item);
		}
		age(item);
		if (expired(item)) {
			items[item].quality = 0;
		}
	}

	private void updateAgedBrie(int item) {
		incrementQuality(item);
		age(item);
		if (expired(item)) {
			incrementQuality(item);
		}
	}

	private void incrementQuality(int itemIndex) {
		if (items[itemIndex].quality < 50) {
			items[itemIndex].quality++;
		}
	}
	
	private void decrementQuality(int item) {
		if (items[item].quality > 0) {
			items[item].quality--;
		}
	}

	private boolean expired(int itemIndex) {
		return items[itemIndex].sellIn < 0;
	}

	private void age(int itemIndex) {
		items[itemIndex].sellIn = items[itemIndex].sellIn - 1;
	}

}
