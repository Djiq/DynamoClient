package group.societyproject.dynamoclient.util.filters;

import group.societyproject.dynamoclient.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;


public class CrystalPlaceFilter extends BlocksListFilter {
    @Override
    public boolean Filter(BlockPos block) {
        Minecraft localMc = Reference.mc;
        BlockPos oneHigher = new BlockPos(block.getX(),block.getY() + 1,block.getZ());
        BlockPos twoHigher = new BlockPos(block.getX(),block.getY() + 1,block.getZ());
        return (localMc.world.getBlockState(block).getBlock() == Blocks.OBSIDIAN || localMc.world.getBlockState(block).getBlock() == Blocks.BEDROCK) && (localMc.world.getBlockState(oneHigher).getBlock() == Blocks.AIR) && (localMc.world.getBlockState(twoHigher).getBlock() == Blocks.AIR) && localMc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(oneHigher)).isEmpty() && localMc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(twoHigher)).isEmpty();
    }
}
