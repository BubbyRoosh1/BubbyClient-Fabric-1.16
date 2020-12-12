package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.SliderSetting;

public class PopbobLag extends Module
{
  int delay = 0;
  SliderSetting speed = new SliderSetting("popbob speed", this, 10, 0, 120, true);

  public PopbobLag()
  {
    super("PopbobLag", "popbob is fat", -1, Category.Spam, true);
    addSetting(speed);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    delay++;
    if(delay == speed.get() * 20)
    {
      mc.player.sendChatMessage("lag");
      delay = 0;
    }
  }
}
