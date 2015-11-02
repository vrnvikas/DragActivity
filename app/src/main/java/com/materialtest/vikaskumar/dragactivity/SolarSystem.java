package com.materialtest.vikaskumar.dragactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;



public class SolarSystem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_system);

        ImageView orrery = (ImageView) findViewById(R.id.orrery);
        OrreryDrawable myOrreryDrawable = OrreryDrawable.Create();
        orrery.setImageDrawable(myOrreryDrawable);

        PropertyValuesHolder earthPositionValues = PropertyValuesHolder.ofFloat("EarthPosition", 0, (float)(2*Math.PI));
        PropertyValuesHolder moonPositionValues = PropertyValuesHolder.ofFloat("MoonPosition", 0, (float) (2 * Math.PI * 13));

        ObjectAnimator orreryAnimator = ObjectAnimator.ofPropertyValuesHolder(myOrreryDrawable, earthPositionValues, moonPositionValues);

        ValueAnimator.setFrameDelay(100);
        orreryAnimator.setDuration(60000);
        orreryAnimator.setInterpolator(new LinearInterpolator());
        orreryAnimator.setRepeatCount(ValueAnimator.INFINITE);
        orreryAnimator.setRepeatMode(ValueAnimator.RESTART);
        orreryAnimator.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_solar_system, menu);
        return true;
    }

}
