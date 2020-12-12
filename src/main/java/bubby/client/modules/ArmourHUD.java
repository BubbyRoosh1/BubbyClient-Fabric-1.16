package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.client.BubbyClient;

public class ArmourHUD extends Module
{
  public ArmourHUD()
  {
    super("ArmourHUD", "ArmourHUD settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("ArmourHUD").visible = true;
  }

  public void
  onDisable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("ArmourHUD").visible = false;
  }
}
