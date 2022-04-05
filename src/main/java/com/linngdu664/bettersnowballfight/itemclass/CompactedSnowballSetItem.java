package com.linngdu664.bettersnowballfight.itemclass;

import com.linngdu664.bettersnowballfight.Register;
import com.linngdu664.bettersnowballfight.entityclass.AdvancedSnowballEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CompactedSnowballSetItem extends Item {
    public CompactedSnowballSetItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            AdvancedSnowballEntity snowballEntity1 = new AdvancedSnowballEntity(world, user, 0);
            AdvancedSnowballEntity snowballEntity2 = new AdvancedSnowballEntity(world, user, 0);
            AdvancedSnowballEntity snowballEntity3 = new AdvancedSnowballEntity(world, user, 0);
            snowballEntity1.setItem(new ItemStack(Register.COMPACTED_SNOWBALL, 1));
            snowballEntity2.setItem(new ItemStack(Register.COMPACTED_SNOWBALL, 1));
            snowballEntity3.setItem(new ItemStack(Register.COMPACTED_SNOWBALL, 1));
            snowballEntity1.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.0F, 10.0F);
            snowballEntity2.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.0F, 10.0F);
            snowballEntity3.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.0F, 10.0F);
            world.spawnEntity(snowballEntity1);
            world.spawnEntity(snowballEntity2);
            world.spawnEntity(snowballEntity3);
        }
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("compacted_snowball_set.tooltip").formatted(Formatting.GRAY));
    }
}
