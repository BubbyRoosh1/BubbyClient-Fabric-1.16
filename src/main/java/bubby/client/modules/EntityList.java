package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.client.BubbyClient;

public class EntityList extends Module
{
  public EntityList()
  {
    super("EntityList", "EntityList settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("EntityList").visible = true;
  }

  public void
  onDisable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("EntityList").visible = false;
  }
}
