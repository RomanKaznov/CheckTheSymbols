package com.example.checkthesymbols;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.checkthesymbols.controller.Validating;


public class NavBarAnim implements View.OnTouchListener {

    private LinearLayout navigation, navigationTitle;
    private Animation animationDown, animationUp;
    private boolean checkAnimState;

    public NavBarAnim(LinearLayout LinearLayout, LinearLayout linearLayout){
        navigation = LinearLayout;
        navigationTitle = linearLayout;
    }

    @SuppressLint("ClickableViewAccessibility")
    protected void setAnimationStart(Animation Down, Animation Up) {
        animationDown = Down;
        animationUp = Up;
        navigationTitle.setOnTouchListener(this);
        navigation.setOnTouchListener(this);

        animationDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //поля отвечают за проверку состояний navigation: up/down
                Validating.navigation_down = true;
                Validating.navigation_up = false;
                //
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) navigation.getLayoutParams();
                lp.height = navigation.getHeight()/ 3;
                navigation.setLayoutParams(lp);
                checkAnimState = true;
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        animationUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                Validating.navigation_down = false;
                Validating.navigation_up = true;
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) navigation.getLayoutParams();
                lp.height = navigation.getHeight() * 3;
                navigation.setLayoutParams(lp);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

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
                        //Log.i("u", "up");
                        navigation.startAnimation(animationDown);
                        return true;
                    }
                    // вверх
                    if (deltaY > 0 && !Validating.navigation_up) {
                        navigation.startAnimation(animationUp);
                        // Log.i("d", "down");
                        return true;
                    }
                }
                return true;
            }
        }

        return true;
    }

}

