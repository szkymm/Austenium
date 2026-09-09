/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.DiamondSmokerBlock
 * TYPE: Java Source
 * DESCRIPTION: Diamond furnace-family blocks with 5x speed.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.block.entity.DiamondSmokerBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * DiamondSmokerBlock CLASS IS CORE PART OF [MBB] AUSTENIUM DiamondSmokerBlock.java.
 */
public class DiamondSmokerBlock extends AbstractFurnaceBlock {

    public static final BlockBehaviour.Properties PROPERTIES = BlockBehaviour.Properties.of()
        .mapColor(MapColor.COLOR_LIGHT_BLUE)
        .strength(1.5f, 1.5f)
        .sound(SoundType.AMETHYST).requiresCorrectToolForDrops()
        
        ;

    public DiamondSmokerBlock() {
        super(PROPERTIES);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DiamondSmokerBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == ModBlockEntities.DIAMOND_SMOKER.get()
            ? (level1, pos, state1, entity) -> DiamondSmokerBlockEntity.serverTick(level1, pos, state1, (DiamondSmokerBlockEntity) entity)
            : null;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof AbstractFurnaceBlockEntity furnace) {
            player.openMenu(furnace);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(AbstractFurnaceBlock.LIT)) {
            double x = pos.getX() + 0.5D;
            double y = pos.getY();
            double z = pos.getZ() + 0.5D;
            if (random.nextDouble() < 0.1D) {
                level.playLocalSound(x, y, z, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
            Direction direction = state.getValue(FACING);
            Direction.Axis axis = direction.getAxis();
            double d3 = random.nextDouble() * 0.6D - 0.3D;
            double d4 = axis == Direction.Axis.X ? direction.getStepX() * 0.52D : d3;
            double d5 = random.nextDouble() * 6.0D / 16.0D;
            double d6 = axis == Direction.Axis.Z ? direction.getStepZ() * 0.52D : d3;
            level.addParticle(ParticleTypes.SMOKE, x + d4, y + d5, z + d6, 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, x + d4, y + d5, z + d6, 0.0D, 0.0D, 0.0D);
        }
    }
}
