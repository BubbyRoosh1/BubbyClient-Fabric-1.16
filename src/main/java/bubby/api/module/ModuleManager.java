package bubby.api.module;

import bubby.client.modules.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager
{
  public ArrayList<Module> modules = new ArrayList<Module>();
  public HashMap<String, Module> modulesHash = new HashMap<String, Module>();

  public ModuleManager()
  {
    modules.add(new AirJump());
    modules.add(new Ambiance());
    modules.add(new Announcer());
    modules.add(new AntiBlind());
    modules.add(new AntiChunkBan());
    modules.add(new AntiForceLook());
    modules.add(new AntiHunger());
    modules.add(new AntiHurtcam());
    modules.add(new AntiLevitate());
    modules.add(new AntiSlow());
    modules.add(new ArmourHUD());
    modules.add(new AutoCrystal());
    modules.add(new AutoDisconnect());
    modules.add(new AutoMine());
    modules.add(new AutoRespawn());
    modules.add(new AutoSprint());
    modules.add(new AutoTotem());
    modules.add(new AutoWalk());
    modules.add(new BlockOutline());
    modules.add(new BowSpam());
    modules.add(new ChatSuffix());
    modules.add(new ChestESP());
    modules.add(new ClickGui());
    modules.add(new Console());
    modules.add(new Criticals());
    modules.add(new CrystalAura());
    modules.add(new ElytraFly());
    modules.add(new ElytraToggle());
    modules.add(new EntityList());
    modules.add(new ESP());
    modules.add(new FancyChat());
    modules.add(new FastFall());
    modules.add(new FastUse());
    modules.add(new FeatureList());
    modules.add(new Fly());
    modules.add(new Freecam());
    modules.add(new Fullbright());
    modules.add(new GreenText());
    modules.add(new HoleESP());
    modules.add(new HoleFill());
    modules.add(new InfoTab());
    modules.add(new InventoryCarry());
    modules.add(new InvPreview());
    modules.add(new IRC());
    modules.add(new Jesus());
    modules.add(new Killaura());
    modules.add(new Liquids());
    modules.add(new MountBypass());
    modules.add(new Toggles());
    modules.add(new NameTags());
    modules.add(new NoClip());
    modules.add(new NoFall());
    modules.add(new NoRender());
    modules.add(new NoSwing());
    modules.add(new PlayerRadar());
    modules.add(new PopbobLag());
    modules.add(new PopCounter());
    modules.add(new PortalESP());
    modules.add(new PortalGodMode());
    modules.add(new Portals());
    modules.add(new PositionTab());
    modules.add(new Spammer());
    modules.add(new Speed());
    modules.add(new SpeedMine());
    modules.add(new Step());
    modules.add(new FakePlayer());
    modules.add(new Timer());
    modules.add(new Tracers());
    modules.add(new Velocity());
    modules.add(new VisualRange());
    modules.forEach(m -> modulesHash.put(m.getName(), m));
  }

  public ArrayList<Module>
  getModules()
  {
    return modules;
  }

  public ArrayList<Module>
  getToggledModules()
  {
    return modules.stream()
            .filter(module -> module.isToggled() && module.visible)
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  }

  public Module
  getModuleByName(String name)
  {
    return modulesHash.get(name);
  }

  public void
  handleKeyPress(int key)
  {
    modules.forEach(m ->
    {
      if(m.getBind() == key)
        m.toggle();
    });
  }
}
