package bubby.client.gui;

import bubby.api.module.Category;
import bubby.client.gui.components.Component;
import bubby.client.gui.components.Frame;
import bubby.client.gui.hud.*;
import bubby.client.utils.FileManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ClickGuiManager extends Screen
{
  public ArrayList<Frame> frames;
  public ArrayList<PinnableFrame> pinnableframes;
  public HashMap<String, PinnableFrame> pinnableFrameHashMap = new HashMap<String, PinnableFrame>();

  public ClickGuiManager()
  {
    super(new LiteralText(""));
    frames = new ArrayList<Frame>();
    for(Category c : Category.values())
    {
      frames.add(new Frame(c));
    }

    pinnableframes = new ArrayList<PinnableFrame>();
    pinnableframes.add(new FeatureList());
    pinnableframes.add(new InfoTab());
    pinnableframes.add(new PositionTab());
    pinnableframes.add(new PlayerRadar());
    pinnableframes.add(new EntityList());
    pinnableframes.add(new ArmourPreview());
    pinnableframes.add(new InvPreview());
    pinnableframes.forEach(frame -> pinnableFrameHashMap.put(frame.title, frame));
    resetGui();
  }

  public void moveFrameToFront(Frame frame)
  {
    frames.stream()
            .filter(f -> f.getZIndex() >= frame.getZIndex())
            .forEach(f -> f.setZIndex(f.getZIndex() - 1));
    frame.setZIndex(frames.size());
  }

  public void movePinnableFrameToFront(PinnableFrame frame)
  {
    pinnableframes.stream()
            .filter(f -> f.getZIndex() >= frame.getZIndex())
            .forEach(f -> f.setZIndex(f.getZIndex() - 1));
    frame.setZIndex(frames.size());
  }

  public void
  resetGui()
  {
    int frameX = 2;
    int frameY = 2;
    for(Frame f : frames)
    {
      f.setOpen(false);
      if(frameX > 300)
      {
        frameX = 2;
        frameY += 28;
      }
      f.setX(frameX);
      f.setY(frameY);
      frameX += f.getWidth() + 2;
    }
    for(PinnableFrame f : pinnableframes)
    {
      f.setOpen(false);
      f.setPinned(false);
      if(frameX > 300)
      {
        frameX = 2;
        frameY += 28;
      }
      f.setX(frameX);
      f.setY(frameY);
      frameX += f.getWidth() + 2;
    }
  }

  @Override
  public boolean
  mouseClicked(double xx, double yy, int mb)
  {
    int x = (int) xx;
    int y = (int) yy;
    for(Frame frame : frames)
    {
      if(frame.isWithinHeaderAndOnTop(x, y) && mb == 0)
      {
        frame.setDrag(true);
        frame.setDragX(x - frame.getX());
        frame.setDragY(y - frame.getY());
      }
      if(frame.isWithinExtendRange(x, y) && (mb == 1 || mb == 0))
        frame.setOpen(!frame.isOpen());

      if(frame.isOpen() && !frame.getComponents().isEmpty())
        for(Component c : frame.getComponents())
          c.mouseClicked(x, y, mb);
    }

    for(PinnableFrame frame : pinnableframes)
    {
      if(!frame.visible) continue;
      if(frame.isWithinHeaderAndOnTop(x, y) && mb == 0)
      {
        frame.setDrag(true);
        frame.dX = x - frame.getX();
        frame.dY = y - frame.getY();
      }
      if(frame.isWithinExtendRange(x, y) && (mb == 1 || mb == 0))
      {
        frame.setOpen(!frame.isOpen());
        frame.setWidth(88);
      }
      if(frame.isWithinPinRange(x, y) && (mb == 1 || mb == 0))
      {
        frame.setPinned(!frame.isPinned());
      }
    }
    FileManager.INSTANCE.saveGuiPos();
    return super.mouseClicked(xx, yy, mb);
  }

  @Override
  public boolean
  mouseReleased(double xx, double yy, int mb)
  {
    int x = (int) xx;
    int y = (int) yy;

    for(Frame frame : frames)
    {
      frame.setDrag(false);
      if(frame.isOpen() && !frame.getComponents().isEmpty())
        for(Component c : frame.getComponents())
          c.mouseReleased(x, y, mb);
    }
    for(PinnableFrame frame : pinnableframes)
    {
      if(!frame.visible) continue;
      frame.setDrag(false);
    }
    FileManager.INSTANCE.saveGuiPos();
    return super.mouseReleased(xx, yy, mb);
  }

  @Override
  public boolean
  keyPressed(int keyCode, int scanCode, int modifiers)
  {
    for(Frame frame : frames)
    {
      for(Component c : frame.getComponents())
      {
        c.keyTyped((char) keyCode, scanCode);
      }
    }
    return super.keyPressed(keyCode, scanCode, modifiers);
  }

  @Override
  public void
  render(MatrixStack m, int x, int y, float d)
  {
    this.renderBackground(m);
    frames.stream()
            .sorted(Comparator.comparingInt(Frame::getZIndex))
            .forEachOrdered(f ->
            {
              f.render(m);
              f.updatePosition(x, y);
              for(Component c : f.getComponents())
              {
                c.updateComponent(x, y);
              }
            });

    pinnableframes.stream()
            .filter(f -> f.visible)
            .sorted(Comparator.comparingInt(PinnableFrame::getZIndex))
            .forEachOrdered(f ->
            {
              f.render(m);
              f.renderText(m);
              f.renderAfter(m);
              f.updatePosition(x, y);
            });

    super.render(m, x, y, d);
  }

  public void
  onGuiClosed()
  {
    FileManager.INSTANCE.saveGuiPos();
  }

  @Override
  public boolean
  isPauseScreen()
  {
    return false;
  }
}
