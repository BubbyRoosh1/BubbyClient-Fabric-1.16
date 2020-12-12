package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.RenderEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.SliderSetting;
import bubby.client.utils.RenderUtils;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.ArrayList;

public class PortalESP extends Module
{
  ArrayList<BlockPos> portals = new ArrayList<>();
  int counter;
  boolean shouldFill;

  SliderSetting rangee = new SliderSetting("PortalESP Range", this, 32, 0, 245, true);
  CheckBoxSetting fill = new CheckBoxSetting("PortalESP Fill", this, false);

  public PortalESP()
  {
    super("PortalESP", "Shows nearby porals", -1, Category.Render, true);
    addSetting(rangee);
    addSetting(fill);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    counter++;
    if(counter < 20)
    {
      counter++;
      return;
    }
    else
      counter = 0;

    int range = (int) rangee.get();
    portals.clear();
    for(int x = range; x >= -range; x--)
      for(int y = range; y >= -range; y--)
        for(int z = range; z >= -range; z--)
        {
          BlockPos pos = new BlockPos(mc.player.getX() + x, mc.player.getY() + y, mc.player.getZ() + z);
          if(mc.world.getBlockState(pos).getBlock() == Blocks.NETHER_PORTAL)
            portals.add(new BlockPos(mc.player.getX() + x, mc.player.getY() + y, mc.player.getZ() + z));
        }
  }

  @PogEvent
  public void
  onRender(RenderEvent event)
  {
    portals.forEach(p -> RenderUtils.drawBox(new Box(p.getX(), p.getY(), p.getZ() + 0.4d, p.getX() + 1.d, p.getY() + 1.d, p.getZ() + 0.6d), 1.f, 0.f, 0.5f, 1.f, fill.get()));
  }
}
