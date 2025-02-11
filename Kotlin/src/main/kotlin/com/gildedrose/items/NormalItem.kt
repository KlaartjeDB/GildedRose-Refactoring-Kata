package com.gildedrose.items

import com.gildedrose.UpdatableItem
import com.gildedrose.Item

/**
 * Standaard item dat in kwaliteit afneemt naarmate het ouder wordt.
 */
class NormalItem(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        decreaseSellIn()
        decreaseQuality()
        if (item.sellIn < 0) decreaseQuality() // Na de vervaldatum dubbel zo snel achteruit
    }
}