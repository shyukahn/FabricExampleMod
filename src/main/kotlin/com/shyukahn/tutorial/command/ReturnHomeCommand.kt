package com.shyukahn.tutorial.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.shyukahn.tutorial.util.IEntityDataSaver
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import kotlin.jvm.Throws

object ReturnHomeCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>, dedicated: Boolean) {
        dispatcher.register(CommandManager.literal("home")
            .then(CommandManager.literal("return").executes(ReturnHomeCommand::run)))
    }

    @Throws(CommandSyntaxException::class)
    private fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source.player as IEntityDataSaver

        // not 0 means it contains SOMETHING
        val homepos: IntArray = player.getPersistentData().getIntArray("homepos")

        return if (homepos.isNotEmpty()) {
            val playerPos = player.getPersistentData().getIntArray("homepos")
            context.source.player.requestTeleport(playerPos[0].toDouble(), playerPos[1].toDouble(),
                playerPos[2].toDouble())

            context.source.sendFeedback(LiteralText("Player returned Home!"), true)
            1
        } else {
            context.source.sendFeedback(LiteralText("No Home Position has been Set!"), true)
            -1
        }
    }
}