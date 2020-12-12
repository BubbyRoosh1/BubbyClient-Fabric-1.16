package bubby.api.mixin;

import bubby.api.events.AddEntityEvent;
import bubby.api.events.RemoveEntityEvent;
import bubby.client.BubbyClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld
{
  @Inject(at = @At("HEAD"), method = "addEntity", cancellable = true)
  public void
  addEntity(int id, Entity entity, CallbackInfo ci)
  {
    AddEntityEvent event = new AddEntityEvent(entity);
    BubbyClient.eventManager.callEvent(event);
    if(event.isCancelled())
      ci.cancel();
  }

  @Inject(at = @At("HEAD"), method = "finishRemovingEntity", cancellable = true)
  private void
  finishRemovingEntity(Entity entity, CallbackInfo ci)
  {
    RemoveEntityEvent event = new RemoveEntityEvent(entity);
    BubbyClient.eventManager.callEvent(event);
    if(event.isCancelled())
      ci.cancel();
  }
}
