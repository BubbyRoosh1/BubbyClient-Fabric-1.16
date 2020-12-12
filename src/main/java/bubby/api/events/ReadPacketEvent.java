package bubby.api.events;

import bubby.api.eventapi.Event;
import net.minecraft.network.Packet;

public class ReadPacketEvent extends Event
{
  public Packet<?> packet;

  public ReadPacketEvent(Packet<?> packet)
  {
    this.packet = packet;
  }
}
