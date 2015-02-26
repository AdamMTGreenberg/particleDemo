package com.particledemo.test.particledemo;

import android.app.Activity;

import com.plattysoft.leonids.ParticleSystem;

/**
 * Extension of the {@link com.plattysoft.leonids.ParticleSystem} that allows us to add an initializer
 * to randomize alpha values
 * <br>
 * Created by Adam Greenberg on 2/26/15.
 * Copyright (c) 2014 Mark One Lifestyle, Inc. All rights reserved.
 */
public class AlphaParticleSystem extends ParticleSystem {

    public AlphaParticleSystem(final Activity a, final int maxParticles, final int drawableRedId,
                               final long timeToLive) {
        super(a, maxParticles, drawableRedId, timeToLive);
    }

    /**
     * Set the {@link AlphaInitializer} on this instance of the
     * {@link com.plattysoft.leonids.ParticleSystem}
     *
     * @param alphaInitializer client defined
     */
    public void setAlphaInitializer(final AlphaInitializer alphaInitializer) {

    }
}
