package com.workout.bodyfat.activity.ideal_body_weight;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Ideal_Body_Weight_Result extends AppCompatActivity {
    String TAG = getClass().getSimpleName();

    Bundle extras;
    GlobalFunction globalFunction;
    Float ideal_body_weight;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_ideal_weight;
    TextView tv_ideal_weight_range;
    TypefaceManager typefaceManager;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_ideal_body_weight_result);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.tv_ideal_weight = (TextView) findViewById(R.id.tv_ideal_weight);
        this.tv_ideal_weight_range = (TextView) findViewById(R.id.tv_ideal_weight_range);
        this.tv_ideal_weight.setTypeface(this.typefaceManager.getLight());
        this.tv_ideal_weight_range.setTypeface(this.typefaceManager.getLight());

        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);


        this.extras = getIntent().getExtras();
        this.ideal_body_weight = Float.valueOf(this.extras.getFloat("ideal_body_weight"));
        if (this.ideal_body_weight.floatValue() <= 0.0f) {
            TextView textView = this.tv_ideal_weight;
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.Your_ideal_body_weight_is));
            sb.append("0");
            sb.append(getString(R.string.kg));
            textView.setText(sb.toString());
            TextView textView2 = this.tv_ideal_weight_range;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getString(R.string.Your_ideal_body_weight_range_is));
            sb2.append("0-0");
            sb2.append(getString(R.string.kg));
            textView2.setText(sb2.toString());
            return;
        }
        TextView textView3 = this.tv_ideal_weight;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getString(R.string.Your_ideal_body_weight_is));
        sb3.append(" ");
        sb3.append(Math.round(this.ideal_body_weight.floatValue()));
        sb3.append(getString(R.string.kg));
        textView3.setText(sb3.toString());
        TextView textView4 = this.tv_ideal_weight_range;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(getString(R.string.Your_ideal_body_weight_range_is));
        sb4.append(" ");
        sb4.append(Math.round(this.ideal_body_weight.floatValue()) - 5);
        sb4.append("-");
        sb4.append(Math.round(this.ideal_body_weight.floatValue()) + 5);
        sb4.append(getString(R.string.kg));
        textView4.setText(sb4.toString());
    }



}
