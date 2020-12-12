package bubby.client.irc;

import bubby.client.irc.pircBot.IrcException;
import bubby.client.irc.pircBot.NickAlreadyInUseException;
import bubby.client.irc.pircBot.PircBot;

import java.io.IOException;


public class IrcManager extends PircBot
{
  private static String username;
  private final String IRC_HostName = "irc.gamesurge.net";
  private final int IRC_HostPort = 6667;
  private final String IRC_ChannelName = "#bubbyclientircpog";

  public IrcManager(String username)
  {
    try
    {
      String firstname = username.substring(0, 1);
      int i = Integer.parseInt(firstname);
      //Innocent.getLogger().error("[IRC] Usernames Cannont begin with numbers");
      username = "MC" + username;
    }
    catch(NumberFormatException e)
    {
    }
    IrcManager.username = username;
  }

  public void connect()
  {
    this.setAutoNickChange(true);
    this.setName(username);
    this.changeNick(username);
    //Innocent.getLogger().info("Connecting To IRC");
    //Innocent.addIRCChatMessage("Attempting to connect to IRC.");
    try
    {
      this.connect(IRC_HostName, IRC_HostPort);
    }
    catch(NickAlreadyInUseException e)
    {
      e.printStackTrace();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
    catch(IrcException e)
    {
      e.printStackTrace();
    }
    //Innocent.getLogger().info("Joing Room");
    //Innocent.addIRCChatMessage("Attempting to join '" + IRC_ChannelName + "'");
    this.joinChannel(IRC_ChannelName);
    //Innocent.getLogger().info("Logged in");
    //Innocent.addIRCChatMessage("Connected.");

  }

}
