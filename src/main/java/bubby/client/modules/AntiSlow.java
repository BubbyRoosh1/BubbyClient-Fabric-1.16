package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.entity.effect.StatusEffects;

public class AntiSlow extends Module
{
  public AntiSlow()
  {
    super("AntiSlow", "Stops the slowness effect", -1, Category.Player, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.player.hasStatusEffect(StatusEffects.SLOWNESS))
      mc.player.removeStatusEffect(StatusEffects.SLOWNESS);
  }
}
