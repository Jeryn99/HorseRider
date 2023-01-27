package mc.craig.software.horse.mixin;

import mc.craig.software.horse.HorseRider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHorse.class)
public class MixinAbstractHorse {

    @Inject(at = @At("HEAD"), method = "positionRider(Lnet/minecraft/world/entity/Entity;)V", cancellable = true)
    public void positionRider(Entity rider, CallbackInfo ci) {
        AbstractHorse abstractHorse = (AbstractHorse) (Object) this;
        HorseRider.positionRider(abstractHorse, rider, ci);
    }

}
