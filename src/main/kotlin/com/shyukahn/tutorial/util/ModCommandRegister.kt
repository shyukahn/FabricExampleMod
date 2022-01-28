package com.shyukahn.tutorial.util

import com.shyukahn.tutorial.command.ReturnHomeCommand
import com.shyukahn.tutorial.command.SetHomeCommand
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback

object ModCommandRegister {
    fun registerCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register)
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register)
    }
}