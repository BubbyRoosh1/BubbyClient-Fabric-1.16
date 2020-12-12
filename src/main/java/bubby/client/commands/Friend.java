package bubby.client.commands;

import bubby.api.chat.Chat;
import bubby.api.command.Command;
import bubby.client.BubbyClient;

public class Friend extends Command
{
  public Friend()
  {
    super("friend", "manages your friends");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    if(args[0].equalsIgnoreCase("add"))
    {
      BubbyClient.friends.addFriend(args[1]);
      Chat.info(args[1] + " added to friends");
    }
    else if(args[0].equalsIgnoreCase("remove"))
    {
      BubbyClient.friends.removeFriend(args[1]);
      Chat.info(args[1] + " removed from friends");
    }
    else if(args[0].equalsIgnoreCase("list"))
    {
      for(String s : BubbyClient.friends.getFriends())
        Chat.info(s);
    }
  }
}
