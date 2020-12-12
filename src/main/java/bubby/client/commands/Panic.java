package bubby.client.commands;

import bubby.api.chat.Chat;
import bubby.api.command.Command;
import bubby.api.module.Module;
import bubby.client.BubbyClient;

public class Panic extends Command
{
  public Panic()
  {
    super("panic", "disables all modules");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    for(Module m : BubbyClient.modules.getToggledModules())
      m.setToggled(false);
    Chat.info("All modules disabled");
  }
}
