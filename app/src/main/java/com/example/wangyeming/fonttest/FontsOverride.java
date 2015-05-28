package com.example.wangyeming.fonttest;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 替换系统字体
 * Created by wangyeming on 15-5-27.
 */
public final class FontsOverride {

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }


    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Map<String, Typeface> newMap = new HashMap<>();
            newMap.put("monospace", newTypeface);
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField(staticTypefaceFieldName);
                staticField.setAccessible(true);
                staticField.set(null, newTypeface);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
