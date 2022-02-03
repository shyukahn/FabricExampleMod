package com.shyukahn.tutorial.block.entity

import com.shyukahn.tutorial.Tutorial
import com.shyukahn.tutorial.block.ModBlocks
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModBlockEntities {
    val LIGHTNING_CHANNELER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
        Identifier(Tutorial.MOD_ID, "lightning_channeler"),
        FabricBlockEntityTypeBuilder.create(::LightningChannelerBlockEntity, ModBlocks.LIGHTNING_CHANNELER_BLOCK)
            .build(null))
}