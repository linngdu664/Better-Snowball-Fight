package com.linngdu664.bettersnowballfight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

import static com.linngdu664.bettersnowballfight.Register.*;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(SNOWBALL_CANNON, new Identifier("pull"), (itemStack, clientWorld, livingEntity,i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
        });

        FabricModelPredicateProviderRegistry.register(SNOWBALL_CANNON, new Identifier("pulling"), (itemStack, clientWorld, livingEntity,i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(POWERFUL_SNOWBALL_CANNON, new Identifier("pull"), (itemStack, clientWorld, livingEntity,i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
        });

        FabricModelPredicateProviderRegistry.register(POWERFUL_SNOWBALL_CANNON, new Identifier("pulling"), (itemStack, clientWorld, livingEntity,i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(FREEZING_SNOWBALL_CANNON, new Identifier("pull"), (itemStack, clientWorld, livingEntity,i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
        });

        FabricModelPredicateProviderRegistry.register(FREEZING_SNOWBALL_CANNON, new Identifier("pulling"), (itemStack, clientWorld, livingEntity,i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(GLOVE, new Identifier("using"), (itemStack, clientWorld, livingEntity,i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });

    }
}
