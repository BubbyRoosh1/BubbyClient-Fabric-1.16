package bubby.client.utils;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

import static bubby.client.BubbyClient.MC;

public class HoleUtils
{
  public static ArrayList<Hole>
  getHolesWithinRange(int range)
  {
    ArrayList<Hole> toReturn = new ArrayList<Hole>();
    for(int x = range; x >= -range; x--)
    {
      for(int y = range; y >= -range; y--)
      {
        for(int z = range; z >= -range; z--)
        {
          BlockPos pos = new BlockPos(MC.player.getX() + x, MC.player.getY() + y, MC.player.getZ() + z);
          int safeBlockAmount = 0;
          if(!(MC.world.getBlockState(pos).getBlock() == Blocks.AIR))
            continue;
          if(!(MC.world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock() == Blocks.AIR))
            continue;
          if(!(MC.world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ())).getBlock() == Blocks.AIR))
            continue;

          //block you stand *on*
          if(MC.world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() == Blocks.BEDROCK)
          {
            safeBlockAmount += 2;
          }
          else if(MC.world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() == Blocks.OBSIDIAN)
          {
            safeBlockAmount += 1;
          }
          else
            continue;

          //sides
          if(MC.world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock() == Blocks.BEDROCK)
          {
            safeBlockAmount += 2;
          }
          else if(MC.world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock() == Blocks.OBSIDIAN)
          {
            safeBlockAmount += 1;
          }
          else
            continue;

          if(MC.world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock() == Blocks.BEDROCK)
          {
            safeBlockAmount += 2;
          }
          else if(MC.world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock() == Blocks.OBSIDIAN)
          {
            safeBlockAmount += 1;
          }
          else
            continue;

          if(MC.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock() == Blocks.BEDROCK)
          {
            safeBlockAmount += 2;
          }
          else if(MC.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock() == Blocks.OBSIDIAN)
          {
            safeBlockAmount += 1;
          }
          else
            continue;

          if(MC.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock() == Blocks.BEDROCK)
          {
            safeBlockAmount += 2;
          }
          else if(MC.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock() == Blocks.OBSIDIAN)
          {
            safeBlockAmount += 1;
          }
          else
            continue;

          toReturn.add(new Hole(safeBlockAmount, pos));
        }
      }
    }

    return toReturn;
  }
}
