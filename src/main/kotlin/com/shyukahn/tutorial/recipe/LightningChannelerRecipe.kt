package com.shyukahn.tutorial.recipe

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.*
import net.minecraft.util.Identifier
import net.minecraft.util.JsonHelper
import net.minecraft.util.collection.DefaultedList
import net.minecraft.world.World

class LightningChannelerRecipe(
    private val id: Identifier,
    private val output: ItemStack,
    private val recipeItems: DefaultedList<Ingredient>,
    private val weather: Weather
    ) : Recipe<SimpleInventory> {

    enum class Weather {
        CLEAR,
        RAIN,
        THUNDERING;

        companion object {
            fun getWeatherByString(s: String): Weather {
                return when (s) {
                    "thundering" -> THUNDERING
                    "rain" -> RAIN
                    else -> CLEAR
                }
            }
        }
    }

    class Serializer : RecipeSerializer<LightningChannelerRecipe> {
        companion object {
            val INSTANCE = Serializer()
            val ID = "lightning"
        }

        override fun read(id: Identifier, json: JsonObject): LightningChannelerRecipe {
            val output: ItemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"))
            val weather: String = JsonHelper.getString(json,"weather")

            val ingredients: JsonArray = JsonHelper.getArray(json, "ingredients")
            val inputs: DefaultedList<Ingredient> = DefaultedList.ofSize(2, Ingredient.EMPTY)

            for (i in 0 until inputs.size) {
                inputs[i] = Ingredient.fromJson(ingredients[i])
            }

            return LightningChannelerRecipe(id, output, inputs, Weather.getWeatherByString(weather))
        }

        override fun read(id: Identifier, buf: PacketByteBuf): LightningChannelerRecipe {
            val inputs: DefaultedList<Ingredient> = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY)

            for (i in 0 until inputs.size) {
                inputs[i] = Ingredient.fromPacket(buf)
            }

            val output: ItemStack = buf.readItemStack()
            val weather: Weather = buf.readEnumConstant(Weather::class.java)

            return LightningChannelerRecipe(id, output, inputs, weather)
        }

        override fun write(buf: PacketByteBuf, recipe: LightningChannelerRecipe) {
            buf.writeInt(recipe.ingredients.size)
            for (ing in recipe.ingredients) {
                ing.write(buf)
            }
            buf.writeItemStack(recipe.getOutput())
                .writeEnumConstant(recipe.weather)
        }

    }

    class Type private constructor() : RecipeType<LightningChannelerRecipe> {
        companion object {
            val INSTANCE = Type()
            val ID = "lightning"
        }
    }

    fun getWeather() = weather

    override fun matches(inventory: SimpleInventory, world: World?): Boolean {
        return recipeItems[0].test(inventory.getStack(0)) && recipeItems[1].test(inventory.getStack(1))
    }

    override fun craft(inventory: SimpleInventory?) = output

    override fun fits(width: Int, height: Int): Boolean {
        return true
    }

    override fun getOutput(): ItemStack = output.copy()

    override fun getId() = id

    override fun getSerializer(): RecipeSerializer<*> {
        return Serializer.INSTANCE
    }

    override fun getType(): RecipeType<*> {
        return Type.INSTANCE
    }
}