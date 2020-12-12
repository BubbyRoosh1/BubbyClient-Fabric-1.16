package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.ModeSetting;
import bubby.api.setting.SliderSetting;


public class Speed extends Module
{
  ModeSetting<Mode> mode = new ModeSetting<Mode>("Speed Modes", this, Mode.OnGround, Mode.values());
  SliderSetting speed = new SliderSetting("Speed Speed", this, 2, 0, 10, false);
  private boolean jumping;

  public Speed()
  {
    super("Speed", "zoom", -1, Category.Movement, true);
    //addSetting(mode); //add when there are multiple modes
    addSetting(speed);
  }

  @PogEvent
  public void onTick(TickEvent event)
  {
    if(mc.options.keySneak.isPressed()) return;
    double speeds = speed.get() / 30;


    if(mc.options.keyJump.isPressed() || mc.player.fallDistance > 0.25) return;

    if(jumping && mc.player.getY() >= mc.player.prevY + 0.399994D)
    {
      mc.player.setVelocity(mc.player.getVelocity().x, -0.9, mc.player.getVelocity().z);
      mc.player.setPos(mc.player.getX(), mc.player.prevY, mc.player.getZ());
      jumping = false;
    }

    if(mc.player.forwardSpeed != 0.0F && !mc.player.horizontalCollision)
    {
      if(mc.player.verticalCollision)
      {
        mc.player.setVelocity(mc.player.getVelocity().x * (0.85 + speeds), mc.player.getVelocity().y, mc.player.getVelocity().z * (0.85 + speeds));
        jumping = true;
        mc.player.jump();
        // 1.0379
      }

      if(jumping && mc.player.getY() >= mc.player.prevY + 0.399994D)
      {
        mc.player.setVelocity(mc.player.getVelocity().x, -100, mc.player.getVelocity().z);
        jumping = false;
      }
    }
  }

  enum Mode
  {
    OnGround
  }
}
