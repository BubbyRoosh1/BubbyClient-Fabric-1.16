package bubby.api.mixin;

import bubby.client.BubbyClient;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard
{
  @Inject(method = "onKey", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputUtil.isKeyPressed(JI)Z", ordinal = 5), cancellable = true)
  private void
  onkeyEvent(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci)
  {
    if(key != -1)
      BubbyClient.modules.handleKeyPress(key);
  }

}
