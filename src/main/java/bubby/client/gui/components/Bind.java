package bubby.client.gui.components;

import bubby.api.module.Module;
import bubby.client.BubbyClient;
import bubby.client.utils.RenderUtils2D;
import bubby.client.utils.Theme;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;

import static bubby.client.BubbyClient.MC;

public class Bind implements Component
{
  public Module mod;
  public Button parent;
  private int offset;
  private int x;
  private int y;
  private boolean over, listening = false;

  public Bind(Module mod, Button button, int offset)
  {
    this.mod = mod;
    this.parent = button;
    this.x = parent.parent.getX() + parent.parent.getWidth() + 5;
    this.y = parent.parent.getY() + offset;
    this.offset = offset;
  }

  @Override
  public void
  renderComponent(MatrixStack m)
  {
    RenderUtils2D.drawBorderedRect(m, this.x - 2, parent.parent.getY() + offset - 2, this.x + 90, parent.parent.getY() + offset + 14, Theme.getInnerColour(), Theme.getBorderColour(), 1);

    MC.textRenderer.drawWithShadow(m, this.listening ? "Listening..." : "Bind: " + (this.mod.getBind() == -1 ? "none" : InputUtil.fromKeyCode(this.mod.getBind(), -1).getLocalizedText().getString()), this.x + 2, parent.parent.getY() + offset + 2, -1);
  }

  @Override
  public void
  setOff(int newOff)
  {
    offset = newOff;
  }

  @Override
  public int
  getHeight()
  {
    return 0;
  }

  @Override
  public int
  getParentHeight()
  {
    return 0;
  }

  @Override
  public void
  handleMouseInput()
  {

  }

  @Override
  public void
  mouseClicked(int mx, int my, int mb)
  {
    if(this.over && this.parent.open)
      this.listening = true;
  }

  @Override
  public void
  mouseReleased(int x, int y, int b)
  {

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
  public void
  keyTyped(char c, int k)
  {
    if(this.parent.open && this.listening && (int) c != 256)
    {
      BubbyClient.modules.getModuleByName(this.mod.getName()).setBind(c);
      this.mod.setBind(c);
      this.listening = false;
      if((int) c == 261)
      {
        BubbyClient.modules.getModuleByName(this.mod.getName()).setBind(-1);
        this.mod.setBind(-1);
      }
    }
  }

  public boolean
  isMouseOnButton(int mx, int my)
  {
    return mx > this.x && mx < this.x + 88 && my > this.y && my < this.y + 13;
  }
}
