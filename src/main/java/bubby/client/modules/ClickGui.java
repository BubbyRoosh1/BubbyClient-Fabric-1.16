package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.api.setting.CheckBoxSetting;
import bubby.api.setting.ModeSetting;
import bubby.client.BubbyClient;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module
{
  public ClickGui()
  {
    super("ClickGui", "Opens ClickGui", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.Gui, true);
    addSetting(new CheckBoxSetting("ClickGui RenderFrames", this, true));
    addSetting(new ModeSetting<Theme>("ClickGui Theme", this, Theme.Nodus, Theme.values()));
  }

  public void
  onEnable()
  {
    mc.openScreen(BubbyClient.clickGuiManager);
    setToggled(false);
  }

  public void
  onDisable()
  {

  }

  public enum Theme
  {
    Nodus,
    nhack,
    WeepCraft,
  }
}
