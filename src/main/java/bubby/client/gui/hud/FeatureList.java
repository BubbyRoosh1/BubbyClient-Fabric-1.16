package bubby.client.gui.hud;
import bubby.api.setting.ModeSetting;
import bubby.client.BubbyClient;
import static bubby.client.modules.FeatureList.Mode;
import java.util.ArrayList;

public class FeatureList extends PinnableFrame
{
  public FeatureList()
  {
    super("FeatureList", new ArrayList<>(), new ArrayList<>(), 50);
  }

  public void
  updateFrame()
  {
    this.leftText.clear();
    this.rightText.clear();
    if(((ModeSetting)BubbyClient.settings.getSettingByName("FeatureList Side")).get() == Mode.Left)
      BubbyClient.modules.getToggledModules().forEach(m -> this.leftText.add(m.getName()));
    else
      BubbyClient.modules.getToggledModules().forEach(m -> this.rightText.add(m.getName()));
  }
}
