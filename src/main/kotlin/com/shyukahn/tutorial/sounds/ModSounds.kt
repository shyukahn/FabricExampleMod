package com.shyukahn.tutorial.sounds

import com.shyukahn.tutorial.Tutorial
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModSounds {

    val SMALL_EXPLOSION = registerSoundEvent("small_explosion")

    private fun registerSoundEvent(name: String): SoundEvent {
        val id = Identifier(Tutorial.MOD_ID, name)
        return Registry.register(Registry.SOUND_EVENT, id, SoundEvent(id))
    }

    fun registerSounds() {
        println("Registering ModSounds for ${Tutorial.MOD_ID}")
    }
}