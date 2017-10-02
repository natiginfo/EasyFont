package com.koonat.easyfont;

import android.content.Context;
import android.graphics.Typeface;

import java.util.concurrent.ConcurrentHashMap;

class TypefaceManager {

    private static final String TAG = "TypefaceManager";
    private static volatile TypefaceManager instance;

    private static ConcurrentHashMap<String, Typeface> fonts;

    private TypefaceManager() {
        fonts = new ConcurrentHashMap<>();
    }

    static TypefaceManager getInstance() {
        if (instance == null) {
            synchronized (TypefaceManager.class) {
                if (instance == null) {
                    instance = new TypefaceManager();
                }
            }
        }
        return instance;
    }

    Typeface getTypeface(Context context, String fontPath) {
        if (!fonts.containsKey(fontPath)) {
            fonts.put(fontPath, Typeface.createFromAsset(context.getAssets(), fontPath));
        }

        return fonts.get(fontPath);
    }
}