package bubby.api.setting;

import bubby.api.module.Module;

public class CheckBoxSetting extends Setting
{
  private boolean value;

  public CheckBoxSetting(String name, Module parent, boolean isChecked)
  {
    super(name, parent, Type.CheckBox);
    this.value = isChecked;
  }

  public void
  set(boolean newValue)
  {
    this.value = newValue;
  }

  public boolean
  get()
  {
    return this.value;
  }
}
