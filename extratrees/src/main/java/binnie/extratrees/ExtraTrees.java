package binnie.extratrees;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import binnie.core.Binnie;
import binnie.core.BinnieCore;
import binnie.core.Constants;
import binnie.core.api.genetics.IBreedingSystem;
import binnie.core.gui.IBinnieGUID;
import binnie.core.modules.BlankModuleContainer;
import binnie.core.network.BinniePacketHandler;
import binnie.core.proxy.IProxyCore;
import binnie.extratrees.modules.ModuleWood;
import binnie.extratrees.config.ConfigurationMain;
import binnie.extratrees.core.ExtraTreesGUID;
import binnie.extratrees.genetics.MothBreedingSystem;
import binnie.extratrees.genetics.TreeBreedingSystem;
import binnie.extratrees.proxy.Proxy;

@Mod(
	modid = Constants.EXTRA_TREES_MOD_ID,
	name = "Binnie's Extra Trees",
	dependencies = "required-after:" + Constants.CORE_MOD_ID
)
public class ExtraTrees extends BlankModuleContainer {

	@SuppressWarnings("NullableProblems")
	@Mod.Instance(Constants.EXTRA_TREES_MOD_ID)
	public static ExtraTrees instance;

	@SuppressWarnings("NullableProblems")
	@SidedProxy(clientSide = "binnie.extratrees.proxy.ProxyClient", serverSide = "binnie.extratrees.proxy.ProxyServer")
	public static Proxy proxy;
	public static IBreedingSystem treeBreedingSystem;
	public static IBreedingSystem mothBreedingSystem;

	public ExtraTrees() {
		super();
		MinecraftForge.EVENT_BUS.register(ModuleWood.class);
	}

	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent evt) {
		container.registerConfigHandler(new ConfigurationMain(container));
		super.preInit(evt);
		treeBreedingSystem = new TreeBreedingSystem();
		mothBreedingSystem = new MothBreedingSystem();
		Binnie.GENETICS.registerBreedingSystem(treeBreedingSystem);
		Binnie.GENETICS.registerBreedingSystem(mothBreedingSystem);
	}

	@Mod.EventHandler
	public void init(final FMLInitializationEvent evt) {
		super.init(evt);
	}

	@Mod.EventHandler
	public void postInit(final FMLPostInitializationEvent evt) {
		super.postInit(evt);
	}

	@Override
	protected void registerModules() {
	}

	@Override
	public IBinnieGUID[] getGUIDs() {
		return ExtraTreesGUID.values();
	}

	@Override
	public Class<?>[] getConfigs() {
		return new Class[]{ConfigurationMain.class};
	}

	@Override
	public String getChannel() {
		return "ET";
	}

	@Override
	public IProxyCore getProxy() {
		return ExtraTrees.proxy;
	}

	@Override
	public String getModID() {
		return Constants.EXTRA_TREES_MOD_ID;
	}

	@Override
	protected Class<? extends BinniePacketHandler> getPacketHandler() {
		return PacketHandler.class;
	}

	@Override
	public boolean isAvailable() {
		return BinnieCore.isExtraTreesActive();
	}

	public static class PacketHandler extends BinniePacketHandler {
		public PacketHandler() {
			super(ExtraTrees.instance);
		}
	}
}