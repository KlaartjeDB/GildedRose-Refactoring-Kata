package com.gildedrose.items

import com.gildedrose.UpdatableItem
import com.gildedrose.Item

/**
 * "Sulfuras" is een legendarisch item en verandert nooit in kwaliteit of verkoopdatum.
 */
class Sulfuras(item: Item) : UpdatableItem(item) {
     override fun updateQuality() {
        // Geen wijzigingen nodig, want Sulfuras blijft altijd hetzelfde.
    }
}