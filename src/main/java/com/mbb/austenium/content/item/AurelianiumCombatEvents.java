/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.AurelianiumCombatEvents
 * TYPE: Java Source
 * DESCRIPTION: Full-set aurelianium damage immunity against melee, projectile and explosion damage.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.item;

import com.mbb.austenium.MbbAustenium;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * AURELIANIUMCOMBATEVENTS CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumCombatEvents.java.
 *
 * com.mbb.austenium.content.item.AurelianiumCombatEvents:
 *     Wearing all four aurelianium armor pieces negates physical damage entirely;
 *     fall, fire, magic, void and starvation damage still apply.
 */
@Mod.EventBusSubscriber(modid = MbbAustenium.MOD_ID)
public final class AurelianiumCombatEvents {

    private AurelianiumCombatEvents() {}

    /**
     * Zeroes physical damage for a wearer of the complete aurelianium set.
     *
     * @param event the incoming hurt event
     */
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!isWearingFullSet(event.getEntity())) {
            return;
        }
        if (!isPhysicalDamage(event.getSource())) {
            return;
        }
        event.setAmount(0.0F);
    }

    private static boolean isWearingFullSet(LivingEntity entity) {
        // Every armor slot must hold an aurelianium piece; an empty slot fails the check.
        for (ItemStack stack : entity.getArmorSlots()) {
            if (!(stack.getItem() instanceof AurelianiumArmorItem)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPhysicalDamage(DamageSource source) {
        // Melee, projectile and explosion damage is physical; everything else keeps its effect.
        return source.is(DamageTypeTags.IS_PROJECTILE)
            || source.is(DamageTypeTags.IS_EXPLOSION)
            || source.is(DamageTypes.PLAYER_ATTACK)
            || source.is(DamageTypes.MOB_ATTACK)
            || source.is(DamageTypes.MOB_ATTACK_NO_AGGRO);
    }
}
