package bubby.client.utils;

import net.minecraft.util.math.BlockPos;

public class Hole
{
  public int safeBlockAmount;
  public BlockPos blockPos;

  public Hole(int safeBlockAmount, BlockPos blockPos)
  {
    this.safeBlockAmount = safeBlockAmount;
    this.blockPos = blockPos;
  }
}
