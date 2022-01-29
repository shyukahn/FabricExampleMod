package com.shyukahn.tutorial.util

import com.shyukahn.tutorial.item.ModItems
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier

object ModLootTableModifiers {
    private val GRASS_BLOCK_ID = Identifier("minecraft", "blocks/grass")
    private val IGLOO_STRUCTURE_CHEST_ID = Identifier("minecraft", "chests/igloo_chest")

    fun modifyLootTables() {
        LootTableLoadingCallback.EVENT.register { _, _, id, supplier, _ ->
            // check for leaves loot table
            if (GRASS_BLOCK_ID == id) {
                // Adds Pepper Seeds to the grass loot table
                val poolBuilder: FabricLootPoolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .conditionally(RandomChanceLootCondition.builder(0.35f)) // Drops 35% of the time
                    .with(ItemEntry.builder(ModItems.PEPPER_SEEDS))
                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build())
                supplier.withPool(poolBuilder.build())
            }

            if (IGLOO_STRUCTURE_CHEST_ID == id) {
                // Adds a Dowsing Rod into the Igloo chest with 75% chance
                val poolBuilder: FabricLootPoolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .conditionally(RandomChanceLootCondition.builder(1.0f)) // Drops 100% of the time
                    .with(ItemEntry.builder(ModItems.DOWSING_ROD))
                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build())
                supplier.withPool(poolBuilder.build())
            }
        }
    }
}