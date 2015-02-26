package com.particledemo.test.particledemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


import com.particledemo.test.R;
import com.plattysoft.leonids.initializers.ParticleInitializer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    private static final int TIME_LIVED = 25000;

    @InjectView(R.id.button1)
    Button emitParticlesButton;

    @InjectView(R.id.background)
    FrameLayout mBackgroud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button1)
    public void onClick(final View view) {
        // Calc the area
        final int minX = mBackgroud.getLeft();
        final int maxX = mBackgroud.getRight();

        final AlphaInitializer alphaInitializer = new AlphaInitializer(5, 100);
        final XAxisInitializer xAxisInitializer = new XAxisInitializer(minX, maxX);

        final EnhancedParticleSystem particleSystemSpark
                = new EnhancedParticleSystem(this, 12, R.mipmap.spark, TIME_LIVED);
        final EnhancedParticleSystem particleSystemCircle
                = new EnhancedParticleSystem(this, 12, R.mipmap.circle_particle, TIME_LIVED);

        initParticleSystem(particleSystemSpark, alphaInitializer, xAxisInitializer, view);
        initParticleSystem(particleSystemCircle, alphaInitializer, xAxisInitializer, view);

//        new ParticleSystem(this, 80, R.mipmap.circle_particle, 10000)
//                .setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
//                .setRotationSpeed(144)
//                .setAcceleration(0.000017f, 90)
//                .emit(findViewById(R.id.emiter_top_right), 8);
//
//        new ParticleSystem(this, 80, R.mipmap.spark, 10000)
//                .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
////                .setRotationSpeed(144)
////                .setAcceleration(0.000017f, 90)
//                .emit(findViewById(R.id.emiter_top_left), 8);

//        new ParticleSystem(this, 1, R.drawable.spark, 3000)
//                .setAcceleration(0.00013f, 90)
//                .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
//                .setFadeOut(200, new AccelerateInterpolator())
//                .emit(findViewById(R.id.emiter_top_left), 8);
////                .emitWithGravity(findViewById(R.id.emiter_top_left), Gravity.BOTTOM, 30);
    }

    private void initParticleSystem(final EnhancedParticleSystem particleSystem, final AlphaInitializer alphaInitializer,
                                    final XAxisInitializer xAxisInitializer, final View view) {
        particleSystem.setAlphaInitializer(alphaInitializer)
                .setXAxisInitializer(xAxisInitializer)
                .setSpeedModuleAndAngleRange(0f, .05f, 270, 270)
                .setScaleRange(0.1f, 4.5f)
                .emit(view, 1);
    }


//
//    private AlphaModifier getAlphaModifier() {
//
//    }
//
//    private ScaleModifier getScaleModifier() {
//
//    }
//
//    private int getRandomAplhaModifierValue() {
//
//    }
//
//    private float getRandomScaleModifierValue() {
//
//    }
}
