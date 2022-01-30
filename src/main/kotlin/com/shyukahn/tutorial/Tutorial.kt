package com.shyukahn.tutorial

import com.shyukahn.tutorial.block.ModBlocks
import com.shyukahn.tutorial.item.ModItems
import com.shyukahn.tutorial.registries.ModRegistries
import com.shyukahn.tutorial.sounds.ModSounds
import com.shyukahn.tutorial.util.ModCommandRegister
import com.shyukahn.tutorial.util.ModEventsRegister
import com.shyukahn.tutorial.util.ModLootTableModifiers
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

        ModLootTableModifiers.modifyLootTables()
        ModSounds.registerSounds()

        println("Example mod has been initialized.")
    }
}