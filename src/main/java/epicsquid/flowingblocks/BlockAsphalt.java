package epicsquid.flowingblocks;

import epicsquid.mysticallib.block.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAsphalt extends BlockBase {

  public BlockAsphalt(Material mat, SoundType type, float hardness, String name) {
    super(mat, type, hardness, name);
  }

  public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
    entityIn.motionX *= 1.3;
    entityIn.motionZ *= 1.3;
    super.onEntityWalk(worldIn, pos, entityIn);
  }

}
