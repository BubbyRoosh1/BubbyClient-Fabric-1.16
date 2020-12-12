package bubby.client;

import bubby.api.eventapi.EventManager;
import bubby.api.logging.LoggerProvider;
import bubby.api.module.ModuleManager;
import bubby.api.setting.SettingManager;
import bubby.client.gui.ClickGuiManager;
import bubby.client.gui.CommandScreen;
import bubby.client.irc.IrcManager;
import bubby.client.utils.FileManager;
import bubby.client.utils.FriendManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.Logger;

public class BubbyClient implements ModInitializer
{
  public static final MinecraftClient MC = MinecraftClient.getInstance();
  public static final String VERSION;
  public static EventManager eventManager;
  public static String NAME = "BubbyClient";
  private static final Logger LOGGER = LoggerProvider.logger();
  public static ClickGuiManager clickGuiManager;
  public static CommandScreen commandScreen;
  public static SettingManager settings;
  public static ModuleManager modules;
  public static FriendManager friends;
  public static IrcManager irc;

  static
  {
    VERSION = FabricLoader.INSTANCE.getMods().stream()
            .filter(mod -> mod.getInfo().getId().equals("bubbyclient"))
            .findFirst()
            .get()
            .getInfo().getVersion().getFriendlyString();
  }

  @Override
  public void onInitialize()
  {
    LOGGER.info("Initializing " + NAME + " Version " + VERSION);

    eventManager = new EventManager();
    friends = new FriendManager();
    settings = new SettingManager();
    modules = new ModuleManager();
    clickGuiManager = new ClickGuiManager();
    commandScreen = new CommandScreen();
    FileManager.INSTANCE.loadModules();
    FileManager.INSTANCE.loadSettingsList();
    FileManager.INSTANCE.loadGuiPos();
    FileManager.INSTANCE.loadBinds();
    FileManager.INSTANCE.loadFriends();

    LOGGER.info("Ready to destroy servers!");
  }
}
