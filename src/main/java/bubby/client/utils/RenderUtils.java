package bubby.client.utils;

import bubby.client.BubbyClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class RenderUtils
{
  public static void
  drawBox(BlockPos pos, float r, float g, float b, float a, boolean fill)
  {
    drawBox(new Box(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1), r, g, b, a, fill);
  }

  public static void
  drawBox(Box boxx, float r, float g, float b, float a, boolean fill)
  {
    Vec3d camPos = getCamPos();
    Box box = boxx.offset(-camPos.getX(), -camPos.getY(), -camPos.getZ());

    GL11.glPushMatrix();
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glDisable(3553);
    GL11.glEnable(2848);
    GL11.glDisable(2929);
    GL11.glDepthMask(false);
    GL11.glLineWidth(2.5F);


    GL11.glRotated(BubbyClient.MC.player.pitch, 1.d, 0.d, 0.d);
    GL11.glRotated((double) BubbyClient.MC.player.yaw + 180, 0.d, 1.d, 0.d);

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder buffer = tessellator.getBuffer();

    buffer.begin(3, VertexFormats.POSITION_COLOR);

    buffer.vertex(box.minX, box.minY, box.minZ).color(r, g, b, a / 2f).next();
    buffer.vertex(box.minX, box.minY, box.maxZ).color(r, g, b, a / 2f).next();
    buffer.vertex(box.maxX, box.minY, box.maxZ).color(r, g, b, a / 2f).next();
    buffer.vertex(box.maxX, box.minY, box.minZ).color(r, g, b, a / 2f).next();

    buffer.vertex(box.minX, box.minY, box.minZ).color(r, g, b, a / 2f).next();
    buffer.vertex(box.minX, box.maxY, box.minZ).color(r, g, b, a / 2f).next();
    buffer.vertex(box.maxX, box.maxY, box.minZ).color(r, g, b, a / 2f).next();
    buffer.vertex(box.maxX, box.maxY, box.maxZ).color(r, g, b, a / 2f).next();

    buffer.vertex(box.minX, box.maxY, box.maxZ).color(r, g, b, a / 2f).next();
    buffer.vertex(box.minX, box.maxY, box.minZ).color(r, g, b, a / 2f).next();
    buffer.vertex(box.minX, box.minY, box.maxZ).color(r, g, b, 0f).next();
    buffer.vertex(box.minX, box.maxY, box.maxZ).color(r, g, b, a / 2f).next();

    buffer.vertex(box.maxX, box.minY, box.maxZ).color(r, g, b, 0f).next();
    buffer.vertex(box.maxX, box.maxY, box.maxZ).color(r, g, b, a / 2f).next();
    buffer.vertex(box.maxX, box.minY, box.minZ).color(r, g, b, 0f).next();
    buffer.vertex(box.maxX, box.maxY, box.minZ).color(r, g, b, a / 2f).next();

    tessellator.draw();
    if(fill)
    {
      buffer.begin(5, VertexFormats.POSITION_COLOR);
      WorldRenderer.drawBox(buffer,
              box.minX, box.minY, box.minZ,
              box.maxX, box.maxY, box.maxZ, r, g, b, a / 2f);
      tessellator.draw();
    }

    GL11.glDisable(2848);
    GL11.glEnable(3553);
    GL11.glEnable(2929);
    GL11.glDepthMask(true);
    GL11.glDisable(3042);
    GL11.glPopMatrix();

  }

  public static void
  drawLine(double x1, double y1, double z1, double x2, double y2, double z2, float r, float g, float b, float a, float thicc)
  {

    GL11.glEnable(GL11.GL_BLEND);
    GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
    GL11.glLineWidth(thicc);
    GL11.glDisable(GL11.GL_TEXTURE_2D);
    GL11.glDisable(GL11.GL_DEPTH_TEST);
    GL11.glMatrixMode(5889);
    GL11.glEnable(GL11.GL_LINE_SMOOTH);
    GL11.glPushMatrix();

    GL11.glRotated(BubbyClient.MC.player.pitch, 1.d, 0.d, 0.d);
    GL11.glRotated(BubbyClient.MC.player.yaw + 180, 0.d, 1.d, 0.d);

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder buffer = tessellator.getBuffer();
    Vec3d camPos = getCamPos();

    buffer.begin(GL11.GL_LINES, VertexFormats.POSITION_COLOR);
    buffer.vertex(x1 - camPos.x, y1 - camPos.y, z1 - camPos.z).color(r, g, b, a).next();
    buffer.vertex(x2 - camPos.x, y2 - camPos.y, z2 - camPos.z).color(r, g, b, a).next();
    tessellator.draw();

    GL11.glDisable(GL11.GL_LINE_SMOOTH);
    GL11.glPopMatrix();
    GL11.glMatrixMode(5888);
    GL11.glEnable(GL11.GL_DEPTH_TEST);
    GL11.glEnable(GL11.GL_TEXTURE_2D);
    GL11.glDisable(GL11.GL_BLEND);
  }

  private static Vec3d
  getCamPos()
  {
    return BubbyClient.MC.gameRenderer.getCamera().getPos();
  }
}
