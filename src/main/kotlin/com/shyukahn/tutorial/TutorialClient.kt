package com.shyukahn.tutorial

import com.shyukahn.tutorial.util.ModRenderHelper
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object TutorialClient : ClientModInitializer {
    override fun onInitializeClient() {
        ModRenderHelper.setRenderLayers()
    }
}