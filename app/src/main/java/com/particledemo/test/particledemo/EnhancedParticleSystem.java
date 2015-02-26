package com.particledemo.test.particledemo;

import android.app.Activity;

import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.ParticleSystem;

/**
 * Extension of the {@link com.plattysoft.leonids.ParticleSystem} that allows us to add an initializer
 * to randomize alpha values
 * <br>
 * Created by Adam Greenberg on 2/26/15.
 * Copyright (c) 2014 Mark One Lifestyle, Inc. All rights reserved.
 */
public class EnhancedParticleSystem extends ParticleSystem {

    public EnhancedParticleSystem(final Activity a, final int maxParticles, final int drawableRedId,
                                  final long timeToLive) {
        super(a, maxParticles, drawableRedId, timeToLive);
    }

    /**
     * Set the {@link AlphaInitializer} on this instance of the
     * {@link com.plattysoft.leonids.ParticleSystem}
     *
     * @param alphaInitializer client defined instance to randomize Alpha values
     * @return instance of self
     */
    public EnhancedParticleSystem setAlphaInitializer(final AlphaInitializer alphaInitializer) {
        // Set the initializer
        mInitializers.add(alphaInitializer);
        return this;
    }

    /**
     * Set the {@link XAxisInitializer} on this instance of the
     * {@link com.plattysoft.leonids.ParticleSystem}
     *
     * @param xAxisInitializer client defined instance to randomize X-Axis values
     * @return instance of self
     */
    public EnhancedParticleSystem setXAxisInitializer(final XAxisInitializer xAxisInitializer) {
        // Set the initializer
        mInitializers.add(xAxisInitializer);
        return this;
    }

    @Override
    protected void activateParticle(long delay) {
        Particle p = mParticles.remove(0);
        p.init();
        // Initialization goes before configuration, scale is required before can be configured properly
        for (int i = 0; i < mInitializers.size(); i++) {
            mInitializers.get(i).initParticle(p, mRandom);
        }

        final int particleX = getParticleX(p);
        final int particleY = getParticleY(p);

        p.configure(mTimeToLive, particleX, particleY);
        p.activate(delay, mModifiers);

        mActiveParticles.add(p);
        mActivatedParticles++;
    }

    private int getParticleX(final Particle p) {
        if(!p.isInitialXFlagSet()) {
            return getFromRange(mEmiterXMin, mEmiterXMax);
        } else {
            return p.getInitialX();
        }
    }

    private int getParticleY(final Particle p) {
        if(!p.isInitialYFlagSet()) {
            return getFromRange(mEmiterYMin, mEmiterYMax);
        } else {
            return p.getInitialY();
        }
    }
}
