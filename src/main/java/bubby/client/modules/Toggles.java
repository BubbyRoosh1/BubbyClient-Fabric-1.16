package bubby.client.modules;

import bubby.api.module.Category;
import bubby.api.module.Module;

public class Toggles extends Module
{
  public Toggles()
  {
    super("Toggles", "Sends a message in chat whenever you toggle a module", -1, Category.Misc, false);
  }
}
