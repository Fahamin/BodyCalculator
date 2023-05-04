package com.workout.bodyfat.activity.body_adiposity_index;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import java.io.PrintStream;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Body_Adiposity_Index_Result extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    
    String bai;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    RelativeLayout rl_main;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_ans_bmr;
    TextView tv_ans_healthrisk;
    TextView tv_whr_chart;
    TypefaceManager typefaceManager;

    private FrameLayout adContainerView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_whr);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);

        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);

        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.extras = getIntent().getExtras();
        this.bai = this.extras.getString("bai");
        this.rl_main = (RelativeLayout) findViewById(R.id.rl_main);
        this.tv_ans_bmr = (TextView) findViewById(R.id.tv_ans_bmr);
        this.tv_ans_healthrisk = (TextView) findViewById(R.id.tv_ans_healthrisk);
        this.tv_whr_chart = (TextView) findViewById(R.id.tv_whr_chart);
        this.tv_ans_healthrisk.setVisibility(8);
        this.tv_ans_bmr.setTypeface(this.typefaceManager.getLight());
        this.tv_ans_healthrisk.setTypeface(this.typefaceManager.getLight());
        this.tv_whr_chart.setTypeface(this.typefaceManager.getBold());
//        this.rl_main.setBackgroundResource(R.drawable.popup_background_gradient7);
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        TextView textView = this.tv_ans_bmr;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.bai_is));
        sb.append(" ");
        sb.append(this.bai);
        sb.append("%");
        textView.setText(sb.toString());
        this.tv_whr_chart.setOnClickListener(new OnClickListener() {
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
                Body_Adiposity_Index_Result.this.startActivity(new Intent(Body_Adiposity_Index_Result.this, Body_Adiposity_Index_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Body_Adiposity_Index_Result.this.onBackPressed();
            }
        });
    }



}
