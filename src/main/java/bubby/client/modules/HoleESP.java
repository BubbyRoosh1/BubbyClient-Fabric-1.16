package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.RenderEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.SliderSetting;
import bubby.client.utils.Hole;
import bubby.client.utils.HoleUtils;
import bubby.client.utils.RenderUtils;

import java.util.ArrayList;

public class HoleESP extends Module
{
  private final ArrayList<Hole> holes = new ArrayList<Hole>();
  SliderSetting range = new SliderSetting("HoleESP Range", this, 12, 0, 24, true);
  CheckBoxSetting fill = new CheckBoxSetting("HoleESP Fill", this, false);

  public HoleESP()
  {
    super("HoleESP", "Renders boxes in good holes ;)", -1, Category.Render, true);
    addSetting(range);
    addSetting(fill);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    holes.clear();
    holes.addAll(HoleUtils.getHolesWithinRange((int) range.get()));
  }

  @PogEvent
  public void
  onRender(RenderEvent event)
  {
    holes.forEach(hole ->
    {
      switch(hole.safeBlockAmount)
      {
        case 5:
          RenderUtils.drawBox(hole.blockPos, 1.f, 0.4f, 0.2f, 0.5f, fill.get());
          break;
        case 6:
        case 7:
        case 8:
        case 9:
          RenderUtils.drawBox(hole.blockPos, 1.f, 0.6f, 0.2f, 0.5f, fill.get());
          break;
        case 10:
          RenderUtils.drawBox(hole.blockPos, 0.4f, 1.f, 0.f, 0.5f, fill.get());
          break;
      }
    });

  }
}
