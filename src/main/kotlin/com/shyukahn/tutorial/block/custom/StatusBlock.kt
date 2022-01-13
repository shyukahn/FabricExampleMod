package com.shyukahn.tutorial.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.LiteralText
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class StatusBlock(settings: Settings) : Block(settings) {
    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult {
        if (!world?.isClient()!!) {
            if (hand == Hand.MAIN_HAND) {
                val playerBlockPos: BlockPos = player!!.blockPos
                player.sendMessage(LiteralText("SERVER: You are " + player.displayName.string + " at Position (" +
                        playerBlockPos.x + ", " + playerBlockPos.y + ", " + playerBlockPos.z + ")"), false)
            }
        } else {
            if (hand == Hand.MAIN_HAND) {
                player!!.sendMessage(LiteralText("CLIENT: This is the CLIENT! MAIN HAND!"), false)
            } else {
                player!!.sendMessage(LiteralText("CLIENT: This is the CLIENT! OFF HAND!"), false)
            }
        }

        return super.onUse(state, world, pos, player, hand, hit)
    }
}