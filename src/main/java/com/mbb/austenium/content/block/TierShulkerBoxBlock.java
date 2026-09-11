/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.
 * TYPE: Java Source
 * DESCRIPTION: Tier shulker box content for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.block.entity.TierShulkerBoxBlockEntity;
import com.mbb.austenium.content.menu.GenericChestMenu;
import com.mbb.austenium.content.menu.GridMenu;
import com.mbb.austenium.content.menu.IronGridMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

/**
 * TierShulkerBoxBlock CLASS IS CORE PART OF [MBB] AUSTENIUM TierShulkerBoxBlock.java.
 *
 * com.mbb.austenium.content.block.TierShulkerBoxBlock:
 *     Shulker box block for one tier; the container size, menu type and map colour
 *     come from the ShulkerTier enum so the twelve tiers share a single class.
 */
public class TierShulkerBoxBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private final ShulkerTier tier;

    /**
     * Creates the TierShulkerBoxBlock instance.
     *
     * @param tier the tier this instance belongs to
     */
    public TierShulkerBoxBlock(ShulkerTier tier) {
        super(BlockBehaviour.Properties.of()
            .mapColor(tier.mapColor())
            // Vanilla flags read from Blocks.shulkerBox (bytecode): without noOcclusion the block
            // occludes light inside its own cell, the block entity renderer is handed light 0 and the
            // box draws black in the world. The item form uses full brightness, which is why the same
            // texture looked correct in the hand and in the inventory.
            .forceSolidOn()
            .strength(2.0f)
            .dynamicShape()
            .noOcclusion()
            .isSuffocating((state, level, pos) -> false)
            .isViewBlocking((state, level, pos) -> false)
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor((state, level, pos) -> false));
        this.tier = tier;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    /**
     * Returns the tier this block or block entity belongs to.
     *
     * @return the tier of this element
     */
    public ShulkerTier getTier() {
        return this.tier;
    }

    /** {@inheritDoc} */
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    /** {@inheritDoc} */
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Vanilla asks the block entity for the opening bounding box and falls back to a full cube.
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof TierShulkerBoxBlockEntity shulker) {
            return Shapes.create(Shulker.getProgressAabb(state.getValue(FACING),
                0.5f * shulker.getProgress(1.0f)));
        }
        return Shapes.block();
    }

    /** {@inheritDoc} */
    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return this.getShape(state, level, pos, CollisionContext.empty());
    }

    /** {@inheritDoc} */
    @Override
    public InteractionResult use(BlockState state, Level level,
        BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (player.isSpectator()) {
            return InteractionResult.CONSUME;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof TierShulkerBoxBlockEntity shulkerBox) {
            // Keep the vanilla rule: a lid blocked by a solid block must not open.
            if (canOpen(state, level, pos, shulkerBox)) {
                player.openMenu((MenuProvider) shulkerBox);
                player.awardStat(Stats.OPEN_SHULKER_BOX);
                // Opening a container angers nearby piglins, exactly like the vanilla shulker box.
                PiglinAi.angerNearbyPiglins(player, true);
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    /**
     * Mirrors the vanilla rule that a shulker box cannot be opened while a solid
     * block occupies the volume its lid opens into.
     *
     * @param state the block state carrying the facing property
     * @param level the level holding the box
     * @param pos the box position
     * @param shulkerBox the block entity being opened
     * @return true when the box may be opened
     */
    private static boolean canOpen(BlockState state, Level level, BlockPos pos, TierShulkerBoxBlockEntity shulkerBox) {
        // A lid that already moved is never re-blocked, matching the vanilla animation status rule.
        if (shulkerBox.getProgress(1.0f) > 0.0f) {
            return true;
        }
        AABB openingVolume = Shulker.getProgressDeltaAabb(state.getValue(FACING), 0.0f, 0.5f)
            .move(pos)
            .deflate(1.0E-6);
        return level.noCollision(openingVolume);
    }

    /** {@inheritDoc} */
    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof TierShulkerBoxBlockEntity shulkerBox) {
            // Creative breaks never run the loot table, so hand back the filled box like vanilla does.
            if (!level.isClientSide && player.isCreative() && !shulkerBox.isEmpty()) {
                ItemStack droppedStack = new ItemStack(this);
                shulkerBox.saveToItem(droppedStack);
                if (shulkerBox.hasCustomName()) {
                    droppedStack.setHoverName(shulkerBox.getCustomName());
                }
                ItemEntity itemEntity =
                    new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, droppedStack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            } else {
                shulkerBox.unpackLootTable(player);
            }
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    /** {@inheritDoc} */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TierShulkerBoxBlockEntity(pos, state);
    }

    /**
     * Returns the getTicker value.
     *
     * @param level the level holding the block
     * @return the getTicker value
     */
    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
        BlockState state, BlockEntityType<T> type) {
        BlockEntityType<TierShulkerBoxBlockEntity> shulkerType =
            (BlockEntityType<TierShulkerBoxBlockEntity>) (BlockEntityType<?>) ModBlockEntities.TIER_SHULKER_BOX.get();
        return createTickerHelper(type, shulkerType, TierShulkerBoxBlockEntity::tick);
    }

    /** {@inheritDoc} */
    @Override
    public void setPlacedBy(Level level, BlockPos pos,
        BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (stack.hasCustomHoverName() && blockEntity instanceof TierShulkerBoxBlockEntity shulkerBox) {
            shulkerBox.setCustomName(stack.getHoverName());
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    /** {@inheritDoc} */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    /** {@inheritDoc} */
    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    /** {@inheritDoc} */
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    /** {@inheritDoc} */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    /**
     * ShulkerTier ENUM IS CORE PART OF [MBB] AUSTENIUM TierShulkerBoxBlock.java.
     *
     *     One entry per tier: id, container size, grid shape, map colour and the
     *     menu factory reused from the matching barrel.
     */
    public enum ShulkerTier {

        COPPER("copper", 36, 9, 4, MapColor.METAL),
        IRON("iron", 40, 10, 4, MapColor.METAL),
        SILVER("silver", 45, 9, 5, MapColor.METAL),
        GOLD("gold", 48, 12, 4, MapColor.METAL),
        EMERALD("emerald", 60, 12, 5, MapColor.COLOR_LIGHT_GREEN),
        DIAMOND("diamond", 50, 10, 5, MapColor.COLOR_LIGHT_BLUE),
        NETHERITE("netherite", 105, 15, 7, MapColor.COLOR_BROWN),
        ADAMANTITE("adamantite", 75, 15, 5, MapColor.COLOR_GREEN),
        MYTHRIL("mythril", 70, 14, 5, MapColor.COLOR_PURPLE),
        ORICHALCUM("orichalcum", 63, 9, 7, MapColor.COLOR_RED),
        RADIANT("radiant", 135, 15, 9, MapColor.COLOR_PINK),
        AURELIANIUM("aurelianium", 162, 9, 18, MapColor.COLOR_BLACK);

        private final String id;
        private final int containerSize;
        private final int columns;
        private final int rows;
        private final MapColor mapColor;

        ShulkerTier(String id, int containerSize, int columns, int rows, MapColor mapColor) {
            this.id = id;
            this.containerSize = containerSize;
            this.columns = columns;
            this.rows = rows;
            this.mapColor = mapColor;
        }

        /**
         * Returns the tier id used in registry names.
         *
         * @return the tier id
         */
        public String id() {
            return this.id;
        }

        /**
         * Returns the container size of this tier.
         *
         * @return the container size
         */
        public int containerSize() {
            return this.containerSize;
        }

        /**
         * Returns the container column count of this tier.
         *
         * @return the column count
         */
        public int columns() {
            return this.columns;
        }

        /**
         * Returns the container row count of this tier.
         *
         * @return the row count
         */
        public int rows() {
            return this.rows;
        }

        /**
         * Returns the map colour of this tier.
         *
         * @return the map colour
         */
        public MapColor mapColor() {
            return this.mapColor;
        }

        /**
         * Reuses the menu type of the matching barrel so the GUI texture and canvas
         * size stay identical to that tier's single container.
         */
        public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Container container) {
            if (this.columns == 9) {
                return new GenericChestMenu(ModMenuTypes.GENERIC_CHEST.get(this.rows).get(),
                    containerId, inventory, container, this.rows);
            }
            if (this.columns == 10 && this.rows == 5) {
                // The diamond box is 10x5 like the diamond barrel, so it must not borrow the 10x4 iron menu.
                return new GridMenu(ModMenuTypes.DIAMOND_10X5.get(),
                    containerId, inventory, container, this.rows, this.columns);
            }
            if (this.columns == 10) {
                return new IronGridMenu(ModMenuTypes.IRON_10X4.get(),
                    containerId, inventory, container, this.rows, this.columns);
            }
            if (this.columns == 12 && this.rows == 4) {
                return new GridMenu(ModMenuTypes.GOLD_12X4.get(),
                    containerId, inventory, container, this.rows, this.columns);
            }
            if (this.columns == 12) {
                return new GridMenu(ModMenuTypes.EMERALD_12X5.get(),
                    containerId, inventory, container, this.rows, this.columns);
            }
            if (this.columns == 14) {
                return new GridMenu(ModMenuTypes.MYTHRIL_14X5.get(),
                    containerId, inventory, container, this.rows, this.columns);
            }
            if (this.rows == 5) {
                return new GridMenu(ModMenuTypes.ADAMANTITE_15X5.get(),
                    containerId, inventory, container, this.rows, this.columns);
            }
            if (this.rows == 7) {
                return new GridMenu(ModMenuTypes.NETHERITE_15X7.get(),
                    containerId, inventory, container, this.rows, this.columns);
            }
            return new GridMenu(ModMenuTypes.RADIANT_15X9.get(),
                containerId, inventory, container, this.rows, this.columns);
        }
    }
}
