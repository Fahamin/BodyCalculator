package com.workout.bodyfat.activity.waist_hip_ratio;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import java.io.PrintStream;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Waist_Hip_Ratio_Result extends Activity {
    String TAG = getClass().getSimpleName();

    Bundle extras;
    GlobalFunction globalFunction;
    String health_risk;
    ImageView iv_close;
    ImageView iv_imoji;
    RelativeLayout rl_main;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_ans_bmr;
    TextView tv_ans_healthrisk;
    TextView tv_whr_chart;
    TypefaceManager typefaceManager;
    Double whr;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_whr);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);


        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);

        this.rl_main = (RelativeLayout) findViewById(R.id.rl_main);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.iv_imoji = (ImageView) findViewById(R.id.iv_imoji);
//        this.rl_main.setBackgroundResource(R.drawable.popup_background_gradient1);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.extras = getIntent().getExtras();
        this.whr = Double.valueOf(this.extras.getString("whr"));
        this.health_risk = this.extras.getString("health_risk");
        this.tv_ans_bmr = (TextView) findViewById(R.id.tv_ans_bmr);
        this.tv_ans_healthrisk = (TextView) findViewById(R.id.tv_ans_healthrisk);
        this.tv_whr_chart = (TextView) findViewById(R.id.tv_whr_chart);
        this.tv_ans_bmr.setTypeface(this.typefaceManager.getLight());
        this.tv_ans_healthrisk.setTypeface(this.typefaceManager.getLight());
        this.tv_whr_chart.setTypeface(this.typefaceManager.getBold());
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.whr);
        Log.d("whr->", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        sb2.append(this.health_risk);
        Log.d("tv_ans_healthrisk->", sb2.toString());
        TextView textView = this.tv_ans_bmr;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getString(R.string.whr_is));
        sb3.append(" ");
        sb3.append(this.whr);
        textView.setText(sb3.toString());
        TextView textView2 = this.tv_ans_healthrisk;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(getString(R.string.health_risk));
        sb4.append(" ");
        sb4.append(this.health_risk);
        textView2.setText(sb4.toString());
        if (this.whr.doubleValue() <= 0.95d) {
            this.iv_imoji.setImageResource(R.drawable.imogi_sad);
        } else if (this.whr.doubleValue() <= 0.95d || this.whr.doubleValue() >= 1.0d) {
            this.iv_imoji.setImageResource(R.drawable.imogi_unhappy);
        } else {
            this.iv_imoji.setImageResource(R.drawable.imoji_smile);
        }
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
                Waist_Hip_Ratio_Result.this.startActivity(new Intent(Waist_Hip_Ratio_Result.this, Waist_Hip_Ratio_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Waist_Hip_Ratio_Result.this.onBackPressed();
            }
        });
    }


}
