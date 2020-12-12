package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.SliderSetting;
import net.minecraft.util.math.Vec3d;

public class ElytraFly extends Module
{
  CheckBoxSetting flatFly = new CheckBoxSetting("ElytraFly FlatFly", this, false);
  SliderSetting speed = new SliderSetting("ElytraFly Speed", this, 0.8d, 0, 5, false);
  CheckBoxSetting uplift = new CheckBoxSetting("ElytraFly uplift", this, false);

  public ElytraFly()
  {
    super("ElytraFly", "better elytra pog", -1, Category.Movement, true);
    addSetting(flatFly);
    addSetting(speed);
    addSetting(uplift);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    Vec3d vec = new Vec3d(0, 0, speed.get())
            .rotateX(flatFly.get() ? 0.02f : -(float) Math.toRadians(mc.player.pitch))
            .rotateY(-(float) Math.toRadians(mc.player.yaw));

    if(mc.player.isFallFlying())
    {
      if(uplift.get() && mc.player.pitch < 0.f)
        return;

      if(mc.options.keyBack.isPressed()) vec = vec.multiply(-1);
      else if(mc.options.keyLeft.isPressed()) vec = vec.rotateY((float) Math.toRadians(90));
      else if(mc.options.keyRight.isPressed()) vec = vec.rotateY(-(float) Math.toRadians(90));
      else if(mc.options.keyJump.isPressed())
        vec = new Vec3d(0, speed.get(), 0);
      else if(mc.options.keySneak.isPressed())
        vec = new Vec3d(0, -speed.get(), 0);
      else if(!mc.options.keyForward.isPressed()) vec = Vec3d.ZERO;
      mc.player.setVelocity(vec);
    }
  }
}
