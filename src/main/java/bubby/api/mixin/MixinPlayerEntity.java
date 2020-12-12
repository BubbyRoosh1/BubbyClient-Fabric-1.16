package bubby.api.mixin;

import bubby.api.events.DropItemEvent;
import bubby.client.BubbyClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity
{
  @Inject(at = @At("HEAD"), method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;")
  private void
  dropItem(ItemStack itemStack, boolean boolean1, boolean boolean2, CallbackInfoReturnable<Boolean> ci)
  {
    DropItemEvent event = new DropItemEvent(itemStack);
    BubbyClient.eventManager.callEvent(event);
  }
}
