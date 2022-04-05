package com.linngdu664.bettersnowballfight;

import com.linngdu664.bettersnowballfight.itemclass.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Register {
    public static final Item COMPACTED_SNOWBALL = new CompactedSnowballItem(new FabricItemSettings().maxCount(16));
    public static final Item SMOOTH_SNOWBALL = new SmoothSnowballItem(new FabricItemSettings().maxCount(16));
    public static final Item STONE_SNOWBALL = new StoneSnowballItem(new FabricItemSettings().maxCount(16));
    public static final Item GLASS_SNOWBALL = new GlassSnowballItem(new FabricItemSettings().maxCount(16));
    public static final Item EXPLOSIVE_SNOWBALL = new ExplosiveSnowballItem(new FabricItemSettings().maxCount(16));
    public static final Item IRON_SNOWBALL = new IronSnowballItem(new FabricItemSettings().maxCount(16));
    public static final Item GOLD_SNOWBALL = new GoldSnowballItem(new FabricItemSettings().maxCount(16));
    public static final Item OBSIDIAN_SNOWBALL = new ObsidianSnowballItem(new FabricItemSettings().maxCount(16));
    public static final Item COMPACTED_SNOWBALL_SET = new CompactedSnowballSetItem(new FabricItemSettings().maxCount(16));
    public static final ToolItem DIAMOND_SNOWBALL_CLAMP = new SnowballClampItem(ToolMaterials.DIAMOND, new FabricItemSettings().maxCount(1));
    public static final ToolItem GOLD_SNOWBALL_CLAMP = new SnowballClampItem(ToolMaterials.GOLD, new FabricItemSettings().maxCount(1));
    public static final ToolItem IRON_SNOWBALL_CLAMP = new SnowballClampItem(ToolMaterials.IRON, new FabricItemSettings().maxCount(1));
    public static final ToolItem STONE_SNOWBALL_CLAMP = new SnowballClampItem(ToolMaterials.STONE, new FabricItemSettings().maxCount(1));
    public static final ToolItem NETHERITE_SNOWBALL_CLAMP = new SnowballClampItem(ToolMaterials.NETHERITE, new FabricItemSettings().maxCount(1));
    public static final ToolItem WOOD_SNOWBALL_CLAMP = new SnowballClampItem(ToolMaterials.WOOD, new FabricItemSettings().maxCount(1));
    public static final BowItem SNOWBALL_CANNON = new SnowballCannonItem(new FabricItemSettings().maxCount(1).maxDamage(256), 0);
    public static final BowItem POWERFUL_SNOWBALL_CANNON = new SnowballCannonItem(new FabricItemSettings().maxCount(1).maxDamage(256),2);
    public static final BowItem FREEZING_SNOWBALL_CANNON = new SnowballCannonItem(new FabricItemSettings().maxCount(1).maxDamage(256),1);
    public static final Item SUPER_POWER_CORE = new Item(new FabricItemSettings());
    public static final Item SUPER_FROZEN_CORE = new Item(new FabricItemSettings());
    public static final ShieldItem GLOVE = new GloveItem(new FabricItemSettings().maxDamage(128));

    public static final ItemGroup BSF_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("bsf", "bsf_group"))
            .icon(() -> new ItemStack(SMOOTH_SNOWBALL))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(SMOOTH_SNOWBALL));
                stacks.add(new ItemStack(COMPACTED_SNOWBALL));
                stacks.add(new ItemStack(STONE_SNOWBALL));
                stacks.add(new ItemStack(GLASS_SNOWBALL));
                stacks.add(new ItemStack(IRON_SNOWBALL));
                stacks.add(new ItemStack(GOLD_SNOWBALL));
                stacks.add(new ItemStack(OBSIDIAN_SNOWBALL));
                stacks.add(new ItemStack(EXPLOSIVE_SNOWBALL));
                stacks.add(new ItemStack(COMPACTED_SNOWBALL_SET));
                stacks.add(new ItemStack(WOOD_SNOWBALL_CLAMP));
                stacks.add(new ItemStack(STONE_SNOWBALL_CLAMP));
                stacks.add(new ItemStack(IRON_SNOWBALL_CLAMP));
                stacks.add(new ItemStack(GOLD_SNOWBALL_CLAMP));
                stacks.add(new ItemStack(DIAMOND_SNOWBALL_CLAMP));
                stacks.add(new ItemStack(NETHERITE_SNOWBALL_CLAMP));
                stacks.add(new ItemStack(SNOWBALL_CANNON));
                stacks.add(new ItemStack(POWERFUL_SNOWBALL_CANNON));
                stacks.add(new ItemStack(FREEZING_SNOWBALL_CANNON));
                stacks.add(new ItemStack(SUPER_POWER_CORE));
                stacks.add(new ItemStack(SUPER_FROZEN_CORE));
                stacks.add(new ItemStack(GLOVE));
            })
            .build();
    public static void register(){
        Registry.register(Registry.ITEM, new Identifier("bsf", "smooth_snowball"), SMOOTH_SNOWBALL);
        Registry.register(Registry.ITEM, new Identifier("bsf", "stone_snowball"), STONE_SNOWBALL);
        Registry.register(Registry.ITEM, new Identifier("bsf", "compacted_snowball"), COMPACTED_SNOWBALL);
        Registry.register(Registry.ITEM, new Identifier("bsf", "explosive_snowball"), EXPLOSIVE_SNOWBALL);
        Registry.register(Registry.ITEM, new Identifier("bsf", "glass_snowball"), GLASS_SNOWBALL);
        Registry.register(Registry.ITEM, new Identifier("bsf", "iron_snowball"), IRON_SNOWBALL);
        Registry.register(Registry.ITEM, new Identifier("bsf", "gold_snowball"), GOLD_SNOWBALL);
        Registry.register(Registry.ITEM, new Identifier("bsf", "obsidian_snowball"), OBSIDIAN_SNOWBALL);
        Registry.register(Registry.ITEM, new Identifier("bsf", "compacted_snowball_set"), COMPACTED_SNOWBALL_SET);
        Registry.register(Registry.ITEM, new Identifier("bsf", "diamond_snowball_clamp"), DIAMOND_SNOWBALL_CLAMP);
        Registry.register(Registry.ITEM, new Identifier("bsf", "gold_snowball_clamp"),GOLD_SNOWBALL_CLAMP );
        Registry.register(Registry.ITEM, new Identifier("bsf", "iron_snowball_clamp"),IRON_SNOWBALL_CLAMP );
        Registry.register(Registry.ITEM, new Identifier("bsf", "stone_snowball_clamp"),STONE_SNOWBALL_CLAMP);
        Registry.register(Registry.ITEM, new Identifier("bsf", "netherite_snowball_clamp"),NETHERITE_SNOWBALL_CLAMP );
        Registry.register(Registry.ITEM, new Identifier("bsf", "wood_snowball_clamp"), WOOD_SNOWBALL_CLAMP);
        Registry.register(Registry.ITEM, new Identifier("bsf", "snowball_cannon"), SNOWBALL_CANNON);
        Registry.register(Registry.ITEM, new Identifier("bsf", "powerful_snowball_cannon"), POWERFUL_SNOWBALL_CANNON);
        Registry.register(Registry.ITEM, new Identifier("bsf", "freezing_snowball_cannon"), FREEZING_SNOWBALL_CANNON);
        Registry.register(Registry.ITEM, new Identifier("bsf", "super_power_core"), SUPER_POWER_CORE);
        Registry.register(Registry.ITEM, new Identifier("bsf", "super_frozen_core"), SUPER_FROZEN_CORE);
        Registry.register(Registry.ITEM, new Identifier("bsf", "glove"), GLOVE);
    }
}
