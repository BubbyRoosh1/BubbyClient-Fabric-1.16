package bubby.client.gui.hud;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityList extends PinnableFrame
{
  ArrayList<Entity> elist;

  public EntityList()
  {
    super("EntityList", new ArrayList<>(), new ArrayList<>(), 50);
  }

  public void
  updateFrame()
  {
    this.leftText.clear();
    elist = new ArrayList<Entity>();
    Map<String, Integer> emap = new HashMap<>();
    mc.world.getEntities().forEach(e ->
    {
      if(e instanceof PlayerEntity)
        return;
      String name = e.getDisplayName().getString();
      int add = 1;
      if(e instanceof ItemEntity)
      {
        ItemEntity ie = (ItemEntity) e;
        name = ie.getStack().toString();
        add = ie.getStack().getCount();
      }
      int count = emap.containsKey(name) ? emap.get(name) : 0;
      emap.put(name, count + add);
    });
    for(Map.Entry<String, Integer> entry : emap.entrySet())
    {
      this.leftText.add(entry.getKey() + " (" + entry.getValue() + ")");
    }
  }
}
