package epicsquid.flowingblocks;

import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidHardening extends BlockFluidSolid {

  IBlockState toHarden = null;

  public BlockFluidHardening(String modid, String name, boolean addToTab, Fluid fluid, MapColor mapcolor, int quanta, IBlockState toHarden) {
    super(modid, name, addToTab, fluid, mapcolor, quanta);
    this.toHarden = toHarden;
    this.setTickRandomly(true);
    this.needsRandomTick = true;
  }

  int getMin(int minLevel, IBlockState state) {
    if (state.getBlock() == this)
      return Math.min(minLevel, state.getValue(LEVEL));
    return minLevel;
  }

  public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
    if (rand.nextInt(5) == 0 && state.getBlock() == this) {
      if (state.getValue(LEVEL) == this.quantaPerBlock - 1 || world.getBlockState(pos.east()).getBlock() != this
          || world.getBlockState(pos.west()).getBlock() != this || world.getBlockState(pos.north()).getBlock() != this
          || world.getBlockState(pos.south()).getBlock() != this) {
        world.setBlockState(pos, toHarden, 8);
        world.notifyBlockUpdate(pos, state, toHarden, 8);
        if (world.getBlockState(pos.east()).getBlock() == this) {
          world.scheduleUpdate(pos.east(), this, this.tickRate * (rand.nextInt(8) + 4));
        }
        if (world.getBlockState(pos.west()).getBlock() == this) {
          world.scheduleUpdate(pos.west(), this, this.tickRate * (rand.nextInt(8) + 4));
        }
        if (world.getBlockState(pos.north()).getBlock() == this) {
          world.scheduleUpdate(pos.north(), this, this.tickRate * (rand.nextInt(8) + 4));
        }
        if (world.getBlockState(pos.south()).getBlock() == this) {
          world.scheduleUpdate(pos.south(), this, this.tickRate * (rand.nextInt(8) + 4));
        }
      }
    }
  }

  public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
    super.updateTick(world, pos, state, rand);
    if (state.getBlock() == this) {
      if (world.getBlockState(pos.east()).getBlock() == toHarden.getBlock() || world.getBlockState(pos.west()).getBlock() == toHarden.getBlock()
          || world.getBlockState(pos.north()).getBlock() == toHarden.getBlock() || world.getBlockState(pos.south()).getBlock() == toHarden.getBlock()) {
        world.setBlockState(pos, toHarden, 8);
        world.notifyBlockUpdate(pos, state, toHarden, 8);
        if (world.getBlockState(pos.east()).getBlock() == this) {
          world.scheduleUpdate(pos.east(), this, this.tickRate * (rand.nextInt(8) + 4));
        }
        if (world.getBlockState(pos.west()).getBlock() == this) {
          world.scheduleUpdate(pos.west(), this, this.tickRate * (rand.nextInt(8) + 4));
        }
        if (world.getBlockState(pos.north()).getBlock() == this) {
          world.scheduleUpdate(pos.north(), this, this.tickRate * (rand.nextInt(8) + 4));
        }
        if (world.getBlockState(pos.south()).getBlock() == this) {
          world.scheduleUpdate(pos.south(), this, this.tickRate * (rand.nextInt(8) + 4));
        }
      }
    }
  }
}
