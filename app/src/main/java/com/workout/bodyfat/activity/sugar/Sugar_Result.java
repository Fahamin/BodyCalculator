package com.workout.bodyfat.activity.sugar;

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

import androidx.appcompat.app.AppCompatActivity;

import com.workout.bodyfat.R;
import com.workout.bodyfat.utils.ConnectionDetector;
import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;
import com.google.android.gms.ads.AdRequest;

import java.io.PrintStream;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Sugar_Result extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    AdRequest adRequest;

    ConnectionDetector connectionDetector;
    Bundle extras;
    Double final_bloodsugar_val;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_ans_bloodsugar;
    TextView tv_bloodsugar_chart;
    TypefaceManager typefaceManager;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_sugar);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.connectionDetector = new ConnectionDetector(this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.tv_ans_bloodsugar = (TextView) findViewById(R.id.tv_ans_bloodsugar);
        this.tv_bloodsugar_chart = (TextView) findViewById(R.id.tv_bloodsugar_chart);


        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);


        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.tv_ans_bloodsugar.setTypeface(this.typefaceManager.getLight());
        this.tv_bloodsugar_chart.setTypeface(this.typefaceManager.getBold());
        this.extras = getIntent().getExtras();
        this.final_bloodsugar_val = Double.valueOf(this.extras.getDouble("final_bloodsugar_val"));
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        if (this.final_bloodsugar_val.doubleValue() < 0.0d) {
            TextView textView = this.tv_ans_bloodsugar;
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.Blood_Sugar));
            sb.append(" : 0 ]");
            sb.append(getString(R.string.mmol_a));
            textView.setText(sb.toString());
        } else {
            TextView textView2 = this.tv_ans_bloodsugar;
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(getString(R.string.Blood_Sugar));
            sb3.append(" : %.2f");
            sb2.append(String.format(sb3.toString(), new Object[]{this.final_bloodsugar_val}));
            sb2.append(" ");
            sb2.append(getString(R.string.mmol_a));
            textView2.setText(sb2.toString());
        }
        this.tv_bloodsugar_chart.setOnClickListener(new OnClickListener() {
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
                Sugar_Result.this.startActivity(new Intent(Sugar_Result.this, Sugar_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Sugar_Result.this.onBackPressed();
            }
        });
    }


}
