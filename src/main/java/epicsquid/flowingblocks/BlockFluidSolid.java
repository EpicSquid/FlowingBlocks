package epicsquid.flowingblocks;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.block.IBlock;
import epicsquid.mysticallib.model.IModeledObject;
import epicsquid.mysticallib.util.NoiseGenUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluidSolid extends BlockFluidClassic implements IBlock, IModeledObject {
  public static class MaterialLiquidSolid extends Material {
    public MaterialLiquidSolid(MapColor color) {
      super(color);
      setReplaceable();
      setNoPushMobility();
    }

    @Override
    public boolean isLiquid() {
      return false;
    }

    @Override
    public boolean blocksMovement() {
      return false;
    }

    @Override
    public boolean isSolid() {
      return false;
    }
  }

  String modid = "";
  AxisAlignedBB boxes[] = new AxisAlignedBB[16];

  public BlockFluidSolid(String modid, String name, boolean addToTab, Fluid fluid, MapColor mapcolor, int quanta) {
    super(fluid, new MaterialLiquidSolid(mapcolor));
    this.modid = modid;
    setRegistryName(modid + ":" + name);
    setQuantaPerBlock(quanta);
    for (int i = 0; i < 16; i++) {
      boxes[i] = new AxisAlignedBB(0, 0, 0, 1, 0.875f - i / 8f, 1);
    }
  }

  @Override
  public int getLightOpacity(IBlockState state) {
    return 0;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
    return boxes[state.getValue(LEVEL)];
  }

  @Override
  public boolean displaceIfPossible(World world, BlockPos pos) {
    if (world.getBlockState(pos).getMaterial().isLiquid() && world.getBlockState(pos).getBlock() != this) {
      return false;
    }
    if (!world.getBlockState(pos.down()).isFullCube() && !world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).getBlock() != this) {
      return false;
    }
    return super.displaceIfPossible(world, pos);
  }

  @SideOnly(Side.CLIENT)
  @Override
  public AxisAlignedBB getSelectedBoundingBox(@Nonnull IBlockState blockState, @Nonnull World worldIn, @Nonnull BlockPos pos) {
    return getBoundingBox(blockState, worldIn, pos);
  }

  @Override
  public float getFluidHeightForRender(IBlockAccess world, BlockPos pos, @Nonnull IBlockState up) {
    float height = 0f;
    IBlockState here = world.getBlockState(pos);
    if (here.getBlock() == this) {
      if (up.getMaterial().isLiquid() || up.getBlock() instanceof IFluidBlock) {
        return 1;
      }

      if (getMetaFromState(here) == getMaxRenderHeightMeta()) {
        height = 0.875F;
      }
    }
    if (here.getBlock() instanceof BlockLiquid) {
      height = Math.min(1 - BlockLiquid.getLiquidHeightPercent(here.getValue(BlockLiquid.LEVEL)), 14f / 16);
    }
    height = !here.getMaterial().isSolid() && up.getBlock() == this ? 1 : this.getQuantaPercentage(world, pos) * 0.875F;
    return (float) (height - 0.0625f + 0.125f * NoiseGenUtil.getOctave(100L, pos.getX(), pos.getY(), 2));
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState blockState, @Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos) {
    return getBoundingBox(blockState, worldIn, pos);
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return true;
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.MODEL;
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getBlockState().getBaseState().withProperty(LEVEL, meta);
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void initModel() {
    Block block = this;
    Item item = Item.getItemFromBlock(block);

    ModelBakery.registerItemVariants(item);

    final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(modid + ":fluid", stack.getFluid().getName());

    ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);

    ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
      @Override
      protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
        return modelResourceLocation;
      }
    });
  }

  @Override
  public Item getItemBlock() {
    return null;
  }
}
