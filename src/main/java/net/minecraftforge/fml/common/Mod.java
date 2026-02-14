package net.minecraftforge.fml.common;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mod {
    String value() default "";

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface EventBusSubscriber {
        String modid() default "";
        Bus bus() default Bus.MOD;
        net.minecraftforge.api.distmarker.Dist value() default net.minecraftforge.api.distmarker.Dist.CLIENT; // use shared Dist
        public enum Bus { MOD, FORGE }
    }
}
