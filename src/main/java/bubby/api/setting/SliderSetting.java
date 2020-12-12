package bubby.api.setting;

import bubby.api.module.Module;

public class SliderSetting extends Setting
{
  private final double min;
  private final double max;
  public boolean intOnly;
  private double value;

  public SliderSetting(String name, Module parent, double current, double min, double max, boolean intOnly)
  {
    super(name, parent, Type.Slider);
    this.value = current;
    this.min = min;
    this.max = max;
    this.intOnly = intOnly;
  }

  public double
  getMin()
  {
    return this.min;
  }

  public double
  getMax()
  {
    return this.max;
  }

  public void
  set(double newVal)
  {
    this.value = newVal;
  }

  public double
  get()
  {
    if(intOnly)
      return (int) value;

    return value;
  }
}
