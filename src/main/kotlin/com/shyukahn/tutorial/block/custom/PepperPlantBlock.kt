package com.shyukahn.tutorial.block.custom

import com.shyukahn.tutorial.item.ModItems
import net.minecraft.block.CropBlock
import net.minecraft.item.ItemConvertible

class PepperPlantBlock(settings: Settings) : CropBlock(settings) {

    override fun getSeedsItem(): ItemConvertible {
        return ModItems.PEPPER_SEEDS
    }
}