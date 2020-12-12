package bubby.api.eventapi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class EventManager
{
  ArrayList<Registry> registeries;

  public EventManager()
  {
    this.registeries = new ArrayList<>();
  }

  public void
  register(Object object)
  {
    ArrayList<Method> methodsToAdd = new ArrayList<>();
    for(Method method : object.getClass().getDeclaredMethods())
    {
      if(method.getParameterTypes().length != 1 || !method.isAnnotationPresent(PogEvent.class))
        continue;
      methodsToAdd.add(method);
    }
    Map<Class<? extends Event>, Method> registryMap = new HashMap<>();
    methodsToAdd.forEach(m -> registryMap.put((Class<? extends Event>) m.getParameterTypes()[0], m));
    this.registeries.add(new Registry(registryMap, object));
  }

  public void
  unregister(Object object)
  {
    this.registeries.remove(getRegistryByObject(object));
  }

  public Registry
  getRegistryByObject(Object object)
  {
    for(Registry registry : this.registeries)
      if(registry.object == object)
        return registry;
    return null;
  }

  public Event
  callEvent(Event event)
  {
    try
    {
      this.registeries.forEach(registry -> {
        registry.getMethodsByEventType(event.getClass()).forEach(method -> {
          try
          {
            method.invoke(registry.object, event);
          }
          catch(IllegalAccessException e){}
          catch(IllegalArgumentException e){}
          catch(InvocationTargetException e){}
        });
      });
    }
    catch(ConcurrentModificationException e){}
    return event;
  }
}
