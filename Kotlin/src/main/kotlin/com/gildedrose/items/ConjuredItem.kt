package com.gildedrose.items

import com.gildedrose.UpdatableItem
import com.gildedrose.Item

/**
 * "Conjured" items verslechteren tweemaal zo snel als normale items.
 */
class ConjuredItem(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        decreaseSellIn()
        val degradation = if (item.sellIn < 0) 4 else 2
        decreaseQuality(degradation)
    }
}