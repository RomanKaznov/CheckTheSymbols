package com.example.checkthesymbols.controller;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;


public class OnSwipeListener implements View.OnTouchListener {

    private LinearLayout navigation, navigationTitle;
    private Animation animationDown, animationUp;


    public void setSwipeListener(LinearLayout LinearLayout, LinearLayout linearLayout, Animation Down, Animation Up) {
        animationDown = Down;
        animationUp = Up;
        navigation = LinearLayout;
        navigationTitle = linearLayout;

        navigationTitle.setOnTouchListener(this);
        navigation.setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {


        float downY = 0;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {

                downY = event.getY();

                return true;

            }

            case MotionEvent.ACTION_UP: {

                float upY = event.getY();

                float deltaY = downY - upY;


                // вертикальный свайп
                if (Math.abs(deltaY) > 10) { //дистанция не меньше минимальной

                    // вниз
                    if (deltaY < 0 && !Validating.navigation_down) {
                        Log.i("m", "up");
                        navigation.startAnimation(animationDown);

                        return true;

                    }

                    // вверх
                    if (deltaY > 0 && !Validating.navigation_up) {
                        navigation.startAnimation(animationUp);
                        Log.i("m", "down");

                        return true;

                    }

                }


                return true;

            }

        }


        return true;
    }

}




