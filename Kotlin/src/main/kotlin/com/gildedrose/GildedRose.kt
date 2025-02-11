package com.gildedrose

import kotlin.math.max

// Base class for all item types
abstract class UpdatableItem(protected val item: Item) {
    abstract fun updateQuality()
}

// "Aged Brie" increases in quality over time
class AgedBrie(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        if (item.quality < 50) item.quality += 1
        item.sellIn -= 1
        if (item.sellIn < 0 && item.quality < 50) item.quality += 1
    }
}

// "Sulfuras" never changes
class Sulfuras(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        // Do nothing, as Sulfuras does not change in quality or sellIn
    }
}

// "Backstage passes" increase in quality but drop to 0 after event
class BackstagePasses(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        if (item.quality < 50) {
            item.quality += 1
            if (item.sellIn <= 10 && item.quality < 50) item.quality += 1
            if (item.sellIn <= 5 && item.quality < 50) item.quality += 1
        }
        item.sellIn -= 1
        if (item.sellIn < 0) item.quality = 0
    }
}

// "Conjured" items degrade twice as fast
class ConjuredItem(item: Item) : UpdatableItem(item){
    override fun updateQuality() {
        item.sellIn -= 1
        val degradation = if (item.sellIn < 0) 4 else 2
        item.quality = max(0, item.quality - degradation) // Prevent negative values
    }
}


// Normal items
class NormalItem(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        if (item.quality > 0) {
            item.quality -= 1
        }
        item.sellIn -= 1
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality -= 1
        }
    }
}

// GildedRose class using polymorphism
class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.map { getItemHandler(it) }.forEach { it.updateQuality() }
    }

    private fun getItemHandler(item: Item): UpdatableItem {
        return when {
            item.name == "Aged Brie" -> AgedBrie(item)
            item.name == "Sulfuras, Hand of Ragnaros" -> Sulfuras(item)
            item.name == "Backstage passes to a TAFKAL80ETC concert" -> BackstagePasses(item)
            item.name.startsWith("Conjured") -> ConjuredItem(item)
            else -> NormalItem(item)  // Normal items
        }
    }
}
