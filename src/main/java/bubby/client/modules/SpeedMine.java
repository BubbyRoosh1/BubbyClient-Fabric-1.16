package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.SliderSetting;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class SpeedMine extends Module
{
  SliderSetting level = new SliderSetting("SpeedMine Level", this, 1, 1, 3, true);

  public SpeedMine()
  {
    super("SpeedMine", "Adds haste effect", -1, Category.Player, true);
    addSetting(level);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.player.removeStatusEffect(StatusEffects.HASTE);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1, (int) level.get()));
  }
}
