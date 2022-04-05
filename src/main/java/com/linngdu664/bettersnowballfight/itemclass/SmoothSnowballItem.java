package com.linngdu664.bettersnowballfight.itemclass;

import com.linngdu664.bettersnowballfight.Register;
import com.linngdu664.bettersnowballfight.entityclass.AdvancedSnowballEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Util;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SmoothSnowballItem extends Item {
    public SmoothSnowballItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, new ProjectileDispenserBehavior() {
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                return Util.make(new AdvancedSnowballEntity(world, position.getX(), position.getY(), position.getZ(), 0), (entity) -> entity.setItem(stack));
            }
        });
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (hand == Hand.MAIN_HAND) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!world.isClient) {
                AdvancedSnowballEntity snowballEntity = new AdvancedSnowballEntity(world, user, 0);
                snowballEntity.setItem(itemStack);
                snowballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.25F, 1.0F);
                world.spawnEntity(snowballEntity);
            }
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        else if (user.getMainHandStack().isEmpty()) {
            user.giveItemStack(new ItemStack(Register.COMPACTED_SNOWBALL, 1));
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("smooth_snowball.tooltip").formatted(Formatting.GRAY));
    }
}
