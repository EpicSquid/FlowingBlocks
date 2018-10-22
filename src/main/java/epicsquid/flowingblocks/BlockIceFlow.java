package epicsquid.flowingblocks;

import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fluids.Fluid;

public class BlockIceFlow extends BlockFluidSolid {

  public BlockIceFlow(String modid, String name, boolean addToTab, Fluid fluid, MapColor mapcolor, int quanta) {
    super(modid, name, addToTab, fluid, mapcolor, quanta);
    this.slipperiness = 0.98F;
  }

  public EnumPushReaction getMobilityFlag(IBlockState state) {
    return EnumPushReaction.NORMAL;
  }

}
