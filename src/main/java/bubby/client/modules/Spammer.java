package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.SliderSetting;
import bubby.client.utils.FileManager;

import java.util.ArrayList;

public class Spammer extends Module
{
  ArrayList<String> spams;
  int index;
  int counter;
  SliderSetting delay = new SliderSetting("Spammer Delay", this, 5, 0, 20, true);

  public Spammer()
  {
    super("Spammer", "Spams stuff from a .txt file in chat. Toggle to refresh", -1, Category.Spam, true);
    addSetting(delay);
  }

  public void
  onEnable()
  {
    super.onEnable();
    spams = FileManager.INSTANCE.getFileContents("Spam.txt");
    index = 0;
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    counter++;
    if(counter >= delay.get() * 20)
    {
      mc.player.sendChatMessage(spams.get(index));
      index++;
      if(index >= spams.size() - 1)
        index = 0;

      counter = 0;
    }
  }
}
