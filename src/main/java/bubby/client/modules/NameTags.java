package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.SliderSetting;

public class NameTags extends Module
{
  public NameTags()
  {
    super("NameTags", "Better nametags", -1, Category.Render, true);
    addSetting(new CheckBoxSetting("NameTags Render Armour", this, true));
    addSetting(new SliderSetting("NameTags Scale", this, 1, 0, 10, false));
  }
}
