package com.shyukahn.tutorial.util

import com.shyukahn.tutorial.Tutorial
import net.fabricmc.fabric.api.tag.TagFactory
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier

object ModTags {
    object Blocks {
        val VALUABLE_BLOCKS = createTag("valuable_blocks")

        private fun createTag(name: String): Tag.Identified<Block> {
            return TagFactory.BLOCK.create(Identifier(Tutorial.MOD_ID, name))
        }

        private fun createCommonTag(name: String): Tag.Identified<Block> {
            return TagFactory.BLOCK.create(Identifier("c", name))
        }
    }

    object Items {
        val GEMS = createCommonTag("gems")
        val RUBIES = createCommonTag("rubies")

        private fun createTag(name: String): Tag.Identified<Item> {
            return TagFactory.ITEM.create(Identifier(Tutorial.MOD_ID, name))
        }

        private fun createCommonTag(name: String): Tag.Identified<Item> {
            return TagFactory.ITEM.create(Identifier("c", name))
        }
    }
}