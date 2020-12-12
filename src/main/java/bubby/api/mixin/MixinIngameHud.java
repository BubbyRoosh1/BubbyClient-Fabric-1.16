package bubby.api.mixin;

import bubby.api.setting.CheckBoxSetting;
import bubby.client.BubbyClient;
import bubby.client.gui.hud.PinnableFrame;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinIngameHud
{
  @Inject(at = @At(value = "RETURN"), method = "render", cancellable = true)
  public void
  render(MatrixStack m, float f, CallbackInfo ci)
  {
    BubbyClient.MC.textRenderer.drawWithShadow(m, BubbyClient.NAME + " " + BubbyClient.VERSION, 1, 1, Formatting.GREEN.getColorValue());
    for(PinnableFrame frame : BubbyClient.clickGuiManager.pinnableframes)
    {
      if(!frame.visible) continue;
      frame.updateFrame();
      if(frame.open)
        frame.renderAfter(m);
      if(frame.isPinned())
      {
        if(((CheckBoxSetting) BubbyClient.settings.getSettingByName("ClickGui RenderFrames")).get())
          frame.render(m);
        frame.renderText(m);
        frame.renderAfter(m);
      }
    }
  }
}
