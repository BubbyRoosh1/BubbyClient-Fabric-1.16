package bubby.client.gui.hud;

import java.util.ArrayList;

public class PlayerRadar extends PinnableFrame
{
  public PlayerRadar()
  {
    super("PlayerRadar", new ArrayList<>(), new ArrayList<>(), 50);
  }

  public void
  updateFrame()
  {
    this.leftText.clear();
    this.rightText.clear();
    mc.world.getPlayers().stream()
            .sorted((player1, player2) -> Double.compare(mc.player.distanceTo(player1), mc.player.distanceTo(player2)))
            .forEach(e ->
            {
              if(e == mc.player)
                return;
              this.leftText.add(e.getDisplayName().getString() + " ");
              this.rightText.add(Math.round(mc.player.getPos().distanceTo(e.getPos())) + "m");
            });
  }
}
