package bubby.client.commands;

import bubby.api.chat.Chat;
import bubby.api.command.Command;
import bubby.api.command.CommandManager;

public class Help extends Command
{
  public Help()
  {
    super("help", "Displays all commands");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    for(Command c : CommandManager.commands)
      Chat.message(c.getName() + " | " + c.getDesc());
  }
}
