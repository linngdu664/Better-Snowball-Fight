package com.linngdu664.bettersnowballfight.itemclass;

import com.linngdu664.bettersnowballfight.Register;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SnowballClampItem extends ToolItem {

    public SnowballClampItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity user = context.getPlayer();
        World world = context.getWorld();
        ItemStack stack = context.getStack();
        if (world.getBlockState(context.getBlockPos()).getBlock() == Blocks.SNOW_BLOCK ||
                world.getBlockState(context.getBlockPos()).getBlock() == Blocks.SNOW ||
                world.getBlockState(context.getBlockPos()).getBlock() == Blocks.POWDER_SNOW) {
            if (user.getMainHandStack().isEmpty() || user.getOffHandStack().isEmpty()) {
                user.getInventory().offer(new ItemStack(Register.SMOOTH_SNOWBALL, 1), true);
                stack.damage(1, user, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof SnowGolemEntity && (user.getMainHandStack().isEmpty() || user.getOffHandStack().isEmpty())) {
            user.getInventory().offer(new ItemStack(Register.COMPACTED_SNOWBALL, 1), true);
            stack.damage(1, user, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("snowball_clamp.tooltip").formatted(Formatting.GRAY));
    }
}