package com.workout.bodyfat.activity.smoking_risk;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
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


public class Smoking_Risk_Result extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    String smoking_risk_msg;
    TextView tv_smoking_risk_result;
    TypefaceManager typefaceManager;


     @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_smoking_risk);

        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.tv_smoking_risk_result = (TextView) findViewById(R.id.tv_smoking_risk_result);

        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);


        this.tv_smoking_risk_result.setTypeface(this.typefaceManager.getLight());
        this.extras = getIntent().getExtras();
        this.smoking_risk_msg = this.extras.getString("smoking_risk_msg");
        this.tv_smoking_risk_result.setText(this.smoking_risk_msg);

        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Smoking_Risk_Result.this.onBackPressed();
            }
        });
    }
}
