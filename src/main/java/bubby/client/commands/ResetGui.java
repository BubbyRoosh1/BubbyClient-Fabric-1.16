package bubby.client.commands;

import bubby.api.command.Command;
import bubby.client.BubbyClient;

public class ResetGui extends Command
{
  public ResetGui()
  {
    super("resetgui", "resets the clickgui");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    BubbyClient.clickGuiManager.resetGui();
  }
}
