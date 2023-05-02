package com.workout.bodyfat.activity.heart_rate;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import java.io.PrintStream;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Heart_Rate_Result extends Activity {
    String TAG = getClass().getSimpleName();

    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_ans_heartrate;
    TextView tv_heartrate_chart;
    TypefaceManager typefaceManager;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    private FrameLayout adContainerView;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_heartrate);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.tv_ans_heartrate = (TextView) findViewById(R.id.tv_ans_heartrate);
        this.tv_heartrate_chart = (TextView) findViewById(R.id.tv_heartrate_chart);
        this.tv_ans_heartrate.setTypeface(this.typefaceManager.getLight());
        this.tv_heartrate_chart.setTypeface(this.typefaceManager.getBold());
        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);


        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        TextView textView = this.tv_ans_heartrate;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.heart_rate));
        sb.append(String.valueOf(Heart_Rate_Calculator.HRmax));
        sb.append(" bpm");
        textView.setText(sb.toString());
        this.tv_heartrate_chart.setOnClickListener(new OnClickListener() {
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
                Heart_Rate_Result.this.startActivity(new Intent(Heart_Rate_Result.this, Heart_Rate_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Heart_Rate_Result.this.onBackPressed();
            }
        });
    }


}
