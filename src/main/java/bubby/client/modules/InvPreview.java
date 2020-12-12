package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.client.BubbyClient;

public class InvPreview extends Module
{
  public InvPreview()
  {
    super("InvPreview", "InvPrewiew settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("InvPreview").visible = true;
  }

  public void
  onDisable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("InvPreview").visible = false;
  }
}
