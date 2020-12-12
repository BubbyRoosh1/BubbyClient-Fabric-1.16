package bubby.api.mixin;

import bubby.api.setting.SliderSetting;
import bubby.client.BubbyClient;
import bubby.client.utils.RenderUtils2D;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer<T extends Entity>
{

  @Shadow
  @Final
  protected EntityRenderDispatcher dispatcher;

  @Inject(at = @At("HEAD"), method = "renderLabelIfPresent", cancellable = true)
  public void renderLabelIfPresent(T entity_1, Text text_1, MatrixStack matrixStack_1, VertexConsumerProvider vertexConsumerProvider_1, int int_1, CallbackInfo info)
  {
    if(BubbyClient.modules.getModuleByName("NameTags").isToggled())
    {
      if(entity_1 instanceof LivingEntity)
      {
        renderTags(entity_1, text_1, matrixStack_1, vertexConsumerProvider_1, int_1);
        info.cancel();
      }
    }
  }

  private void
  renderTags(T e, Text t, MatrixStack m, VertexConsumerProvider v, int l)
  {
    if(t.getString().contains("Health")) return;
    boolean bl = true;
    float f = e.getHeight() + 0.5F;
    int y = "deadmau5".equals(t.getString()) ? -10 : 0;

    m.push();
    m.translate(0.D, f, 0.D);
    m.multiply(this.dispatcher.getRotation());

    float scale = (float) ((SliderSetting) BubbyClient.settings.getSettingByName("NameTags Scale")).get();
    Camera camera = BubbyClient.MC.gameRenderer.getCamera();
    double distance = Math.sqrt(camera.getPos().squaredDistanceTo(e.getX(), e.getY(), e.getZ()));
    if(distance > 15)
      scale *= distance / 15;

    m.scale(-0.025F * scale, -0.025F * scale, 0.025F);
    scale += 2;

    Matrix4f m4f = m.peek().getModel();
    LivingEntity ent = (LivingEntity) e;

    String str = t.getString();
    str = str + " [" + (int) (ent.getHealth() + ent.getAbsorptionAmount()) + "/" + (int) ent.getMaxHealth() + "]";
    MinecraftClient mc = MinecraftClient.getInstance();

    float x1 = (-mc.textRenderer.getWidth(str) / 2);
    float x2 = x1 + mc.textRenderer.getWidth(str) * 1.5f;

    RenderUtils2D.drawBorderedRect(m, (int) x1 - 2, y - 3 - (int) scale, (int) (x1 + x2) + 2, y + 11 - (int) scale, 0xaa000000, 0xaa3d3d3d, 1);

    mc.textRenderer.draw(str, x1, y - scale, -1, false, m4f, v, true, 0, l);

    m.pop();
  }

}
