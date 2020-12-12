package bubby.api.mixin;

import bubby.api.mixininterface.MinecraftClientInterface;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient implements MinecraftClientInterface
{
  @Shadow
  private int itemUseCooldown;

  @Override
  public void
  setItemUseCooldown(int newCooldown)
  {
    itemUseCooldown = newCooldown;
  }
}
