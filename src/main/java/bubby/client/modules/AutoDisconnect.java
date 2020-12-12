package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.ModeSetting;
import bubby.api.setting.SliderSetting;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;

public class AutoDisconnect extends Module
{
  ModeSetting<Mode> mode = new ModeSetting<Mode>("Disconnect Mode", this, Mode.Health, Mode.values());
  SliderSetting health = new SliderSetting("Disconnect Health", this, 10, 0, 20, true);
  SliderSetting range = new SliderSetting("Disconnect Range", this, 10, 0, 100, true);

  public AutoDisconnect()
  {
    super("Disconnect", "Disconnects you automatically according to the set parameters", -1, Category.Player, true);
    addSetting(mode);
    addSetting(health);
    addSetting(range);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mode.get() == Mode.Health && mc.player.getHealth() + mc.player.getAbsorptionAmount() < health.get())
    {
      mc.player.networkHandler.getConnection().disconnect(new LiteralText("Player's health was below " + health.get()));
      setToggled(false);
    }

    mc.world.getEntities()
            .forEach(entity -> {
              if(mode.get() == Mode.Crystal && entity instanceof EndCrystalEntity && mc.player.distanceTo(entity) <= range.get())
              {
                mc.player.networkHandler.getConnection().disconnect(new LiteralText("Player was too close to a crystal"));
                setToggled(false);
              }
              else if(mode.get() == Mode.Player && entity instanceof PlayerEntity && mc.player.distanceTo(entity) <= range.get() && entity != mc.player)
              {
                mc.player.networkHandler.getConnection().disconnect(new LiteralText("Player was too close to another player"));
                setToggled(false);
              }
            });
  }

  enum Mode
  {
    Health,
    Crystal,
    Player
  }
}
