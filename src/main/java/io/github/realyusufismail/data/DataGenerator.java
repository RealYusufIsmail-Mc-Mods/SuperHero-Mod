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
package io.github.realyusufismail.data;

import io.github.realyusufismail.SuperHeroMod;
import io.github.realyusufismail.data.lang.ModEnLangProvider;
import io.github.realyusufismail.data.texture.ModModelProvider;
import lombok.val;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerator {
    private DataGenerator() {}

    public static void gatherData(GatherDataEvent.Client event) {
        val gen = event.getGenerator();
        val packOutput = gen.getPackOutput();

        try {
            gen.addProvider(true, new ModEnLangProvider(packOutput));
            gen.addProvider(true, new ModModelProvider(packOutput));
        } catch (Exception e) {
            SuperHeroMod.logger.error("Error while generating data", e);
        }
    }
}
