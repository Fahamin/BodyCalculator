package com.workout.bodyfat.activity.alcohol;

import static com.workout.bodyfat.utils.Fun.showBanner;
import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.workout.bodyfat.R;
import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Alcohol_Chart extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    
    GlobalFunction globalFunction;
    ImageView iv_back;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_alcohol_effects;
    TextView tv_alcohol_effects_level;
    TextView tv_alcohol_effects_title;
    TextView tv_title;
    TypefaceManager typefaceManager;

    private FrameLayout adContainerView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.alcohol_chart);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        this.globalFunction = new GlobalFunction(this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);


        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.tv_alcohol_effects_title = (TextView) findViewById(R.id.tv_alcohol_effects_title);
        this.tv_alcohol_effects_level = (TextView) findViewById(R.id.tv_alcohol_effects_level);
        this.tv_alcohol_effects = (TextView) findViewById(R.id.tv_alcohol_effects);
        this.tv_title.setTypeface(this.typefaceManager.getBold());
        this.tv_alcohol_effects_title.setTypeface(this.typefaceManager.getBold());
        this.tv_alcohol_effects_level.setTypeface(this.typefaceManager.getBold());
        this.tv_alcohol_effects.setTypeface(this.typefaceManager.getBold());
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }

        this.iv_back.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Alcohol_Chart.this.onBackPressed();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
