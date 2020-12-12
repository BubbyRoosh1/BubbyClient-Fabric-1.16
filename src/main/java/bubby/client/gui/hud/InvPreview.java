package bubby.client.gui.hud;

import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public class InvPreview extends PinnableFrame
{
  public InvPreview()
  {
    //smh
    super("InvPreview", new ArrayList<>(), new ArrayList<>(), 70);
  }


  public void
  render(MatrixStack matrix)
  {
    super.render(matrix);

    /* this renders all the items I think, just in the wrong order and
     * direction..
    int iteration = 0;
    int yOff = 0;
    for(int currentSlot = 39; currentSlot > 9; currentSlot--)
    {
      iteration++;
      GlStateManager.enableDepthTest();
      int xx = this.x - 97 + (9 - iteration) * 20 + 2;
      if(iteration > 9)
      {
        iteration = 0;
        yOff += 10;
      }
      mc.getItemRenderer().zOffset = 200.f;
      mc.getItemRenderer().renderInGuiWithOverrides(mc.player.inventory.getStack(currentSlot), xx, this.y + 16 + yOff);
      mc.getItemRenderer().renderGuiItemOverlay(mc.textRenderer, mc.player.inventory.getStack(currentSlot), xx, this.y + 16 + yOff);
      mc.getItemRenderer().zOffset = 0.f;
    }
    */
  }
}


