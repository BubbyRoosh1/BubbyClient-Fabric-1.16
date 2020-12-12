package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.ReadPacketEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.SliderSetting;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

public class Velocity extends Module
{
  SliderSetting speed = new SliderSetting("Velocity PushSpeed", this, 0, 0, 100, true);

  public Velocity()
  {
    super("Velocity", "s t o p", -1, Category.Player, true);
    addSetting(speed);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.player.pushSpeedReduction = 0;
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    mc.player.pushSpeedReduction = (float) (1 - speed.get() / 100);
  }

  @PogEvent
  public void
  onPacketRead(ReadPacketEvent event)
  {
    if(event.packet instanceof EntityVelocityUpdateS2CPacket && ((EntityVelocityUpdateS2CPacket) event.packet).getId() == mc.player.getEntityId())
      event.setCancelled(true);
    else if(event.packet instanceof ExplosionS2CPacket)
      event.setCancelled(true);
  }
}
