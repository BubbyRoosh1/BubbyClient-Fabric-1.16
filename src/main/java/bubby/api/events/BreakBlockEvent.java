package bubby.api.events;

import bubby.api.eventapi.Event;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BreakBlockEvent extends Event
{
  public BlockPos blockPos;
  public BlockState blockState;

  public BreakBlockEvent(BlockPos blockPos, World world)
  {
    this.blockPos = blockPos;
    this.blockState = world.getBlockState(blockPos);
  }
}
