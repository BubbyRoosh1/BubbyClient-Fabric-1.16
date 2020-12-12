package bubby.client.commands;

import bubby.api.chat.Chat;
import bubby.api.command.Command;
import bubby.client.BubbyClient;

public class Pitch extends Command
{
  public Pitch()
  {
    super("pitch", "Sets your player's pitch");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    BubbyClient.MC.player.pitch = Integer.parseInt(args[0]);
    Chat.info("Pitch set to: " + args[0]);
  }
}
