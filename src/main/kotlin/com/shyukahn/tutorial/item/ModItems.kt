package com.shyukahn.tutorial.item

import com.shyukahn.tutorial.Tutorial
import com.shyukahn.tutorial.item.custom.DowsingRodItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModItems {

    val RUBY = registerItem("ruby",
        Item(FabricItemSettings().group(ModItemGroup.RUBY)))

    val PEPPER = registerItem("pepper", Item(FabricItemSettings()
        .food(FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build())
        .group(ModItemGroup.RUBY)))

    val DOWSING_ROD = registerItem("dowsing_rod",
        DowsingRodItem(FabricItemSettings().group(ModItemGroup.RUBY).maxDamage(10)))

    val IRON_WOOL = registerItem("iron_wool",
        Item(FabricItemSettings().group(ModItemGroup.RUBY)))

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registry.ITEM, Identifier(Tutorial.MOD_ID, name), item)
    }

    fun registerModItems() {
        println("Registering Mod Items for " + Tutorial.MOD_ID)
    }
}