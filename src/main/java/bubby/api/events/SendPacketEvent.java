package bubby.api.events;

import bubby.api.eventapi.Event;
import net.minecraft.network.Packet;

public class SendPacketEvent extends Event
{
  public Packet<?> packet;

  public SendPacketEvent(Packet<?> packet)
  {
    this.packet = packet;
  }
}
