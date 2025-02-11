package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GildedRoseTest {

    @Test
    fun `Normal item degrades in quality and sellIn decreases`() {
        val items = listOf(Item("Normal Item", 10, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(9, items[0].sellIn, "SellIn should decrease")
        assertEquals(19, items[0].quality, "Quality should decrease")
    }

    @Test
    fun `Quality degrades twice as fast after sellIn date`() {
        val items = listOf(Item("Normal Item", 0, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(-1, items[0].sellIn)
        assertEquals(18, items[0].quality, "Quality should decrease by 2 after expiry")
    }

    @Test
    fun `Quality never drops below zero`() {
        val items = listOf(Item("Normal Item", 5, 0))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(4, items[0].sellIn)
        assertEquals(0, items[0].quality, "Quality should not go below 0")
    }

    @Test
    fun `Aged Brie increases in quality`() {
        val items = listOf(Item("Aged Brie", 5, 10))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(4, items[0].sellIn)
        assertEquals(11, items[0].quality, "Aged Brie should increase in quality")
    }

    @Test
    fun `Aged Brie increases in quality twice as fast after expiry`() {
        val items = listOf(Item("Aged Brie", 0, 10))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(-1, items[0].sellIn)
        assertEquals(12, items[0].quality, "Aged Brie should increase by 2 after expiry")
    }

    @Test
    fun `Quality never exceeds 50`() {
        val items = listOf(Item("Aged Brie", 5, 50))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(4, items[0].sellIn)
        assertEquals(50, items[0].quality, "Quality should not exceed 50")
    }

    @Test
    fun `Sulfuras never changes`() {
        val items = listOf(Item("Sulfuras, Hand of Ragnaros", 10, 80))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(10, items[0].sellIn, "Sulfuras should not decrease in sellIn")
        assertEquals(80, items[0].quality, "Sulfuras should not change in quality")
    }

    @Test
    fun `Backstage passes increase in quality as sellIn approaches`() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(10, items[0].sellIn)
        assertEquals(21, items[0].quality, "Backstage passes should increase by 1 when sellIn > 10")
    }

    @Test
    fun `Backstage passes increase by 2 when sellIn is 10 or less`() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(9, items[0].sellIn)
        assertEquals(22, items[0].quality, "Backstage passes should increase by 2 when 10 or less")
    }

    @Test
    fun `Backstage passes increase by 3 when sellIn is 5 or less`() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(4, items[0].sellIn)
        assertEquals(23, items[0].quality, "Backstage passes should increase by 3 when 5 or less")
    }

    @Test
    fun `Backstage passes drop to 0 after sellIn date`() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(-1, items[0].sellIn)
        assertEquals(0, items[0].quality, "Backstage passes should drop to 0 after the concert")
    }

    @Test
    fun `Conjured item degrades in quality twice as fast`() {
        val item = Item("Conjured Mana Cake", sellIn = 5, quality = 10)
        val conjuredItem = ConjuredItem(item)

        conjuredItem.updateQuality()

        assertEquals(8, item.quality) // Should decrease by 2
        assertEquals(4, item.sellIn) // Should decrease by 1
    }

    @Test
    fun `Conjured item degrades twice as fast after sell-by date`() {
        val item = Item("Conjured Mana Cake", sellIn = 0, quality = 10)
        val conjuredItem = ConjuredItem(item)

        conjuredItem.updateQuality()

        assertEquals(6, item.quality) // Should decrease by 4 (double degradation)
        assertEquals(-1, item.sellIn) // Should decrease by 1
    }

    @Test
    fun `Conjured item quality never goes below 0`() {
        val item = Item("Conjured Mana Cake", sellIn = 3, quality = 1)
        val conjuredItem = ConjuredItem(item)

        conjuredItem.updateQuality()

        assertEquals(0, item.quality) // Should never go negative
        assertEquals(2, item.sellIn) // Should decrease by 1
    }

    @Test
    fun `Conjured item degrades normally when sellIn is positive`() {
        val item = Item("Conjured Sword", sellIn = 10, quality = 20)
        val conjuredItem = ConjuredItem(item)

        conjuredItem.updateQuality()

        assertEquals(18, item.quality) // -2 per day
        assertEquals(9, item.sellIn)
    }
}



