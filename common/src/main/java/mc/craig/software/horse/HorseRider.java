package mc.craig.software.horse;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class HorseRider {
    public static final String MOD_ID = "riding_partners";

    public static float getOffset() {
        return -0.6F;
    }

    public static void positionRider(@NotNull AbstractHorse abstractHorse, Entity rider, CallbackInfo ci) {
        int riderIndex = abstractHorse.getPassengers().indexOf(rider);
        if (riderIndex == 1) {
            float kickUpCompensation = 0.20F * abstractHorse.standAnimO;
            float offset = HorseRider.getOffset();

            float yOffset = (float) (abstractHorse.getY() + abstractHorse.getPassengersRidingOffset() + rider.getMyRidingOffset() - (1.5 * kickUpCompensation));
            Vec3 vec3 = (new Vec3(0.0, 0.0, offset)).yRot(-abstractHorse.yBodyRot * 0.017453292F);

            if(kickUpCompensation > 0){
                vec3 = vec3.relative(abstractHorse.getDirection().getOpposite(), 0.5F);
            }

            rider.setPos(abstractHorse.getX() + vec3.x, yOffset, abstractHorse.getZ() + vec3.z);

            if (rider instanceof LivingEntity livingEntity) {
                livingEntity.yBodyRot = abstractHorse.yBodyRot;
            }
            ci.cancel();
        }
    }


}
