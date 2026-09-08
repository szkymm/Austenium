/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jei;

import com.mbb.austenium.content.block.ModBlocks;
import com.mbb.austenium.content.item.ModItems;

import jeresources.api.IJERAPI;
import jeresources.api.IJERPlugin;
import jeresources.api.IWorldGenRegistry;
import jeresources.api.JERPlugin;
import jeresources.api.distributions.DistributionBase;
import jeresources.api.distributions.DistributionCustom;
import jeresources.api.distributions.DistributionHelpers;
import jeresources.api.drop.LootDrop;
import jeresources.api.restrictions.Restriction;

import net.minecraft.world.item.ItemStack;

@JERPlugin
public class AusteniumJerPlugin implements IJERPlugin {

    @Override
    public void receive(IJERAPI api) {
        IWorldGenRegistry worldgen = api.getWorldGenRegistry();

        // 山铜：双三角峰 y≈35 与 y≈-35（各半跨度30，尾部 65/-60），峰≈原版金、衰减快
        float[] upper = DistributionHelpers.getTriangularDistribution(5, 30, 1.0f);
        float[] lower = DistributionHelpers.getTriangularDistribution(-65, 30, 1.0f);
        DistributionBase orichalcum = new DistributionCustom(DistributionHelpers.addDistribution(upper, lower));
        worldgen.register(
            new ItemStack(ModBlocks.ORICHALCUM_ORE.get()),
            new ItemStack(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get()),
            orichalcum, Restriction.OVERWORLD,
            new LootDrop(ModItems.RAW_ORICHALCUM.get(), 1, 2, 1.0f));

        // 银：上下两带（-24..56 与 80..384，近似现有银矿分布）
        float[] silverLow = DistributionHelpers.getTriangularDistribution(-24, 40, 1.0f);
        float[] silverHigh = DistributionHelpers.getTriangularDistribution(80, 152, 1.0f);
        DistributionBase silver = new DistributionCustom(DistributionHelpers.addDistribution(silverLow, silverHigh));
        worldgen.register(
            new ItemStack(ModBlocks.SILVER_ORE.get()),
            new ItemStack(ModBlocks.DEEPSLATE_SILVER_ORE.get()),
            silver, Restriction.OVERWORLD,
            new LootDrop(ModItems.RAW_SILVER.get(), 1, 2, 1.0f));

        // 秘银：双三角峰 y≈25 与 y≈-25（各半跨度20），高于山铜频率、衰减更快
        float[] mythrilUpper = DistributionHelpers.getTriangularDistribution(5, 20, 1.0f);
        float[] mythrilLower = DistributionHelpers.getTriangularDistribution(-45, 20, 1.0f);
        DistributionBase mythril = new DistributionCustom(DistributionHelpers.addDistribution(mythrilUpper, mythrilLower));
        worldgen.register(
            new ItemStack(ModBlocks.MYTHRIL_ORE.get()),
            new ItemStack(ModBlocks.DEEPSLATE_MYTHRIL_ORE.get()),
            mythril, Restriction.OVERWORLD,
            new LootDrop(ModItems.RAW_MYTHRIL.get(), 1, 2, 1.0f));
    }
}
