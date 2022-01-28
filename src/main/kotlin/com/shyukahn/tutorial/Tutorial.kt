package com.shyukahn.tutorial

import com.shyukahn.tutorial.block.ModBlocks
import com.shyukahn.tutorial.item.ModItems
import com.shyukahn.tutorial.registries.ModRegistries
import com.shyukahn.tutorial.util.ModCommandRegister
import com.shyukahn.tutorial.util.ModEventsRegister
import net.fabricmc.api.ModInitializer

@Suppress("UNUSED")
object Tutorial: ModInitializer {

    const val MOD_ID = "tutorial"

    override fun onInitialize() {

        ModItems.registerModItems()
        ModBlocks.registerModBlocks()

        ModRegistries.registerModFuels()

        ModCommandRegister.registerCommands()
        ModEventsRegister.registerEvents()

        println("Example mod has been initialized.")
    }
}