package bubby.api.eventapi;

public class Event
{
  private boolean isCancelled;

  public boolean
  isCancelled()
  {
    return isCancelled;
  }

  public void
  setCancelled(boolean cancelled)
  {
    isCancelled = cancelled;
  }
}
