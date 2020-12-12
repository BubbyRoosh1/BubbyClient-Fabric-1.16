package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.*;
import bubby.api.module.*;
import bubby.api.setting.*;
import bubby.client.BubbyClient;
import bubby.client.utils.FakePlayer;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

public class Freecam extends Module {
  private FakePlayer fake;
  private Vec3d oldPos;

  
  CheckBoxSetting flight = new CheckBoxSetting("Freecam Flight", this, true);
  SliderSetting speed = new SliderSetting("Freecam Speed", this, 1, 0, 5, false);
  CheckBoxSetting noclip = new CheckBoxSetting("Freecam NoClip", this, true);
  boolean oldFlying;

  public Freecam() {

    super("Freecam", "Player move but player not move hmmmm", -1, Category.Player, true);
    addSetting(flight);
    addSetting(speed);
    addSetting(noclip);
  }

  public void
  onEnable() {
    oldPos = mc.player.getPos();
    fake = new FakePlayer();
    fake.copyPositionAndRotation(mc.player);
    fake.setBoundingBox(fake.getBoundingBox().expand(0.1));
    fake.spawn();
    super.onEnable();
    if (flight.get()) {
      mc.player.abilities.flying = true;
      mc.player.abilities.allowFlying = true;
    } else {
      mc.player.abilities.flying = false;
      mc.player.abilities.allowFlying = false;
    }

  }

  public void
  onDisable() {
    super.onDisable();

    fake.despawn();
    mc.player.setPos(oldPos.x, oldPos.y, oldPos.z);
    oldFlying = mc.player.abilities.allowFlying;
    mc.player.abilities.allowFlying = false;
    mc.player.abilities.flying = false;
    mc.player.noClip = false;
    mc.player.setOnGround(true);
  }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event) {
    if (event.packet instanceof ClientCommandC2SPacket || event.packet instanceof PlayerMoveC2SPacket)
      event.setCancelled(true);
  }

  @PogEvent
  public void
  onWalkingUpdate(WalkingUpdateEvent event)
  {
    if(noclip.get())
    {
      mc.player.noClip = true;
      mc.player.setOnGround(false);
    }
  }

  @PogEvent
  public void
  onTick(TickEvent event) 
  {
    if (flight.get()) 
    {
      mc.player.abilities.setFlySpeed((float) speed.get() / 10);
      mc.player.abilities.allowFlying = true;
      mc.player.abilities.flying = true;
    }
    else 
    {
      mc.player.abilities.allowFlying = false;
      mc.player.abilities.flying = false;
    }
  }
}
