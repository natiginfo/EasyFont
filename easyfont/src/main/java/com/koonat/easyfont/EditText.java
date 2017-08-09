package com.koonat.easyfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class EditText extends AppCompatEditText {

    private String mFontPath;

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditText);

            Drawable drawableLeft = null;
            Drawable drawableRight = null;
            Drawable drawableBottom = null;
            Drawable drawableTop = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawableLeft = typedArray.getDrawable(R.styleable.EditText_drawableLeftCompat);
                drawableRight = typedArray.getDrawable(R.styleable.EditText_drawableRightCompat);
                drawableBottom = typedArray.getDrawable(R.styleable.EditText_drawableBottomCompat);
                drawableTop = typedArray.getDrawable(R.styleable.EditText_drawableTopCompat);
            } else {
                final int drawableLeftId = typedArray.getResourceId(R.styleable.EditText_drawableLeftCompat, -1);
                final int drawableRightId = typedArray.getResourceId(R.styleable.EditText_drawableRightCompat, -1);
                final int drawableBottomId = typedArray.getResourceId(R.styleable.EditText_drawableBottomCompat, -1);
                final int drawableTopId = typedArray.getResourceId(R.styleable.EditText_drawableTopCompat, -1);

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

            mFontPath = typedArray.getString(R.styleable.EditText_font_path);
            if (mFontPath != null && !mFontPath.isEmpty()) {
                Typeface typeface = Typeface.createFromAsset(context.getAssets(), mFontPath);
                setTypeface(typeface);
            }
            typedArray.recycle();

            setTransformationMethod(null);
        }
    }
}
