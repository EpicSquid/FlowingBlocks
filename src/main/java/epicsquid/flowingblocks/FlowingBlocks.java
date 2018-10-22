package epicsquid.flowingblocks;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FlowingBlocks.MODID, name = FlowingBlocks.NAME, version = FlowingBlocks.VERSION, dependencies = "required-before:mysticallib")
public class FlowingBlocks {
  public static final String MODID = "flowingblocks";
  public static final String NAME = "Flowing Blocks";
  public static final String VERSION = "@VERSION@";
  public static ModContainer CONTAINER = null;

  static {
    FluidRegistry.enableUniversalBucket();
  }

  @EventHandler
  public void preinit(FMLPreInitializationEvent event) {
    CONTAINER = Loader.instance().activeModContainer();
    MinecraftForge.EVENT_BUS.register(new Registrar());
  }
}
