package com.workout.bodyfat.activity.calories_intake;


import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.workout.bodyfat.R;
import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import java.io.PrintStream;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Daily_Calories_Intake_Result extends AppCompatActivity {
    int BMR;
    String TAG = getClass().getSimpleName();
    String activity_level;
    
    int cal_togain;
    int cal_tolose;
    Double cal_tomaintain;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_bmr_result;
    TextView tv_burn_cal;
    TextView tv_calories_chart;
    TypefaceManager typefaceManager;


     @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_calories_intake);


        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.tv_bmr_result = (TextView) findViewById(R.id.tv_bmr_result);
        this.tv_burn_cal = (TextView) findViewById(R.id.tv_burn_cal);
        this.tv_calories_chart = (TextView) findViewById(R.id.tv_calories_chart);
        this.tv_bmr_result.setTypeface(this.typefaceManager.getLight());
        this.tv_burn_cal.setTypeface(this.typefaceManager.getLight());
        this.tv_calories_chart.setTypeface(this.typefaceManager.getBold());
        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);

        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        this.extras = getIntent().getExtras();
        this.BMR = this.extras.getInt("BMR");
        this.activity_level = this.extras.getString("ActivityLevel").trim();
        StringBuilder sb = new StringBuilder();
        sb.append("BMIII");
        sb.append(this.BMR);
        Log.d("BMIII", sb.toString());
        TextView textView = this.tv_bmr_result;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R.string.Your_BMR_value_is));
        sb2.append(" \n");
        sb2.append(String.valueOf(this.BMR));
        sb2.append(" ");
        sb2.append(getString(R.string.calories));
        textView.setText(sb2.toString());
        TextView textView2 = this.tv_burn_cal;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getString(R.string.This_means_that_your_body_will_burn));
        sb3.append(" ");
        sb3.append(String.valueOf(this.BMR));
        sb3.append(" ");
        sb3.append(getString(R.string.calories_each_day_if_you_engage_in_no_activity_for_the_entire_day));
        textView2.setText(sb3.toString());
        this.tv_calories_chart.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int random = ((int) (Math.random() * 2.0d)) + 1;
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("random_number==>");
                sb.append(random);
                printStream.println(sb.toString());
                if (random == 2) {
                    Fun.addShow();
                } else {
                    Daily_Calories_Intake_Result.this.calculate();
                }
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Daily_Calories_Intake_Result.this.onBackPressed();
            }
        });
    }

    public void calculate() {
        if (this.activity_level.equals(getString(R.string.sedentary))) {
            double d = (double) this.BMR;
            Double.isNaN(d);
            this.cal_tomaintain = Double.valueOf(d * 1.2d);
        } else if (this.activity_level.equals(getString(R.string.lightly_active))) {
            double d2 = (double) this.BMR;
            Double.isNaN(d2);
            this.cal_tomaintain = Double.valueOf(d2 * 1.375d);
        } else if (this.activity_level.equals(getString(R.string.moderately_active))) {
            double d3 = (double) this.BMR;
            Double.isNaN(d3);
            this.cal_tomaintain = Double.valueOf(d3 * 1.55d);
        } else if (this.activity_level.equals(getString(R.string.very_active))) {
            double d4 = (double) this.BMR;
            Double.isNaN(d4);
            this.cal_tomaintain = Double.valueOf(d4 * 1.725d);
        } else if (this.activity_level.equals(getString(R.string.super_active))) {
            double d5 = (double) this.BMR;
            Double.isNaN(d5);
            this.cal_tomaintain = Double.valueOf(d5 * 1.9d);
        }
        this.cal_togain = this.BMR + 500;
        this.cal_tolose = this.BMR - 500;
        Intent intent = new Intent(this, Daily_Calories_Intake_Chart.class);
        intent.putExtra("maintain", this.cal_tomaintain.intValue());
        intent.putExtra("gain", this.cal_togain);
        intent.putExtra("lose", this.cal_tolose);
        startActivity(intent);
    }

}
