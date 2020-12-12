package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;

public class AntiHurtcam extends Module
{
  public AntiHurtcam()
  {
    super("AntiHurtcam", "Stops the hurtcam animation", -1, Category.Render, true);
  }
}
