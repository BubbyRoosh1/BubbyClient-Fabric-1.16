package bubby.api.mixin;

import bubby.api.setting.ModeSetting;
import bubby.api.setting.SliderSetting;
import bubby.client.BubbyClient;
import bubby.client.modules.NoRender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class MixinInGameOverlayRenderer
{
  @Inject(at = {@At("HEAD")},
          method = {"renderFireOverlay(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/util/math/MatrixStack;)V"},
          cancellable = true)
  private static void
  onRenderFireOverlay(MinecraftClient mc, MatrixStack ms, CallbackInfo ci)
  {
    if(((ModeSetting) BubbyClient.settings.getSettingByName("AntiFire Render")).get() == NoRender.RenderMode.None
            && BubbyClient.modules.getModuleByName("AntiFire").isToggled())
      ci.cancel();
  }

  @ModifyConstant(method =
          "renderFireOverlay(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/util/math/MatrixStack;)V", constant = @Constant(doubleValue = -0.30000001192092896D))
  private static double getFireOffset(double orig)
  {
    float offset = (float) ((SliderSetting) BubbyClient.settings.getSettingByName("AntiFire Level")).get();
    if(((ModeSetting) BubbyClient.settings.getSettingByName("AntiFire Render")).get() == NoRender.RenderMode.Lowered
            && BubbyClient.modules.getModuleByName("AntiFire").isToggled())
      return -offset;

    return orig;
  }
}
