package bubby.api.mixin;

import bubby.client.BubbyClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.PacketInflater;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Mixin(PacketInflater.class)
public class MixinPacketInflater
{
  @Shadow
  private int compressionThreshold;

  @Shadow
  @Final
  private Inflater inflater;

  @Inject(method = "decode", at = @At("HEAD"), cancellable = true)
  private void onDecode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list, CallbackInfo ci) throws DataFormatException
  {
    if(BubbyClient.modules.getModuleByName("AntiChunkBan").isToggled())
      ci.cancel();

    if(byteBuf.readableBytes() != 0)
    {
      PacketByteBuf packetByteBuf = new PacketByteBuf(byteBuf);
      int i = packetByteBuf.readVarInt();
      if(i == 0)
      {
        list.add(packetByteBuf.readBytes(packetByteBuf.readableBytes()));
      }
      else
      {
        if(i < this.compressionThreshold)
        {
          throw new DecoderException("Badly compressed packet - size of " + i + " is below server threshold of " + this.compressionThreshold);
        }

        if(i > 51200000)
        {
          throw new DecoderException("Badly compressed packet - size of " + i + " is larger than protocol maximum of " + 51200000);
        }

        byte[] bs = new byte[packetByteBuf.readableBytes()];
        packetByteBuf.readBytes(bs);
        this.inflater.setInput(bs);
        byte[] cs = new byte[i];
        this.inflater.inflate(cs);
        list.add(Unpooled.wrappedBuffer(cs));
        this.inflater.reset();
      }
    }
  }
}
