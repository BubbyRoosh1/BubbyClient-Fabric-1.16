package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.SliderSetting;

public class Step extends Module
{
  SliderSetting height = new SliderSetting("Step Height", this, 2, 0, 6, true);

  public Step()
  {
    super("Step", "Increases step height", -1, Category.Movement, true);
    addSetting(height);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.player.stepHeight = 0.6f;
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.player == null || mc.world == null)
      return;
    mc.player.stepHeight = (float) height.get();
  }

}
