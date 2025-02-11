package com.gildedrose.items

import com.gildedrose.UpdatableItem
import com.gildedrose.Item

/**
 * "Backstage passes" stijgen in waarde naarmate het evenement dichterbij komt,
 * maar verliezen alle waarde na de vervaldatum.
 */
class BackstagePasses(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        when {
            item.sellIn <= 0 -> item.quality = 0 // Na het concert is de kwaliteit 0
            item.sellIn <= 5 -> increaseQuality(3) // Minder dan 5 dagen: +3
            item.sellIn <= 10 -> increaseQuality(2) // Minder dan 10 dagen: +2
            else -> increaseQuality(1) // Standaard +1
        }
        decreaseSellIn()
    }
}