package net.horse_rider.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class MixinEntity {

    @Inject(at = @At("HEAD"), method = "canAddPassenger(Lnet/minecraft/world/entity/Entity;)Z", cancellable = true)
    protected void canAddPassenger(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof AbstractHorse abstractHorse) {
            cir.setReturnValue(abstractHorse.getPassengers().size() < 2);
        }
    }

}
