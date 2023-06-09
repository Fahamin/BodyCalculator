package com.workout.bodyfat.activity.calories_burn;

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

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import java.io.PrintStream;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Calories_Burn_Result extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    
    float caloriesburn;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    String tips;
    TextView tv_ans_calburn;
    TextView tv_calburn;
    TextView tv_calburnchart;
    TextView tv_recomended;
    TypefaceManager typefaceManager;

    private FrameLayout adContainerView;

     @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_calories_burn);
        this.globalFunction = new GlobalFunction(this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.tv_ans_calburn = (TextView) findViewById(R.id.tv_ans_calburn);
        this.tv_calburn = (TextView) findViewById(R.id.tv_calburn);
        this.tv_recomended = (TextView) findViewById(R.id.tv_recomended);
        this.tv_calburnchart = (TextView) findViewById(R.id.tv_calburnchart);
        this.tv_ans_calburn.setTypeface(this.typefaceManager.getLight());
        this.tv_calburn.setTypeface(this.typefaceManager.getLight());
        this.tv_recomended.setTypeface(this.typefaceManager.getLight());
        this.tv_calburnchart.setTypeface(this.typefaceManager.getBold());
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);
        this.extras = getIntent().getExtras();
        this.caloriesburn = this.extras.getFloat("caloriesburn");
        this.tips = this.extras.getString("tips");
        StringBuilder sb = new StringBuilder();
        sb.append("tips");
        sb.append(this.tips);
        Log.d("tips", sb.toString());
        try {
            TextView textView = this.tv_ans_calburn;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getString(R.string.Calories_burned));
            sb2.append(" : ");
            sb2.append(String.format("%.02f", new Object[]{Float.valueOf(this.caloriesburn)}));
            textView.setText(String.valueOf(sb2.toString()));
            this.tv_recomended.setText(String.valueOf(this.tips));
            this.tv_calburn.setText("");
        } catch (Exception unused) {
        }
        this.tv_calburnchart.setOnClickListener(new OnClickListener() {
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
                Calories_Burn_Result.this.startActivity(new Intent(Calories_Burn_Result.this, Calories_Burn_Chart.class));
            }
        });
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Calories_Burn_Result.this.onBackPressed();
            }
        });
    }



}
