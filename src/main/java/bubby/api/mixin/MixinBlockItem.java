package bubby.api.mixin;

import bubby.api.events.PlaceBlockEvent;
import bubby.client.BubbyClient;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class MixinBlockItem
{
  @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"))
  private void
  place(ItemPlacementContext itemPlacementContext, BlockState blockState, CallbackInfoReturnable<Boolean> ci)
  {
    PlaceBlockEvent event = new PlaceBlockEvent(itemPlacementContext.getBlockPos(), blockState.getBlock());
    BubbyClient.eventManager.callEvent(event);
  }
}
