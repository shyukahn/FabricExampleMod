package com.shyukahn.tutorial.block

import com.shyukahn.tutorial.Tutorial
import com.shyukahn.tutorial.block.custom.ModStairsBlock
import com.shyukahn.tutorial.block.custom.StatusBlock
import com.shyukahn.tutorial.item.ModItemGroup
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModBlocks {

    val RUBY_ORE = registerBlock("ruby_ore",
        Block(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool()))

    val RUBY_BLOCK = registerBlock("ruby_block",
        Block(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool()))

    val RUBY_STAIRS = registerBlock("ruby_stairs",
        ModStairsBlock(RUBY_BLOCK.defaultState,
            FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool()))

    val RUBY_SLAB = registerBlock("ruby_slab",
        SlabBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool()))

    val STATUS_BLOCK = registerBlock("status_block",
        StatusBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool()))

    private fun registerBlock(name: String, block: Block): Block {
        registerBlockItem(name, block)
        return Registry.register(Registry.BLOCK, Identifier(Tutorial.MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block): Item {
        return Registry.register(Registry.ITEM, Identifier(Tutorial.MOD_ID, name),
            BlockItem(block, FabricItemSettings().group(ModItemGroup.RUBY)))
    }

    fun registerModBlocks() {
        println("Registering Mod Blocks for " + Tutorial.MOD_ID)
    }
}