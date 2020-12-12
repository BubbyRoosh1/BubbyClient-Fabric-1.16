package bubby.client.modules;

import bubby.api.chat.Chat;
import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import net.minecraft.client.gui.screen.DeathScreen;

public class AutoRespawn extends Module
{
  //mm clean r e a d a b l e code
  CheckBoxSetting deathCoords = new CheckBoxSetting("AutoRespawn DeathCoords", this, true);
  boolean sentDeathCoords = false;

  public AutoRespawn()
  {
    super("AutoRespawn", "Allows you to respawn automatically", -1, Category.World, true);
    addSetting(deathCoords);
  }

  public void
  onEnable()
  {
    super.onEnable();
    sentDeathCoords = false;
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.currentScreen instanceof DeathScreen)
    {
      if(deathCoords.get() && !sentDeathCoords)
      {
        Chat.info("Died at X: " + (int) mc.player.getX() + " Y: " + (int) mc.player.getY() + " Z: " + (int) mc.player.getZ());
        sentDeathCoords = true;
      }

      mc.player.requestRespawn();
      mc.openScreen(null);
    }
    else if(!(mc.currentScreen instanceof DeathScreen) && sentDeathCoords == true)
      sentDeathCoords = false;
  }
}
