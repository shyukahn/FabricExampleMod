package com.shyukahn.tutorial.screen

import com.shyukahn.tutorial.Tutorial
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier

object ModScreenHandlers {
    val LIGHTNING_CHANNELER_SCREEN_HANDLER: ScreenHandlerType<LightningChannelerScreenHandler> = ScreenHandlerRegistry
        .registerSimple(Identifier(Tutorial.MOD_ID, "lightning_channeler"), ::LightningChannelerScreenHandler)
}