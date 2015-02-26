package com.particledemo.test.particledemo;

import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.initializers.ParticleInitializer;

import java.util.Random;

/**
 * Randomize the X-axis point for the particle on creation
 * <br>
 * Created by Adam Greenberg on 2/26/15.
 * Copyright (c) 2014 Mark One Lifestyle, Inc. All rights reserved.
 */
public class XAxisInitializer implements ParticleInitializer {

    private final int mMinX;
    private final int mMaxX;

    public XAxisInitializer(final int minX, final int maxX) {
        mMinX = minX;
        mMaxX = maxX;
    }
    @Override
    public void initParticle(final Particle p, final Random r) {
        // Randomize for the range
        final int range = (mMaxX - mMinX);
        final int calcRange = r.nextInt(range) + mMinX;

        // Set the value
        p.setInitialX(calcRange);
    }
}
