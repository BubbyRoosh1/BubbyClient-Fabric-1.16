package bubby.client.utils;

import java.util.ArrayList;
import java.util.Collections;

public final class ArrayUtils
{

  public static <T> ArrayList<T> toArrayList(T[] array)
  {
    ArrayList<T> list = new ArrayList<>(array.length);
    Collections.addAll(list, array);
    return list;
  }

}
