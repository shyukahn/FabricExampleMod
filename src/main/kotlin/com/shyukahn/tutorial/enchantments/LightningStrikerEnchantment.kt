package com.shyukahn.tutorial.enchantments

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos

class LightningStrikerEnchantment(
    weight: Rarity,
    type: EnchantmentTarget,
    vararg slotTypes: EquipmentSlot
) : Enchantment(weight, type, slotTypes) {

    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (!user.world.isClient()) {
            val world = user.world as ServerWorld
            val player = user as PlayerEntity
            val position: BlockPos = target.blockPos

            for (i in 1..level) {
                EntityType.LIGHTNING_BOLT.spawn(world, null, null, player, position,
                    SpawnReason.TRIGGERED, true, true)
            }
        }

        super.onTargetDamaged(user, target, level)
    }

    override fun getMaxLevel() = 2
}