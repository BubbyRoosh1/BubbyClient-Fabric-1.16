package bubby.client.utils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.explosion.Explosion;

import static bubby.client.BubbyClient.MC;

public class EntityUtils
{
  public static int
  changeHotbarSlotToItem(Item item)
  {
    int oldSlot = -1;
    int itemSlot = -1;
    for(int i = 0; i < 9; ++i)
    {
      if(MC.player.inventory.getStack(i).getItem() == item)
      {
        itemSlot = i;
        oldSlot = MC.player.inventory.selectedSlot;
      }
    }
    if(itemSlot != -1)
      MC.player.inventory.selectedSlot = itemSlot;
    return oldSlot;
  }

  public static boolean
  isAnimal(Entity e)
  {
    return e instanceof AnimalEntity || e instanceof AmbientEntity || e instanceof WaterCreatureEntity || e instanceof GolemEntity || e instanceof VillagerEntity;
  }

  public static void
  setGlowing(Entity e, Formatting color, String n)
  {
    Team team = MC.world.getScoreboard().getTeamNames().contains(n) ? MC.world.getScoreboard().getTeam(n) : MC.world.getScoreboard().addTeam(n);
    MC.world.getScoreboard().addPlayerToTeam(e instanceof PlayerEntity ? e.getEntityName() : e.getUuidAsString(), team);
    MC.world.getScoreboard().getTeam(n).setColor(color);
    e.setGlowing(true);
  }

  public static double
  getCrystalDamage(LivingEntity entity, BlockPos blockPos)
  {
    if(MC.world.getDifficulty() == Difficulty.PEACEFUL || entity.isImmuneToExplosion())
      return 0;

    Vec3d vec3d = Vec3d.of(blockPos).add(0.5, 1, 0.5f);
    double distance = Math.sqrt(entity.squaredDistanceTo(vec3d));
    if(distance >= 13)
      return 0;

    double density = Explosion.getExposure(vec3d, entity);
    double impact = (1.d - (distance / 12.d)) * density;
    double damage = ((impact * impact + impact) / 2 * 7 * (6 * 2) + 1);

    Difficulty difficulty = MC.world.getDifficulty();
    if(difficulty == Difficulty.PEACEFUL)
      damage = 0.f;
    else if(difficulty == Difficulty.EASY)
      damage = Math.min(damage / 2.f + 1.f, damage);
    else if(difficulty == Difficulty.HARD)
      damage = damage * 3.f / 2.f;

    if(entity instanceof PlayerEntity)
    {
      PlayerEntity playerEntity = (PlayerEntity) entity;
      if(playerEntity.hasStatusEffect(StatusEffects.RESISTANCE))
      {
        damage *= (1 - ((playerEntity.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() + 1) * 0.2));
      }
      if(damage < 0)
        damage = 0;
    }

    damage = DamageUtil.getDamageLeft((float) damage, (float) entity.getArmor(), (float) entity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).getValue());

    Explosion explosion = new Explosion(MC.world, null, vec3d.x, vec3d.y, vec3d.z, 6.f, false, Explosion.DestructionType.DESTROY);
    int protectionLevel = EnchantmentHelper.getProtectionAmount(entity.getArmorItems(), DamageSource.explosion(explosion));
    if(protectionLevel > 20)
      protectionLevel = 20;

    damage *= (1 - (protectionLevel / 25.d));
    if(damage < 0)
      damage = 0;

    return damage;

  }

  public static void
  attackEntity(Entity entity)
  {
    MC.interactionManager.attackEntity(MC.player, entity);
    MC.player.networkHandler.sendPacket(new PlayerInteractEntityC2SPacket(entity, MC.player.isSneaking()));
    MC.player.swingHand(Hand.MAIN_HAND);
    MC.player.networkHandler.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
  }

  public static void
  useEntity(Entity entity)
  {
    MC.interactionManager.attackEntity(MC.player, entity);
    MC.player.networkHandler.sendPacket(new PlayerInteractEntityC2SPacket(entity, MC.player.isSneaking()));
    MC.player.swingHand(Hand.MAIN_HAND);
    MC.player.networkHandler.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
  }
}
