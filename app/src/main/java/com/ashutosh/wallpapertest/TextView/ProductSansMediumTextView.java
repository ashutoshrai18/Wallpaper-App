package com.ashutosh.wallpapertest.TextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class ProductSansMediumTextView extends androidx.appcompat.widget.AppCompatTextView {

    public ProductSansMediumTextView(Context context) {
        super(context);
        initTypeFace(context);
    }

    public ProductSansMediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeFace(context);
    }

    public ProductSansMediumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeFace(context);
    }

    private void initTypeFace(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/sans_med.ttf");
        this.setTypeface(typeface);
    }
}
