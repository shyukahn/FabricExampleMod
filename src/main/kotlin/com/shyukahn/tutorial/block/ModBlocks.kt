package com.shyukahn.tutorial.block

import com.shyukahn.tutorial.Tutorial
import com.shyukahn.tutorial.block.custom.ModStairsBlock
import com.shyukahn.tutorial.block.custom.StatusBlock
import com.shyukahn.tutorial.item.ModItemGroup
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.SlabBlock
import net.minecraft.item.BlockItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModBlocks {

    val RUBY_ORE = Block(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool())
    val RUBY_BLOCK = Block(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool())
    val RUBY_STAIRS = ModStairsBlock(RUBY_BLOCK.defaultState,
        FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool())
    val RUBY_SLAB = SlabBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool())

    val STATUS_BLOCK = StatusBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool())

    private fun registerBlock(name: String, block: Block) {
        registerBlockItem(name, block)
        Registry.register(Registry.BLOCK, Identifier(Tutorial.MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block) {
        Registry.register(Registry.ITEM, Identifier(Tutorial.MOD_ID, name),
            BlockItem(block, FabricItemSettings().group(ModItemGroup.RUBY)))
    }

    fun registerModBlocks() {
        println("Registering Mod Blocks for " + Tutorial.MOD_ID)

        registerBlock("ruby_ore", RUBY_ORE)
        registerBlock("ruby_block", RUBY_BLOCK)
        registerBlock("ruby_stairs", RUBY_STAIRS)
        registerBlock("ruby_slab", RUBY_SLAB)
        registerBlock("status_block", STATUS_BLOCK)
    }
}