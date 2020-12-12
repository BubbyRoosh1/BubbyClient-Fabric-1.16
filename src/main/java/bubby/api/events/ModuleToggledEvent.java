package bubby.api.events;

import bubby.api.eventapi.Event;

public class ModuleToggledEvent extends Event
{
  public String testInput;

  public ModuleToggledEvent(String testInput)
  {
    this.testInput = testInput;
  }
}
