package bubby.api.mixin;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.ScreenshotUtils;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.function.Consumer;

@Mixin(ScreenshotUtils.class)
public class MixinScreenshotUtils
{
  @Inject(at = @At("HEAD"), method = "method_1661")
  private static void
  capturedScreenshot(NativeImage nativeImage, File file, Consumer<Text> consumer, CallbackInfo ci)
  {
  }
}
