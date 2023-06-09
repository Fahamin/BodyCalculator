package com.workout.bodyfat.activity.child_growth;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
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

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Child_growth_result extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    
    String age;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    String result;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_ans_age;
    TextView tv_max_heightweight;
    TextView tv_min_heightweight;
    TypefaceManager typefaceManager;


     @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_child_growth);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.tv_ans_age = (TextView) findViewById(R.id.tv_ans_age);
        this.tv_min_heightweight = (TextView) findViewById(R.id.tv_min_heightweight);
        this.tv_max_heightweight = (TextView) findViewById(R.id.tv_max_heightweight);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.tv_ans_age.setTypeface(this.typefaceManager.getLight());
        this.tv_min_heightweight.setTypeface(this.typefaceManager.getLight());
        this.tv_max_heightweight.setTypeface(this.typefaceManager.getLight());
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);

        this.extras = getIntent().getExtras();
        this.age = this.extras.getString("age");
        this.result = this.extras.getString("result");
        TextView textView = this.tv_ans_age;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.Age_of_Child_in_Months));
        sb.append(" : \n");
        sb.append(this.age);
        sb.append(" ");
        sb.append(getString(R.string.Month));
        textView.setText(sb.toString());
        this.tv_min_heightweight.setText(this.result);
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Child_growth_result.this.onBackPressed();
            }
        });
    }



}
