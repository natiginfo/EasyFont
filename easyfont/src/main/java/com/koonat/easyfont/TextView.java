package com.koonat.easyfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class TextView extends AppCompatTextView {

    private String mFontPath;

    public TextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextView);

            Drawable drawableLeft = null;
            Drawable drawableRight = null;
            Drawable drawableBottom = null;
            Drawable drawableTop = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawableLeft = typedArray.getDrawable(R.styleable.TextView_drawableLeftCompat);
                drawableRight = typedArray.getDrawable(R.styleable.TextView_drawableRightCompat);
                drawableBottom = typedArray.getDrawable(R.styleable.TextView_drawableBottomCompat);
                drawableTop = typedArray.getDrawable(R.styleable.TextView_drawableTopCompat);
            } else {
                final int drawableLeftId = typedArray.getResourceId(R.styleable.TextView_drawableLeftCompat, -1);
                final int drawableRightId = typedArray.getResourceId(R.styleable.TextView_drawableRightCompat, -1);
                final int drawableBottomId = typedArray.getResourceId(R.styleable.TextView_drawableBottomCompat, -1);
                final int drawableTopId = typedArray.getResourceId(R.styleable.TextView_drawableTopCompat, -1);

                if (drawableLeftId != -1)
                    drawableLeft = AppCompatResources.getDrawable(context, drawableLeftId);
                if (drawableRightId != -1)
                    drawableRight = AppCompatResources.getDrawable(context, drawableRightId);
                if (drawableBottomId != -1)
                    drawableBottom = AppCompatResources.getDrawable(context, drawableBottomId);
                if (drawableTopId != -1)
                    drawableTop = AppCompatResources.getDrawable(context, drawableTopId);
            }

            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);

            mFontPath = typedArray.getString(R.styleable.TextView_font_path);
            if (mFontPath != null && !mFontPath.isEmpty()) {
                Typeface typeface = TypefaceManager.getInstance().getTypeface(context, mFontPath);
                setTypeface(typeface);
            }
            typedArray.recycle();

            setTransformationMethod(null);
        }
    }
}
