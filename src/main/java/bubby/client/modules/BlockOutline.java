package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.RenderEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.SliderSetting;
import bubby.client.utils.RenderUtils;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class BlockOutline extends Module
{
  SliderSetting red = new SliderSetting("BlockOutline Red", this, 255, 0, 255, true);
  SliderSetting green = new SliderSetting("BlockOutline Green", this, 255, 0, 255, true);
  SliderSetting blue = new SliderSetting("BlockOutline Blue", this, 255, 0, 255, true);
  SliderSetting alpha = new SliderSetting("BlockOutline Alpha", this, 255, 0, 255, true);
  CheckBoxSetting fill = new CheckBoxSetting("BlockOutline Fill", this, false);
  private HitResult blockHit;

  public BlockOutline()
  {
    super("BlockOutline", "Outlines the block you are looking at", -1, Category.Render, true);
    addSetting(red);
    addSetting(green);
    addSetting(blue);
    addSetting(alpha);
    addSetting(fill);
  }

  @PogEvent
  public void
  onRender(RenderEvent event)
  {
    blockHit = mc.getCameraEntity().rayTrace(20.d, 0.f, false);
    if(blockHit.getType() == HitResult.Type.BLOCK)
      RenderUtils.drawBox(((BlockHitResult) blockHit).getBlockPos(),
              (float) red.get() / 255,
              (float) green.get() / 255,
              (float) blue.get() / 255,
              (float) alpha.get() / 255,
              fill.get());
  }
}
