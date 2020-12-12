package bubby.client.gui.components;

import bubby.api.setting.Setting;
import bubby.api.setting.SliderSetting;
import bubby.client.utils.FileManager;
import bubby.client.utils.RenderUtils2D;
import bubby.client.utils.Theme;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static bubby.client.BubbyClient.MC;

public class Slider implements Component
{
  private final SliderSetting setting;
  private final Button parent;
  private boolean over;
  private int offset;
  private int x;
  private int y;
  private boolean dragging = false;
  private double renderWidth;

  public Slider(Setting value, Button button, int offset)
  {
    this.setting = (SliderSetting) value;
    this.parent = button;
    this.x = button.parent.getX() + button.parent.getWidth();
    this.y = button.parent.getY() + button.offset;
    this.offset = offset;
  }

  private static double
  roundToPlace(double value, int places)
  {
    if(places < 0)
      throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  @Override
  public void
  renderComponent(MatrixStack m)
  {
    RenderUtils2D.drawBorderedRect(m, 
        this.x - 2, parent.parent.getY() + offset - 2, 
        this.x + 90, parent.parent.getY() + offset + 14, 
        Theme.getInnerColour(), Theme.getBorderColour(), 1);

    Screen.fill(m, this.x, parent.parent.getY() + offset, this.x + (int) renderWidth, parent.parent.getY() + offset + 12, Theme.getExtraColour());

    MC.textRenderer.drawWithShadow(m, setting.name.replace(this.parent.mod.getName() + " ", "") + ": " + Math.round(setting.get() * 100d) / 100d, x + 2, (parent.parent.getY() + offset + 2), -1);
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
  updateComponent(int mx, int my)
  {
    this.over = isMouseOnButtonD(mx, my) || isMouseOnButtonI(mx, my);
    this.x = parent.parent.getX() + parent.parent.getWidth() + 5;
    this.y = parent.parent.getY() + offset;

    double diff = Math.min(88, Math.max(0, mx - this.x));

    double min = setting.getMin();
    double max = setting.getMax();

    renderWidth = 88 * (setting.get() - min) / (max - min);

    if(dragging)
    {
      if(diff == 0)
      {
        setting.set(setting.getMin());
      }
      else
      {
        double newValue = roundToPlace(((diff / 88) * (max - min) + min), 2);
        setting.set(newValue);
      }

      FileManager.INSTANCE.saveSettingsList();
    }
  }

  @Override
  public void
  mouseClicked(int mx, int my, int mb)
  {
    if(isMouseOnButtonD(mx, my) && mb == 0 && this.parent.open)
    {
      dragging = true;
    }

    if(isMouseOnButtonI(mx, my) && mb == 0 && this.parent.open)
    {
      dragging = true;
    }
  }

  @Override
  public void
  mouseReleased(int mx, int my, int mb)
  {
    dragging = false;
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
  isMouseOnButtonD(int mx, int my)
  {
    return mx > this.x - 2 && mx < this.x + (parent.parent.getWidth() / 2 + 1) && my > this.y && my < this.y + 12 + 1;

  }

  public boolean
  isMouseOnButtonI(int mx, int my)
  {
    return mx > this.x + parent.parent.getWidth() / 2 && mx < this.x + parent.parent.getWidth() && my > this.y && my < this.y + 12 + 1;

  }
}
