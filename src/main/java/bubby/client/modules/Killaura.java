package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.SliderSetting;
import bubby.client.BubbyClient;
import bubby.client.utils.EntityUtils;
import com.google.common.collect.Streams;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Killaura extends Module
{
  CheckBoxSetting players = new CheckBoxSetting("Killaura Players", this, true);
  CheckBoxSetting mobs = new CheckBoxSetting("Killaura Mobs", this, true);
  CheckBoxSetting animals = new CheckBoxSetting("Killaura Animals", this, true);
  CheckBoxSetting delayy = new CheckBoxSetting("Killaura Delay", this, true);
  SliderSetting range = new SliderSetting("Killaura Range", this, 6, 0, 6, false);
  SliderSetting cps = new SliderSetting("Killaura CPS", this, 10, 0, 20, false);
  private int delay = 0;

  public Killaura()
  {
    super("Killaura", "autowack", -1, Category.Combat, true);
    addSetting(players);
    addSetting(mobs);
    addSetting(animals);
    addSetting(delayy);
    addSetting(range);
    addSetting(cps);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    delay++;
    int rDelay = (int) Math.round(20 / cps.get());

    List<Entity> targets = Streams.stream(mc.world.getEntities()).filter(e ->
            (e instanceof PlayerEntity && players.get())
                    || (e instanceof Monster && mobs.get())
                    || (EntityUtils.isAnimal(e) && animals.get()))
            .sorted((a, b) -> Float.compare(a.distanceTo(mc.player), b.distanceTo(mc.player)))
            .collect(Collectors.toList());

    for(Entity e : targets)
    {
      if(!BubbyClient.friends.isFriend(e.getEntityName()))
      {
        if(mc.player.distanceTo(e) > range.get()
                || ((LivingEntity) e).getHealth() <= 0
                || e.getEntityName().equals(mc.getSession().getUsername())
                || e == mc.player.getVehicle())
          continue;

        if(((delay > rDelay || rDelay == 0) && !delayy.get()) || (mc.player.getAttackCooldownProgress(mc.getTickDelta()) == 1.f && delayy.get()))
        {
          EntityUtils.attackEntity(e);
          delay = 0;
        }
      }
    }
  }
}
