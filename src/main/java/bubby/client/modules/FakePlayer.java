package bubby.client.modules;

import bubby.api.module.Module;
import bubby.api.module.*;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;

import java.util.ArrayList;
import com.mojang.authlib.GameProfile;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;

public class FakePlayer extends Module {
    private bubby.client.utils.FakePlayer fake;
    private Vec3d oldPos;
    private Vec3d newPos;

    public FakePlayer() {

        super("FakePlayer", "FakePlayer", -1, Category.Misc, true);
    }



    public void
    onEnable()
    {
        oldPos = mc.player.getPos();

       // fake.setAbsorptionAmount(69);
        fake = new bubby.client.utils.FakePlayer();
        fake.copyPositionAndRotation(mc.player);
        fake.setBoundingBox(fake.getBoundingBox().expand(0.1));
        fake.spawn();
        fake.abilities.invulnerable = true;
        //fake.abilities.invulnerable = true;
        super.onEnable();
    }

    public void
    onDisable()
    {
        newPos = mc.player.getPos();
        fake.despawn();
        mc.player.setPos(newPos.x, newPos.y, newPos.z);
        super.onDisable();
    }
}
