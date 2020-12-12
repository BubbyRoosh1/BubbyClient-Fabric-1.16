package bubby.client.commands;

import bubby.api.chat.Chat;
import bubby.api.command.Command;
import bubby.client.BubbyClient;

public class Fov extends Command
{
    public Fov()
    {
        super("fov", "pro pvp module");
    }

    @Override
    public void
    execute(String command, String[] args) throws Exception
    {
        BubbyClient.MC.options.fov = Integer.parseInt(args[0]);
        Chat.info("Fov set to: " + args[0]);
    }
}
