package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.entity.effect.StatusEffects;

public class AntiBlind extends Module
{
  public AntiBlind()
  {
    super("AntiBlind", "Stops the blindness effect", -1, Category.Render, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.player.hasStatusEffect(StatusEffects.BLINDNESS))
      mc.player.removeStatusEffect(StatusEffects.BLINDNESS);
  }
}
