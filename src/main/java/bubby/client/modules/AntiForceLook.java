package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.ReadPacketEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;

public class AntiForceLook extends Module
{
  public AntiForceLook()
  {
    super("NoForceLook", "Prevents servers from forcing your pitch/yaw/movement", -1, Category.Player, true);
  }

  @PogEvent
  public void
  onPacketRead(ReadPacketEvent event)
  {
    if(mc.world == null || mc.player == null)
      return;

    if(event.packet instanceof PlayerPositionLookS2CPacket)
      event.setCancelled(true);
  }
}
