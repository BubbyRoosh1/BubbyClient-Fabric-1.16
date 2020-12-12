package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.WalkingUpdateEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;

public class NoClip extends Module
{
  public NoClip()
  {
    super("NoClip", "no, you cannot clip me blocks", -1, Category.World, true);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.player.noClip = false;
    mc.player.setOnGround(true);
  }

  @PogEvent
  public void
  onWalkingUpdate(WalkingUpdateEvent event)
  {
    mc.player.noClip = true;
    mc.player.setOnGround(false);
  }
}
