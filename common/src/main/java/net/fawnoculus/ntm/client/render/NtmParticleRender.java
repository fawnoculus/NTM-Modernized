package net.fawnoculus.ntm.client.render;

import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import net.fawnoculus.ntm.misc.NtmParticles;
import net.minecraft.client.particle.EndRodParticle;

public class NtmParticleRender {
    public static void init() {
        ParticleProviderRegistry.register(NtmParticles.TEST, EndRodParticle.Provider::new);
    }
}
