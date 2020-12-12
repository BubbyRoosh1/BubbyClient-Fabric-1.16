package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;

public class AutoWalk extends Module
{
  public AutoWalk()
  {
    super("AutoWalk", "Makes you walk automatically", -1, Category.Movement, true);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.options.keyForward.setPressed(false);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    mc.options.keyForward.setPressed(true);
  }
}
