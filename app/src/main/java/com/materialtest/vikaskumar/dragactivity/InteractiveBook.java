package com.materialtest.vikaskumar.dragactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ViewAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;


public class InteractiveBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_book);


        final ViewAnimator pages =
                (ViewAnimator) findViewById(R.id.pages);
        Button prev = (Button) findViewById(R.id.prev);
        Button next = (Button) findViewById(R.id.next);
        prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pages.showPrevious();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pages.showNext();
            }
        });


        AnimationSet slideAndScale = new AnimationSet(true);

        TranslateAnimation slide = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,1f,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0
        );

        ScaleAnimation scale = new ScaleAnimation(
                10,
                1,
                10,
                1
        );

        slideAndScale.addAnimation(slide);
        slideAndScale.addAnimation(scale);
        slideAndScale.setDuration(1000);

        pages.setInAnimation(slideAndScale);


        View rollingBall = findViewById(R.id.rollingball);
        ObjectAnimator ballRoller =
                ObjectAnimator.ofFloat(
                        rollingBall,
                        "TranslationX",
                        0,
                        400
                );


        ballRoller.setDuration(2000);
        ballRoller.setRepeatMode(ObjectAnimator.REVERSE);
        ballRoller.setRepeatCount(ObjectAnimator.INFINITE);
        ballRoller.start();


        final View bouncingBall = findViewById(R.id.bouncingball);

        ValueAnimator ballBouncer = ValueAnimator.ofInt(0, 41);
        ballBouncer.setDuration(2000);
        ballBouncer.setRepeatMode(ValueAnimator.REVERSE);
        ballBouncer.setRepeatCount(ValueAnimator.INFINITE);
        ballBouncer.setInterpolator(new DecelerateInterpolator()); // change this

        ballBouncer.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator ballBouncer) {
                        final int animatedValue =
                                (Integer) ballBouncer.getAnimatedValue();
                        bouncingBall.post(new Runnable() {
                                              public void run() {
                                                  bouncingBall.setPadding(
                                                          bouncingBall.getPaddingLeft(),
                                                          40 - animatedValue,
                                                          bouncingBall.getPaddingRight(),
                                                          animatedValue);
                                                  bouncingBall.invalidate();
                                              }
                                          }
                        );
                    }
                }
        );


        ballBouncer.start();

    }


}
