package com.shyukahn.tutorial.item.custom

import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class DataTabletItem(settings: Settings) : Item(settings) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (user.getStackInHand(hand).hasNbt()) {
            user.getStackInHand(hand).nbt = NbtCompound()
        }

        return super.use(world, user, hand)
    }

    override fun hasGlint(stack: ItemStack): Boolean {
        return stack.hasNbt()
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        if (stack.hasNbt()) {
            val currentOre = stack.nbt!!.getString("Last Found Ore")
            tooltip.add(LiteralText(currentOre))
        }
    }
}