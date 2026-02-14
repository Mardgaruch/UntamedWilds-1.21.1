package net.minecraftforge.common;

import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ForgeHooks {
    public static boolean onCropsGrowPre(Level world, BlockPos pos, BlockState state, boolean flag) {
        return true;
    }

    public static void onCropsGrowPost(Level world, BlockPos pos, BlockState state) {
    }
}
