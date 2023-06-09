package com.workout.bodyfat.activity.waist_hip_ratio;

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

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Waist_Hip_Ratio_Chart extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    
    GlobalFunction globalFunction;
    ImageView iv_back;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_avg_fat;
    TextView tv_avg_fat_man;
    TextView tv_avg_fat_woman;
    TextView tv_bodyfat;
    TextView tv_fat_level;
    TextView tv_low_fat;
    TextView tv_low_fat_man;
    TextView tv_low_fat_woman;
    TextView tv_men_fatlevel;
    TextView tv_title;
    TextView tv_vlow_fat;
    TextView tv_vlow_fat_man;
    TextView tv_vlow_fat_woman;
    TextView tv_women_fatlevel;
    TypefaceManager typefaceManager;


     @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.waist_hip_ratio_chart);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);

        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.tv_bodyfat = (TextView) findViewById(R.id.tv_bodyfat);
        this.tv_fat_level = (TextView) findViewById(R.id.tv_fat_level);
        this.tv_men_fatlevel = (TextView) findViewById(R.id.tv_men_fatlevel);
        this.tv_women_fatlevel = (TextView) findViewById(R.id.tv_women_fatlevel);
        this.tv_vlow_fat = (TextView) findViewById(R.id.tv_vlow_fat);
        this.tv_low_fat = (TextView) findViewById(R.id.tv_low_fat);
        this.tv_avg_fat = (TextView) findViewById(R.id.tv_avg_fat);
        this.tv_vlow_fat_man = (TextView) findViewById(R.id.tv_vlow_fat_man);
        this.tv_vlow_fat_woman = (TextView) findViewById(R.id.tv_vlow_fat_woman);
        this.tv_low_fat_man = (TextView) findViewById(R.id.tv_low_fat_man);
        this.tv_low_fat_woman = (TextView) findViewById(R.id.tv_low_fat_woman);
        this.tv_avg_fat_man = (TextView) findViewById(R.id.tv_avg_fat_man);
        this.tv_avg_fat_woman = (TextView) findViewById(R.id.tv_avg_fat_woman);
        this.tv_title.setTypeface(this.typefaceManager.getBold());
        this.tv_bodyfat.setTypeface(this.typefaceManager.getBold());
        this.tv_fat_level.setTypeface(this.typefaceManager.getBold());
        this.tv_men_fatlevel.setTypeface(this.typefaceManager.getBold());
        this.tv_women_fatlevel.setTypeface(this.typefaceManager.getBold());
        this.tv_vlow_fat.setTypeface(this.typefaceManager.getBold());
        this.tv_low_fat.setTypeface(this.typefaceManager.getBold());
        this.tv_avg_fat.setTypeface(this.typefaceManager.getBold());
        this.tv_vlow_fat_man.setTypeface(this.typefaceManager.getLight());
        this.tv_vlow_fat_woman.setTypeface(this.typefaceManager.getLight());
        this.tv_low_fat_man.setTypeface(this.typefaceManager.getLight());
        this.tv_low_fat_woman.setTypeface(this.typefaceManager.getLight());
        this.tv_avg_fat_man.setTypeface(this.typefaceManager.getLight());
        this.tv_avg_fat_woman.setTypeface(this.typefaceManager.getLight());
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }

        this.iv_back.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Waist_Hip_Ratio_Chart.this.onBackPressed();
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


    public void onResume() {
        super.onResume();
        if (!this.sharedPreferenceManager.get_Remove_Ad().booleanValue()) {
        } else {
            
        }
    }
}
