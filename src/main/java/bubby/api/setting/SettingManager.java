package bubby.api.setting;

import bubby.api.module.Module;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingManager
{
  public ArrayList<Setting> settingsList;
  public HashMap<String, Setting> settingsMap;

  public SettingManager()
  {
    this.settingsList = new ArrayList<>();
    this.settingsMap = new HashMap<>();
  }

  public void
  addSetting(Setting setting)
  {
    this.settingsList.add(setting);
    this.settingsMap.put(setting.name, setting);
  }

  public ArrayList<Setting>
  getSettingsByMod(Module module)
  {
    return this.settingsList.stream()
            .filter(setting -> setting.parent == module)
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  }

  public Setting
  getSettingByName(String name)
  {
    return this.settingsMap.get(name);
  }

  public ArrayList<Setting>
  getSettingsByType(Type t)
  {
    return this.settingsList.stream()
            .filter(setting -> setting.type == t)
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  }
}
