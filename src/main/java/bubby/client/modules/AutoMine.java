package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;

public class AutoMine extends Module
{
  public AutoMine()
  {
    super("AutoMine", "Makes you mine automatically", -1, Category.Player, true);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.options.keyAttack.setPressed(false);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(!this.isToggled())
      return;
    mc.options.keyAttack.setPressed(true);
  }
}
