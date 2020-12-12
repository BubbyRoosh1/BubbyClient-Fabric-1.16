package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;

public class AutoSprint extends Module
{
  public AutoSprint()
  {
    super("AutoSprint", "Makes you sprint without pressing your sprint button", -1, Category.Movement, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.options.keyForward.isPressed())
      mc.player.setSprinting(true);
  }
}
