package com.shyukahn.tutorial.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot
import net.minecraft.world.World

class LightningChannelerScreenHandler(syncId: Int, playerInventory: PlayerInventory, private val inventory: Inventory)
    : ScreenHandler(ModScreenHandlers.LIGHTNING_CHANNELER_SCREEN_HANDLER, syncId) {

    private val world: World = playerInventory.player.world

    init {
        checkSize(inventory, 3)
        inventory.onOpen(playerInventory.player)

        // Our Slots
        this.addSlot(Slot(inventory, 0, 80, 31))
        this.addSlot(Slot(inventory, 1, 80, 53))
        this.addSlot(Slot(inventory, 2, 123, 42))

        addPlayerInventory(playerInventory)
        addPlayerHotbar(playerInventory)
    }

    constructor(syncId: Int, playerInventory: PlayerInventory) : this(syncId, playerInventory, SimpleInventory(3))

    fun isLightningStorm(): Boolean {
        return world.isThundering
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return this.inventory.canPlayerUse(player)
    }

    override fun transferSlot(player: PlayerEntity, invSlot: Int): ItemStack {
        var newStack: ItemStack = ItemStack.EMPTY
        val slot = slots[invSlot]
        if (slot.hasStack()) {
            val originalStack = slot.stack
            newStack = originalStack.copy()
            if (invSlot < inventory.size()) {
                if (!insertItem(originalStack, inventory.size(), slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!insertItem(originalStack, 0, inventory.size(), false)) {
                return ItemStack.EMPTY
            }
            if (originalStack.isEmpty) {
                slot.stack = ItemStack.EMPTY
            } else {
                slot.markDirty()
            }
        }
        return newStack
    }

    private fun addPlayerInventory(playerInventory: PlayerInventory) {
        for (i in 0 until 3) {
            for (j in 0 until 9) {
                this.addSlot(Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18))
            }
        }
    }

    private fun addPlayerHotbar(playerInventory: PlayerInventory) {
        for (i in 0 until 9) {
            this.addSlot(Slot(playerInventory, i, 8 + i * 18, 144))
        }
    }
}