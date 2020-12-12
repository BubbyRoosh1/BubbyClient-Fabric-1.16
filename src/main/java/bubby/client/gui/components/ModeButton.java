package bubby.client.gui.components;

import bubby.api.module.Module;
import bubby.api.setting.ModeSetting;
import bubby.api.setting.Setting;
import bubby.client.utils.FileManager;
import bubby.client.utils.RenderUtils2D;
import bubby.client.utils.Theme;
import net.minecraft.client.util.math.MatrixStack;

import static bubby.client.BubbyClient.MC;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ModeButton implements Component
{
  private final Button parent;
  private final ModeSetting setting;
  private final Module mod;
  private boolean over;
  private int offset;
  private int x;
  private int y;

  public ModeButton(Setting set, Button button, Module mod, int offset)
  {
    this.setting = (ModeSetting) set;
    this.parent = button;
    this.mod = mod;
    this.x = parent.parent.getX() + parent.parent.getWidth() + 5;
    this.y = parent.parent.getY() + offset;
    this.offset = offset;
  }

  @Override
  public void
  setOff(int newOff)
  {
    offset = newOff;
  }

  @Override
  public int getHeight()
  {
    return 0;
  }

  @Override
  public void handleMouseInput()
  {

  }

  @Override
  public void
  renderComponent(MatrixStack m)
  {
    RenderUtils2D.drawBorderedRect(m, this.x - 2,
            parent.parent.getY() + offset - 2,
            this.x + 90, parent.parent.getY() + offset + 14,
            Theme.getInnerColour(), Theme.getBorderColour(), 1);

    MC.textRenderer.drawWithShadow(m, 
        this.setting.name.replace(this.parent.mod.getName() + " ", "") + ": " + setting.get().name().replace(this.parent.mod.getName() + " ", ""), x + 2, (parent.parent.getY() + offset + 2), -1);
  }

  @Override
  public void
  updateComponent(int mx, int my)
  {
    this.over = isMouseOnButton(mx, my);
    this.x = parent.parent.getX() + parent.parent.getWidth() + 5;
    this.y = parent.parent.getY() + offset;
  }

  @Override
  public void mouseClicked(int mx, int my, int mb)
  {
    if(isMouseOnButton(mx, my) && mb == 0 && this.parent.open)
    {
      setting.set(setting.getNext());
      FileManager.INSTANCE.saveSettingsList();
    }
  }

  @Override
  public void
  mouseReleased(int mx, int my, int mb)
  {

  }

  @Override
  public int getParentHeight()
  {
    return 0;
  }

  @Override
  public void keyTyped(char c, int k)
  {

  }

  public boolean
  isMouseOnButton(int mx, int my)
  {
    return mx > this.x && mx < this.x + 88 && my > this.y && my < this.y + 13;
  }
}
