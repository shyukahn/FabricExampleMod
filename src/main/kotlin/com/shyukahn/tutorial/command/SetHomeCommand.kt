package com.shyukahn.tutorial.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.shyukahn.tutorial.util.IEntityDataSaver
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.util.math.BlockPos
import kotlin.jvm.Throws

object SetHomeCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>, dedicated: Boolean) {
        dispatcher.register(CommandManager.literal("home")
            .then(CommandManager.literal("set").executes(SetHomeCommand::run)))
    }

    @Throws(CommandSyntaxException::class)
    private fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source.player as IEntityDataSaver
        val playerPos: BlockPos = context.source.player.blockPos
        val pos = "(${playerPos.x}, ${playerPos.y}, ${playerPos.z})"

        player.getPersistentData().putIntArray("homepos", intArrayOf(playerPos.x, playerPos.y, playerPos.z))

        context.source.sendFeedback(LiteralText("Set home at $pos"), true)
        return 1
    }
}