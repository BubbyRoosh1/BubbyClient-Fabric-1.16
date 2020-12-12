package bubby.client.utils;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class RenderUtils2D
{
  public static void
  drawRectOutline(MatrixStack m, int x1, int y1, int x2, int y2, int colour, int thiccness)
  {
    Screen.fill(m, x1 + thiccness, y1, x2 - thiccness, y1 + thiccness, colour);
    Screen.fill(m, x1, y1, x1 + thiccness, y2, colour);
    Screen.fill(m, x2 - thiccness, y1, x2, y2, colour);
    Screen.fill(m, x1 + thiccness, y2 - thiccness, x2 - thiccness, y2, colour);
  }

  public static void
  drawBorderedRect(MatrixStack m, int x1, int y1, int x2, int y2, int colour1, int colour2, int thiccness)
  {
    Screen.fill(m, x1 + thiccness, y1 + thiccness, x2 - thiccness, y2 - thiccness, colour1);
    drawRectOutline(m, x1, y1, x2, y2, colour2, thiccness);
  }
}
