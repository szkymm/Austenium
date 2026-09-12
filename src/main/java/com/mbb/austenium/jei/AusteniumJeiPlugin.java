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
import net.minecraft.world.level.block.Block;
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

    /** {@inheritDoc} */
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "jei");
    }

    /** {@inheritDoc} */
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        addHopperInfo(registration, ModBlocks.COPPER_HOPPER.get(), "铜", "3.3", 6, 1);
        addHopperInfo(registration, ModBlocks.IRON_HOPPER.get(), "铁", "5.0", 4, 1);
        addHopperInfo(registration, ModBlocks.SILVER_HOPPER.get(), "银", "6.7", 3, 1);
        addHopperInfo(registration, ModBlocks.GOLD_HOPPER.get(), "金", "8.0", 5, 2);
        addHopperInfo(registration, ModBlocks.DIAMOND_HOPPER.get(), "钻石", "10", 2, 1);
        addHopperInfo(registration, ModBlocks.EMERALD_HOPPER.get(), "绿宝石", "12", 5, 3);
        addHopperInfo(registration, ModBlocks.ORICHALCUM_HOPPER.get(), "山铜", "15", 4, 3);
        addHopperInfo(registration, ModBlocks.MYTHRIL_HOPPER.get(), "秘银", "20", 1, 1);
        addHopperInfo(registration, ModBlocks.ADAMANTITE_HOPPER.get(), "精金", "30", 2, 3);
        addHopperInfo(registration, ModBlocks.NETHERITE_HOPPER.get(), "下界合金", "40", 1, 2);
        addHopperInfo(registration, ModBlocks.RADIANT_HOPPER.get(), "耀金", "60", 1, 3);
        addHopperInfo(registration, ModBlocks.AURELIANIUM_HOPPER.get(), "奥雷利亚尼姆", "100", 1, 5);
        // ---- Austeniumcraft World (AW, 0.beta.6) ----
        registration.addIngredientInfo(ModItems.AUSTENIUMCRAFT_PORTAL.get(),
            Component.literal("奥氏挖矿维度（Austeniumcraft World）：y -64..383 的实心分层世界"),
            Component.literal("分层 -64 基岩 / -63..-1 深板岩 / 0..256 石头 / 257..354 下界岩 / 355..383 末地石；全维度无水无岩浆"),
            Component.literal("矿物按带压缩：主世界带分段压缩、下界带把下界 0..127 压进 257..354、末地带把英雄躯骸压进 355..383"),
            Component.literal("干枯噪声洞穴约原版一半密度；主世界敌怪四带都刷，蝙蝠在深板岩与石头带，末影人在末地带"),
            Component.literal("传送门：12 种档位块按末地门式摆放（5x5 去四角），左键敲奥雷利亚尼姆块激活；只连主世界，坐标 1:1，AW 侧 y>320 回主世界落 y=317"));
        // ---- 山铜 (Orichalcum) ----
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_ORE.get(),
            Component.literal("山铜矿石：钻石级采掘；方块发光亮度9；掉落粗山铜1-2（时运/精准采集）；矿石可直接烧炼/高炉成山铜锭；双峰生成 y≈±35，尾部可至65/-60"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get(),
            Component.literal("深板岩山铜矿石：钻石级采掘；方块发光亮度9；掉落粗山铜1-2；矿石可直接烧炼/高炉成山铜锭"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_BLOCK.get(), Component.literal("山铜块：发光亮度9；钻石镐级采集"));
        registration.addIngredientInfo(ModBlocks.RAW_ORICHALCUM_BLOCK.get(), Component.literal("粗山铜块：发光亮度9；钻石镐级采集"));
        registration.addIngredientInfo(ModItems.RAW_ORICHALCUM.get(), Component.literal("粗山铜：熔炉/高炉烧炼成山铜锭；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.ORICHALCUM_INGOT.get(), Component.literal("山铜锭：升级/装备材料；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.ORICHALCUM_NUGGET.get(), Component.literal("山铜粒：9粒=1锭；山铜链材料"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_FURNACE.get(),
            Component.literal("山铜熔炉：原版×10速（燃料+产物）；仅可从绿宝石熔炉升级（8山铜锭）"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_BLAST_FURNACE.get(),
            Component.literal("山铜高炉：原版×10速；仅可从绿宝石高炉升级；同档熔炉+铁锭环+平滑石可制"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_SMOKER.get(),
            Component.literal("山铜烟熏炉：原版×10速；仅可从绿宝石烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_CHEST.get(),
            Component.literal("山铜箱子：63槽（并排126）；容器用斧采集（钻石级）；GUI #B02E26"));
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
        registration.addIngredientInfo(ModBlocks.MYTHRIL_ORE.get(),
            Component.literal("秘银矿石：钻石级采掘；方块发光亮度8；掉落粗秘银1-2（时运/精准采集）；双峰生成 y≈±25"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_MYTHRIL_ORE.get(),
            Component.literal("深板岩秘银矿石：钻石级采掘；方块发光亮度8；掉落粗秘银1-2"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_BLOCK.get(), Component.literal("秘银块：发光亮度8；钻石镐级采集"));
        registration.addIngredientInfo(ModBlocks.RAW_MYTHRIL_BLOCK.get(),
            Component.literal("粗秘银块：发光亮度8；钻石镐级采集；9粗秘银合成/可拆"));
        registration.addIngredientInfo(ModItems.RAW_MYTHRIL.get(), Component.literal("粗秘银：熔炉/高炉烧炼成秘银锭；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.MYTHRIL_INGOT.get(), Component.literal("秘银锭：升级/装备材料；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.MYTHRIL_NUGGET.get(), Component.literal("秘银粒：9粒=1锭；秘银链材料"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_FURNACE.get(),
            Component.literal("秘银熔炉：原版×12速（燃料+产物）；仅可从山铜熔炉升级（8秘银锭）"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_BLAST_FURNACE.get(),
            Component.literal("秘银高炉：原版×12速；仅可从山铜高炉升级；同档熔炉+铁锭环+平滑石可制"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_SMOKER.get(),
            Component.literal("秘银烟熏炉：原版×12速；仅可从山铜烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_CHEST.get(),
            Component.literal("秘银箱子：70槽（并排140）；容器用斧采集（钻石级）；GUI #8932B8"));
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
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_ORE.get(),
            Component.literal("精金矿石：钻石级采掘；方块发光亮度8；掉落粗精金1-2（时运/精准采集）；矿石可直接烧炼/高炉成精金锭；双峰生成 y≈±15，尾部可至60/-60"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get(),
            Component.literal("深板岩精金矿石：钻石级采掘；方块发光亮度8；掉落粗精金1-2；矿石可直接烧炼/高炉成精金锭"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_BLOCK.get(), Component.literal("精金块：发光亮度8；钻石镐级采集"));
        registration.addIngredientInfo(ModBlocks.RAW_ADAMANTITE_BLOCK.get(),
            Component.literal("粗精金块：发光亮度8；钻石镐级采集；9粗精金合成/可拆"));
        registration.addIngredientInfo(ModItems.RAW_ADAMANTITE.get(), Component.literal("粗精金：熔炉/高炉烧炼成精金锭；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.ADAMANTITE_INGOT.get(), Component.literal("精金锭：升级/装备材料；物品带主题色静态辉光"));
        registration.addIngredientInfo(ModItems.ADAMANTITE_NUGGET.get(), Component.literal("精金粒：9粒=1锭；精金链材料"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_FURNACE.get(),
            Component.literal("精金熔炉：原版×15速（燃料+产物）；仅可从秘银熔炉升级（8精金锭）"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_BLAST_FURNACE.get(),
            Component.literal("精金高炉：原版×15速；仅可从秘银高炉升级；同档熔炉+铁锭环+平滑石可制"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_SMOKER.get(),
            Component.literal("精金烟熏炉：原版×15速；仅可从秘银烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_CHEST.get(),
            Component.literal("精金箱子：75槽（并排150）；容器用斧采集（钻石级）；GUI #5E7C16"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_BARREL.get(), Component.literal("精金木桶：75槽；容器用斧采集（钻石级）"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_CHAIN.get(), Component.literal("精金链：精金粒+精金锭交叉合成（无链甲）"));

        registration.addIngredientInfo(ModBlocks.NETHERITE_FURNACE.get(),
            Component.literal("下界合金熔炉：原版×20速（燃料+产物）；可从钻石熔炉（4下界合金锭+4钻石块）或精金熔炉（4下界合金锭）升级"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_BLAST_FURNACE.get(),
            Component.literal("下界合金高炉：原版×20速；可从钻石高炉或精金高炉升级；同档熔炉+5铁锭+3平滑石可制"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_SMOKER.get(),
            Component.literal("下界合金烟熏炉：原版×20速；可从钻石烟熏炉或精金烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_CHEST.get(),
            Component.literal("下界合金箱子：105槽（并排210）；容器用斧采集（钻石级）；GUI #835432"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_BARREL.get(), Component.literal("下界合金木桶：105槽；容器用斧采集（钻石级）"));
        // ---- 耀金 (Radiant) ----
        registration.addIngredientInfo(ModBlocks.RADIANT_DEBRIS.get(),
            Component.literal("光辉遗骸：主世界以 y=0 逐块镜像生成（正负层成对）、允许暴露空气、光照12；烧炼/高炉→耀金碎片（XP 3.5）"));
        registration.addIngredientInfo(ModItems.RADIANT_SCRAP.get(),
            Component.literal("耀金碎片：9个→耀金碎片堆；4碎片+山铜锭+精金锭+秘银锭+钻石+铁锭（乱序）→2耀金锭"));
        registration.addIngredientInfo(ModItems.RADIANT_INGOT.get(), Component.literal("耀金锭：9个→耀金块；4个+下界合金件→耀金机械/容器"));
        registration.addIngredientInfo(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(),
            Component.literal("耀金升级模板：4深板岩+4平滑石+1耀金锭→1模板；模板+精金锭+7平滑石→2模板；锻造台升级下界合金装备→耀金装备"));
        registration.addIngredientInfo(ModBlocks.RADIANT_SCRAP_BLOCK.get(), Component.literal("耀金碎片堆：钻石镐级采集；9碎片↔1堆"));
        registration.addIngredientInfo(ModBlocks.RADIANT_BLOCK.get(), Component.literal("耀金块：钻石镐级采集；9耀金锭↔1块"));
        registration.addIngredientInfo(ModBlocks.RADIANT_FURNACE.get(),
            Component.literal("耀金熔炉：原版×25速（燃料+产物）；4耀金锭+下界合金熔炉升级"));
        registration.addIngredientInfo(ModBlocks.RADIANT_BLAST_FURNACE.get(),
            Component.literal("耀金高炉：原版×25速；可从下界合金高炉升级；同档熔炉+5铁锭+3平滑石可制"));
        registration.addIngredientInfo(ModBlocks.RADIANT_SMOKER.get(),
            Component.literal("耀金烟熏炉：原版×25速；可从下界合金烟熏炉升级；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.RADIANT_CHEST.get(),
            Component.literal("耀金箱子：135槽（并排270）；容器用斧采集（钻石级）；GUI #F38BAA"));
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
        registration.addIngredientInfo(ModBlocks.SILVER_ORE.get(),
            Component.literal("银矿石：石镐级采集；掉落粗银1-2（时运/精准采集）；矿石可直接烧炼/高炉成银锭"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_SILVER_ORE.get(),
            Component.literal("深板岩银矿石：石镐级采集；掉落粗银1-2；矿石可直接烧炼/高炉成银锭"));
        registration.addIngredientInfo(ModItems.RAW_SILVER.get(), Component.literal("粗银：熔炉/高炉烧炼成银锭"));
        registration.addIngredientInfo(ModItems.SILVER_INGOT.get(), Component.literal("银锭：升级/装备材料"));
        registration.addIngredientInfo(ModItems.SILVER_NUGGET.get(), Component.literal("银粒：9粒=1锭；银链材料"));
        registration.addIngredientInfo(ModBlocks.SILVER_BLOCK.get(), Component.literal("银块：铁镐级采集"));
        registration.addIngredientInfo(ModBlocks.RAW_SILVER_BLOCK.get(), Component.literal("粗银块：铁镐级采集；可拆9粗银"));
        registration.addIngredientInfo(ModBlocks.SILVER_FURNACE.get(), Component.literal("银熔炉：原版×3速；可从铁熔炉升级（8银锭）"));
        registration.addIngredientInfo(ModBlocks.SILVER_BLAST_FURNACE.get(),
            Component.literal("银高炉：原版×3速；同档熔炉+铁锭环+平滑石可制"));
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

        // ---- 铜档 ----
        registration.addIngredientInfo(ModBlocks.COPPER_FURNACE.get(),
            Component.literal("铜熔炉：原版×1.25速（200→160 ticks）；仅可从原版熔炉升级"));
        registration.addIngredientInfo(ModBlocks.COPPER_BLAST_FURNACE.get(),
            Component.literal("铜高炉：原版×1.25速（100→80 ticks）"));
        registration.addIngredientInfo(ModBlocks.COPPER_SMOKER.get(), Component.literal("铜烟熏炉：原版×1.25速（100→80 ticks）"));
        registration.addIngredientInfo(ModBlocks.COPPER_CHEST.get(), Component.literal("铜箱子：36槽（并排72）；容器用斧采集，无挖掘门控"));
        registration.addIngredientInfo(ModBlocks.COPPER_BARREL.get(), Component.literal("铜木桶：36槽；容器用斧采集，无挖掘门控"));
        registration.addIngredientInfo(ModItems.COPPER_NUGGET.get(), Component.literal("铜粒：9粒=1铜锭；铜链材料"));
        registration.addIngredientInfo(ModBlocks.COPPER_CHAIN.get(), Component.literal("铜链：铜粒+铜锭交叉合成（无链甲）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.COPPER_SWORD.get()), new ItemStack(ModItems.COPPER_PICKAXE.get()),
            new ItemStack(ModItems.COPPER_AXE.get()), new ItemStack(ModItems.COPPER_SHOVEL.get()),
            new ItemStack(ModItems.COPPER_HOE.get())),
            Component.literal("铜工具：耐久210、速度5.5、攻击+1.5、等级1"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.COPPER_HELMET.get()), new ItemStack(ModItems.COPPER_CHESTPLATE.get()),
            new ItemStack(ModItems.COPPER_LEGGINGS.get()), new ItemStack(ModItems.COPPER_BOOTS.get())),
            Component.literal("铜护甲：2/5/4/1"));

        // ---- 铁档 ----
        registration.addIngredientInfo(ModBlocks.IRON_FURNACE.get(),
            Component.literal("铁熔炉：原版×2.5速（200→80 ticks）；仅可从铜熔炉升级"));
        registration.addIngredientInfo(ModBlocks.IRON_BLAST_FURNACE.get(),
            Component.literal("铁高炉：原版×2.5速（100→40 ticks）；同档熔炉+5铁锭+3平滑石可制"));
        registration.addIngredientInfo(ModBlocks.IRON_SMOKER.get(),
            Component.literal("铁烟熏炉：原版×2.5速（100→40 ticks）；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.IRON_CHEST.get(), Component.literal("铁箱子：40槽（并排80）；容器用斧采集（铁级）"));
        registration.addIngredientInfo(ModBlocks.IRON_BARREL.get(), Component.literal("铁木桶：40槽；容器用斧采集（铁级）"));

        // ---- 金档 ----
        registration.addIngredientInfo(ModBlocks.GOLD_FURNACE.get(),
            Component.literal("金熔炉：原版×5速（200→40 ticks）；可从银熔炉或铁熔炉升级"));
        registration.addIngredientInfo(ModBlocks.GOLD_BLAST_FURNACE.get(),
            Component.literal("金高炉：原版×5速（100→20 ticks）；同档熔炉+5铁锭+3平滑石可制"));
        registration.addIngredientInfo(ModBlocks.GOLD_SMOKER.get(),
            Component.literal("金烟熏炉：原版×5速（100→20 ticks）；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.GOLD_CHEST.get(), Component.literal("金箱子：48槽（并排96）；容器用斧采集（铁级）"));
        registration.addIngredientInfo(ModBlocks.GOLD_BARREL.get(), Component.literal("金木桶：48槽；容器用斧采集（铁级）"));
        registration.addIngredientInfo(ModBlocks.GOLD_CHAIN.get(), Component.literal("金链：金粒+金锭交叉合成（无链甲）"));

        // ---- 钻石档 ----
        registration.addIngredientInfo(ModBlocks.DIAMOND_FURNACE.get(),
            Component.literal("钻石熔炉：原版×6速（200→33 ticks）；仅可从金熔炉升级"));
        registration.addIngredientInfo(ModBlocks.DIAMOND_BLAST_FURNACE.get(),
            Component.literal("钻石高炉：原版×6速（100→16 ticks）；同档熔炉+5铁锭+3平滑石可制"));
        registration.addIngredientInfo(ModBlocks.DIAMOND_SMOKER.get(),
            Component.literal("钻石烟熏炉：原版×6速（100→16 ticks）；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.DIAMOND_CHEST.get(), Component.literal("钻石箱子：50槽（并排100）；容器用斧采集（铁级）"));
        registration.addIngredientInfo(ModBlocks.DIAMOND_BARREL.get(), Component.literal("钻石木桶：50槽；容器用斧采集（铁级）"));

        // ---- 绿宝石档 ----
        registration.addIngredientInfo(ModBlocks.EMERALD_FURNACE.get(),
            Component.literal("绿宝石熔炉：原版×8速（200→25 ticks）；仅可从钻石熔炉升级；GUI #80C71F"));
        registration.addIngredientInfo(ModBlocks.EMERALD_BLAST_FURNACE.get(),
            Component.literal("绿宝石高炉：原版×8速（100→12 ticks）；同档熔炉+5铁锭+3平滑石可制"));
        registration.addIngredientInfo(ModBlocks.EMERALD_SMOKER.get(),
            Component.literal("绿宝石烟熏炉：原版×8速（100→12 ticks）；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.EMERALD_CHEST.get(), Component.literal("绿宝石箱子：60槽（并排120）；容器用斧采集（铁级）"));
        registration.addIngredientInfo(ModBlocks.EMERALD_BARREL.get(), Component.literal("绿宝石木桶：60槽；容器用斧采集（铁级）"));

        // ---- 奥雷利亚尼姆（末地顶级档） ----
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_DEBRIS.get(),
            Component.literal("英雄躯骸：末地生成（正态分布 y 19..60，μ39.5/σ7.5）；自发光15级；掉落自身1个（不受时运影响）；熔炉/高炉→碎片（XP 4.0）"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_SCRAP_BLOCK.get(),
            Component.literal("奥雷利亚尼姆碎片堆：发光15级；9碎片合成/可拆"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_BLOCK.get(), Component.literal("奥雷利亚尼姆块：发光15级；钻石镐级采集"));
        registration.addIngredientInfo(ModItems.AURELIANIUM_SCRAP.get(),
            Component.literal("奥雷利亚尼姆碎片：4碎片+耀金锭+精金锭+秘银锭+山铜锭+金锭（乱序）→3锭"));
        registration.addIngredientInfo(ModItems.AURELIANIUM_INGOT.get(),
            Component.literal("奥雷利亚尼姆锭：升级/装备材料；物品防火，附魔光效"));
        registration.addIngredientInfo(ModItems.AURELIANIUM_UPGRADE_SMITHING_TEMPLATE.get(),
            Component.literal("奥雷利亚尼姆升级模板：4黑曜石+4末地石+1锭；复制=模板+耀金锭+7末地石→4个"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_FURNACE.get(),
            Component.literal("奥雷利亚尼姆熔炉：原版×50速（200→4 ticks）；仅可从耀金熔炉升级（XNX/NPN/XNX = 4锭+耀金熔炉）"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_BLAST_FURNACE.get(),
            Component.literal("奥雷利亚尼姆高炉：原版×50速（100→2 ticks）；同档熔炉+5铁锭+3平滑石可制"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_SMOKER.get(),
            Component.literal("奥雷利亚尼姆烟熏炉：原版×50速（100→2 ticks）；同档熔炉+4原木十字可制"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_CHEST.get(),
            Component.literal("奥雷利亚尼姆箱子：162槽（并排324）；容器用斧采集（钻石级）；GUI #1D1D21"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_BARREL.get(),
            Component.literal("奥雷利亚尼姆木桶：162槽；容器用斧采集（钻石级）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.AURELIANIUM_SWORD.get()), new ItemStack(ModItems.AURELIANIUM_PICKAXE.get()),
            new ItemStack(ModItems.AURELIANIUM_AXE.get()), new ItemStack(ModItems.AURELIANIUM_SHOVEL.get()),
            new ItemStack(ModItems.AURELIANIUM_HOE.get())),
            Component.literal("奥雷利亚尼姆工具：耐久4000、速度16.0、攻击+8.0；自带效率5/时运5/耐久5/经验修补；时运5可用精准采集书在铁砧替换（5级，其余附魔与耐久保留）"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.AURELIANIUM_HELMET.get()), new ItemStack(ModItems.AURELIANIUM_CHESTPLATE.get()),
            new ItemStack(ModItems.AURELIANIUM_LEGGINGS.get()), new ItemStack(ModItems.AURELIANIUM_BOOTS.get())),
            Component.literal("奥雷利亚尼姆护甲：9/15/14/9、韧性6.0、击退抗性0.2；自带保护6/耐久6/经验修补，靴子+摔落保护6；全套4件免疫近战/弹射物/爆炸伤害"));
    }


    /**
     * Adds the shared tier hopper description to one hopper entry.
     *
     * @param registration the JEI registration handle
     * @param block the tier hopper block shown in JEI
     * @param tierName the tier name that opens the text
     * @param rate the transfer rate in items per second, already formatted
     * @param cooldownTicks the cooldown between two activations, in ticks
     * @param batch the number of items moved per activation
     */
    private static void addHopperInfo(IRecipeRegistration registration, Block block, String tierName,
        String rate, int cooldownTicks, int batch) {
        registration.addIngredientInfo(block, Component.literal(tierName + "漏斗：" + rate + "件/秒（冷却"
            + cooldownTicks + " tick，每次搬" + batch + "件）；5 格；从上方容器/掉落物吸取；链式升级而来"));
    }

    /** {@inheritDoc} */
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

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.AURELIANIUM_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.AURELIANIUM_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.AURELIANIUM_SMOKER.get())));
    }
}