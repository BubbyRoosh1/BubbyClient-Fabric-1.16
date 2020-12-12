package bubby.client.commands;

import bubby.api.command.Command;
import bubby.client.BubbyClient;

public class Shrug extends Command
{
  public Shrug()
  {
    super("shrug", "\u00af\\_(\u30b7)_/\u00af");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    BubbyClient.MC.player.sendChatMessage("\u00af\\_(\u30b7)_/\u00af");
  }
}
