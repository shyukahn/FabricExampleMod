package com.shyukahn.tutorial.screen

import com.mojang.blaze3d.systems.RenderSystem
import com.shyukahn.tutorial.Tutorial
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier


class LightningChannelerScreen(handler: LightningChannelerScreenHandler, inventory: PlayerInventory, title: Text)
    : HandledScreen<LightningChannelerScreenHandler>(handler, inventory, title) {

    companion object {
        val TEXTURE = Identifier(Tutorial.MOD_ID, "textures/gui/lightning_channeler_gui.png")
    }

    override fun init() {
        super.init()
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader { GameRenderer.getPositionTexShader() }
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)

        if (handler.isLightningStorm()) {
            this.drawTexture(matrices, x + 26, y + 31, 176, 0, 28, 36)
        }
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
    }
}