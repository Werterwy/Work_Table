package com.example.worktable;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class DoubleTapListener implements GestureDetector.OnDoubleTapListener {
    private Context context;

    public DoubleTapListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        // Обработка двойного нажатия
        // Ваш код здесь
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }
}
