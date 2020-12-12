package bubby.client.commands;

import bubby.api.chat.Chat;
import bubby.api.command.Command;
import bubby.client.BubbyClient;

public class Coords extends Command
{
  public Coords()
  {
    super("coords", "copies your coords to your clipboard");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    String coords = "X: " + (int) BubbyClient.MC.player.getPos().getX() + " Y: " + (int) BubbyClient.MC.player.getPos().getY() + " Z: " + (int) BubbyClient.MC.player.getPos().getZ();
    BubbyClient.MC.keyboard.setClipboard(coords);
    Chat.info("Coords copied to clipboard");
  }
}
