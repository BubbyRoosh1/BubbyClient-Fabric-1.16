package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.SendPacketEvent;
import bubby.api.mixin.MixinIChatMessageC2SPacket;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.ModeSetting;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class ChatSuffix extends Module
{
  private final String BC_SUFFIX = " | ʙᴜʙʙʏᴄʟɪᴇɴᴛ";
  private final String BCBC_SUFFIX = " | ʙᴄʙᴄ";
  ModeSetting<Mode> mode = new ModeSetting<Mode>("ChatSuffix Suffix", this, Mode.BCBC, Mode.values());

  public ChatSuffix()
  {
    super("ChatSuffix", "Adds a Suffix to the chat.", -1, Category.Misc, true);
    addSetting(mode);
  }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if(event.packet instanceof ChatMessageC2SPacket)
    {
      MixinIChatMessageC2SPacket m = (MixinIChatMessageC2SPacket) event.packet;
      String message = m.getChatMessage();
      if(message.startsWith("/") || message.startsWith(".") || message.endsWith(BC_SUFFIX) || message.endsWith(BCBC_SUFFIX) || message.startsWith(";"))
        return;

      if(mode.get() == Mode.BubbyClient)
      {
        m.setChatMessage(message + BC_SUFFIX);
      }
      else if(mode.get() == Mode.BCBC)
      {
        m.setChatMessage(message + BCBC_SUFFIX);
      }
    }
  }

  enum Mode
  {
    BubbyClient,
    BCBC
  }
}
