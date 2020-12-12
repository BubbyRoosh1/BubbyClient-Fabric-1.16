package bubby.api.module;

import bubby.api.chat.Chat;
import bubby.api.setting.Setting;
import bubby.client.BubbyClient;
import bubby.client.utils.FileManager;
import net.minecraft.client.MinecraftClient;

public class Module
{
  private final String name;
  private final String desc;
  private final Category category;
  public boolean visible;
  protected MinecraftClient mc = MinecraftClient.getInstance();
  boolean isEnabled;
  private int bind;

  public Module(String name, String desc, int bind, Category category, boolean visible)
  {
    this.name = name;
    this.desc = desc;
    this.bind = bind;
    this.category = category;
    this.visible = visible;
  }

  public void
  onEnable()
  {
    BubbyClient.eventManager.register(this);
    try
    {
      if(BubbyClient.modules.getModuleByName("Toggles").isToggled())
        Chat.message("Enabled " + this.name);
    }
    catch(Exception e)
    {
    }
  }

  public void
  onDisable()
  {
    BubbyClient.eventManager.unregister(this);
    try
    {
      if(BubbyClient.modules.getModuleByName("Toggles").isToggled())
        Chat.message("Disabled " + this.name);
    }
    catch(Exception e)
    {
    }
  }

  public void
  toggle()
  {
    this.setToggled(!this.isEnabled);
    FileManager.INSTANCE.saveModules();
  }

  public boolean
  isToggled()
  {
    return isEnabled;
  }

  public void
  setToggled(boolean b)
  {
    this.isEnabled = b;
    if(this.isEnabled)
    {
      this.onEnable();
    }
    else
    {
      this.onDisable();
    }
  }

  public int
  getBind()
  {
    return this.bind;
  }

  public void
  setBind(int bind)
  {
    this.bind = bind;
    FileManager.INSTANCE.saveBinds();
  }

  public String
  getName()
  {
    return this.name;
  }

  public String
  getDescription()
  {
    return this.desc;
  }

  public Category
  getCategory()
  {
    return this.category;
  }

  public void
  addSetting(Setting setting)
  {
    BubbyClient.settings.addSetting(setting);
  }
}
