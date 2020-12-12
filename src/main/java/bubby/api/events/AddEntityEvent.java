package bubby.api.events;

import bubby.api.eventapi.Event;
import net.minecraft.entity.Entity;

public class AddEntityEvent extends Event
{
  public Entity entity;

  public AddEntityEvent(Entity entity)
  {
    this.entity = entity;
  }
}
