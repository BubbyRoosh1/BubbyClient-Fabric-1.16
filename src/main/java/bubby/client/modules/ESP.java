package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.RenderEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.ModeSetting;
import bubby.client.BubbyClient;
import bubby.client.utils.EntityUtils;
import bubby.client.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;

import java.util.ArrayList;

public class ESP extends Module
{
  CheckBoxSetting rPlayers = new CheckBoxSetting("ESP Players", this, true);
  CheckBoxSetting rMobs = new CheckBoxSetting("ESP Mobs", this, true);
  CheckBoxSetting rAnimals = new CheckBoxSetting("ESP Animals", this, true);
  CheckBoxSetting rItems = new CheckBoxSetting("ESP Items", this, true);
  CheckBoxSetting rCrystals = new CheckBoxSetting("ESP Crystals", this, true);
  ModeSetting<Mode> mode = new ModeSetting<Mode>("ESP Mode", this, Mode.Glow, Mode.values());
  ArrayList<Entity> players = new ArrayList<>();
  ArrayList<Entity> friends = new ArrayList<>();
  ArrayList<Entity> mobs = new ArrayList<>();
  ArrayList<Entity> animals = new ArrayList<>();
  ArrayList<Entity> items = new ArrayList<>();
  ArrayList<Entity> crystals = new ArrayList<>();

  public ESP()
  {
    super("ESP", "See entities pog", -1, Category.Render, true);
    addSetting(rPlayers);
    addSetting(rMobs);
    addSetting(rAnimals);
    addSetting(rItems);
    addSetting(rCrystals);
    addSetting(mode);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    players.clear();
    friends.clear();
    mobs.clear();
    animals.clear();
    items.clear();
    crystals.clear();

    mc.world.getEntities().forEach(e ->
    {
      if(e instanceof PlayerEntity && rPlayers.get() && e != mc.player)
        if(BubbyClient.friends.isFriend(e.getEntityName()))
          friends.add(e);
        else
          players.add(e);
      else if(e instanceof Monster && rMobs.get())
        mobs.add(e);
      else if(EntityUtils.isAnimal(e) && rAnimals.get())
        animals.add(e);
      else if(e instanceof ItemEntity && rItems.get())
        items.add(e);
      else if(e instanceof EndCrystalEntity && rCrystals.get())
        crystals.add(e);
      if(mode.get() == Mode.Box)
        e.setGlowing(false);
    });
  }

  public void
  onDisable()
  {
    super.onDisable();
    for(Entity e : mc.world.getEntities())
      if(e != mc.player)
        if(e.isGlowing()) e.setGlowing(false);
  }

  @PogEvent
  public void
  onRender(RenderEvent event)
  {
    if(mode.get() == Mode.Glow)
    {
      friends.forEach(e -> EntityUtils.setGlowing(e, Formatting.GREEN, "friends"));
      players.forEach(e -> EntityUtils.setGlowing(e, Formatting.RED, "players"));
      mobs.forEach(e -> EntityUtils.setGlowing(e, Formatting.BLUE, "mobs"));
      animals.forEach(e -> EntityUtils.setGlowing(e, Formatting.YELLOW, "passive"));
      items.forEach(e -> EntityUtils.setGlowing(e, Formatting.GOLD, "items"));
      crystals.forEach(e -> EntityUtils.setGlowing(e, Formatting.LIGHT_PURPLE, "crystals"));
    }
    else
    {
      friends.forEach(e -> RenderUtils.drawBox(e.getBoundingBox(), 0.4f, 1.f, 0.f, 1.f, false));
      players.forEach(e -> RenderUtils.drawBox(e.getBoundingBox(), 0.8f, 0.f, 0.1f, 1.f, false));
      mobs.forEach(e -> RenderUtils.drawBox(e.getBoundingBox(), 0.5f, 0.5f, 1.f, 1.f, false));
      animals.forEach(e -> RenderUtils.drawBox(e.getBoundingBox(), 0.9f, 1.f, 0.3f, 1.f, false));
      items.forEach(e -> RenderUtils.drawBox(e.getBoundingBox(), 0.9f, 0.5f, 0.1f, 1.f, false));
      crystals.forEach(e -> RenderUtils.drawBox(e.getBoundingBox(), 1.f, 0.f, 0.8f, 1.f, false));
    }
  }

  enum Mode
  {
    Glow,
    Box
  }
}
