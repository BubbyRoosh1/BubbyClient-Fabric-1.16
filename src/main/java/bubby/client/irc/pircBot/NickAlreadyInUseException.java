package bubby.client.irc.pircBot;

public class NickAlreadyInUseException extends IrcException
{

  /**
   * Constructs a new IrcException.
   *
   * @param e The error message to report.
   */
  public NickAlreadyInUseException(String e)
  {
    super(e);
  }

}
