package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;

public class AirJump extends Module
{
  public AirJump()
  {
    super("AirJump", "Accurately named jetpack", -1, Category.Movement, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.options.keyJump.isPressed())
      mc.player.jump();
  }
}
