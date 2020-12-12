package bubby.client.gui.components;

import bubby.api.module.Category;
import bubby.api.module.Module;
import bubby.client.BubbyClient;
import bubby.client.utils.RenderUtils2D;
import bubby.client.utils.Theme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.Comparator;

import static bubby.client.BubbyClient.clickGuiManager;

public class Frame
{
  private final int barHeight;
  public ArrayList<Component> components;
  public Category category;
  public boolean open;
  public boolean visible;
  private int width;
  private int x;
  private int y;
  private int dragX;
  private int dragY;
  private int zIndex;
  private boolean isDragging;
  private boolean isHovered;
  private boolean isVisible;
  private boolean isExtendButtonHovered;
  private int dragged;
  private int c1, c2 = -1;

  public Frame(Category c)
  {
    this.components = new ArrayList<>();
    this.category = c;
    this.width = 88;
    this.x = 5;
    this.y = 5;
    this.barHeight = 13;
    this.dragX = 0;
    try
    {
      this.zIndex = clickGuiManager.frames.size();
    }
    catch(NullPointerException e)
    {
      this.zIndex = 0;
    }
    this.open = false;
    this.isDragging = false;
    int tY = this.barHeight + 3;

    BubbyClient.modules.modules.sort(Comparator.comparing(Module::getName));
    for(Module m : BubbyClient.modules.getModules())
    {
      if(!m.getCategory().equals(c)) continue;
      Button modButton = new Button(m, this, tY);
      this.components.add(modButton);
      tY += 14;
    }

    isVisible = false;
  }

  public ArrayList<Component>
  getComponents()
  {
    return components;
  }

  public void
  onButtonOpen(Component c)
  {
    for(Component comp : this.getComponents())
    {
      if(comp != c)
      {
        Button cc = (Button) comp;
        cc.open = false;
      }
    }
  }

  public void
  setDragX(int newX)
  {
    this.dragX = newX;
  }

  public void
  setDragY(int newY)
  {
    this.dragY = newY;
  }

  public void
  setDrag(boolean drag)
  {
    this.isDragging = drag;
    if(drag)
    {
      clickGuiManager.moveFrameToFront(this);
    }
  }

  public boolean
  isOpen()
  {
    return open;
  }

  public void
  setOpen(boolean open)
  {
    this.open = open;
  }

  public int
  getBarHeight()
  {
    return this.barHeight;
  }

  public void
  render(MatrixStack m)
  {
    this.c1 = Theme.getInnerColour();
    this.c2 = Theme.getBorderColour();

    RenderUtils2D.drawBorderedRect(m, this.x, this.y, this.x + this.width, this.y + this.barHeight, this.c1, this.c2, 1);

    MinecraftClient.getInstance().textRenderer.drawWithShadow(m, this.category.name(), this.x + 3, this.y + 3, Theme.getTitleColour());

    RenderUtils2D.drawRectOutline(m, this.x + this.width - 3, this.y + 2, this.x + this.width - 10, this.y + 11, this.c2, 1);

    if(this.open)
    {
      Screen.fill(m, this.x + this.width - 3, this.y + 3, this.x + this.width - 10, this.y + 10, Theme.getButtonColour());
      if(!this.components.isEmpty())
      {
        RenderUtils2D.drawBorderedRect(m, this.x, 
            this.y + this.barHeight + 1, 
            this.x + this.width, this.y + this.barHeight + (14 * this.components.size()) + 3, this.c1, this.c2, 1);

        components.forEach(c -> c.renderComponent(m));
      }
    }
  }

  public void
  refresh()
  {
    int off = this.barHeight;
    for(Component c : components)
    {
      c.setOff(off);
      off += 14;
    }
  }

  public int
  getX()
  {
    return x;
  }

  public void setX(int newX)
  {
    this.x = newX;
  }

  public int
  getY()
  {
    return y;
  }

  public void
  setY(int newY)
  {
    this.y = newY;
  }

  public int
  getWidth()
  {
    return width;
  }

  public void
  setWidth(int w)
  {
    this.width = w;
  }

  public void
  updatePosition(int x, int y)
  {
    this.isHovered = this.isWithinHeader(x, y);
    this.isExtendButtonHovered = isWithinExtendRange(x, y);
    if(this.isDragging)
    {
      this.setX(x - dragX);
      this.setY(y - dragY);
    }
  }

  public boolean
  isWithinHeader(int x, int y)
  {
    return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
  }

  public boolean
  isWithinHeaderAndOnTop(int x, int y)
  {
    return clickGuiManager.frames.stream()
            // get all that are within header
            .filter(frame -> frame.isWithinHeader(x, y))
            // get the top most one by z index
            .max(Comparator.comparingInt(Frame::getZIndex))
            // check if that top most frame is this one
            .filter(frame -> frame == this).isPresent();
  }

  public boolean
  isWithinExtendRange(int x, int y)
  {
    return x <= this.x + this.width - 2 && x >= this.x + this.width - 10 && y >= this.y + 2 && y <= this.y + 10;
  }

  public boolean isVisible()
  {
    return isVisible;
  }

  public void setVisiable(boolean newVis)
  {
    this.isVisible = newVis;
  }

  public int getZIndex()
  {
    return zIndex;
  }

  public void setZIndex(int zIndex)
  {
    this.zIndex = zIndex;
  }
}
