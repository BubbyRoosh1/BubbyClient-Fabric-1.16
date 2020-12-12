package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.RenderEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.client.utils.RenderUtils;
import net.minecraft.block.entity.*;

import java.util.ArrayList;

public class ChestESP extends Module
{
  ArrayList<BlockEntity> chests = new ArrayList<BlockEntity>();
  ArrayList<BlockEntity> endChests = new ArrayList<BlockEntity>();
  ArrayList<BlockEntity> hoppers = new ArrayList<BlockEntity>();
  ArrayList<BlockEntity> shulkers = new ArrayList<BlockEntity>();

  CheckBoxSetting rChests = new CheckBoxSetting("ChestESP Chests", this, true);
  CheckBoxSetting rEndChests = new CheckBoxSetting("ChestESP EndChests", this, true);
  CheckBoxSetting rShulkers = new CheckBoxSetting("ChestESP Shulkers", this, true);
  CheckBoxSetting rHoppers = new CheckBoxSetting("ChestESP Hoppers", this, true);
  CheckBoxSetting fill = new CheckBoxSetting("ChestESP Fill", this, true);

  public ChestESP()
  {
    super("ChestESP", "Box around storage", -1, Category.Render, true);
    addSetting(rChests);
    addSetting(rEndChests);
    addSetting(rShulkers);
    addSetting(rHoppers);
    addSetting(fill);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    chests.clear();
    endChests.clear();
    hoppers.clear();
    shulkers.clear();

    mc.world.blockEntities.forEach(e ->
    {
      if((e instanceof ChestBlockEntity
              || e instanceof BarrelBlockEntity)
              && rChests.get())
        chests.add(e);
      else if(e instanceof EnderChestBlockEntity && rEndChests.get())
        endChests.add(e);
      else if(e instanceof HopperBlockEntity && rHoppers.get())
        hoppers.add(e);
      else if(e instanceof ShulkerBoxBlockEntity && rShulkers.get())
        shulkers.add(e);
    });
  }

  @PogEvent
  public void
  onRender(RenderEvent event)
  {
    chests.forEach(e -> RenderUtils.drawBox(e.getPos(), 1.9f, 1.5f, 0.3f, 0.7f, fill.get()));
    endChests.forEach(e -> RenderUtils.drawBox(e.getPos(), 1.f, 0.05f, 1.f, 0.7f, fill.get()));
    hoppers.forEach(e -> RenderUtils.drawBox(e.getPos(), 0.45f, 0.45f, 0.6f, 0.7f, fill.get()));
    shulkers.forEach(e -> RenderUtils.drawBox(e.getPos(), 0.5f, 0.2f, 1.f, 0.7f, fill.get()));
  }
}
