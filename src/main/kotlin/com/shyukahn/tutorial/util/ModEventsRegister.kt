package com.shyukahn.tutorial.util

import com.shyukahn.tutorial.events.PlayerEvents
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents

object ModEventsRegister {
    fun registerEvents() {
        ServerPlayerEvents.COPY_FROM.register(PlayerEvents())
    }
}