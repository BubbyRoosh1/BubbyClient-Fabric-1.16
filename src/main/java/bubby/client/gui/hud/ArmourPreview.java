package bubby.client.gui.hud;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ArmourPreview extends PinnableFrame
{
  public ArmourPreview()
  {
    super("ArmourHUD", new ArrayList<>(), new ArrayList<>(), 50);
    this.leftText.add("");
    this.leftText.add("");
  }

  public void
  renderAfter(MatrixStack m)
  {
    int iteration = 0;
    for(ItemStack is : mc.player.inventory.armor)
    {
      iteration++;
      if(is.isEmpty()) continue;
      int xx = this.x - 97 + (9 - iteration) * 20 + 2;
      GlStateManager.enableDepthTest();

      mc.getItemRenderer().zOffset = 200F;
      mc.getItemRenderer().renderInGuiWithOverrides(is, xx, this.y + 16);
      mc.getItemRenderer().renderGuiItemOverlay(mc.textRenderer, is, xx, this.y + 16);
      mc.getItemRenderer().zOffset = 0F;
    }
  }
}

