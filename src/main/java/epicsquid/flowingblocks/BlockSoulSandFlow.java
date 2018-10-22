package epicsquid.flowingblocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BlockSoulSandFlow extends BlockFluidSolid {

  public BlockSoulSandFlow(String modid, String name, boolean addToTab, Fluid fluid, MapColor mapcolor, int quanta) {
    super(modid, name, addToTab, fluid, mapcolor, quanta);
  }

  public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    entityIn.motionX *= 0.4D;
    entityIn.motionZ *= 0.4D;
  }

}
