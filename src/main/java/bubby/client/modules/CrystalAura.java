package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.SliderSetting;
import bubby.client.utils.EntityUtils;
import com.google.common.collect.Streams;
import net.minecraft.entity.decoration.EndCrystalEntity;

public class CrystalAura extends Module
{
  int counter = 0;
  SliderSetting range = new SliderSetting("CrystalAura Range", this, 4, 0, 6, false);
  SliderSetting waitTicks = new SliderSetting("CrystalAura WaitTicks", this, 0, 0, 20, true);

  public CrystalAura()
  {
    super("CrystalAura", "Automatically attacks crystals", -1, Category.Combat, true);
    addSetting(range);
    addSetting(waitTicks);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    counter++;
    if(counter < waitTicks.get())
      return;
    counter = 0;

    Streams.stream(mc.world.getEntities())
            .filter(e -> e instanceof EndCrystalEntity
                    && mc.player.distanceTo(e) < range.get())
            .forEach(EntityUtils::attackEntity);
  }
}
