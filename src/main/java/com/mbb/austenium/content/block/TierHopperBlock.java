package com.mbb.austenium.content.block;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.block.entity.TierHopperBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

/** Tier hopper block; the spike registers the copper tier only. */
public class TierHopperBlock extends HopperBlock {

    private final HopperTier tier;

    /**
     * Creates the TierHopperBlock instance.
     *
     * @param tier the tier this instance belongs to
     */
    public TierHopperBlock(HopperTier tier) {
        super(BlockBehaviour.Properties.of()
            .mapColor(tier.mapColor())
            .strength(3.0f, 4.8f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL)
            .noOcclusion());
        this.tier = tier;
    }

    /**
     * Returns the tier this block belongs to.
     * @return the tier of this block
     */
    public HopperTier getTier() {
        return this.tier;
    }

    /** {@inheritDoc} */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TierHopperBlockEntity(pos, state);
    }

    /**
     * Returns the getTicker value.
     *
     * @param level the level holding the block
     * @param state the block state
     * @return the getTicker value
     */
    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        BlockEntityType<TierHopperBlockEntity> hopperType =
            (BlockEntityType<TierHopperBlockEntity>) (BlockEntityType<?>) ModBlockEntities.TIER_HOPPER.get();
        return createTickerHelper(type, hopperType, TierHopperBlockEntity::tick);
    }

    /** {@inheritDoc} */
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof TierHopperBlockEntity hopper) {
            player.openMenu(hopper);
            player.awardStat(Stats.INSPECT_HOPPER);
        }
        return InteractionResult.CONSUME;
    }

    /** {@inheritDoc} */
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
                            ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof TierHopperBlockEntity hopper) {
                hopper.setCustomName(stack.getHoverName());
            }
        }
    }

    /** One entry per tier: id, cooldown ticks, items per activation, map colour. */
    public enum HopperTier {

        COPPER("copper", 6, 1, MapColor.METAL),
        IRON("iron", 4, 1, MapColor.METAL),
        SILVER("silver", 3, 1, MapColor.METAL),
        GOLD("gold", 5, 2, MapColor.METAL),
        DIAMOND("diamond", 2, 1, MapColor.COLOR_LIGHT_BLUE),
        EMERALD("emerald", 5, 3, MapColor.COLOR_LIGHT_GREEN),
        ORICHALCUM("orichalcum", 4, 3, MapColor.COLOR_RED),
        MYTHRIL("mythril", 1, 1, MapColor.COLOR_PURPLE),
        ADAMANTITE("adamantite", 2, 3, MapColor.COLOR_GREEN),
        NETHERITE("netherite", 1, 2, MapColor.COLOR_BROWN),
        RADIANT("radiant", 1, 3, MapColor.COLOR_PINK),
        AURELIANIUM("aurelianium", 1, 5, MapColor.COLOR_BLACK);

        private final String id;
        private final int cooldownTicks;
        private final int itemsPerActivation;
        private final MapColor mapColor;

        HopperTier(String id, int cooldownTicks, int itemsPerActivation, MapColor mapColor) {
            this.id = id;
            this.cooldownTicks = cooldownTicks;
            this.itemsPerActivation = itemsPerActivation;
            this.mapColor = mapColor;
        }

        /**
         * Returns the registry id of this tier, used in block, item and texture names.
         * @return the lowercase tier id
         */
        public String id() {
            return this.id;
        }

        /**
         * Returns the cooldown between two activations, in ticks.
         * @return the cooldown in ticks
         */
        public int cooldownTicks() {
            return this.cooldownTicks;
        }

        /**
         * Returns how many items one activation moves.
         * @return the items moved per activation
         */
        public int itemsPerActivation() {
            return this.itemsPerActivation;
        }

        /**
         * Returns the map colour of this tier.
         * @return the map colour
         */
        public MapColor mapColor() {
            return this.mapColor;
        }
    }
}
