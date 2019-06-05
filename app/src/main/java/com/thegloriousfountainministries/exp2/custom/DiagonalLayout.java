package com.thegloriousfountainministries.exp2.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by My HP on 10/26/2018.
 */

public class DiagonalLayout extends LinearLayout {
    int realwidth;
    int realheight;
    public DiagonalLayout(Context context) {
        super(context);
    }

    public DiagonalLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DiagonalLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        realheight = getHeight();
        realwidth = getWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    @Override
    protected void dispatchDraw(Canvas canvas) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(0, height / 3 - height / 10);
        path.lineTo(width , height / 3 + height / 10);
        path.lineTo(width, 0);
        path.close();
        canvas.save();
        canvas.clipPath(path, Region.Op.INTERSECT);
        canvas.drawColor(ContextCompat.getColor(getContext(), android.R.color.white));
        canvas.restore();
        super.dispatchDraw(canvas);
    }
}
