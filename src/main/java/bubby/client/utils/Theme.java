package bubby.client.utils;

import bubby.api.setting.ModeSetting;
import bubby.client.BubbyClient;
public class Theme
{
  public static int
  getInnerColour()
  {
    switch(((ModeSetting) BubbyClient.settings.getSettingByName("ClickGui Theme")).get().name())
    {
      case "Nodus":
        return 0xaa000000;
      case "nhack":
        return 0x502d2d2d;
      case "WeepCraft":
        return 0xa3000000;
    }
    return 0xaa000000;
  }


  public static int
  getBorderColour()
  {
    switch(((ModeSetting) BubbyClient.settings.getSettingByName("ClickGui Theme")).get().name())
    {
      case "Nodus":
        return 0xaa3d3d3d;
      case "nhack":
        return 0xff6d6d6d;
      case "WeepCraft":
        return 0xaa3d3d3d;
    }
    return 0xaa000000;
  }

  public static int
  getExtraColour()
  {
    switch(((ModeSetting) BubbyClient.settings.getSettingByName("ClickGui Theme")).get().name())
    {
      case "Nodus":
        return 0xaa1aff00;
      case "nhack":
        return 0xaa00bed2;
      case "WeepCraft":
        return 0xaafff759;
    }
    return 0xaa000000;
  }

  public static int
  getButtonColour()
  {
    switch(((ModeSetting) BubbyClient.settings.getSettingByName("ClickGui Theme")).get().name())
    {
      case "Nodus":
        return 0xaa1aff00;
      case "nhack":
        return 0xaa00bed2;
      case "WeepCraft":
        return 0xaa3d3d3d;
    }
    return 0xaa000000;
  }

  public static int
  getTitleColour()
  {
    switch(((ModeSetting) BubbyClient.settings.getSettingByName("ClickGui Theme")).get().name())
    {
      case "Nodus":
        return 0xff1aff00;
      case "nhack":
        return 0xffffffff;
      case "WeepCraft":
        return 0xfffaff64;
    }
    return 0xaa000000;
  }

  public static int
  getToggledColour()
  {
    switch(((ModeSetting) BubbyClient.settings.getSettingByName("ClickGui Theme")).get().name())
    {
      case "Nodus":
        return 0xff1aff00;
      case "nhack":
        return 0xff00bed2;
      case "WeepCraft":
        return 0xff1aff00;
    }
    return 0xaa000000;
  }

  public static int
  getUnToggledColour()
  {
    switch(((ModeSetting) BubbyClient.settings.getSettingByName("ClickGui Theme")).get().name())
    {
      case "Nodus":
        return 0xffffffff;
      case "nhack":
        return 0xffffffff;
      case "WeepCraft":
        return 0xffa10000;
    }
    return 0xaa000000;
  }
}
