package com.shyukahn.tutorial.item

import com.shyukahn.tutorial.Tutorial
import com.shyukahn.tutorial.item.custom.*
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModItems {

    val RUBY = registerItem("ruby",
        Item(FabricItemSettings().group(ModItemGroup.RUBY)))

    val PEPPER = registerItem("pepper", Item(FabricItemSettings()
        .food(FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build())
        .group(ModItemGroup.RUBY)))

    val DOWSING_ROD = registerItem("dowsing_rod",
        DowsingRodItem(FabricItemSettings().group(ModItemGroup.RUBY).maxDamage(10)))

    val IRON_WOOL = registerItem("iron_wool",
        Item(FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_SWORD = registerItem("ruby_sword",
        SwordItem(ModToolMaterial.RUBY, 3, -2.4f,
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_SHOVEL = registerItem("ruby_shovel",
        ShovelItem(ModToolMaterial.RUBY, 1.5f, -3.0f,
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_PICKAXE = registerItem("ruby_pickaxe",
        ModPickaxeItem(ModToolMaterial.RUBY, 1, -2.8f,
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_AXE = registerItem("ruby_axe",
        ModAxeItem(ModToolMaterial.RUBY, 6.0f, -3.0f,
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_HOE = registerItem("ruby_hoe",
        ModHoeItem(ModToolMaterial.RUBY, -2, -1.0f,
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_HELMET = registerItem("ruby_helmet",
        ModArmorItem(ModArmorMaterial.RUBY, EquipmentSlot.HEAD,
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_CHESTPLATE = registerItem("ruby_chestplate",
        ArmorItem(ModArmorMaterial.RUBY, EquipmentSlot.CHEST,
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_LEGGINGS = registerItem("ruby_leggings",
        ArmorItem(ModArmorMaterial.RUBY, EquipmentSlot.LEGS,
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_BOOTS = registerItem("ruby_boots",
        ArmorItem(ModArmorMaterial.RUBY, EquipmentSlot.FEET,
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val RUBY_HORSE_ARMOR = registerItem("ruby_horse_armor",
        HorseArmorItem(9, "ruby",
            FabricItemSettings().group(ModItemGroup.RUBY)))

    val DATA_TABLET = registerItem("data_tablet",
        DataTabletItem(FabricItemSettings().maxCount(1).group(ModItemGroup.RUBY)))

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registry.ITEM, Identifier(Tutorial.MOD_ID, name), item)
    }

    fun registerModItems() {
        println("Registering Mod Items for " + Tutorial.MOD_ID)
    }
}