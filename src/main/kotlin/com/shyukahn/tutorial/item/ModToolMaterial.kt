package com.shyukahn.tutorial.item

import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.util.Lazy
import java.util.function.Supplier

enum class ModToolMaterial(
    private val miningLevel: Int,
    private val itemDurability: Int,
    private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int,
    _repairIngredient: Supplier<Ingredient>) : ToolMaterial {

    // Vanilla tool materials
    //
    // WOOD(MiningLevels.WOOD, 59, 2.0f, 0.0f, 15,
    //     { Ingredient.fromTag(ItemTags.PLANKS) }),
    // STONE(MiningLevels.STONE, 131, 4.0f, 1.0f, 5,
    //     { Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS) }),
    // IRON(MiningLevels.IRON, 250, 6.0f, 2.0f, 14,
    //     { Ingredient.ofItems(Items.IRON_INGOT) }),
    // DIAMOND(MiningLevels.DIAMOND, 1561, 8.0f, 3.0f, 10,
    //     { Ingredient.ofItems(Items.DIAMOND) }),
    // GOLD(MiningLevels.WOOD, 32, 12.0f, 0.0f, 22,
    //     { Ingredient.ofItems(Items.GOLD_INGOT) }),
    // NETHERITE(MiningLevels.NETHERITE, 2031, 9.0f, 4.0f, 15,
    //     { Ingredient.ofItems(Items.NETHERITE_INGOT) }),

    RUBY(MiningLevels.DIAMOND, 1500, 7.0f, 2.0f, 17,
        { Ingredient.ofItems(ModItems.RUBY) });

    private val repairIngredient = Lazy(_repairIngredient)

    override fun getMiningLevel() = miningLevel
    override fun getDurability() = itemDurability
    override fun getMiningSpeedMultiplier() = miningSpeed
    override fun getAttackDamage() = attackDamage
    override fun getEnchantability() = enchantability
    override fun getRepairIngredient():  Ingredient = repairIngredient.get()
}