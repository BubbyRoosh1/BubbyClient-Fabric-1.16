package bubby.client.utils;

import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import static bubby.client.BubbyClient.MC;

public class WorldUtils
{
  // need to do funy recursion for finding neighbors and placing on those to
  // get to the end result haha
  public static void
  placeBlock(Vec3d vec, Hand hand, Direction direction)
  {
    MC.interactionManager.interactBlock(MC.player, MC.world, hand, new BlockHitResult(vec, direction, new BlockPos(vec), false));
    MC.player.swingHand(Hand.MAIN_HAND);
  }
}
