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
package io.github.realyusufismail.data.lang;

import io.github.realyusufismail.SuperHeroMod;
import io.github.realyusufismail.events.SuperHeroModRegistries;
import io.github.realyusufismail.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

public class ModEnLangProvider extends LanguageProvider {
    public ModEnLangProvider(PackOutput output) {
        super(output, SuperHeroMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Items
        item(ItemInit.MARVEL_LOGO, "Marvel Logo");
        item(ItemInit.MJOLNIR, "Mjolnir");
        item(ItemInit.STORMBREAKER, "Stormbreaker");

        // Creative Menus
        add(SuperHeroModRegistries.SuperHeroModCreativeModeTabs.MARVEL_TAB_NAME.getString(), "Marvel Item Group");
    }

    private <T extends Item> void item(@NotNull DeferredItem<T> entry, String name) {
        add(entry.get(), name);
    }

    private <T extends Block> void block(@NotNull DeferredBlock<T> entry, String name) {
        add(entry.get(), name);
    }
}
