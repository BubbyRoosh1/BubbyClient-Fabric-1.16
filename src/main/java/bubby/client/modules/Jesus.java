package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class Jesus extends Module
{
  ArrayList<Material> fluids;

  public Jesus()
  {
    super("Jesus", "Allows you to walk on water", -1, Category.Player, true);
    fluids = new ArrayList<Material>();
    fluids.add(Material.WATER);
    fluids.add(Material.LAVA);
    fluids.add(Material.UNDERWATER_PLANT);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    Entity e = mc.player.getVehicle() != null ? mc.player.getVehicle() : mc.player;
    if(e.isSneaking() || e.fallDistance > 4.f) return;

    if(fluids.contains(mc.world.getBlockState(new BlockPos(e.getPos().add(0, 0.3, 0))).getMaterial()))
      e.setVelocity(e.getVelocity().x, 0.08, e.getVelocity().z);
    else if(fluids.contains(mc.world.getBlockState(new BlockPos(e.getPos().add(0, 0.1, 0))).getMaterial()))
      e.setVelocity(e.getVelocity().x, 0.05, e.getVelocity().z);
    else if(fluids.contains(mc.world.getBlockState(new BlockPos(e.getPos().add(0, 0.05, 0))).getMaterial()))
      e.setVelocity(e.getVelocity().x, 0.01, e.getVelocity().z);
    else if(fluids.contains(mc.world.getBlockState(new BlockPos(e.getPos())).getMaterial()))
    {
      e.setVelocity(e.getVelocity().x, -0.005, e.getVelocity().z);
      e.setOnGround(true);
    }
  }
}
