package com.koonat.easyfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class EditText extends android.widget.EditText {

    private String mFontPath;

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditText);
        int count = typedArray.getIndexCount();

        try {
            for (int i = 0; i < count; ++i) {
                int attr = typedArray.getIndex(i);
                if (attr == R.styleable.EditText_font_path)
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
