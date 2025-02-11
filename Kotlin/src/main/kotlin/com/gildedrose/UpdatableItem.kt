package com.gildedrose

import kotlin.math.max
import kotlin.math.min

/**
 * Abstracte klasse voor items die kunnen updaten.
 */
abstract class UpdatableItem(protected val item: Item) {
    abstract fun updateQuality()

    /**
     * Vermindert de verkoopdatum van het item met 1.
     */
    protected fun decreaseSellIn() {
        item.sellIn -= 1
    }

    /**
     * Verhoogt de kwaliteit met een opgegeven hoeveelheid, met een max van 50.
     */
    protected fun increaseQuality(amount: Int = 1) {
        require(amount > 0) { "Verhoogde kwaliteit moet positief zijn" }
        item.quality = min(50, item.quality + amount)
    }

    /**
     * Vermindert de kwaliteit met een opgegeven hoeveelheid, met een minimum van 0.
     */
    protected fun decreaseQuality(amount: Int = 1) {
        require(amount > 0) { "Verminderde kwaliteit moet positief zijn" }
        item.quality = max(0, item.quality - amount)
    }
}