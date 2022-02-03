package com.shyukahn.tutorial.block.entity

import com.shyukahn.tutorial.item.ModItems
import com.shyukahn.tutorial.item.inventory.ImplementedInventory
import com.shyukahn.tutorial.screen.LightningChannelerScreenHandler
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class LightningChannelerBlockEntity(pos: BlockPos, state: BlockState)
    : BlockEntity(ModBlockEntities.LIGHTNING_CHANNELER_BLOCK_ENTITY, pos, state),
    NamedScreenHandlerFactory, ImplementedInventory {

    companion object {

        fun tick(world: World, pos: BlockPos, state: BlockState, entity: LightningChannelerBlockEntity) {
            if (hasRecipe(entity) && world.isThundering && hasNotReachedStackLimit(entity)) {
                craftItem(entity)

                if (!world.isClient) {
                    EntityType.LIGHTNING_BOLT.spawn(world as ServerWorld, null, null, null, pos,
                        SpawnReason.TRIGGERED, true, true)
                }
            }
        }

        private fun craftItem(entity: LightningChannelerBlockEntity) {
            entity.removeStack(0, 1)
            entity.removeStack(1, 1)
            entity.setStack(2, ItemStack(ModItems.RUBY, entity.getStack(2).count + 1))
        }

        private fun hasRecipe(entity: LightningChannelerBlockEntity): Boolean {
            val hasItemInFirstSlot = entity.getStack(0).item == ModItems.IRON_WOOL
            val hasItemInSecondSlot = entity.getStack(1).item == ModItems.PEPPER

            return hasItemInFirstSlot && hasItemInSecondSlot
        }

        private fun hasNotReachedStackLimit(entity: LightningChannelerBlockEntity): Boolean {
            return entity.getStack(2).count < entity.getStack(2).maxCount
        }
    }

    private val inventory: DefaultedList<ItemStack> = DefaultedList.ofSize(3, ItemStack.EMPTY)

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity?): ScreenHandler {
        return LightningChannelerScreenHandler(syncId, inv, this)
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