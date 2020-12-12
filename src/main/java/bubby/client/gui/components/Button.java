package bubby.client.gui.components;
import bubby.api.module.Module;
import bubby.api.setting.Setting;
import bubby.api.setting.Type;
import bubby.client.BubbyClient;
import bubby.client.utils.Theme;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;

import java.util.ArrayList;

import static bubby.client.BubbyClient.MC;

public class Button implements Component
{
  private final ArrayList<Component> subcomponents;
  private final int height;
  public Module mod;
  public Frame parent;
  public int offset;
  public boolean open;
  private boolean isHovered;

  public Button(Module mod, Frame parent, int offset)
  {
    this.mod = mod;
    this.parent = parent;
    this.offset = offset;
    this.subcomponents = new ArrayList<Component>();
    this.open = false;
    height = 12;
    int opY = offset;
    if(BubbyClient.settings.getSettingsByMod(mod) != null)
    {
      for(Setting s : BubbyClient.settings.getSettingsByMod(mod))
      {
        if(s.type == Type.Mode)
        {
          this.subcomponents.add(new ModeButton(s, this, mod, opY));
          opY += 12;
        }
        else if(s.type == Type.Slider)
        {
          this.subcomponents.add(new Slider(s, this, opY));
          opY += 12;
        }
        else if(s.type == Type.CheckBox)
        {
          this.subcomponents.add(new CheckBox(s, this, opY));
          opY += 12;
        }
      }
    }
    this.subcomponents.add(new Bind(mod, this, opY));
  }

  @Override
  public void
  setOff(int newOff)
  {
    for(Component c : this.subcomponents)
    {
      c.setOff(newOff);
      newOff += 16;
    }
  }

  @Override
  public int getHeight()
  {
    return this.height;
  }

  @Override
  public void handleMouseInput()
  {

  }

  public int
  getX()
  {
    return parent.getX();
  }

  public ArrayList<Component>
  getSubcomponents()
  {
    return this.subcomponents;
  }

  @Override
  public void renderComponent(MatrixStack m)
  {

    MC.textRenderer.drawWithShadow(m, 
        this.mod.getName(), parent.getX() + 4.f, 
        this.parent.getY() + offset + 2, 
        this.mod.isToggled() ? Theme.getToggledColour() : Theme.getUnToggledColour());

    if(this.isHovered)
      MC.textRenderer.drawWithShadow(m, 
          this.mod.getDescription(), 
          BubbyClient.MC.getWindow().getScaledWidth() - BubbyClient.MC.textRenderer.getWidth(this.mod.getDescription()) - 1, 
          1, -1);

    if(this.open)
    {
      MC.textRenderer.drawWithShadow(m, "-", parent.getX() + parent.getWidth() - 12, this.parent.getY() + this.offset + 2, Theme.getExtraColour());
      this.subcomponents.forEach(c -> c.renderComponent(m));
    }
    else
      MC.textRenderer.drawWithShadow(m, "+", parent.getX() + parent.getWidth() - 12, this.parent.getY() + this.offset + 2, Theme.getExtraColour());
  }

  @Override
  public void updateComponent(int x, int y)
  {
    this.isHovered = isMouseOnButton(x, y);
    this.subcomponents.forEach(c -> c.updateComponent(x, y));
  }

  @Override
  public void
  mouseClicked(int mx, int my, int mb)
  {
    if(isMouseOnButton(mx, my) && mb == 0)
    {
      this.mod.toggle();
    }
    else if(isMouseOnButton(mx, my) && mb == 1)
    {
      this.open = !this.open;
      this.parent.onButtonOpen(this);
      this.parent.refresh();
    }

    this.subcomponents.forEach(c -> c.mouseClicked(mx, my, mb));
  }

  @Override
  public void
  mouseReleased(int mx, int my, int mb)
  {
    this.subcomponents.forEach(c -> c.mouseReleased(mx, my, mb));
  }

  @Override
  public int getParentHeight()
  {
    return 0;
  }

  @Override
  public void
  keyTyped(char typedChar, int key)
  {
    this.subcomponents.forEach(c -> c.keyTyped(typedChar, key));
  }

  public boolean
  isMouseOnButton(int x, int y)
  {
    return x > parent.getX() && x < parent.getX() + parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
  }
}
