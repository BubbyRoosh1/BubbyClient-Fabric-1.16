package bubby.api.events;

import bubby.api.eventapi.Event;
import net.minecraft.item.ItemStack;

public class DropItemEvent extends Event
{
  public ItemStack itemStack;

  public DropItemEvent(ItemStack itemStack)
  {
    this.itemStack = itemStack;
  }
}
