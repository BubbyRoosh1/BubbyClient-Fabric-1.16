package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.SendPacketEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;

public class PortalGodMode extends Module
{
  public PortalGodMode()
  {
    super("PortalGod", "God mode through portals", -1, Category.World, true);
  }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if(event.packet instanceof TeleportConfirmC2SPacket)
      event.setCancelled(true);
  }
}
