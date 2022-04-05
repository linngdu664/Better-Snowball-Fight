package com.linngdu664.bettersnowballfight.itemclass;

import com.linngdu664.bettersnowballfight.Register;
import com.linngdu664.bettersnowballfight.entityclass.AdvancedSnowballEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class SnowballCannonItem extends BowItem {
    private final int coreType;

    public SnowballCannonItem(Settings settings, int type) {
        super(settings);
        this.coreType = type;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            ItemStack itemStack = new ItemStack(Items.AIR, 0);
            boolean flag = false;
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            float f = getPullProgress(i);
            if (!((double)f < 0.1D)) {
                for (int j = 0; j < playerEntity.getInventory().size(); ++j) {
                    ItemStack stack1 = playerEntity.getInventory().getStack(j);
                    if (stack1.getItem() == Register.COMPACTED_SNOWBALL || stack1.getItem() == Register.STONE_SNOWBALL ||
                            stack1.getItem() == Register.GLASS_SNOWBALL || stack1.getItem() == Register.IRON_SNOWBALL ||
                            stack1.getItem() == Register.GOLD_SNOWBALL || stack1.getItem() == Register.OBSIDIAN_SNOWBALL ||
                            stack1.getItem() == Register.EXPLOSIVE_SNOWBALL) {
                        itemStack = stack1;
                        flag = true;
                        break;
                    }
                }
                if (flag && !world.isClient) {
                    AdvancedSnowballEntity snowballEntity = new AdvancedSnowballEntity(world, user, 1);
                    if (itemStack.getItem() == Register.STONE_SNOWBALL) {
                        snowballEntity.setDamage(f * 3.0F);
                        snowballEntity.setBlazeDamage(f * 5.0F);
                        snowballEntity.setType(2);
                    } else if (itemStack.getItem() == Register.GLASS_SNOWBALL) {
                        snowballEntity.setDamage(f * 4.0F);
                        snowballEntity.setBlazeDamage(f * 6.0F);
                        snowballEntity.setType(3);
                    } else if (itemStack.getItem() == Register.IRON_SNOWBALL) {
                        snowballEntity.setDamage(f * 5.0F);
                        snowballEntity.setBlazeDamage(f * 7.0F);
                        snowballEntity.setType(4);
                    } else if (itemStack.getItem() == Register.GOLD_SNOWBALL) {
                        snowballEntity.setDamage(f * 6.0F);
                        snowballEntity.setBlazeDamage(f * 8.0F);
                        snowballEntity.setType(5);
                    } else if (itemStack.getItem() == Register.OBSIDIAN_SNOWBALL) {
                        snowballEntity.setDamage(f * 7.0F);
                        snowballEntity.setBlazeDamage(f * 9.0F);
                        snowballEntity.setType(6);
                    } else if (itemStack.getItem() == Register.EXPLOSIVE_SNOWBALL) {
                        snowballEntity.setDamage(5.0F);
                        snowballEntity.setBlazeDamage(7.0F);
                        snowballEntity.setType(7);
                        snowballEntity.setExplode(true);
                    }

                    snowballEntity.setItem(itemStack);
                    snowballEntity.setPunch(f * 1.8);
                    snowballEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 2.0F, 1.0F);
                    if (coreType == 1) {
                        snowballEntity.setFrozenTicks(140);
                    } else if (coreType == 2) {
                        snowballEntity.setPunch(f * 2.5);
                        snowballEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 2.53F, 1.0F);
                        snowballEntity.setWeaknessTicks(180);
                    }

                    stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                    world.spawnEntity(snowballEntity);
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!playerEntity.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                        if (itemStack.isEmpty()) {
                            playerEntity.getInventory().removeOne(itemStack);
                        }
                    }
                }
            }
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.IRON_INGOT);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("snowball_cannon.tooltip").formatted(Formatting.GRAY));
    }

}
