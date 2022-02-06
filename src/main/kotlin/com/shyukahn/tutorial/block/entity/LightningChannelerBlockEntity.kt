package com.shyukahn.tutorial.block.entity

import com.shyukahn.tutorial.item.inventory.ImplementedInventory
import com.shyukahn.tutorial.recipe.LightningChannelerRecipe
import com.shyukahn.tutorial.screen.LightningChannelerScreenHandler
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*


class LightningChannelerBlockEntity(pos: BlockPos, state: BlockState)
    : BlockEntity(ModBlockEntities.LIGHTNING_CHANNELER_BLOCK_ENTITY, pos, state),
    NamedScreenHandlerFactory, ImplementedInventory {

    companion object {
        fun tick(world: World, pos: BlockPos, state: BlockState, entity: LightningChannelerBlockEntity) {
            if (hasRecipe(entity)) {
                entity.progress++
                if (entity.progress > entity.maxProgress) {
                    craftItem(entity)
                }
            } else {
                entity.resetProgress()
            }
        }

        private fun craftItem(entity: LightningChannelerBlockEntity) {
            val world = entity.world
            val inventory = SimpleInventory(entity.inventory.size)
            for (i in 0 until entity.inventory.size) {
                inventory.setStack(i, entity.getStack(i))
            }

            val match: Optional<LightningChannelerRecipe> = world!!.recipeManager
                .getFirstMatch(LightningChannelerRecipe.Type.INSTANCE, inventory, world)

            if (match.isPresent) {
                entity.removeStack(0, 1)
                entity.removeStack(1, 1)
                entity.setStack(2, ItemStack(match.get().output.item, entity.getStack(2).count + 1))

                if (!world.isClient() && (match.get().getWeather() == LightningChannelerRecipe.Weather.THUNDERING)) {
                    EntityType.LIGHTNING_BOLT.spawn(
                        world as ServerWorld?, null, null, null, entity.pos,
                        SpawnReason.TRIGGERED, true, true
                    )
                }
                entity.resetProgress()
            }
        }

        private fun hasRecipe(entity: LightningChannelerBlockEntity): Boolean {
            val world = entity.world
            val inventory = SimpleInventory(entity.inventory.size)
            for (i in 0 until entity.inventory.size) {
                inventory.setStack(i, entity.getStack(i))
            }

            val match: Optional<LightningChannelerRecipe> = world!!.recipeManager
                .getFirstMatch(LightningChannelerRecipe.Type.INSTANCE, inventory, world)

            return (match.isPresent && evaluateWeather(match.get().getWeather(), world)
                    && canInsertAmountIntoOutputSlot(inventory)
                    && canInsertItemIntoOutputSlot(inventory, match.get().output))
        }

        private fun canInsertItemIntoOutputSlot(inventory: SimpleInventory, output: ItemStack): Boolean {
            return inventory.getStack(2).isEmpty || inventory.getStack(2).item == output.item
        }

        private fun canInsertAmountIntoOutputSlot(inventory: SimpleInventory): Boolean {
            return inventory.getStack(2).maxCount > inventory.getStack(2).count
        }

        private fun evaluateWeather(weather: LightningChannelerRecipe.Weather, world: World): Boolean {
            return when {
                weather == LightningChannelerRecipe.Weather.CLEAR && !world.isRaining -> true
                weather == LightningChannelerRecipe.Weather.RAIN && world.isRaining -> true
                weather == LightningChannelerRecipe.Weather.THUNDERING && world.isThundering -> true
                else -> false
            }
        }
    }

    private val inventory: DefaultedList<ItemStack> = DefaultedList.ofSize(3, ItemStack.EMPTY)
    private var progress = 0
    // How many ticks it will take to craft the item (divide by twenty to get seconds count)
    // In our case this should be even divisible by 21 as that's our pixel count for our progress arrow
    private var maxProgress = 63

    private val propertyDelegate: PropertyDelegate = object : PropertyDelegate {
        override fun get(index: Int): Int {
            return when (index) {
                0 -> progress
                1 -> maxProgress
                else -> 0
            }
        }

        override fun set(index: Int, value: Int) {
            when (index) {
                0 -> progress = value
                1 -> maxProgress = value
            }
        }

        override fun size(): Int = 2
    }

    fun resetProgress() {
        this.progress = 0
    }

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity?): ScreenHandler {
        return LightningChannelerScreenHandler(syncId, inv, this, propertyDelegate)
    }

    override fun getDisplayName() = LiteralText("Lightning Channeler") as Text

    override fun getItems() = inventory

    override fun markDirty() {
        super<BlockEntity>.markDirty()
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, inventory)
    }
}