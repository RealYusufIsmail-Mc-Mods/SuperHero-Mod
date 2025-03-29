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
package io.github.realyusufismail.items.projectile;

import io.github.realyusufismail.init.ItemInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class StormBreakerProjectile extends CoreHammerProjectile {
    protected StormBreakerProjectile(EntityType<StormBreakerProjectile> p_331098_, Level p_331626_) {
        super(p_331098_, p_331626_);
    }

    public StormBreakerProjectile(
            EntityType<StormBreakerProjectile> entityType,
            Level level,
            LivingEntity shooter,
            ItemStack pickupItemStack) {
        super(entityType, level, shooter, pickupItemStack);
    }

    public StormBreakerProjectile(
            EntityType<StormBreakerProjectile> entityType,
            Level level,
            double x,
            double y,
            double z,
            ItemStack pickupItemStack) {
        super(entityType, level, x, y, z, pickupItemStack);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(ItemInit.STORMBREAKER.get());
    }
}
