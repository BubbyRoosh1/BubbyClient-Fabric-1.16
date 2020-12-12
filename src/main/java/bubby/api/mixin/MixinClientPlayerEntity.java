package bubby.api.mixin;

import bubby.api.events.TickEvent;
import bubby.api.events.WalkingUpdateEvent;
import bubby.client.BubbyClient;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity extends AbstractClientPlayerEntity
{

  public MixinClientPlayerEntity(ClientWorld cliendWorld_1, GameProfile gameProfile_1)
  {
    super(cliendWorld_1, gameProfile_1);
  }

  @Inject(at = @At("RETURN"), method = "tick()V", cancellable = true)
  public void tickPost(CallbackInfo info)
  {
    TickEvent event = new TickEvent();
    BubbyClient.eventManager.callEvent(event);
    if(event.isCancelled())
      info.cancel();
  }

  @Inject(at = @At("HEAD"), method = "move", cancellable = true)
  public void
  move(MovementType m, Vec3d v, CallbackInfo ci)
  {
    WalkingUpdateEvent event = new WalkingUpdateEvent();
    BubbyClient.eventManager.callEvent(event);
    if(event.isCancelled())
      ci.cancel();
  }

  @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"), method = "tickMovement()V")
  private boolean
  isUsingItem(ClientPlayerEntity player)
  {
    if(BubbyClient.modules.getModuleByName("Velocity").isToggled())
      return false;
    return player.isUsingItem();
  }

  @Redirect(at = @At(value = "INVOKE",
          target = "Lnet/minecraft/client/gui/screen/Screen;isPauseScreen()Z",
          ordinal = 0),
          method = {"updateNausea()V"})
  private boolean onUpdateNausea(Screen screen)
  {
    if(BubbyClient.modules.getModuleByName("Portals").isToggled())
      return true;

    return screen.isPauseScreen();
  }
}
