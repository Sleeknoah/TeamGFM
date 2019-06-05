package com.thegloriousfountainministries.exp2.mylib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by My HP on 9/12/2017.
 */

public class ThreeTwo extends android.support.v7.widget.AppCompatImageView {

    public ThreeTwo(Context context) {
        super(context);
    }

    public ThreeTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ThreeTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int twoHeight = MeasureSpec.getSize(widthMeasureSpec) * 2/3;
        int twoHeightSpec = MeasureSpec.makeMeasureSpec(twoHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, twoHeightSpec);
    }
}
