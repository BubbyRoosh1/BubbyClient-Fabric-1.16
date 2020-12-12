package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.entity.effect.StatusEffects;

public class AntiLevitate extends Module
{
  public AntiLevitate()
  {
    super("AntiLevitate", "Stops the levitation effect", -1, Category.Player, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.player.hasStatusEffect(StatusEffects.LEVITATION))
      mc.player.removeStatusEffect(StatusEffects.LEVITATION);
  }
}
