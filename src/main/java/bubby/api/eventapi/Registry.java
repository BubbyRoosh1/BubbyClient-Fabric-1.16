package bubby.api.eventapi;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registry
{
  public Map<Class<? extends Event>, Method> methods;
  public Object object;

  public Registry(Map<Class<? extends Event>, Method> methods, Object object)
  {
    this.methods = new HashMap<Class<? extends Event>, Method>(methods);
    this.object = object;
  }

  ArrayList<Method>
  getMethodsByEventType(Class<? extends Event> eventType)
  {
    ArrayList<Method> methodsToReturn = new ArrayList<Method>();
    this.methods.forEach((event, method) -> {
      if(event == eventType)
        methodsToReturn.add(method);
    });
    return methodsToReturn;
  }
}
