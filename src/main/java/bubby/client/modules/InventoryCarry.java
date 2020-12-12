package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.SendPacketEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.network.packet.c2s.play.GuiCloseC2SPacket;

public class InventoryCarry extends Module
{
  public InventoryCarry()
  {
    super("XCarry", "Allows you to carry extra items in the crafting slots", -1, Category.Player, true);
  }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if(event.packet instanceof GuiCloseC2SPacket)
      event.setCancelled(true);
  }
}
