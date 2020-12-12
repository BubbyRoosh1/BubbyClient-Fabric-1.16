package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.SendPacketEvent;
import bubby.api.mixin.MixinIChatMessageC2SPacket;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.ModeSetting;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class FancyChat extends Module
{
  ModeSetting<Mode> mode = new ModeSetting<Mode>("FancyChat Mode", this, Mode.Classic, Mode.values());

  public FancyChat()
  {
    super("FancyChat", "talk kul ha", -1, Category.Spam, true);
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
      if(message.startsWith("/") || message.startsWith(".") || message.startsWith(";")) return;
      if(mode.get() == Mode.Classic)
      {
        String out = "";
        char[] arr = message.toCharArray();
        for(int i = arr.length, j = 0; j < i; ++j)
        {
          final char c = arr[j];
          if(c >= '!' && c <= '\u0080' && !">(){}[]|".contains(Character.toString(c)))
            out = out + new String(Character.toChars(c + '\ufee0'));
          else
            out = out + c;
        }
        m.setChatMessage(out);
        return;
      }
      else if(mode.get() == Mode.Derp)
      {
        char[] arr = message.toCharArray();
        for(int i = 0; i < arr.length; i += 2)
        {
          arr[i] = (Character.isUpperCase(arr[i]) ? Character.toLowerCase(arr[i]) : Character.toUpperCase(arr[i]));
        }
        String out = new String(arr);
        m.setChatMessage(out);
        return;
      }
    }
  }

  enum Mode
  {
    Classic,
    Derp
  }
}
