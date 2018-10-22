package epicsquid.flowingblocks;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidSolid extends Fluid {
  public FluidSolid(String name, ResourceLocation still, ResourceLocation flowing, Block block) {
    super(name, still, flowing);
    setBlock(block);
    setUnlocalizedName(name);
    this.setViscosity(4000);
  }

  @Override
  public int getColor() {
    return Color.WHITE.getRGB();
  }
}
