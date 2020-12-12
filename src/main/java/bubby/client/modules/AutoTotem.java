package bubby.client.modules;
import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.ModeSetting;
import bubby.api.setting.SliderSetting;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotem extends Module
{
  SliderSetting swapHealth = new SliderSetting("AutoTotem SwapHealth", this, 16, 0, 20, true);
  ModeSetting<Mode> mode = new ModeSetting<Mode>("AutoTotem Mode", this, Mode.Force, Mode.values());

  public AutoTotem()
  {
    super("AutoTotem", "fast totem", -1, Category.Combat, true);
    addSetting(swapHealth);
    addSetting(mode);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mode.get() == Mode.Health && mc.player.getHealth() > swapHealth.get())
      return;
    if(mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING)
      return;
    swapToTotem();
  }

  private void
  swapToTotem()
  {
    for(int i = 0; i < 44; i++)
    {
      if(mc.player.inventory.getStack(i).getItem() == Items.TOTEM_OF_UNDYING)
      {
        mc.interactionManager.clickSlot(0, i < 9 ? i + 36 : i, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(0, 45, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(0, i < 9 ? i + 36 : i, 0, SlotActionType.PICKUP, mc.player);
        return;
      }
    }
  }

  enum Mode
  {
    Force,
    Health
  }
}
