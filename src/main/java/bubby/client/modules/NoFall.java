package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Module
{
  public NoFall()
  {
    super("NoFall", "Prevents fall damage", -1, Category.Player, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.player.fallDistance >= 2 && !mc.player.isFallFlying())
      mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket(true));
  }
}
