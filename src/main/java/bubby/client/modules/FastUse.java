package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.mixininterface.MinecraftClientInterface;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.ModeSetting;
import net.minecraft.item.Items;

public class FastUse extends Module
{
  ModeSetting<Mode> mode = new ModeSetting<Mode>("FastUse Mode", this, Mode.All, Mode.values());

  public FastUse()
  {
    super("FastUse", "Use fast", -1, Category.Player, true);
    addSetting(mode);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mode.get() == Mode.EXP && mc.player.getMainHandStack().getItem() != Items.EXPERIENCE_BOTTLE)
      return;
    ((MinecraftClientInterface) mc).setItemUseCooldown(0);
  }

  enum Mode
  {
    All,
    EXP
  }
}
