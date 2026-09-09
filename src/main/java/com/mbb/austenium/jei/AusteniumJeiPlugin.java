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

        // ---- 精金 (Adamantite) ----
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_ORE.get(), Component.literal("精金矿石：钻石级采掘；方块发光亮度8；掉落粗精金1-2（时运/精准采集）；矿石可直接烧炼/高炉成精金锭；双峰生成 y≈±15，尾部可至60/-60"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get(), Component.literal("深板岩精金矿石：钻石级采掘；方块发光亮度8；掉落粗精金1-2；矿石可直接烧炼/高炉成精金锭"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_BLOCK.get(), Component.literal("精金块：发光亮度8；钻石镐级采集"));
        registration.addIngredientInfo(ModBlocks.RAW_ADAMANTITE_BLOCK.get(), Component.literal("粗精金块：发光亮度8；钻石镐级采集；9粗精金合成/可拆"));
        registration.addIngredientInfo(ModItems.RAW_ADAMANTITE.get(), Component.literal("粗精金：熔炉/高炉烧炼成精金锭；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.ADAMANTITE_INGOT.get(), Component.literal("精金锭：升级/装备材料；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.ADAMANTITE_NUGGET.get(), Component.literal("精金粒：9粒=1锭；精金链材料"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_FURNACE.get(), Component.literal("精金熔炉：原版×15速（燃料+产物）；仅可从秘银熔炉升级（8精金锭）"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_BLAST_FURNACE.get(), Component.literal("精金高炉：原版×15速；仅可从秘银高炉升级；同档熔炉+铁锭环+平滑石可制"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_SMOKER.get(), Component.literal("精金烟熏炉：原版×15速；仅可从秘银烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_CHEST.get(), Component.literal("精金箱子：75槽（并排150）；容器用斧采集（钻石级）；GUI #5E7C16"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_BARREL.get(), Component.literal("精金木桶：75槽；容器用斧采集（钻石级）"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_CHAIN.get(), Component.literal("精金链：精金粒+精金锭交叉合成（无链甲）"));

        registration.addIngredientInfo(ModBlocks.NETHERITE_FURNACE.get(), Component.literal("下界合金熔炉：原版×20速（燃料+产物）；可从钻石熔炉（4下界合金锭+4钻石块）或精金熔炉（4下界合金锭）升级"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_BLAST_FURNACE.get(), Component.literal("下界合金高炉：原版×20速；可从钻石高炉或精金高炉升级；同档熔炉+5铁锭+3平滑石可制"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_SMOKER.get(), Component.literal("下界合金烟熏炉：原版×20速；可从钻石烟熏炉或精金烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_CHEST.get(), Component.literal("下界合金箱子：105槽（并排210）；容器用斧采集（钻石级）；GUI #835432"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_BARREL.get(), Component.literal("下界合金木桶：105槽；容器用斧采集（钻石级）"));
        // ---- 耀金 (Radiant) ----
        registration.addIngredientInfo(ModBlocks.RADIANT_DEBRIS.get(), Component.literal("光辉遗骸：主世界以 y=0 逐块镜像生成（正负层成对）、允许暴露空气、光照12；烧炼/高炉→耀金碎片（XP 3.5）"));
        registration.addIngredientInfo(ModItems.RADIANT_SCRAP.get(), Component.literal("耀金碎片：9个→耀金碎片堆；4碎片+山铜锭+精金锭+秘银锭+钻石+铁锭（乱序）→2耀金锭"));
        registration.addIngredientInfo(ModItems.RADIANT_INGOT.get(), Component.literal("耀金锭：9个→耀金块；4个+下界合金件→耀金机械/容器"));
        registration.addIngredientInfo(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), Component.literal("耀金升级模板：4深板岩+4平滑石+1耀金锭→1模板；模板+精金锭+7平滑石→2模板；锻造台升级下界合金装备→耀金装备"));
        registration.addIngredientInfo(ModBlocks.RADIANT_SCRAP_BLOCK.get(), Component.literal("耀金碎片堆：钻石镐级采集；9碎片↔1堆"));
        registration.addIngredientInfo(ModBlocks.RADIANT_BLOCK.get(), Component.literal("耀金块：钻石镐级采集；9耀金锭↔1块"));
        registration.addIngredientInfo(ModBlocks.RADIANT_FURNACE.get(), Component.literal("耀金熔炉：原版×25速（燃料+产物）；4耀金锭+下界合金熔炉升级"));
        registration.addIngredientInfo(ModBlocks.RADIANT_BLAST_FURNACE.get(), Component.literal("耀金高炉：原版×25速；可从下界合金高炉升级；同档熔炉+5铁锭+3平滑石可制"));
        registration.addIngredientInfo(ModBlocks.RADIANT_SMOKER.get(), Component.literal("耀金烟熏炉：原版×25速；可从下界合金烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.RADIANT_CHEST.get(), Component.literal("耀金箱子：135槽（并排270）；容器用斧采集（钻石级）；GUI #F38BAA"));
        registration.addIngredientInfo(ModBlocks.RADIANT_BARREL.get(), Component.literal("耀金木桶：135槽；容器用斧采集（钻石级）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.RADIANT_SHOVEL.get()), new ItemStack(ModItems.RADIANT_PICKAXE.get()),
            new ItemStack(ModItems.RADIANT_AXE.get()), new ItemStack(ModItems.RADIANT_SWORD.get()),
            new ItemStack(ModItems.RADIANT_HOE.get())),
            Component.literal("耀金工具：耐久2500、速度11.5；自带 效率III+时运III+耐久III+经验修补；主题色静态辉光（无原版紫光、不发光）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.RADIANT_HELMET.get()), new ItemStack(ModItems.RADIANT_CHESTPLATE.get()),
            new ItemStack(ModItems.RADIANT_LEGGINGS.get()), new ItemStack(ModItems.RADIANT_BOOTS.get())),
            Component.literal("耀金护甲：6/11/10/6、韧性3.5、击退抗性0.15；自带 保护III+耐久III+经验修补（靴子另加摔落保护III）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.ADAMANTITE_SHOVEL.get()), new ItemStack(ModItems.ADAMANTITE_PICKAXE.get()),
            new ItemStack(ModItems.ADAMANTITE_AXE.get()), new ItemStack(ModItems.ADAMANTITE_SWORD.get()),
            new ItemStack(ModItems.ADAMANTITE_HOE.get())),
            Component.literal("精金工具：耐久1900、速度10.5；自带 时运II；主题色静态辉光（无原版紫光、不发光）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.ADAMANTITE_HELMET.get()), new ItemStack(ModItems.ADAMANTITE_CHESTPLATE.get()),
            new ItemStack(ModItems.ADAMANTITE_LEGGINGS.get()), new ItemStack(ModItems.ADAMANTITE_BOOTS.get())),
            Component.literal("精金护甲：5/9/8/5、韧性2.5、击退抗性0.05；自带 保护III；主题色静态辉光（无原版紫光、不发光）"));

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

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ADAMANTITE_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ADAMANTITE_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ADAMANTITE_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.NETHERITE_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.NETHERITE_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.NETHERITE_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.RADIANT_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.RADIANT_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.RADIANT_SMOKER.get())));
    }
}