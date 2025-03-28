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
package io.github.realyusufismail.init;

import io.github.realyusufismail.events.SuperHeroModRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {
    private static final DeferredRegister.Items ITEMS = SuperHeroModRegistries.ITEMS;

    public static DeferredItem<Item> MARVEL_LOGO;
    public static DeferredItem<Item> MJOLNIR;
    public static DeferredItem<Item> STORMBREAKER;

    public static void init() {
        MARVEL_LOGO = ITEMS.registerItem("marvel_logo", Item::new);
        MJOLNIR = ITEMS.registerItem("mjolnir", Item::new);
        STORMBREAKER = ITEMS.registerItem("stormbreaker", Item::new);
    }
}
