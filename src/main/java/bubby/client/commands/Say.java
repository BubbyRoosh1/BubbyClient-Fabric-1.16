package bubby.client.commands;

import bubby.api.command.Command;
import bubby.client.BubbyClient;

public class Say extends Command
{
  public Say()
  {
    super("say", "says stuff in chat");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    BubbyClient.MC.player.sendChatMessage(String.join(" ", args));
  }
}
