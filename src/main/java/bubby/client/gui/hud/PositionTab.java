package bubby.client.gui.hud;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class PositionTab extends PinnableFrame
{
  public PositionTab()
  {
    super("PositionTab", new ArrayList<>(), new ArrayList<>(), 50);
  }

  public void
  updateFrame()
  {
    if(mc.world != null)
    {

      boolean nether = mc.world.getRegistryKey().getValue().getPath().contains("nether");
      BlockPos pos = mc.player.getBlockPos();
      BlockPos pos2;
      Vec3d vec = mc.player.getPos();
      if(nether)
        pos2 = new BlockPos(vec.getX() * 8, vec.getY(), vec.getZ() * 8);
      else
        pos2 = new BlockPos(vec.getX() / 8, vec.getY(), vec.getZ() / 8);

      this.leftText.clear();
      this.rightText.clear();
      this.leftText.add("X: ");
      this.leftText.add("Y: ");
      this.leftText.add("Z: ");
      this.leftText.add("Pitch: ");
      this.leftText.add("Yaw: ");
      this.rightText.add((nether ? "\u00a74" : "\u00a7b") + pos.getX() + " \u00a77[" + (nether ? "\u00a7b" : "\u00a74") + pos2.getX() + "\u00a77]");
      this.rightText.add((nether ? "\u00a74" : "\u00a7b") + pos.getY() + " \u00a77[" + (nether ? "\u00a7b" : "\u00a74") + pos2.getY() + "\u00a77]");
      this.rightText.add((nether ? "\u00a74" : "\u00a7b") + pos.getZ() + " \u00a77[" + (nether ? "\u00a7b" : "\u00a74") + pos2.getZ() + "\u00a77]");
      this.rightText.add("" + (int) MathHelper.wrapDegrees(mc.player.pitch));
      this.rightText.add("" + (int) MathHelper.wrapDegrees(mc.player.yaw));
    }
  }
}
