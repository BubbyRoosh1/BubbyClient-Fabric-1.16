package bubby.client.commands;

import bubby.api.command.Command;
import bubby.client.BubbyClient;

public class Toggle extends Command
{
  public Toggle()
  {
    super("toggle", "toggles a mod through command");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    BubbyClient.modules.getModuleByName(args[0]).toggle();
  }
}
