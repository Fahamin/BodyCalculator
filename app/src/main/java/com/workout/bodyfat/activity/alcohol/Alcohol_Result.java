package com.workout.bodyfat.activity.alcohol;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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


public class Alcohol_Result extends AppCompatActivity {
    Double BACinPer;
    String TAG = getClass().getSimpleName();
    
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_alcohol_result;
    TextView tv_alcohol_result_chart;
    TypefaceManager typefaceManager;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_alcohol);
        this.globalFunction = new GlobalFunction(this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.tv_alcohol_result = (TextView) findViewById(R.id.tv_alcohol_result);
        this.tv_alcohol_result_chart = (TextView) findViewById(R.id.tv_alcohol_result_chart);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);

        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);



        this.tv_alcohol_result.setTypeface(this.typefaceManager.getLight());
        this.tv_alcohol_result_chart.setTypeface(this.typefaceManager.getBold());
        this.extras = getIntent().getExtras();
        this.BACinPer = Double.valueOf(this.extras.getDouble("BACinPer"));
        if (this.BACinPer.doubleValue() < 0.0d) {
            TextView textView = this.tv_alcohol_result;
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.BAC_level));
            sb.append(" : 0");
            textView.setText(sb.toString());
        } else {
            TextView textView2 = this.tv_alcohol_result;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getString(R.string.BAC_level));
            sb2.append(" : %.2f");
            textView2.setText(String.format(sb2.toString(), new Object[]{this.BACinPer}));
        }
        this.tv_alcohol_result_chart.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int random = ((int) (Math.random() * 2.0d)) + 1;
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("random_number==>");
                sb.append(random);
                printStream.println(sb.toString());
                if (random == 2) {
                    Fun.addShow();
                    return;
                }
                Alcohol_Result.this.startActivity(new Intent(Alcohol_Result.this, Alcohol_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Alcohol_Result.this.onBackPressed();
            }
        });
    }

}
