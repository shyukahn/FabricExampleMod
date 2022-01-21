package com.shyukahn.tutorial.item.custom

import com.google.common.collect.ImmutableMap
import com.shyukahn.tutorial.item.ModArmorMaterial
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import java.util.*

class ModArmorItem(
    material: ArmorMaterial,
    slot: EquipmentSlot,
    settings: Settings) : ArmorItem(material, slot, settings) {

    companion object {
        private val MATERIAL_TO_EFFECT_MAP =
            ImmutableMap.Builder<ArmorMaterial, StatusEffect>()
            .put(ModArmorMaterial.RUBY, StatusEffects.INVISIBILITY)
            .build() as Map<ArmorMaterial, StatusEffect>
    }

    override fun inventoryTick(stack: ItemStack?, world: World, entity: Entity?, slot: Int, selected: Boolean) {
        if (!world.isClient) {
            if (entity is PlayerEntity) {
                if (hasFullSuitOfArmorOn(entity)) {
                    evaluateArmorEffects(entity)
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    private fun evaluateArmorEffects(player: PlayerEntity) {
        for (entry in MATERIAL_TO_EFFECT_MAP.entries) {
            val mapArmorMaterial = entry.key
            val mapStatusEffect = entry.value

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect)
            }
        }
    }

    private fun addStatusEffectForMaterial(player: PlayerEntity,
                                           mapArmorMaterial: ArmorMaterial,
                                           mapStatusEffect: StatusEffect) {
        val hasPlayerEffect = player.hasStatusEffect(mapStatusEffect)

        if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addStatusEffect(StatusEffectInstance(mapStatusEffect, 200))

            if (Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
                player.inventory.damageArmor(DamageSource.MAGIC, 1f, intArrayOf(0, 1, 2, 3))
            }
        }
    }

    private fun hasFullSuitOfArmorOn(player: PlayerEntity): Boolean {
        val boots: ItemStack = player.inventory.getArmorStack(0)
        val leggings: ItemStack = player.inventory.getArmorStack(1)
        val chestplate: ItemStack = player.inventory.getArmorStack(2)
        val helmet: ItemStack = player.inventory.getArmorStack(3)

        return !helmet.isEmpty && !chestplate.isEmpty && !leggings.isEmpty && !boots.isEmpty
    }

    private fun hasCorrectArmorOn(material: ArmorMaterial, player: PlayerEntity): Boolean {
        val boots = player.inventory.getArmorStack(0).item as ArmorItem
        val leggings = player.inventory.getArmorStack(1).item as ArmorItem
        val chestplate = player.inventory.getArmorStack(2).item as ArmorItem
        val helmet = player.inventory.getArmorStack(3).item as ArmorItem

        return helmet.material == material && chestplate.material == material &&
                leggings.material == material && boots.material == material
    }
}