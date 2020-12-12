package bubby.client.modules;
import bubby.api.module.Module;
import bubby.api.events.*;
import bubby.api.module.*;
import bubby.api.eventapi.*;
import bubby.api.setting.*;
import bubby.client.utils.EntityUtils;
import bubby.client.utils.WorldUtils;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Surround extends Module {

    CheckBoxSetting rotate = new CheckBoxSetting("Rotate", this, false);
    CheckBoxSetting autotoggle = new CheckBoxSetting("Auto Toggle", this, false);

    public Surround() {
        super("Surround", "AutoFeetPlace", -1, Category.Combat, true);
        addSetting(rotate);
        addSetting(autotoggle);
    }

}