package com.gildedrose.items

import com.gildedrose.UpdatableItem
import com.gildedrose.Item

/**
 * "Aged Brie" wordt beter naarmate het ouder wordt.
 */
class AgedBrie(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        decreaseSellIn()
        increaseQuality(if (item.sellIn < 0) 2 else 1)
    }
}

