/*
 * Copyright 2025 RealYusufIsmail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.events;

import io.github.realyusufismail.SuperHeroMod;
import io.github.realyusufismail.init.ItemInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class SuperHeroModRegistries {
    private static final String MOD_ID = SuperHeroMod.MOD_ID;

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static void init(IEventBus modEventBus) {
        ItemInit.init();
        SuperHeroModCreativeModeTabs.init();

        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    public static class SuperHeroModCreativeModeTabs {
        public static MutableComponent MARVEL_TAB_NAME = Component.translatable("itemGroup.superheromod.marvel");

        public static DeferredHolder<CreativeModeTab, CreativeModeTab> MARVEL_TAB;

        static void init() {
            MARVEL_TAB = CREATIVE_MODE_TABS.register("marvel_tab", () -> CreativeModeTab.builder()
                    .title(MARVEL_TAB_NAME)
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ItemInit.MARVEL_LOGO.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        handelItemAndBlockLoading(output);
                    })
                    .build());
        }

        static void handelItemAndBlockLoading(CreativeModeTab.@NotNull Output output) {
            BLOCKS.getEntries().stream().map(block -> block.get().asItem()).forEach(output::accept);

            ITEMS.getEntries().stream().map(item -> item.get().asItem()).forEach(output::accept);
        }
    }
}
