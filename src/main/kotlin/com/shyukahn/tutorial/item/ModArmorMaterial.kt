package com.shyukahn.tutorial.item

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Lazy
import java.util.function.Supplier

enum class ModArmorMaterial(
    private val _name: String,
    private val durabilityMultiplier: Int,
    private val protectionAmounts: IntArray,
    private val enchantability: Int,
    private val equipSound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    _repairIngredientSupplier: Supplier<Ingredient>) : ArmorMaterial {

    // Vanilla armor materials
    //
    // LEATHER("leather", 5, intArrayOf(1, 2, 3, 1), 15,
    //     SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f,
    //     { Ingredient.ofItems(Items.LEATHER) }),
    // CHAIN("chainmail", 15, intArrayOf(1, 4, 5, 2), 12,
    //     SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.0f,
    //     { Ingredient.ofItems(Items.IRON_INGOT) }),
    // IRON("iron", 15, intArrayOf(2, 5, 6, 2), 9,
    //     SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f,
    //     { Ingredient.ofItems(Items.IRON_INGOT) }),
    // GOLD("gold", 7, intArrayOf(1, 3, 5, 2), 25,
    //     SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f, 0.0f,
    //     { Ingredient.ofItems(Items.GOLD_INGOT) }),
    // DIAMOND("diamond", 33, intArrayOf(3, 6, 8, 3), 10,
    //     SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f, 0.0f,
    //     { Ingredient.ofItems(Items.DIAMOND) }),
    // TURTLE("turtle", 25, intArrayOf(2, 5, 6, 2), 9,
    //     SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0f, 0.0f,
    //     { Ingredient.ofItems(Items.SCUTE) }),
    // NETHERITE("netherite", 37, intArrayOf(3, 6, 8, 3), 15,
    //     SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0f, 0.1f,
    //     { Ingredient.ofItems(Items.NETHERITE_INGOT) }),

    RUBY("ruby", 27, intArrayOf(3, 6, 8, 3), 18,
        SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0f, 0.0f,
        { Ingredient.ofItems(ModItems.RUBY) });

    private val repairIngredientSupplier = Lazy(_repairIngredientSupplier)

    companion object {
        private val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    }

    override fun getName() = _name
    override fun getDurability(slot: EquipmentSlot) = BASE_DURABILITY[slot.entitySlotId] * durabilityMultiplier
    override fun getProtectionAmount(slot: EquipmentSlot) = protectionAmounts[slot.entitySlotId]
    override fun getEnchantability() = enchantability
    override fun getEquipSound() = equipSound
    override fun getToughness() = toughness
    override fun getKnockbackResistance() = knockbackResistance
    override fun getRepairIngredient(): Ingredient = repairIngredientSupplier.get()
}