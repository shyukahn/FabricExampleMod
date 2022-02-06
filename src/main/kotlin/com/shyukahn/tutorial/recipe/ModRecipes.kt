package com.shyukahn.tutorial.recipe

import com.shyukahn.tutorial.Tutorial
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModRecipes {
    fun register() {
        Registry.register(Registry.RECIPE_SERIALIZER,
            Identifier(Tutorial.MOD_ID, LightningChannelerRecipe.Serializer.ID),
            LightningChannelerRecipe.Serializer.INSTANCE)

        Registry.register(Registry.RECIPE_TYPE,
            Identifier(Tutorial.MOD_ID, LightningChannelerRecipe.Type.ID),
            LightningChannelerRecipe.Type.INSTANCE)
    }
}