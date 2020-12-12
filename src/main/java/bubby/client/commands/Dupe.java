package bubby.client.commands;

import bubby.api.command.Command;
import bubby.client.BubbyClient;
import net.minecraft.text.LiteralText;

public class Dupe extends Command
{
  public Dupe()
  {
    super("dupe", "d00ps 1t3m");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    BubbyClient.MC.player.dropSelectedItem(true);
    BubbyClient.MC.player.networkHandler.getConnection().disconnect(new LiteralText("d000000p"));
  }
}
