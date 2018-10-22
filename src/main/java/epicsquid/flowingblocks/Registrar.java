package epicsquid.flowingblocks;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.block.BlockStairsBase;
import epicsquid.mysticallib.block.BlockWallBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.event.RegisterModRecipesEvent;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

public class Registrar {
  public static Block cement, cement_slab, cement_double_slab, cement_wall, cement_stairs;
  public static Block asphalt, asphalt_slab, asphalt_double_slab, asphalt_wall, asphalt_stairs;
  public static Block flowing_sand, flowing_redsand, flowing_gravel, flowing_soulsand, flowing_magma, flowing_slime, flowing_ice, flowing_cement, flowing_asphalt;

  public static Fluid fluid_sand, fluid_redsand, fluid_gravel, fluid_soulsand, fluid_magma, fluid_slime, fluid_ice, fluid_cement, fluid_asphalt;

  @SubscribeEvent
  public void onRegisterContent(RegisterContentEvent event) {
    LibRegistry.setActiveMod(FlowingBlocks.MODID, FlowingBlocks.CONTAINER);

    event.addBlock(cement = new BlockBase(Material.ROCK, SoundType.STONE, 1.8f, "cement").setModelCustom(true).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
    event.addBlock(
        cement_wall = new BlockWallBase(cement, SoundType.STONE, 1.8f, "cement_wall").setModelCustom(true).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
    event.addBlock(cement_stairs = new BlockStairsBase(cement.getDefaultState(), SoundType.STONE, 1.8f, "cement_stairs").setModelCustom(true)
        .setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
    LibRegistry.addSlabPair(Material.ROCK, SoundType.STONE, 1.8f, "cement", cement.getDefaultState(), new Block[] { cement_slab, cement_double_slab }, true,
        CreativeTabs.BUILDING_BLOCKS);

    event.addBlock(
        asphalt = new BlockAsphalt(Material.ROCK, SoundType.STONE, 1.8f, "asphalt").setModelCustom(true).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
    event.addBlock(
        asphalt_wall = new BlockWallBase(asphalt, SoundType.STONE, 1.8f, "asphalt_wall").setModelCustom(true).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
    event.addBlock(asphalt_stairs = new BlockStairsBase(asphalt.getDefaultState(), SoundType.STONE, 1.8f, "asphalt_stairs").setModelCustom(true)
        .setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
    LibRegistry.addSlabPair(Material.ROCK, SoundType.STONE, 1.8f, "asphalt", asphalt.getDefaultState(), new Block[] { asphalt_slab, asphalt_double_slab }, true,
        CreativeTabs.BUILDING_BLOCKS);

    FluidRegistry.registerFluid(
        fluid_sand = new FluidSolid("sand", new ResourceLocation("blocks/sand"), new ResourceLocation(FlowingBlocks.MODID + ":blocks/sand_flow"), flowing_sand)
            .setDensity(2000));
    FluidRegistry.addBucketForFluid(fluid_sand);
    event.addBlock(flowing_sand = new BlockFluidSolid(FlowingBlocks.MODID, "sand", false, fluid_sand, MapColor.SAND, 5));

    FluidRegistry.registerFluid(
        fluid_redsand = new FluidSolid("redsand", new ResourceLocation("blocks/red_sand"), new ResourceLocation(FlowingBlocks.MODID + ":blocks/redsand_flow"),
            flowing_redsand).setDensity(2000));
    FluidRegistry.addBucketForFluid(fluid_redsand);
    event.addBlock(flowing_redsand = new BlockFluidSolid(FlowingBlocks.MODID, "redsand", false, fluid_redsand, MapColor.SAND, 5));

    FluidRegistry.registerFluid(
        fluid_gravel = new FluidSolid("gravel", new ResourceLocation("blocks/gravel"), new ResourceLocation(FlowingBlocks.MODID + ":blocks/gravel_flow"),
            flowing_gravel).setDensity(2000));
    FluidRegistry.addBucketForFluid(fluid_gravel);
    event.addBlock(flowing_gravel = new BlockFluidSolid(FlowingBlocks.MODID, "gravel", false, fluid_gravel, MapColor.STONE, 4));

    FluidRegistry.registerFluid(fluid_soulsand = new FluidSolid("soulsand", new ResourceLocation("blocks/soul_sand"),
        new ResourceLocation(FlowingBlocks.MODID + ":blocks/soulsand_flow"), flowing_soulsand).setDensity(2000));
    FluidRegistry.addBucketForFluid(fluid_soulsand);
    event.addBlock(flowing_soulsand = new BlockSoulSandFlow(FlowingBlocks.MODID, "soulsand", false, fluid_soulsand, MapColor.BROWN, 6));

    FluidRegistry.registerFluid(
        fluid_magma = new FluidSolid("magma", new ResourceLocation("blocks/magma"), new ResourceLocation(FlowingBlocks.MODID + ":blocks/magma_flow"),
            flowing_magma).setDensity(2000));
    FluidRegistry.addBucketForFluid(fluid_magma);
    event.addBlock(flowing_magma = new BlockMagmaFlow(FlowingBlocks.MODID, "magma", false, fluid_magma, MapColor.NETHERRACK, 6));

    FluidRegistry.registerFluid(
        fluid_ice = new FluidSolid("ice", new ResourceLocation("blocks/ice"), new ResourceLocation(FlowingBlocks.MODID + ":blocks/ice_flow"), flowing_ice)
            .setDensity(2000));
    FluidRegistry.addBucketForFluid(fluid_ice);
    event.addBlock(flowing_ice = new BlockIceFlow(FlowingBlocks.MODID, "ice", false, fluid_ice, MapColor.ICE, 6));

    FluidRegistry.registerFluid(
        fluid_slime = new FluidSolid("slime", new ResourceLocation("blocks/slime"), new ResourceLocation(FlowingBlocks.MODID + ":blocks/slime_flow"),
            flowing_slime).setDensity(2000));
    FluidRegistry.addBucketForFluid(fluid_slime);
    event.addBlock(flowing_slime = new BlockSlimeFlow(FlowingBlocks.MODID, "slime", false, fluid_slime, MapColor.GRASS, 6));

    FluidRegistry.registerFluid(fluid_cement = new FluidSolid("cement", new ResourceLocation(FlowingBlocks.MODID + ":blocks/liquid_cement"),
        new ResourceLocation(FlowingBlocks.MODID + ":blocks/cement_flow"), flowing_cement).setDensity(2000));
    FluidRegistry.addBucketForFluid(fluid_cement);
    event.addBlock(
        flowing_cement = new BlockFluidHardening(FlowingBlocks.MODID, "liquid_cement", false, fluid_cement, MapColor.STONE, 6, cement.getDefaultState()));

    FluidRegistry.registerFluid(fluid_asphalt = new FluidSolid("asphalt", new ResourceLocation(FlowingBlocks.MODID + ":blocks/liquid_asphalt"),
        new ResourceLocation(FlowingBlocks.MODID + ":blocks/asphalt_flow"), flowing_asphalt).setDensity(2000));
    FluidRegistry.addBucketForFluid(fluid_asphalt);
    event.addBlock(
        flowing_asphalt = new BlockFluidHardening(FlowingBlocks.MODID, "liquid_asphalt", false, fluid_asphalt, MapColor.BLACK, 6, asphalt.getDefaultState()));
  }

  public static ResourceLocation getRL(String s) {
    return new ResourceLocation(FlowingBlocks.MODID + ":" + s);
  }

  public static void registerShapeless(IForgeRegistry<IRecipe> registry, String name, ItemStack result, Object... ingredients) {
    registry.register(new ShapelessOreRecipe(getRL(name), result, ingredients).setRegistryName(getRL(name)));
  }

  public static ItemStack bucketOfFluid(Fluid f) {
    return FluidUtil.getFilledBucket(new FluidStack(f, 1000));
  }

  @SubscribeEvent
  public void onRegisterRecipes(RegisterModRecipesEvent event) {
    LibRegistry.setActiveMod(FlowingBlocks.MODID, FlowingBlocks.CONTAINER);

    registerShapeless(event.getRegistry(), "fluid_sand", bucketOfFluid(fluid_sand), Items.BUCKET, new ItemStack(Blocks.SAND, 1, 0));
    registerShapeless(event.getRegistry(), "fluid_redsand", bucketOfFluid(fluid_redsand), Items.BUCKET, new ItemStack(Blocks.SAND, 1, 1));
    registerShapeless(event.getRegistry(), "fluid_gravel", bucketOfFluid(fluid_gravel), Items.BUCKET, Blocks.GRAVEL);
    registerShapeless(event.getRegistry(), "fluid_soulsand", bucketOfFluid(fluid_soulsand), Items.BUCKET, Blocks.SOUL_SAND);
    registerShapeless(event.getRegistry(), "fluid_magma", bucketOfFluid(fluid_magma), Items.BUCKET, Blocks.MAGMA);
    registerShapeless(event.getRegistry(), "fluid_ice", bucketOfFluid(fluid_ice), Items.BUCKET, Blocks.ICE);
    registerShapeless(event.getRegistry(), "fluid_slime", bucketOfFluid(fluid_slime), Items.BUCKET, Blocks.SLIME_BLOCK);
    registerShapeless(event.getRegistry(), "fluid_cement", bucketOfFluid(fluid_cement), Items.BUCKET, Blocks.SAND, Blocks.SAND, Items.CLAY_BALL,
        Items.CLAY_BALL);
    registerShapeless(event.getRegistry(), "fluid_asphalt", bucketOfFluid(fluid_asphalt), Items.BUCKET, Blocks.GRAVEL, Items.COAL, Items.CLAY_BALL,
        Items.CLAY_BALL);
  }
}
