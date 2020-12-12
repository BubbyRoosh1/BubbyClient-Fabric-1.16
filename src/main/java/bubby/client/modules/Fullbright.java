package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;

public class Fullbright extends Module
{
  public Fullbright()
  {
    super("Fullbright", "Makes screen brighter", -1, Category.Render, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    mc.options.gamma = 16;
  }

  public void
  onEnable()
  {
    super.onEnable();
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.options.gamma = 0.5;
  }
}
