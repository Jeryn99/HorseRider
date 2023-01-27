package net.horse_rider;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class HorseRider {
    public static final String MOD_ID = "horse_rider";

    public static float getOffset() {
        return -0.6F;
    }

    public static void positionRider(@NotNull AbstractHorse abstractHorse, Entity rider, CallbackInfo ci) {
        int riderIndex = abstractHorse.getPassengers().indexOf(rider);
        System.out.println(riderIndex);
        if (riderIndex == 1) {
            float i = 0.15F * abstractHorse.standAnimO;
            float offset = HorseRider.getOffset();

            float yOffset = (float) (abstractHorse.getY() + abstractHorse.getPassengersRidingOffset() + rider.getMyRidingOffset() + i);
            Vec3 vec3 = (new Vec3(0.0, 0.0, offset)).yRot(-abstractHorse.yBodyRot * 0.017453292F);
            rider.setPos(abstractHorse.getX() + vec3.x, yOffset, abstractHorse.getZ() + vec3.z);

            if (rider instanceof LivingEntity livingEntity) {
                livingEntity.yBodyRot = abstractHorse.yBodyRot;
            }

            ci.cancel();
        }
    }


    private static void clampRotation(Entity rider, Entity horse) {
        rider.setYBodyRot(horse.getYRot());
        float f = rider.getYRot();
        float g = Mth.wrapDegrees(f - horse.getYRot());
        float h = Mth.clamp(g, -160.0F, 160.0F);
        rider.yRotO += h - g;
        float i = f + h - g;
        rider.setYRot(i);
        rider.setYHeadRot(i);
    }

}
