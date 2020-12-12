package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.ModeSetting;
import bubby.api.setting.SliderSetting;
import bubby.client.utils.EntityUtils;
import bubby.client.utils.Hole;
import bubby.client.utils.HoleUtils;
import bubby.client.utils.WorldUtils;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class HoleFill extends Module
{
  ArrayList<Hole> holesToFill = new ArrayList<>();
  int oldSlot = -1;
  ModeSetting<Item> item = new ModeSetting<Item>("HoleFill Item", this, Item.Obby, Item.values());
  SliderSetting range = new SliderSetting("HoleFill Range", this, 10, 0, 20, true);
  SliderSetting bpt = new SliderSetting("HoleFill Blocks/Tick", this, 2, 0, 20, true);
  CheckBoxSetting autoSwap = new CheckBoxSetting("HoleFill AutoSwap", this, true);
  CheckBoxSetting autoToggle = new CheckBoxSetting("HoleFill AutoToggle", this, true);

  public HoleFill()
  {
    super("HoleFill", "Fills nearby holes", -1, Category.Combat, true);
    addSetting(item);
    addSetting(range);
    addSetting(bpt);
    addSetting(autoSwap);
    addSetting(autoToggle);
  }

  public void
  onDisable()
  {
    super.onDisable();
    if(oldSlot != -1)
      mc.player.inventory.selectedSlot = oldSlot;
    oldSlot = -1;
    holesToFill.clear();
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    holesToFill.clear();
    holesToFill.addAll(HoleUtils.getHolesWithinRange((int) range.get()));
    if(holesToFill.size() < 1)
      setToggled(false);
    int amountPlacedThisTick = 0;
    for(Hole hole : holesToFill)
    {
      if(amountPlacedThisTick > bpt.get())
        return;
      amountPlacedThisTick++;
      if(autoSwap.get())
      {
        if(item.get() == Item.Obby && mc.player.getMainHandStack().getItem() != Items.OBSIDIAN)
          oldSlot = EntityUtils.changeHotbarSlotToItem(Items.OBSIDIAN);
        else if(item.get() == Item.Cobweb && mc.player.getMainHandStack().getItem() != Items.COBWEB)
          oldSlot = EntityUtils.changeHotbarSlotToItem(Items.COBWEB);
      }
      WorldUtils.placeBlock(new Vec3d(hole.blockPos.getX(), hole.blockPos.getY(), hole.blockPos.getZ()), Hand.MAIN_HAND, Direction.UP);
    }
  }

  enum Item
  {
    Obby,
    Cobweb
  }
}
