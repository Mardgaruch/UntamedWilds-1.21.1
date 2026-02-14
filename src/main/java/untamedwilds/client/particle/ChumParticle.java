package untamedwilds.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
public class ChumParticle extends RisingParticle {
    private final SpriteSet spriteWithAge;

    public ChumParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ, @javax.annotation.Nullable SpriteSet spriteWithAge) {
        super(world, x, y, z, motionX, Math.abs(motionY) * -1, motionZ);
        this.spriteWithAge = spriteWithAge;
        this.lifetime = this.random.nextInt(80) + 120;
        if (this.spriteWithAge != null) this.setSpriteFromAge(spriteWithAge);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.spriteWithAge);
        /*if (!this.isExpired) {
            this.selectSpriteWithAge(this.spriteWithAge);
        }*/
    }

    /*public int getBrightnessForRender(float partialTick) {
        int i = super.getBrightnessForRender(partialTick);
        float f = (float)this.age / (float)this.lifetime;
        f = f * f;
        f = f * f;
        int j = i & 255;
        int k = i >> 16 & 255;
        k = k + (int)(f * 15.0F * 16.0F);
        if (k > 240) {
            k = 240;
        }

        return j | k << 16;
    }*/

    public float getScale(float scaleFactor) {
        float f = 0.6F + ((float)this.age + scaleFactor) / (float)this.lifetime;
        return this.quadSize * (1.0F + f * f * 0.5F);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ChumParticle soulparticle = new ChumParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
            soulparticle.setAlpha(0.8F);
            return soulparticle;
        }
    }
}