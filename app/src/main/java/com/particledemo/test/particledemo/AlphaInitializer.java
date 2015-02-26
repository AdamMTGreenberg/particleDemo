package com.particledemo.test.particledemo;

import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.initializers.ParticleInitializer;

import java.util.Random;

/**
 * Changes the alpha scale at random for the particle
 * <br>
 * Created by Adam Greenberg on 2/25/15.
 * Copyright (c) 2014 Mark One Lifestyle, Inc. All rights reserved.
 */
public class AlphaInitializer implements ParticleInitializer {

    // Alpha values of the particle
    private final int mMinAplha;
    private final int mMaxAlpha;

    /**
     * Initializes and sets the alpha scale on the particle
     *
     * @param minAlpha 0 to 255 scale for minimum alpha transparency
     * @param maxAlpha 0 to 255 scale for the maximum alpha transparency scale
     */
    public AlphaInitializer(final int minAlpha, final int maxAlpha) {
        if (minAlpha < 0 || maxAlpha > 255) {
            throw new IllegalArgumentException("Arguments cannot be less than 0 or greater than 255");
        }

        mMinAplha = minAlpha;
        mMaxAlpha = maxAlpha;
    }

    @Override
    public void initParticle(final Particle particle, final Random random) {
        // Randomize for the range
        final int range = (mMaxAlpha - mMinAplha);
        final int alpha = random.nextInt(range) + mMinAplha;
    }
}
