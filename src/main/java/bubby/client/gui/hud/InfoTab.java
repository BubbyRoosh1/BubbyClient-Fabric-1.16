package bubby.client.gui.hud;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.ReadPacketEvent;
import bubby.client.BubbyClient;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

public class InfoTab extends PinnableFrame
{
  private double tps = 20;
  private long lp, pt = 0;
  private int fps, ping;

  public InfoTab()
  {
    super("InfoTab", new ArrayList<>(), new ArrayList<>(), 50);
    BubbyClient.eventManager.register(this);
  }

  public void
  updateFrame()
  {
    String s = "\u00a77";
    if(lp + 7600 < System.currentTimeMillis()) s += "....";
    else if(lp + 5000 < System.currentTimeMillis()) s += "...";
    else if(lp + 2500 < System.currentTimeMillis()) s += "..";
    else if(lp + 1200 < System.currentTimeMillis()) s += ".";
    fps = Integer.parseInt(mc.fpsDebugString.split(" ")[0].split("/")[0]);
    try
    {
      ping = mc.getNetworkHandler().getPlayerListEntry(mc.player.getGameProfile().getId()).getLatency();
    }
    catch(Exception e)
    {
    }

    this.leftText.clear();
    this.rightText.clear();
    this.leftText.add("FPS: ");
    this.leftText.add("Ping: ");
    this.leftText.add("TPS: ");
    this.rightText.add(getColorString(fps, 120, 60, 30, 15, 10, false) + fps);
    this.rightText.add(getColorString(ping, 75, 180, 300, 500, 1000, true) + ping);
    this.rightText.add(getColorString((int) tps, 18, 15, 12, 8, 4, false) + tps + s);

  }

  @PogEvent
  public void
  onReadPacket(ReadPacketEvent event)
  {
    lp = System.currentTimeMillis();
    if(event.packet instanceof WorldTimeUpdateS2CPacket)
    {
      long t = System.currentTimeMillis();
      if(t < 500) return;
      long to = Math.abs(1000 - (t - pt)) + 1000;
      tps = Math.round(MathHelper.clamp(20 / ((double) to / 1000), 0, 20) * 100.d) / 100.d;
      pt = t;
    }
  }

  public String
  getColorString(int value, int best, int good, int mid, int bad, int worst, boolean rev)
  {
    if(!rev ? value > best : value < best) return "\u00a72";
    else if(!rev ? value > good : value < good) return "\u00a7a";
    else if(!rev ? value > mid : value < mid) return "\u00a7e";
    else if(!rev ? value > bad : value < bad) return "\u00a76";
    else if(!rev ? value > worst : value < worst) return "\u00a7c";
    else return "\u00a74";
  }

}
