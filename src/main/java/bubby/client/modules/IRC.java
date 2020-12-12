package bubby.client.modules;

import bubby.api.chat.Chat;
import bubby.api.eventapi.PogEvent;
import bubby.api.events.SendPacketEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.client.BubbyClient;
import bubby.client.irc.IrcLine;
import bubby.client.irc.IrcManager;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class IRC extends Module
{
  public IRC()
  {
    super("IRC", "IRC Chat for BubbyClient users", -1, Category.Misc, false);
  }

  public void
  onEnable()
  {
    //WHYWHYWHYHWYWHYWHYWHYWHYWHYWHY DOES THIS NOT WORK
    new Thread(() -> {
      try
      {
        BubbyClient.irc = new IrcManager(mc.getSession().getUsername());
        BubbyClient.irc.connect();
        if(mc.world != null && mc.player != null)
          Chat.info("Connected to IRC");
      }
      catch(Exception e)
      {
      }
    }, "Enable IRC").start();
    super.onEnable();
  }

  public void
  onDisable()
  {
    super.onDisable();
    try
    {
      if(BubbyClient.irc.isConnected())
      {
        BubbyClient.irc.disconnect();
        Chat.info("Disconnected from IRC");
      }
    }
    catch(Exception e)
    {
    }
  }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if(event.packet instanceof ChatMessageC2SPacket)
    {
      ChatMessageC2SPacket pack = (ChatMessageC2SPacket) event.packet;
      if(pack.getChatMessage().startsWith(";") && BubbyClient.irc.isConnected())
      {
        String message = pack.getChatMessage();
        BubbyClient.irc.sendMessage("#bubbyclientircpog", message.replace(";", ""));
        BubbyClient.irc.getLines().add(new IrcLine(BubbyClient.irc.getChatLine(true), message.replace(";", ""), BubbyClient.irc.getName(), false));
        BubbyClient.irc.setUnreadMessages(true);
        event.setCancelled(true);
      }
    }
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(BubbyClient.irc.isConnected())
    {
      for(IrcLine ircLine : BubbyClient.irc.getUnreadLines())
      {
        Chat.message("[IRC] " + ircLine.getSender() + ": " + ircLine.getLine());
        ircLine.setRead(true);
      }
    }
  }
}
