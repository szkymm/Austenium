/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jei.AusteniumJeiPlugin
 * TYPE: Java Source
 * DESCRIPTION: JEI integration for copper furnace machines.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jei;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.ModBlocks;
import com.mbb.austenium.content.item.ModItems;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * AusteniumJeiPlugin CLASS IS CORE PART OF [MBB] AUSTENIUM AusteniumJeiPlugin.java.
 *
 * com.mbb.austenium.jei.AusteniumJeiPlugin:
 *     Registers copper machines as JEI catalysts for vanilla cooking categories.
 */
@JeiPlugin
public class AusteniumJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MbbAustenium.MOD_ID, "jei");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // ---- 山铜 (Orichalcum) ----
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_ORE.get(), Component.literal("山铜矿石：钻石级采掘；方块发光亮度9；掉落粗山铜1-2（时运/精准采集）；矿石可直接烧炼/高炉成山铜锭；双峰生成 y≈±35，尾部可至65/-60"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get(), Component.literal("深板岩山铜矿石：钻石级采掘；方块发光亮度9；掉落粗山铜1-2；矿石可直接烧炼/高炉成山铜锭"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_BLOCK.get(), Component.literal("山铜块：发光亮度9；钻石镐级采集"));
        registration.addIngredientInfo(ModBlocks.RAW_ORICHALCUM_BLOCK.get(), Component.literal("粗山铜块：发光亮度9；钻石镐级采集"));
        registration.addIngredientInfo(ModItems.RAW_ORICHALCUM.get(), Component.literal("粗山铜：熔炉/高炉烧炼成山铜锭；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.ORICHALCUM_INGOT.get(), Component.literal("山铜锭：升级/装备材料；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.ORICHALCUM_NUGGET.get(), Component.literal("山铜粒：9粒=1锭；山铜链材料"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_FURNACE.get(), Component.literal("山铜熔炉：原版×10速（燃料+产物）；仅可从绿宝石熔炉升级（8山铜锭）"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_BLAST_FURNACE.get(), Component.literal("山铜高炉：原版×10速；仅可从绿宝石高炉升级；同档熔炉+铁锭环+平滑石可制"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_SMOKER.get(), Component.literal("山铜烟熏炉：原版×10速；仅可从绿宝石烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_CHEST.get(), Component.literal("山铜箱子：63槽（并排126）；容器用斧采集（钻石级）；GUI #B02E26"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_BARREL.get(), Component.literal("山铜木桶：63槽；容器用斧采集（钻石级）"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_CHAIN.get(), Component.literal("山铜链：山铜粒+山铜锭交叉合成（无链甲）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.ORICHALCUM_SHOVEL.get()), new ItemStack(ModItems.ORICHALCUM_PICKAXE.get()),
            new ItemStack(ModItems.ORICHALCUM_AXE.get()), new ItemStack(ModItems.ORICHALCUM_SWORD.get()),
            new ItemStack(ModItems.ORICHALCUM_HOE.get())),
            Component.literal("山铜工具：钻石↔下界合金属性；自带 耐久II；主题色静态辉光（无原版紫光、不发光）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.ORICHALCUM_HELMET.get()), new ItemStack(ModItems.ORICHALCUM_CHESTPLATE.get()),
            new ItemStack(ModItems.ORICHALCUM_LEGGINGS.get()), new ItemStack(ModItems.ORICHALCUM_BOOTS.get())),
            Component.literal("山铜护甲：3/7/6/3、韧性1.0；自带 保护I；主题色静态辉光（无原版紫光、不发光）"));

        // ---- 秘银 (Mythril) ----
        registration.addIngredientInfo(ModBlocks.MYTHRIL_ORE.get(), Component.literal("秘银矿石：钻石级采掘；方块发光亮度8；掉落粗秘银1-2（时运/精准采集）；双峰生成 y≈±25"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_MYTHRIL_ORE.get(), Component.literal("深板岩秘银矿石：钻石级采掘；方块发光亮度8；掉落粗秘银1-2"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_BLOCK.get(), Component.literal("秘银块：发光亮度8；钻石镐级采集"));
        registration.addIngredientInfo(ModBlocks.RAW_MYTHRIL_BLOCK.get(), Component.literal("粗秘银块：发光亮度8；钻石镐级采集；9粗秘银合成/可拆"));
        registration.addIngredientInfo(ModItems.RAW_MYTHRIL.get(), Component.literal("粗秘银：熔炉/高炉烧炼成秘银锭；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.MYTHRIL_INGOT.get(), Component.literal("秘银锭：升级/装备材料；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.MYTHRIL_NUGGET.get(), Component.literal("秘银粒：9粒=1锭；秘银链材料"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_FURNACE.get(), Component.literal("秘银熔炉：原版×12速（燃料+产物）；仅可从山铜熔炉升级（8秘银锭）"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_BLAST_FURNACE.get(), Component.literal("秘银高炉：原版×12速；仅可从山铜高炉升级；同档熔炉+铁锭环+平滑石可制"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_SMOKER.get(), Component.literal("秘银烟熏炉：原版×12速；仅可从山铜烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_CHEST.get(), Component.literal("秘银箱子：70槽（并排140）；容器用斧采集（钻石级）；GUI #8932B8"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_BARREL.get(), Component.literal("秘银木桶：70槽；容器用斧采集（钻石级）"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_CHAIN.get(), Component.literal("秘银链：秘银粒+秘银锭交叉合成（无链甲）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.MYTHRIL_SHOVEL.get()), new ItemStack(ModItems.MYTHRIL_PICKAXE.get()),
            new ItemStack(ModItems.MYTHRIL_AXE.get()), new ItemStack(ModItems.MYTHRIL_SWORD.get()),
            new ItemStack(ModItems.MYTHRIL_HOE.get())),
            Component.literal("秘银工具：下界合金属性（速度10）；自带 效率II；主题色静态辉光（无原版紫光、不发光）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.MYTHRIL_HELMET.get()), new ItemStack(ModItems.MYTHRIL_CHESTPLATE.get()),
            new ItemStack(ModItems.MYTHRIL_LEGGINGS.get()), new ItemStack(ModItems.MYTHRIL_BOOTS.get())),
            Component.literal("秘银护甲：4/8/7/4、韧性2.0；自带 保护II；主题色静态辉光（无原版紫光、不发光）"));

        // ---- 银 (Silver) ----
        registration.addIngredientInfo(ModBlocks.SILVER_ORE.get(), Component.literal("银矿石：石镐级采集；掉落粗银1-2（时运/精准采集）；矿石可直接烧炼/高炉成银锭"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_SILVER_ORE.get(), Component.literal("深板岩银矿石：石镐级采集；掉落粗银1-2；矿石可直接烧炼/高炉成银锭"));
        registration.addIngredientInfo(ModItems.RAW_SILVER.get(), Component.literal("粗银：熔炉/高炉烧炼成银锭"));
        registration.addIngredientInfo(ModItems.SILVER_INGOT.get(), Component.literal("银锭：升级/装备材料"));
        registration.addIngredientInfo(ModItems.SILVER_NUGGET.get(), Component.literal("银粒：9粒=1锭；银链材料"));
        registration.addIngredientInfo(ModBlocks.SILVER_BLOCK.get(), Component.literal("银块：铁镐级采集"));
        registration.addIngredientInfo(ModBlocks.RAW_SILVER_BLOCK.get(), Component.literal("粗银块：铁镐级采集；可拆9粗银"));
        registration.addIngredientInfo(ModBlocks.SILVER_FURNACE.get(), Component.literal("银熔炉：原版×3速；可从铁熔炉升级（8银锭）"));
        registration.addIngredientInfo(ModBlocks.SILVER_BLAST_FURNACE.get(), Component.literal("银高炉：原版×3速；同档熔炉+铁锭环+平滑石可制"));
        registration.addIngredientInfo(ModBlocks.SILVER_SMOKER.get(), Component.literal("银烟熏炉：原版×3速；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.SILVER_CHEST.get(), Component.literal("银箱子：45槽（并排90）；容器用斧采集（铁级）"));
        registration.addIngredientInfo(ModBlocks.SILVER_BARREL.get(), Component.literal("银木桶：45槽；容器用斧采集（铁级）"));
        registration.addIngredientInfo(ModBlocks.SILVER_CHAIN.get(), Component.literal("银链：银粒+银锭交叉合成"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.SILVER_SHOVEL.get()), new ItemStack(ModItems.SILVER_PICKAXE.get()),
            new ItemStack(ModItems.SILVER_AXE.get()), new ItemStack(ModItems.SILVER_SWORD.get()),
            new ItemStack(ModItems.SILVER_HOE.get())),
            Component.literal("银工具：铁+属性（耐久300、速度6.5）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.SILVER_HELMET.get()), new ItemStack(ModItems.SILVER_CHESTPLATE.get()),
            new ItemStack(ModItems.SILVER_LEGGINGS.get()), new ItemStack(ModItems.SILVER_BOOTS.get())),
            Component.literal("银护甲：3/6/5/2，铁+属性"));

        // ---- 其余档位概览 ----
        registration.addIngredientInfo(ModBlocks.COPPER_FURNACE.get(), Component.literal("铜机器：原版×1.25速"));
        registration.addIngredientInfo(ModBlocks.IRON_FURNACE.get(), Component.literal("铁机器：原版×2.5速；铁级采掘"));
        registration.addIngredientInfo(ModBlocks.GOLD_FURNACE.get(), Component.literal("金机器：原版×5速；铁级采掘"));
        registration.addIngredientInfo(ModBlocks.DIAMOND_FURNACE.get(), Component.literal("钻石机器：原版×6速；铁级采掘（钻石档=铁级）"));
        registration.addIngredientInfo(ModBlocks.EMERALD_FURNACE.get(), Component.literal("绿宝石机器：原版×8速；铁级采掘；GUI #80C71F"));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.COPPER_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.COPPER_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.COPPER_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.IRON_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.IRON_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.IRON_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.SILVER_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.SILVER_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.SILVER_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.GOLD_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.GOLD_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.GOLD_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.DIAMOND_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.DIAMOND_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.DIAMOND_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.EMERALD_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.EMERALD_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.EMERALD_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ORICHALCUM_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ORICHALCUM_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ORICHALCUM_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.MYTHRIL_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.MYTHRIL_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.MYTHRIL_SMOKER.get())));
    }
}