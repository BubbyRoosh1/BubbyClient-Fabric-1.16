package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class FastFall extends Module
{
  public FastFall()
  {
    super("FastFall", "Fall fast in h o l e", -1, Category.Movement, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.world == null
            || mc.player == null
            || mc.player.isInLava()
            || mc.player.isSubmergedInWater())
      return;

    if(mc.player.isOnGround())
    {
      mc.player.setVelocity(mc.player.getVelocity().x, -1, mc.player.getVelocity().z);
      mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket(true));
    }
  }
}
