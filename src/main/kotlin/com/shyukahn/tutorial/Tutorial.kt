package com.shyukahn.tutorial

import net.fabricmc.api.ModInitializer

@Suppress("UNUSED")
object Tutorial: ModInitializer {

    private const val MOD_ID = "tutorial"

    override fun onInitialize() {
        println("Example mod has been initialized.")
    }
}