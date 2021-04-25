package com.ashutosh.wallpapertest.TextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class MontserratSemiBoldTextView extends androidx.appcompat.widget.AppCompatTextView {

    public MontserratSemiBoldTextView(Context context) {
        super(context);
        initTypeFace(context);
    }

    public MontserratSemiBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeFace(context);
    }

    public MontserratSemiBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeFace(context);
    }

    private void initTypeFace(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/mont_sem.otf");
        this.setTypeface(typeface);
    }
}
