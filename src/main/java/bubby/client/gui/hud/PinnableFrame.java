package bubby.client.gui.hud;

import bubby.client.utils.RenderUtils2D;
import bubby.client.utils.Theme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.Comparator;

import static bubby.client.BubbyClient.clickGuiManager;

public class PinnableFrame
{
  public boolean open;
  public int x, y, barHeight, dX, dY, zIndex, defaultWidth;
  public String title;
  public ArrayList<String> leftText, rightText;
  public boolean visible;
  protected MinecraftClient mc = MinecraftClient.getInstance();
  private String longestLeft, longestRight;
  private int width;
  private boolean isHovered, isPinned, isExtendButtonHovered, isDragging;
  private int dragged;
  private int theColorOfTheInsideOfThePinnableFrame = -1;
  private int theColorOfTheBorderOfThePinnableFrame = -1;

  public PinnableFrame(String title, ArrayList<String> leftText, ArrayList<String> rightText, int y)
  {
    this.title = title;
    this.leftText = leftText;
    this.rightText = rightText;
    this.width = 88;
    this.defaultWidth = 88;
    this.x = 200;
    this.y = y;
    this.barHeight = 13;
    this.dX = 0;
    try
    {
      this.zIndex = clickGuiManager.pinnableframes.size();
    }
    catch(NullPointerException e)
    {
      this.zIndex = 0;
    }
    this.open = false;
    this.isDragging = false;
    this.visible = false;
  }

  public void
  setDrag(boolean drag)
  {
    this.isDragging = drag;
    if(drag)
    {
      clickGuiManager.movePinnableFrameToFront(this);
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

  public void
  updateFrame()
  {
    //for updating text yes mmhhm
  }

  public void
  render(MatrixStack m)
  {
    this.theColorOfTheInsideOfThePinnableFrame = Theme.getInnerColour();
    this.theColorOfTheBorderOfThePinnableFrame = Theme.getBorderColour();

    this.longestLeft = this.leftText.stream()
            .sorted((s1, s2) -> s1.length() > s2.length() ? -1 : 1)
            .findFirst()
            .orElse("");
    this.longestRight = this.rightText.stream()
            .sorted((s1, s2) -> s1.length() > s2.length() ? -1 : 1)
            .findFirst()
            .orElse("");

    this.setWidth(mc.textRenderer.getWidth(longestLeft) + mc.textRenderer.getWidth(longestRight) + 5);
    if(this.width < this.defaultWidth)
      this.setWidth(this.defaultWidth);

    //titlebar
    RenderUtils2D.drawBorderedRect(m, this.x, this.y, this.x + this.width, this.y + this.barHeight, this.theColorOfTheInsideOfThePinnableFrame, this.theColorOfTheBorderOfThePinnableFrame, 1);

    //title
    mc.textRenderer.drawWithShadow(m, this.title, this.x + 3, this.y + 3, Theme.getTitleColour());

    //open button bg
    RenderUtils2D.drawRectOutline(m, this.x + this.width - 3, this.y + 2, this.x + this.width - 10, this.y + 11, this.theColorOfTheBorderOfThePinnableFrame, 1);

    //pin button bg
    RenderUtils2D.drawRectOutline(m, this.x + this.width - 13, this.y + 2, this.x + this.width - 20, this.y + 11, this.theColorOfTheBorderOfThePinnableFrame, 1);
    if(this.open)
    {
      RenderUtils2D.drawBorderedRect(m, this.x, this.y + this.barHeight + 1, this.x + this.getWidth(), this.y + this.barHeight + (10 * ((this.leftText.size() > 0) ? this.leftText.size() : this.rightText.size()) + 2), this.theColorOfTheInsideOfThePinnableFrame, this.theColorOfTheBorderOfThePinnableFrame, 1);
      //open button
      Screen.fill(m, this.x + this.width - 3, this.y + 3, this.x + this.width - 10, this.y + 10, Theme.getButtonColour());
    }
    if(this.isPinned())
      Screen.fill(m, this.x + this.width - 13, this.y + 3, this.x + this.width - 20, this.y + 10, Theme.getButtonColour());
  }

  public void
  renderAfter(MatrixStack m)
  {

  }

  public void
  renderText(MatrixStack m)
  {
    if(this.open)
    {
      int yCount = this.y + this.barHeight + 3;
      for(String line : this.leftText)
      {
        mc.textRenderer.drawWithShadow(m, line, this.x + 3, yCount, -1);
        yCount += 10;
      }
      yCount = this.y + this.barHeight + 3;
      for(String line : rightText)
      {
        mc.textRenderer.drawWithShadow(m, line, this.x + this.width - mc.textRenderer.getWidth(line) - 3, yCount, -1);
        yCount += 10;
      }
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

  public void setY(int newY)
  {
    this.y = newY;
  }

  public int
  getWidth()
  {
    return this.width;
  }

  public void
  setWidth(int w)
  {
    this.width = w;
  }

  public void
  updatePosition(int x, int y)
  {
    this.isHovered = this.isWithinHeaderAndOnTop(x, y);
    this.isExtendButtonHovered = isWithinExtendRange(x, y);
    if(this.isDragging)
    {
      this.setX(x - dX);
      this.setY(y - dY);
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
    return clickGuiManager.pinnableframes.stream()
            // get all that are within header
            .filter(frame -> frame.isWithinHeader(x, y))
            // get the top most one by z index
            .max(Comparator.comparingInt(PinnableFrame::getZIndex))
            // check if that top most frame is this one
            .filter(frame -> frame == this).isPresent();
  }


  public boolean
  isWithinExtendRange(int x, int y)
  {
    return x <= this.x + this.width - 2 && x >= this.x + this.width - 10 && y >= this.y + 2 && y <= this.y + 10;
  }

  public boolean
  isWithinPinRange(int x, int y)
  {
    return x <= this.x + this.width - 12 && x >= this.x + this.width - 20 && y >= this.y + 2 && y <= this.y + 10;
  }

  public boolean
  isPinned()
  {
    return isPinned;
  }

  public void
  setPinned(boolean pinned)
  {
    this.isPinned = pinned;
  }

  public int
  getZIndex()
  {
    return zIndex;
  }

  public void
  setZIndex(int zIndex)
  {
    this.zIndex = zIndex;
  }
}
