package com.shyukahn.tutorial

import com.shyukahn.tutorial.block.ModBlocks
import com.shyukahn.tutorial.item.ModItems
import net.fabricmc.api.ModInitializer

@Suppress("UNUSED")
object Tutorial: ModInitializer {

    const val MOD_ID = "tutorial"

    override fun onInitialize() {

        ModItems.registerModItems()
        ModBlocks.registerModBlocks()

        println("Example mod has been initialized.")
    }
}