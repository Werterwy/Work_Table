package com.example.worktable;

import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SwipeMain2 extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private Context context;

    public SwipeMain2(Context context) {
        this.context = context;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();

        if (Math.abs(diffX) > Math.abs(diffY) &&
                Math.abs(diffX) > SWIPE_THRESHOLD &&
                Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffX > 0) {
                // Свайп вправо
                // Осуществите переход на предыдущую активность
                context.startActivity(new Intent(context, MainActivity.class));
                return true;
            } else {
                // Свайп влево
                // Осуществите переход на следующую активность
                context.startActivity(new Intent(context, MainActivity3.class));
                return true;
            }
        }
        return false;
    }
}
