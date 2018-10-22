package epicsquid.flowingblocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BlockSlimeFlow extends BlockFluidSolid {

  public BlockSlimeFlow(String modid, String name, boolean addToTab, Fluid fluid, MapColor mapcolor, int quanta) {
    super(modid, name, addToTab, fluid, mapcolor, quanta);
    this.slipperiness = 0.8F;
  }

  @Override
  public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
    if (entityIn.isSneaking()) {
      super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    } else {
      entityIn.fall(fallDistance, 0.0F);
    }
  }

  @Override
  public void onLanded(World worldIn, Entity entityIn) {
    if (entityIn.isSneaking()) {
      super.onLanded(worldIn, entityIn);
    } else if (entityIn.motionY < 0.0D) {
      entityIn.motionY = -entityIn.motionY;

      if (!(entityIn instanceof EntityLivingBase)) {
        entityIn.motionY *= 0.8D;
      }
    }
  }

  @Override
  public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
    if (Math.abs(entityIn.motionY) < 0.1D && !entityIn.isSneaking()) {
      double d0 = 0.4D + Math.abs(entityIn.motionY) * 0.2D;
      entityIn.motionX *= d0;
      entityIn.motionZ *= d0;
    }

    super.onEntityWalk(worldIn, pos, entityIn);
  }

}
