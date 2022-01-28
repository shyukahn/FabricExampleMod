package com.shyukahn.tutorial.util

import net.minecraft.nbt.NbtCompound

interface IEntityDataSaver {
    fun getPersistentData(): NbtCompound
}