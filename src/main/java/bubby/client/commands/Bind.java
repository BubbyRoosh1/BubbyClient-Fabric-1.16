package bubby.client.commands;

import bubby.api.chat.Chat;
import bubby.api.command.Command;
import bubby.client.BubbyClient;
import bubby.client.utils.FileManager;
import net.minecraft.client.util.InputUtil;

public class Bind extends Command
{
  public Bind()
  {
    super("bind", "binds mods through a command");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    BubbyClient.modules.getModuleByName(args[0]).setBind(InputUtil.fromTranslationKey("key.keyboard." + args[1].toLowerCase()).getCode());
    Chat.info(args[0] + " bound to " + args[1]);
    FileManager.INSTANCE.saveBinds();
  }
}
