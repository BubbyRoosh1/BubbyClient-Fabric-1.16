/*package bubby.client.client.modules;

import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import EntityUtils;
import bubby.api.eventapi.PogEvent;
import com.google.common.collect.Streams;
import de.Hero.settings.Setting;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;

public class CrystalAura extends Module
{
  int counter = 0;

  public CrystalAura()
  {
    super("CrystalAura", "Automatically attacks crystals", -1, Category.Combat, true);
    set.addSetting(new Setting("CrystalAura Range", this, 4, 0, 6, false));
    set.addSetting(new Setting("CrystalAura WaitTicks", this, 0, 0, 20, true));
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    counter++;
    if (counter < set.getSettingByName("CrystalAura WaitTicks").getValDouble())
      return;
    counter = 0;

    Streams.stream(mc.world.getBlockEntitie())
        .filter(e -> e instanceof BedBlockEntity
            && mc.player.distanceTo(e) < set.getSettingByName("CrystalAura Range").getValDouble())
        .forEach(e ->
        {
          EntityUtils.attackEntity(e);
        });
  }
}


 */