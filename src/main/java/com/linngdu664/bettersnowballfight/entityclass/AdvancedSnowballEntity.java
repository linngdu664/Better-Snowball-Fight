package com.linngdu664.bettersnowballfight.entityclass;

import com.linngdu664.bettersnowballfight.Register;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class AdvancedSnowballEntity extends SnowballEntity {
    private int weaknessTicks = 0;
    private int frozenTicks = 0;
    private double punch = 0.0;
    private float damage = Float.intBitsToFloat(1);
    private float blazeDamage = 3.0F;
    private boolean explode = false;
    private int type;

    public AdvancedSnowballEntity(World world, LivingEntity owner, int type) {
        super(world, owner);
        this.type = type;
    }

    public AdvancedSnowballEntity(World world, LivingEntity owner, int type, float damage, float blazeDamage) {
        super(world, owner);
        this.type = type;
        this.damage = damage;
        this.blazeDamage = blazeDamage;
    }

    public AdvancedSnowballEntity(World world, double x, double y, double z, int type) {
        super(world, x, y, z);
        this.type = type;
    }

    public AdvancedSnowballEntity(World world, double x, double y, double z, int type, float damage, float blazeDamage) {
        super(world, x, y, z);
        this.type = type;
        this.damage = damage;
        this.blazeDamage = blazeDamage;
    }

    private boolean isHeadingToEntity(PlayerEntity player) {
        float pitch = player.getPitch() * MathHelper.PI / 180.0F;
        float yaw = player.getYaw() * MathHelper.PI / 180.0F;
        Vec3d speedVec = this.getVelocity().normalize();
        Vec3d cameraVec = new Vec3d(-MathHelper.cos(pitch) * MathHelper.sin(yaw), -MathHelper.sin(pitch), MathHelper.cos(pitch) * MathHelper.cos(yaw));
        return MathHelper.abs((float) (cameraVec.dotProduct(speedVec) + 1.0F)) < 0.2F;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        LivingEntity entity = (LivingEntity) entityHitResult.getEntity();
        if (entity instanceof PlayerEntity player && (player.getOffHandStack().isItemEqual(new ItemStack(Register.GLOVE)) ||
                player.getMainHandStack().isItemEqual(new ItemStack(Register.GLOVE))) &&
                player.isUsingItem() && isHeadingToEntity(player)) {
            switch (type) {
                case 0 -> player.getInventory().offer(new ItemStack(Register.SMOOTH_SNOWBALL, 1), true);
                case 1 -> player.getInventory().offer(new ItemStack(Register.COMPACTED_SNOWBALL, 1), true);
                case 2 -> player.getInventory().offer(new ItemStack(Register.STONE_SNOWBALL, 1), true);
                case 3 -> player.getInventory().offer(new ItemStack(Register.GLASS_SNOWBALL, 1), true);
                case 4 -> player.getInventory().offer(new ItemStack(Register.IRON_SNOWBALL, 1), true);
                case 5 -> player.getInventory().offer(new ItemStack(Register.GOLD_SNOWBALL, 1), true);
                case 6 -> player.getInventory().offer(new ItemStack(Register.OBSIDIAN_SNOWBALL, 1), true);
                case 7 -> player.getInventory().offer(new ItemStack(Register.EXPLOSIVE_SNOWBALL, 1), true);
                case 8 -> player.getInventory().offer(new ItemStack(Items.SNOWBALL, 1), true);
            }
            if (player.getMainHandStack().isItemEqual(new ItemStack(Register.GLOVE))) {
                player.getMainHandStack().damage(1, player, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            } else if (player.getOffHandStack().isItemEqual(new ItemStack(Register.GLOVE))) {
                player.getOffHandStack().damage(1, player, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
            }
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.NEUTRAL, 3F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            return;
        }

        float i = entity instanceof BlazeEntity ? blazeDamage : damage;
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), i);
        if (frozenTicks > 0) {
            entity.setFrozenTicks(frozenTicks);
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 1));
        }
        if (weaknessTicks > 0) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, weaknessTicks, 1));
        }
        Vec3d vec3d = this.getVelocity().multiply(0.2 * punch, 0.0, 0.2 * punch);
        entity.addVelocity(vec3d.x, 0.0, vec3d.z);
        if (explode) {
            if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                world.createExplosion(null, this.getX(), this.getY(), this.getZ(), 1.5F, Explosion.DestructionType.DESTROY);
            } else {
                world.createExplosion(null, this.getX(), this.getY(), this.getZ(), 1.5F, Explosion.DestructionType.NONE);
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (explode) {
            if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                world.createExplosion(null, this.getX(), this.getY(), this.getZ(), 1.5F, Explosion.DestructionType.DESTROY);
            } else {
                world.createExplosion(null, this.getX(), this.getY(), this.getZ(), 1.5F, Explosion.DestructionType.NONE);
            }
        }
    }

    public void setPunch(double punch) {
        this.punch = punch;
    }
    public void setDamage(float damage) {
        this.damage = damage;
    }
    public void setBlazeDamage(float damage) {
        this.blazeDamage = damage;
    }
    public void setExplode(boolean explode) {
        this.explode = explode;
    }
    public void setFrozenTicks(int frozenTicks) {
        this.frozenTicks = frozenTicks;
    }
    public void setWeaknessTicks(int weaknessTicks) {
        this.weaknessTicks = weaknessTicks;
    }
    public void setType(int type) {
        this.type = type;
    }

}
