package net.minecraftforge.common;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;

import java.util.List;

public interface IForgeShearable {
    List<ItemStack> onSheared(ItemStack item, Level world, BlockPos pos, int fortune);
    boolean isShearable(ItemStack item, Level world, BlockPos pos);
}
