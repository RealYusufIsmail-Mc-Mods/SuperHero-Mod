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
package io.github.realyusufismail;

import com.mojang.logging.LogUtils;
import io.github.realyusufismail.config.Config;
import io.github.realyusufismail.data.DataGenerator;
import io.github.realyusufismail.events.EventRegistries;
import io.github.realyusufismail.events.SuperHeroModRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(SuperHeroMod.MOD_ID)
public class SuperHeroMod {
    public static final String MOD_ID = "superheromod";
    public static final Logger logger = LogUtils.getLogger();

    public SuperHeroMod(IEventBus bus, @NotNull ModContainer modContainer) {

        // register
        SuperHeroModRegistries.init(bus);

        // listeners
        bus.addListener(DataGenerator::gatherData);
        bus.addListener(EventRegistries::init);
        bus.addListener(this::commonSetup);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        logger.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock) {
            // TODO: Use to recipe book
        }
    }
}
