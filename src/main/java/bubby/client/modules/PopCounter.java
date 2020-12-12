package bubby.client.modules;

import bubby.api.chat.Chat;
import bubby.api.eventapi.PogEvent;
import bubby.api.events.ReadPacketEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;

import java.util.HashMap;

public class PopCounter extends Module
{
  private HashMap<String, Integer> pops = new HashMap<>();

  public PopCounter()
  {
    super("PopCounter", "Counts totem pops", -1, Category.Combat, true);
  }

  public void
  onDisable()
  {
    super.onDisable();
    pops.clear();
  }

  @PogEvent
  public void
  onReadPacket(ReadPacketEvent event)
  {
    if(event.packet instanceof EntityStatusS2CPacket)
    {
      EntityStatusS2CPacket pack = (EntityStatusS2CPacket) event.packet;

      if(pack.getStatus() == 35)
      {
        handlePop(pack.getEntity(mc.world));
      }
    }
  }

  @PogEvent
  public void
  onTick(TickEvent tick)
  {
    if(mc.world == null)
      return;

    mc.world.getPlayers().forEach(player -> {
      if(player.getHealth() <= 0)
      {
        if(pops.containsKey(player.getEntityName()))
        {
          Chat.info(player.getEntityName() + " died after popping " + pops.get(player.getEntityName()) + " totems");
          pops.remove(player.getEntityName(), pops.get(player.getEntityName()));
        }
      }
    });
  }

  private void
  handlePop(Entity entity)
  {
    if(pops == null)
      pops = new HashMap<>();

    if(entity == mc.player)
      return;

    if(pops.get(entity.getEntityName()) == null)
    {
      pops.put(entity.getEntityName(), 1);
      Chat.info(entity.getEntityName() + " popped 1 totem");
    }
    else if(!(pops.get(entity.getEntityName()) == null))
    {
      int popc = pops.get(entity.getEntityName());
      popc += 1;
      pops.put(entity.getEntityName(), popc);
      Chat.info(entity.getEntityName() + " popped " + popc + " totems");
    }
  }
}
