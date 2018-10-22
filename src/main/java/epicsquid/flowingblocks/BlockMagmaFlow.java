package epicsquid.flowingblocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMagmaFlow extends BlockFluidSolid {

  public BlockMagmaFlow(String modid, String name, boolean addToTab, Fluid fluid, MapColor mapcolor, int quanta) {
    super(modid, name, addToTab, fluid, mapcolor, quanta);
    this.setLightLevel(0.2F);
  }

  public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
    if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase) entityIn)) {
      entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
    }

    super.onEntityWalk(worldIn, pos, entityIn);
  }

  @SideOnly(Side.CLIENT)
  public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
    return 15728880;
  }

}
