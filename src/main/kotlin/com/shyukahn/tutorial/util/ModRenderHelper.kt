package com.shyukahn.tutorial.util

import com.shyukahn.tutorial.block.ModBlocks
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer

object ModRenderHelper {
    fun setRenderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_DOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_TRAPDOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEPPER_PLANT, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUEBELLS, RenderLayer.getCutout())
    }
}