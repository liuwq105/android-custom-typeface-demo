package com.example.wangyeming.fonttest;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Created by wangyeming on 15-5-28.
 */
public class FontHelper {

    public static final void setAppFont(ViewGroup mContainer, Typeface mFont, boolean reflect) {
        if (mContainer == null || mFont == null) return;
        for (int i = 0; i < mContainer.getChildCount(); ++i) {
            final View mChild = mContainer.getChildAt(i);
            if (mChild instanceof TextView) {
                ((TextView) mChild).setTypeface(mFont);
            } else if (mChild instanceof EditText) {
                ((EditText) mChild).setTypeface(mFont);
            } else if (mChild instanceof ViewGroup) {
                setAppFont((ViewGroup) mChild, mFont, true);
            } else if (reflect) {
                try {
                    Method mSetTypeface = mChild.getClass().getMethod("setTypeface", Typeface.class);
                    mSetTypeface.invoke(mChild, mFont);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}