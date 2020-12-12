package bubby.api.logging;

import bubby.client.BubbyClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.ReflectionUtil;

public class LoggerProvider
{

  public static final Logger ROOT_LOGGER = LogManager.getFormatterLogger(BubbyClient.NAME);

  public static Logger logger()
  {
    return LogManager.getFormatterLogger(BubbyClient.NAME + "|" + ReflectionUtil.getCallerClass(2).getSimpleName());
  }

}
