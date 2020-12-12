package bubby.api.command;

import bubby.api.chat.Chat;
import bubby.client.commands.*;

import java.util.Arrays;
import java.util.List;

public class CommandManager
{
  public static String prefix = ".";

  public static List<Command> commands = Arrays.asList(
          new Baritone(),
          new Coords(),
          new Dupe(),
          new Bind(),
          new Friend(),
          new Help(),
          new Panic(),
          new Pitch(),
          new ResetGui(),
          new Say(),
          new Shrug(),
          new Toggle(),
          new Yaw(),
          new Fov()
  );

  public static void
  runCommand(String input)
  {
    String[] argss = input.split(" ");
    String command = argss[0];
    String args = input.substring(command.length()).trim();

    commands.forEach(c -> {
      if(c.getName().equalsIgnoreCase(command))
        try
        {
          c.execute(command, args.split(" "));
        }
        catch(Exception e)
        {
          Chat.error("Invalid Syntax");
        }
    });
  }
}
