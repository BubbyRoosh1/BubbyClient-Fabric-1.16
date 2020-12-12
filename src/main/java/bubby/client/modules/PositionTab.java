package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.client.BubbyClient;

public class PositionTab extends Module
{
  public PositionTab()
  {
    super("PositionTab", "PositionTab settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("PositionTab").visible = true;
  }

  public void
  onDisable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("PositionTab").visible = false;
  }
}
