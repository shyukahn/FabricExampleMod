package com.shyukahn.tutorial.item.custom

import com.shyukahn.tutorial.item.ModItems
import com.shyukahn.tutorial.util.ModTags
import net.minecraft.block.Block
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class DowsingRodItem(settings: Settings) : Item(settings) {

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        if (context?.world?.isClient() == true) {
            context.player?.let {
                val player = it
                val positionClicked: BlockPos = context.blockPos
                var foundBlock = false

                for (i in 0..positionClicked.y + 64) {
                    val blockBelow: Block = context.world.getBlockState(positionClicked.down(i)).block

                    if (isValuableBlock(blockBelow)) {
                        outputValuableCoordinates(blockBelow, positionClicked.add(0, -i, 0), player)
                        foundBlock = true

                        if (hasPlayerEmptyDataTablet(player)) {
                            addNbtToDataTablet(player, positionClicked.add(0, -i, 0), blockBelow)
                        }

                        break
                    }
                }
                if (!foundBlock) {
                    player.sendMessage(LiteralText("Didn't find any valuable below!"), false)
                }
            }
        }

        context?.stack?.damage(1, context.player) {
            it?.sendToolBreakStatus(it.activeHand)
        }

        return super.useOnBlock(context)
    }

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        if (Screen.hasShiftDown()) {
            tooltip?.add(TranslatableText("tooltip.tutorial.dowsing_rod_shift"))
        } else {
            tooltip?.add(TranslatableText("tooltip.tutorial.dowsing_rod"))
        }
        super.appendTooltip(stack, world, tooltip, context)
    }

    private fun addNbtToDataTablet(player: PlayerEntity, pos: BlockPos, blockBelow: Block) {
        val dataTablet: ItemStack = player.inventory.getStack(getFirstInventoryIndexWithoutNbt(player, ModItems.DATA_TABLET))
        val nbtData = NbtCompound()
        nbtData.putString("Last Found Ore", "Found " + blockBelow.asItem().name.string + " at (" +
                pos.x + ", " + pos.y + ", " + pos.z + ")")

        dataTablet.nbt = nbtData
    }

    private fun isValuableBlock(block: Block): Boolean {
        return block.defaultState.isIn(ModTags.Blocks.VALUABLE_BLOCKS)
    }

    private fun outputValuableCoordinates(blockFound: Block, pos: BlockPos, player: PlayerEntity) {
        player.sendMessage(LiteralText("Found " + blockFound.asItem().name.string + " at (" +
        pos.x + ", " + pos.y + ", " + pos.z + ")"), false)
    }

    private fun hasPlayerEmptyDataTablet(player: PlayerEntity): Boolean {
        return hasPlayerStackInInventoryWithoutNbt(player, ModItems.DATA_TABLET)
    }

    private fun hasPlayerStackInInventoryWithoutNbt(player: PlayerEntity, item: Item): Boolean {
        return getFirstInventoryIndexWithoutNbt(player, item) >= 0
    }

    private fun getFirstInventoryIndexWithoutNbt(player: PlayerEntity, item: Item): Int {
        for (i in 0 until player.inventory.size()) {
            val currentStack: ItemStack = player.inventory.getStack(i)
            if (!currentStack.isEmpty && currentStack.isItemEqual(ItemStack(item)) && !currentStack.hasNbt()) {
                return i
            }
        }
        return -1
    }
}