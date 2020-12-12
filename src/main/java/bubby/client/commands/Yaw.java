package bubby.client.commands;

import bubby.api.chat.Chat;
import bubby.api.command.Command;
import bubby.client.BubbyClient;

public class Yaw extends Command
{
  public Yaw()
  {
    super("yaw", "Sets your player's yaw");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    BubbyClient.MC.player.yaw = Integer.parseInt(args[0]);
    Chat.info("Yaw set to: " + args[0]);
  }
}
