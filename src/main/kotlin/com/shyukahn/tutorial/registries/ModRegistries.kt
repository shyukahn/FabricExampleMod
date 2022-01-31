package com.shyukahn.tutorial.registries

import com.shyukahn.tutorial.Tutorial
import com.shyukahn.tutorial.block.ModBlocks
import com.shyukahn.tutorial.item.ModItems
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry

object ModRegistries {
    fun registerModFuels() {
        println("Now registering Fuels for " + Tutorial.MOD_ID)
        val registry = FuelRegistry.INSTANCE

        registry.add(ModItems.IRON_WOOL, 300)
        registry.add(ModItems.PEPPER, 60)
    }

    fun registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.REDWOOD_LOG, ModBlocks.STRIPPED_REDWOOD_LOG)
        StrippableBlockRegistry.register(ModBlocks.REDWOOD_WOOD, ModBlocks.STRIPPED_REDWOOD_WOOD)
    }
}