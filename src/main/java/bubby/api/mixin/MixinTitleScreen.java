package bubby.api.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen
{
  @Shadow
  private final String splashText = "BubbyClient best client!";
}
