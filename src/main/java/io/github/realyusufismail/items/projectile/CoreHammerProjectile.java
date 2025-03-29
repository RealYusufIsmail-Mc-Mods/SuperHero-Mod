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

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * @see ThrownTrident
 */
abstract class CoreHammerProjectile extends AbstractArrow {
    private static final EntityDataAccessor<Byte> ID_LOYALTY;
    private static final EntityDataAccessor<Boolean> ID_FOIL;

    protected CoreHammerProjectile(EntityType<? extends CoreHammerProjectile> p_331098_, Level p_331626_) {
        super(p_331098_, p_331626_);
    }

    public CoreHammerProjectile(
            EntityType<? extends CoreHammerProjectile> entityType,
            Level level,
            LivingEntity shooter,
            ItemStack pickupItemStack) {
        super(entityType, shooter, level, pickupItemStack, (ItemStack) null);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(pickupItemStack));
        this.entityData.set(ID_FOIL, pickupItemStack.hasFoil());
    }

    public CoreHammerProjectile(
            EntityType<? extends CoreHammerProjectile> entityType,
            Level level,
            double x,
            double y,
            double z,
            ItemStack pickupItemStack) {
        super(entityType, x, y, z, level, pickupItemStack, pickupItemStack);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(pickupItemStack));
        this.entityData.set(ID_FOIL, pickupItemStack.hasFoil());
    }

    // TODO -> Use to store owner id, etc
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder p_326249_) {
        super.defineSynchedData(p_326249_);
        p_326249_.define(ID_LOYALTY, (byte) 0);
        p_326249_.define(ID_FOIL, false);
    }

    // TODO -> Implement with help from ThrownTrident
    public void tick() {}

    // TODO -> Use owner id here
    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() && (!(entity instanceof ServerPlayer) || !entity.isSpectator());
    }

    private byte getLoyaltyFromItem(ItemStack stack) {
        Level var3 = this.level();
        byte var10000;
        if (var3 instanceof ServerLevel serverlevel) {
            var10000 = (byte)
                    Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverlevel, stack, this), 0, 127);
        } else {
            var10000 = 0;
        }

        return var10000;
    }

    @Override
    protected abstract @NotNull ItemStack getDefaultPickupItem();

    /**
     * TODO -> Make custom sound for both hammers
     */
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    static {
        ID_LOYALTY = SynchedEntityData.defineId(CoreHammerProjectile.class, EntityDataSerializers.BYTE);
        ID_FOIL = SynchedEntityData.defineId(CoreHammerProjectile.class, EntityDataSerializers.BOOLEAN);
    }
}
