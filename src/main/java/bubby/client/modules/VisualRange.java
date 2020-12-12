package bubby.client.modules;

import bubby.api.chat.Chat;
import bubby.api.eventapi.PogEvent;
import bubby.api.events.AddEntityEvent;
import bubby.api.events.RemoveEntityEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.entity.player.PlayerEntity;

public class VisualRange extends Module
{
  public VisualRange()
  {
    super("VisualRange", "Sends a notification whenever a player enters or exits your visual range", -1, Category.World, true);
  }

  @PogEvent
  public void
  onEntityAdd(AddEntityEvent event)
  {
    if(event.entity instanceof PlayerEntity)
      Chat.message(event.entity.getDisplayName().getString() + " entered visual range");
  }

  @PogEvent
  public void
  onEntityRemove(RemoveEntityEvent event)
  {
    if(event.entity instanceof PlayerEntity)
      Chat.message(event.entity.getDisplayName().getString() + " left visual range");
  }
}
