package bubby.client.modules;

import bubby.api.eventapi.PogEvent;
import bubby.api.events.BreakBlockEvent;
import bubby.api.events.DropItemEvent;
import bubby.api.events.PlaceBlockEvent;
import bubby.api.events.TickEvent;
import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.SliderSetting;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.text.DecimalFormat;

public class Announcer extends Module
{
  private static double distance;
  double lposX, lposY, lposZ, cposX, cposY, cposZ;
  int walkDelay;
  int placeDelay;
  int breakDelay;
  int dropDelay;
  int eatDelay;
  int jumpDelay;
  int swapDelay;
  int playerListDelay;
  int experienceDelay;
  int currentExperience;
  int lastExperience;
  Block lastPlacedBlock;
  Block placedBlock;
  int placeAmount = 0;
  Block lastBrokenBlock;
  Block brokenBlock;
  int breakAmount = 0;
  int delayy;

  CheckBoxSetting walk = new CheckBoxSetting("Announcer Walk", this, true);
  CheckBoxSetting eat = new CheckBoxSetting("Announcer Eat", this, true);
  CheckBoxSetting place = new CheckBoxSetting("Announcer Place", this, true);
  CheckBoxSetting breakk = new CheckBoxSetting("Announcer Break", this, true);
  CheckBoxSetting swap = new CheckBoxSetting("Announcer Swap", this, true);
  CheckBoxSetting drop = new CheckBoxSetting("Announcer Drop", this, true);
  CheckBoxSetting jump = new CheckBoxSetting("Announcer Jump", this, true);
  CheckBoxSetting sneak = new CheckBoxSetting("Announcer Sneak", this, true);
  CheckBoxSetting playerList = new CheckBoxSetting("Announcer PlayerList", this, true);
  SliderSetting delay = new SliderSetting("Announcer Delay", this, 5, 0, 20, true);

  public Announcer()
  {
    super("Announcer", "Announces stuff", -1, Category.Spam, true);
    addSetting(walk);
    addSetting(eat);
    addSetting(place);
    addSetting(breakk);
    addSetting(swap);
    addSetting(drop);
    addSetting(jump);
    addSetting(sneak);
    addSetting(playerList);
    addSetting(delay);
  }

  @PogEvent
  public void
  onBlockPlace(PlaceBlockEvent event)
  {
    if(event.block == lastPlacedBlock)
    {
      placeAmount++;
    }
    else
    {
      lastPlacedBlock = event.block;
      placeAmount = 1;
    }
    if(placeDelay >= delayy && place.get())
    {
      if(placeAmount == 1)
        mc.player.sendChatMessage("I just placed a block of " + lastPlacedBlock.getName().getString() + "!");
      else
        mc.player.sendChatMessage("I just placed " + (placeAmount == 1 ? 1 : placeAmount / 2) + " blocks of " + lastPlacedBlock.getName().getString() + "!");
      placeDelay = 0;
      placeAmount = 0;
    }
  }

  @PogEvent
  public void
  onBlockBreak(BreakBlockEvent event)
  {
    if(lastBrokenBlock == event.blockState.getBlock())
    {
      breakAmount++;
    }
    else
    {
      lastBrokenBlock = event.blockState.getBlock();
      breakAmount = 1;
    }
    if(breakDelay >= delayy && breakk.get())
    {
      if(breakAmount == 1)
        mc.player.sendChatMessage("I just broke a block of " + lastBrokenBlock.getName().getString() + "!");
      else
        mc.player.sendChatMessage("I just broke " + breakAmount + " blocks of " + lastBrokenBlock.getName().getString() + "!");
      breakDelay = 0;
      breakAmount = 0;
    }
  }

  @PogEvent
  public void
  onDrop(DropItemEvent event)
  {
    if(event.itemStack.getItem() == Items.AIR)
      return;
    if(dropDelay >= delayy && drop.get())
    {
      mc.player.sendChatMessage("I just dropped " + event.itemStack.getCount() + " " + event.itemStack.getItem().toString().replace("_", " ") + "!");
      dropDelay = 0;
    }
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    walkDelay++;
    placeDelay++;
    breakDelay++;
    dropDelay++;
    eatDelay++;
    swapDelay++;
    jumpDelay++;
    playerListDelay++;
    experienceDelay++;
    delayy = (int) delay.get() * 20;

    if(walk.get() && walkDelay >= delayy)
    {
      cposX = mc.player.getX() - lposX;
      cposY = mc.player.getY() - lposY;
      cposZ = mc.player.getZ() - lposZ;
      distance = Math.sqrt(cposX * cposX + cposY * cposY + cposZ * cposZ);
      if(distance > 5 && distance < 1000)
      {
        mc.player.sendChatMessage("I just walked " + new DecimalFormat("#").format(distance) + " blocks!");
      }
      lposX = mc.player.getX();
      lposY = mc.player.getY();
      lposZ = mc.player.getZ();
      walkDelay = 0;
    }

    if(swap.get() && mc.options.keySwapHands.isPressed() && swapDelay >= delayy)
    {
      mc.player.sendChatMessage("I just swapped hands, there is now a " + mc.player.getOffHandStack().getItem().toString().replace("_", " ") + " in my offhand!");
      swapDelay = 0;
    }


    if(eat.get() && mc.options.keyUse.isPressed() && eatDelay >= delayy)
    {
      Item food = mc.player.getMainHandStack().getItem();
      if(food.isFood())
      {
        mc.player.sendChatMessage("I am currently eating " + food.toString().replace("_", " ") + "!");
        eatDelay = 0;
      }
    }

    if(jump.get() && mc.options.keyJump.isPressed() && jumpDelay >= delayy)
    {
      mc.player.sendChatMessage("I just jumped!");
      jumpDelay = 0;
    }

    if(sneak.get() && mc.options.keySneak.isPressed() && swapDelay >= delayy)
    {
      mc.player.sendChatMessage("I just crouched!");
      swapDelay = 0;
    }
    if(playerList.get() && mc.options.keyPlayerList.isPressed() && playerListDelay >= delayy)
    {
      mc.player.sendChatMessage("I just opened the playerlist!");
      playerListDelay = 0;
    }

    if(experienceDelay >= delayy)
    {
      currentExperience = mc.player.totalExperience;
      if(currentExperience > lastExperience && currentExperience - lastExperience < 100)
      {
        mc.player.sendChatMessage("I just gained " + (currentExperience - lastExperience) + " experience!");
      }
      experienceDelay = 0;
      lastExperience = currentExperience;
    }
  }
}
