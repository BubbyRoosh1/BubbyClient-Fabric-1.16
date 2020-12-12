package bubby.api.mixin;

import net.minecraft.client.gl.ShaderEffect;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer
{
  //need to get cool shaders to replace gay ass glow for ESP
  @Shadow
  private ShaderEffect entityOutlineShader;
}
