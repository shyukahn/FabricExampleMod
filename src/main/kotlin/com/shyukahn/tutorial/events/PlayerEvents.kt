package com.shyukahn.tutorial.events

import com.shyukahn.tutorial.util.IEntityDataSaver
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.minecraft.server.network.ServerPlayerEntity

class PlayerEvents : ServerPlayerEvents.CopyFrom {
    override fun copyFromPlayer(oldPlayer: ServerPlayerEntity?, newPlayer: ServerPlayerEntity?, alive: Boolean) {
        val original = oldPlayer as IEntityDataSaver
        val player = newPlayer as IEntityDataSaver

        player.getPersistentData().putIntArray("homepos", original.getPersistentData().getIntArray("homepos"))
    }
}