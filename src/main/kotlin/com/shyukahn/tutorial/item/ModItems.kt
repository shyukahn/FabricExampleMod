package com.shyukahn.tutorial.item

import com.shyukahn.tutorial.Tutorial
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModItems {

    val RUBY = Item(FabricItemSettings().group(ModItemGroup.RUBY))
    val PEPPER = Item(FabricItemSettings()
        .food(FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build())
        .group(ModItemGroup.RUBY))

    private fun registerItem(name: String, item: Item) {
        Registry.register(Registry.ITEM, Identifier(Tutorial.MOD_ID, name), item)
    }

    fun registerModItems() {
        println("Registering Mod Items for " + Tutorial.MOD_ID)

        registerItem("ruby", RUBY)
        registerItem("pepper", PEPPER)
    }
}