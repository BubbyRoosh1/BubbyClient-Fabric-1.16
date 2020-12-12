package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.RenderEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.SliderSetting;
import bubby.client.BubbyClient;
import bubby.client.utils.EntityUtils;
import bubby.client.utils.RenderUtils;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Tracers extends Module
{
  double x, y, z;

  CheckBoxSetting players = new CheckBoxSetting("Tracers Players", this, true);
  CheckBoxSetting animals = new CheckBoxSetting("Tracers Animals", this, true);
  CheckBoxSetting mobs = new CheckBoxSetting("Tracers Mobs", this, true);
  CheckBoxSetting crystals = new CheckBoxSetting("Tracers Crystals", this, true);
  CheckBoxSetting items = new CheckBoxSetting("Tracers Items", this, true);
  CheckBoxSetting chests = new CheckBoxSetting("Tracers Chests", this, true);
  CheckBoxSetting endChests = new CheckBoxSetting("Tracers EndChests", this, true);
  CheckBoxSetting shulkers = new CheckBoxSetting("Tracers Shulkers", this, true);
  SliderSetting thiccness = new SliderSetting("Tracers Thickness", this, 2, 0, 5, false);

  public Tracers()
  {
    super("Tracers", "Draws lines to various entities", -1, Category.Render, true);
    addSetting(players);
    addSetting(animals);
    addSetting(mobs);
    addSetting(crystals);
    addSetting(items);
    addSetting(chests);
    addSetting(endChests);
    addSetting(shulkers);
    addSetting(thiccness);
  }

  @PogEvent
  public void
  onRender(RenderEvent event)
  {
    float thicc = (float) thiccness.get();
    Vec3d eyes = new Vec3d(0, 0, 75)
            .rotateX(-(float) Math.toRadians(mc.cameraEntity.pitch))
            .rotateY(-(float) Math.toRadians(mc.cameraEntity.yaw))
            .add(mc.cameraEntity.getPos().add(0, mc.cameraEntity.getEyeHeight(mc.cameraEntity.getPose()), 0));

    mc.world.getEntities().forEach(e ->
    {
      x = e.getX();
      y = e.getY() + e.getHeight() / 2;
      z = e.getZ();
      if(e instanceof PlayerEntity && e != mc.player && e != mc.cameraEntity && players.get())
        if(BubbyClient.friends.isFriend(e.getEntityName()))
          RenderUtils.drawLine(x, y, z, eyes.x, eyes.y, eyes.z, 0.5f, 1.f, 0.5f, 1.f, thicc);
        else
          RenderUtils.drawLine(x, y, z, eyes.x, eyes.y, eyes.z, 0.8f, 0.f, 0.1f, 1.f, thicc);
      else if(e instanceof Monster && mobs.get())
        RenderUtils.drawLine(x, y, z, eyes.x, eyes.y, eyes.z, 0.5f, 0.5f, 1.f, 1.f, thicc);
      else if(EntityUtils.isAnimal(e) && animals.get())
        RenderUtils.drawLine(x, y, z, eyes.x, eyes.y, eyes.z, 0.f, 1.f, 0.f, 1.f, thicc);
      else if(e instanceof ItemEntity && items.get())
        RenderUtils.drawLine(x, y, z, eyes.x, eyes.y, eyes.z, 1.f, 0.7f, 0.f, 1.f, thicc);
      else if(e instanceof EndCrystalEntity && crystals.get())
        RenderUtils.drawLine(x, y, z, eyes.x, eyes.y, eyes.z, 1.f, 0.f, 1.f, 1.f, thicc);
    });

    mc.world.blockEntities.forEach(e ->
    {
      x = e.getPos().getX() + 0.5;
      y = e.getPos().getY() + 0.5;
      z = e.getPos().getZ() + 0.5;

      if(e instanceof ChestBlockEntity && chests.get())
        RenderUtils.drawLine(x, y, z, eyes.x, eyes.y, eyes.z, 1.9f, 1.5f, 0.3f, 1.f, thicc);
      else if(e instanceof EnderChestBlockEntity && endChests.get())
        RenderUtils.drawLine(x, y, z, eyes.x, eyes.y, eyes.z, 1.f, 0.05f, 1.f, 1.f, thicc);
      else if(e instanceof ShulkerBoxBlockEntity && shulkers.get())
        RenderUtils.drawLine(x, y, z, eyes.x, eyes.y, eyes.z, 0.5f, 0.2f, 1.f, 1.f, thicc);
    });
  }
}
