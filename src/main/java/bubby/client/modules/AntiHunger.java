package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.SendPacketEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.ModeSetting;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;

public class AntiHunger extends Module {

    public AntiHunger() 
    {
      super("AntiHunger", "Prevents hunger", -1, Category.Misc, true);
    }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if (((ClientCommandC2SPacket) event.packet).getMode() == ClientCommandC2SPacket.Mode.START_SPRINTING 
        || ((ClientCommandC2SPacket) event.packet).getMode() == ClientCommandC2SPacket.Mode.STOP_SPRINTING)
        event.setCancelled(true);
  }
}
