package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.SendPacketEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Criticals extends Module
{
  public Criticals()
  {
    super("Criticals", "Lets you get criticals without jumping", -1, Category.Combat, true);
  }

  @PogEvent
  public void
  onPacket(SendPacketEvent event)
  {
    if(event.packet instanceof PlayerInteractEntityC2SPacket)
    {
      PlayerInteractEntityC2SPacket packet = (PlayerInteractEntityC2SPacket) event.packet;

      if(packet.getType() == PlayerInteractEntityC2SPacket.InteractionType.ATTACK)
      {
        if(!mc.player.isOnGround())
          return;
        if(mc.player.isInLava() || mc.player.isTouchingWater())
          return;
        double posX = mc.player.getX();
        double posY = mc.player.getY();
        double posZ = mc.player.getZ();

        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY + 0.0625, posZ, true));
        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY, posZ, false));
      }
    }
  }
}
