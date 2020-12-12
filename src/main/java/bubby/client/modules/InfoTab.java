package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.client.BubbyClient;

public class InfoTab extends Module
{
  public InfoTab()
  {
    super("InfoTab", "InfoTab settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("InfoTab").visible = true;
  }

  public void
  onDisable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("InfoTab").visible = false;
  }
}
