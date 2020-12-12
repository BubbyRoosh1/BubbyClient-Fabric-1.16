package bubby.api.mixin;

import bubby.api.command.CommandManager;
import bubby.api.events.ReadPacketEvent;
import bubby.api.events.SendPacketEvent;
import bubby.client.BubbyClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection
{

  @Shadow
  private Channel channel;

  @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
  public void
  channelRead0(ChannelHandlerContext chc, Packet<?> packet, CallbackInfo ci)
  {
    if(this.channel.isOpen() && packet != null)
    {
      try
      {
        ReadPacketEvent event = new ReadPacketEvent(packet);
        BubbyClient.eventManager.callEvent(event);
        if(event.isCancelled())
          ci.cancel();
      }
      catch(Exception e)
      {
      }
    }
  }

  @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
  public void
  send(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> genericFutureListener, CallbackInfo callback)
  {
    SendPacketEvent event = new SendPacketEvent(packet);
    BubbyClient.eventManager.callEvent(event);
    if(event.isCancelled())
      callback.cancel();

    if(packet instanceof ChatMessageC2SPacket)
    {
      ChatMessageC2SPacket pack = (ChatMessageC2SPacket) packet;
      if(pack.getChatMessage().startsWith(CommandManager.prefix))
      {
        CommandManager.runCommand(pack.getChatMessage().substring(CommandManager.prefix.length()));
        callback.cancel();
      }
    }
  }
}
