package com.shyukahn.tutorial.block

import com.shyukahn.tutorial.Tutorial
import com.shyukahn.tutorial.block.custom.*
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

    val RUBY_FENCE = registerBlock("ruby_fence",
        FenceBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool()))

    val RUBY_FENCE_GATE = registerBlock("ruby_fence_gate",
        FenceGateBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool()))

    val RUBY_BUTTON = registerBlock("ruby_button",
        ModStoneButtonBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool()))

    val RUBY_PRESSURE_PLATE = registerBlock("ruby_pressure_plate",
        ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
            FabricBlockSettings.of(Material.STONE).strength(6.0f).requiresTool()))

    val RUBY_DOOR = registerBlock("ruby_door",
        ModDoorBlock(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool().nonOpaque()))

    val RUBY_TRAPDOOR = registerBlock("ruby_trapdoor",
        ModTrapdoorBlock(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool().nonOpaque()))

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