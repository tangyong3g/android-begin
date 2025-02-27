/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ty.animation;


import com.ty.tools.path.Matrix4;

/**
 * Defines the transformation to be applied at
 * one point in time of an Animation.
 *
 */
//CHECKSTYLE:OFF
public class Transformation {
    /**
     * Indicates a transformation that has no effect (alpha = 1 and identity matrix.)
     */
    public static int TYPE_IDENTITY = 0x0;
    /**
     * Indicates a transformation that applies an alpha only (uses an identity matrix.)
     */
    public static int TYPE_ALPHA = 0x1;
    /**
     * Indicates a transformation that applies a matrix only (alpha = 1.)
     */
    public static int TYPE_MATRIX = 0x2;
    /**
     * Indicates a transformation that applies an alpha and a matrix.
     */
    public static int TYPE_BOTH = TYPE_ALPHA | TYPE_MATRIX;

    protected Matrix4 mMatrix;
    protected float mAlpha;
    protected int mTransformationType;
    private static final Matrix4 sTemp = new Matrix4();

    /**
     * Creates a new transformation with alpha = 1 and the identity matrix.
     */
    public Transformation() {
        clear();
    }

    /**
     * Reset the transformation to a state that leaves the object
     * being animated in an unmodified state. The transformation type is
     * {@link #TYPE_BOTH} by default.
     */
    public void clear() {
        if (mMatrix == null) {
            mMatrix = new Matrix4();
        } else {
            mMatrix.idt();
        }
        mAlpha = 1.0f;
        mTransformationType = TYPE_BOTH;
    }

    /**
     * Indicates the nature of this transformation.
     *
     * @return {@link #TYPE_ALPHA}, {@link #TYPE_MATRIX},
     *         {@link #TYPE_BOTH} or {@link #TYPE_IDENTITY}.
     */
    public int getTransformationType() {
        return mTransformationType;
    }

    /**
     * Sets the transformation type.
     *
     * @param transformationType One of {@link #TYPE_ALPHA},
     *        {@link #TYPE_MATRIX}, {@link #TYPE_BOTH} or
     *        {@link #TYPE_IDENTITY}.
     */
    public void setTransformationType(int transformationType) {
        mTransformationType = transformationType;
    }

    /**
     * Clones the specified transformation.
     *
     * @param t The transformation to clone.
     */
    public void set(Transformation t) {
        mAlpha = t.getAlpha();
        mMatrix.set(t.getMatrix());
        mTransformationType = t.getTransformationType();
    }
    
    /**
     * Apply this Transformation to an existing Transformation, e.g. apply
     * a scale effect to something that has already been rotated.
     * @param t
     */
    public void compose(Transformation t) {
        mAlpha *= t.getAlpha();
//        mMatrix.preConcat(t.getMatrix());
        mMatrix.mul(t.getMatrix());
    }
    
    /**
     * Like {@link #compose(Transformation)} but does this.postConcat(t) of
     * the transformation matrix.
     * @hide
     */
    public void postCompose(Transformation t) {
        mAlpha *= t.getAlpha();
//        mMatrix.postConcat(t.getMatrix());
        sTemp.set(t.getMatrix());
        mMatrix.set(sTemp.mul(mMatrix));
    }

    /**
     * @return The 3x3 Matrix representing the trnasformation to apply to the
     * coordinates of the object being animated
     */
    public Matrix4 getMatrix() {
        return mMatrix;
    }
    
    /**
     * Sets the degree of transparency
     * @param alpha 1.0 means fully opaqe and 0.0 means fully transparent
     */
    public void setAlpha(float alpha) {
        mAlpha = alpha;
    }

    /**
     * @return The degree of transparency
     */
    public float getAlpha() {
        return mAlpha;
    }
}
