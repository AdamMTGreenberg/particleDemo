package com.plattysoft.leonids;

import java.util.List;

import com.plattysoft.leonids.modifiers.ParticleModifier;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Particle {

    protected Bitmap mImage;

    public float mCurrentX;
    public float mCurrentY;

    public float mScale = 1f;
    public int mAlpha = 255;

    public float mInitialRotation = 0f;

    public float mRotationSpeed = 0f;

    public float mSpeedX = 0f;
    public float mSpeedY = 0f;

    public float mAccelerationX;
    public float mAccelerationY;

    protected Matrix mMatrix;
    protected Paint mPaint;

    protected float mInitialX;
    protected float mInitialY;

    protected float mRotation;

    protected long mTimeToLive;

    protected long mStartingMilisecond;

    protected int mBitmapHalfWidth;
    protected int mBitmapHalfHeight;

    protected List<ParticleModifier> mModifiers;

    private boolean mInitialXFlagSet = false;
    private boolean mInitialYFlagSet = false;

    protected Particle() {
        mMatrix = new Matrix();
        mPaint = new Paint();
    }

    public Particle(Bitmap bitmap) {
        this();
        mImage = bitmap;
    }

    public void init() {
        mScale = 1;
        mAlpha = 255;
    }

    public void configure(long timeToLive, float emiterX, float emiterY) {
        mBitmapHalfWidth = mImage.getWidth() / 2;
        mBitmapHalfHeight = mImage.getHeight() / 2;

        mInitialX = emiterX - mBitmapHalfWidth;
        mInitialY = emiterY - mBitmapHalfHeight;
        mCurrentX = mInitialX;
        mCurrentY = mInitialY;

        mTimeToLive = timeToLive;
    }

    public boolean update(long miliseconds) {
        long realMiliseconds = miliseconds - mStartingMilisecond;
        if (realMiliseconds > mTimeToLive) {
            return false;
        }
        mCurrentX = mInitialX + mSpeedX * realMiliseconds + mAccelerationX * realMiliseconds * realMiliseconds;
        mCurrentY = mInitialY + mSpeedY * realMiliseconds + mAccelerationY * realMiliseconds * realMiliseconds;
        mRotation = mInitialRotation + mRotationSpeed * realMiliseconds / 1000;
        for (int i = 0; i < mModifiers.size(); i++) {
            mModifiers.get(i).apply(this, realMiliseconds);
        }
        return true;
    }

    public void draw(Canvas c) {
        mMatrix.reset();
        mMatrix.postRotate(mRotation, mBitmapHalfWidth, mBitmapHalfHeight);
        mMatrix.postScale(mScale, mScale, mBitmapHalfWidth, mBitmapHalfHeight);
        mMatrix.postTranslate(mCurrentX, mCurrentY);
        mPaint.setAlpha(mAlpha);
        c.drawBitmap(mImage, mMatrix, mPaint);
    }

    public Particle activate(long startingMilisecond, List<ParticleModifier> modifiers) {
        mStartingMilisecond = startingMilisecond;
        // We do store a reference to the list, there is no need to copy, since the modifiers do not carte about states
        mModifiers = modifiers;
        return this;
    }

    /**
     * Set the initial x-axis value
     *
     * @param x integer defining the x-axis value
     */
    public void setInitialX(final int x) {
        mInitialXFlagSet = true;
        mInitialX = x;
    }

    /**
     * Set the initial y-axis value
     *
     * @param y integer defining the y-axis value
     */
    public void setInitialY(final int y) {
        mInitialYFlagSet = true;
        mInitialY = y;
    }

    public boolean isInitialXFlagSet() {
        return mInitialXFlagSet;
    }

    public boolean isInitialYFlagSet() {
        return mInitialYFlagSet;
    }

    public int getInitialX() {
        return (int) mInitialX;
    }

    public int getInitialY() {
        return (int) mInitialY;
    }
}
