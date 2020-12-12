package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

//need to figure out why it sometimes *doesn't* work and allows you to
//accidentally drop the elytra
public class ElytraToggle extends Module
{
  public ElytraToggle()
  {
    super("ElytraToggle", "toggles elytra without needing to open gui", -1, Category.Movement, true);
  }

  public void
  onEnable()
  {
    super.onEnable();
    try
    {
      if(mc.player.inventory.getArmorStack(2).getItem() == Items.ELYTRA) return;

      for(int slot = 0; slot < 44; slot++)
      {
        if(mc.player.inventory.getStack(slot).getItem() == Items.ELYTRA)
        {
          mc.interactionManager.clickSlot(0, slot < 9 ? slot + 36 : slot, 0, SlotActionType.PICKUP, mc.player);
          mc.interactionManager.clickSlot(0, 6, 0, SlotActionType.PICKUP, mc.player);
          mc.interactionManager.clickSlot(0, slot < 9 ? slot + 36 : slot, 0, SlotActionType.PICKUP, mc.player);
          return;
        }
      }
    }
    catch(Exception e)
    {
    }
  }

  public void
  onDisable()
  {
    super.onDisable();
    for(int slot = 0; slot < 44; slot++)
    {
      if(mc.player.inventory.getStack(slot).getItem() == Items.DIAMOND_CHESTPLATE || mc.player.inventory.getStack(slot).getItem() == Items.NETHERITE_CHESTPLATE)
      {
        mc.interactionManager.clickSlot(0, slot < 9 ? slot + 36 : slot, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(0, 6, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(0, slot < 9 ? slot + 36 : slot, 0, SlotActionType.PICKUP, mc.player);
        return;
      }
    }
  }
}
