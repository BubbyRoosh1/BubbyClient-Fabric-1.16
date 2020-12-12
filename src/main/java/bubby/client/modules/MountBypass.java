package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.SendPacketEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

public class MountBypass extends Module
{
  public MountBypass()
  {
    super("MountBypass", "mount mmmm", -1, Category.Misc, true);
  }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if(event.packet instanceof PlayerInteractEntityC2SPacket
            && ((PlayerInteractEntityC2SPacket) event.packet).getEntity(mc.world) instanceof AbstractDonkeyEntity
            && ((PlayerInteractEntityC2SPacket) event.packet).getType() == PlayerInteractEntityC2SPacket.InteractionType.INTERACT_AT)

      event.setCancelled(true);
  }
}
