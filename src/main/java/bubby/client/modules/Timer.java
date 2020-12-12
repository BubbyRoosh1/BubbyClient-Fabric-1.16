package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.SliderSetting;

public class Timer extends Module
{
  public Timer()
  {
    super("Timer", "Change the tick speed", -1, Category.World, true);
    addSetting(new SliderSetting("Timer Speed", this, 1, 0, 10, false));
  }
}
