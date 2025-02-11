package com.gildedrose

import com.gildedrose.items.*

class GildedRose(var items: List<Item>) {
    /**
     * Update de kwaliteit van alle items in de lijst.
     * Maakt gebruik van polymorfisme om specifieke regels per itemtype toe te passen.
     */
    fun updateQuality() {
        items.forEach { getItemHandler(it).updateQuality() }
    }

    /**
     * Bepaalt het juiste type handler op basis van de naam van het item.
     */
    private fun getItemHandler(item: Item): UpdatableItem {
        return when {
            item.name == "Aged Brie" -> AgedBrie(item)
            item.name == "Sulfuras, Hand of Ragnaros" -> Sulfuras(item)
            item.name == "Backstage passes to a TAFKAL80ETC concert" -> BackstagePasses(item)
            item.name.startsWith("Conjured") -> ConjuredItem(item)
            else -> NormalItem(item)
        }
    }
}