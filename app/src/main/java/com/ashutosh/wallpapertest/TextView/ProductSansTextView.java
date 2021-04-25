package com.ashutosh.wallpapertest.TextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class ProductSansTextView extends androidx.appcompat.widget.AppCompatTextView {

    public ProductSansTextView(Context context) {
        super(context);
        initTypeFace(context);
    }

    public ProductSansTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeFace(context);
    }

    public ProductSansTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeFace(context);
    }

    private void initTypeFace(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/sans_reg.ttf");
        this.setTypeface(typeface);
    }
}
