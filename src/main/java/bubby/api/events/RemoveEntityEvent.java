package bubby.api.events;

import bubby.api.eventapi.Event;
import net.minecraft.entity.Entity;

public class RemoveEntityEvent extends Event
{
  public Entity entity;

  public RemoveEntityEvent(Entity e)
  {
    this.entity = e;
  }
}
