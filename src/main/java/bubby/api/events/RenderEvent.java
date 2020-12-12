package bubby.api.events;

import bubby.api.eventapi.Event;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;

public class RenderEvent extends Event
{
  public MatrixStack m;
  public Camera c;
  public float f;

  public RenderEvent(MatrixStack m, Camera c, float f)
  {
    this.m = m;
    this.c = c;
    this.f = f;
  }
}
