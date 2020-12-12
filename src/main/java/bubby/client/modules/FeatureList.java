package bubby.client.modules;
import bubby.api.module.*;
import bubby.api.setting.*;
import bubby.client.BubbyClient;

public class FeatureList extends Module
{
  public enum Mode
  {
    Left,
    Right
  }
  
  ModeSetting<Mode> mode = new ModeSetting<Mode>("FeatureList Side", this, Mode.Left, Mode.values());
  public FeatureList()
  {
    super("FeatureList", "FeatureList settings yes", -1, Category.Hud, false);
    addSetting(mode);
  }

  public void
  onEnable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("FeatureList").visible = true;
  }

  public void
  onDisable()
  {
    BubbyClient.clickGuiManager.pinnableFrameHashMap.get("FeatureList").visible = false;
  }
}
