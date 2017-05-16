package com.koonat.easyfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class Button extends android.widget.Button {

    private String mFontPath;

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Button);
        int count = typedArray.getIndexCount();

        try {
            for (int i = 0; i < count; ++i) {
                int attr = typedArray.getIndex(i);
                if (attr == R.styleable.Button_font_path)
                    mFontPath = typedArray.getString(attr);
            }
            if (mFontPath != null && !mFontPath.isEmpty()) {
                Typeface typeface = Typeface.createFromAsset(context.getAssets(), mFontPath);
                setTypeface(typeface);
            }
        } finally {
            typedArray.recycle();
        }
    }
}