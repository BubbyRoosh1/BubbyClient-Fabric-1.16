package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.client.BubbyClient;

public class Console extends Module
{
  public Console()
  {
    super("Console", "Opens Console", 80, Category.Gui, true);
  }

  public void
  onEnable()
  {
    mc.openScreen(BubbyClient.commandScreen);
    setToggled(false);
  }

  public void
  onDisable()
  {

  }
}
