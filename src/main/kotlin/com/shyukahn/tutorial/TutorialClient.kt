package com.shyukahn.tutorial

import com.shyukahn.tutorial.screen.LightningChannelerScreen
import com.shyukahn.tutorial.screen.ModScreenHandlers
import com.shyukahn.tutorial.util.ModRenderHelper
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry

@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object TutorialClient : ClientModInitializer {
    override fun onInitializeClient() {
        ModRenderHelper.setRenderLayers()
        ScreenRegistry.register(ModScreenHandlers.LIGHTNING_CHANNELER_SCREEN_HANDLER, ::LightningChannelerScreen)
    }
}