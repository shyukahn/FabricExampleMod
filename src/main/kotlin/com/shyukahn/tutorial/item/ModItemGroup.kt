package com.shyukahn.tutorial.item

import com.shyukahn.tutorial.Tutorial
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object ModItemGroup {
    val RUBY: ItemGroup = FabricItemGroupBuilder.build(Identifier(Tutorial.MOD_ID, "ruby"))
        { ItemStack(ModItems.RUBY) }
}