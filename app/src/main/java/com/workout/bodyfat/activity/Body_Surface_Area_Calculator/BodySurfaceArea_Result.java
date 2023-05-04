package com.workout.bodyfat.activity.Body_Surface_Area_Calculator;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class BodySurfaceArea_Result extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    
    double bsa;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    LinearLayout rl_main;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_ans_bmr;
    TypefaceManager typefaceManager;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_bmr);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);
        this.rl_main = (LinearLayout) findViewById(R.id.rl_main);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
//        this.rl_main.setBackgroundResource(R.drawable.popup_background_gradient2);
        this.extras = getIntent().getExtras();
        this.bsa = this.extras.getDouble("bsa");
        this.tv_ans_bmr = (TextView) findViewById(R.id.tv_ans_bmr);
        this.tv_ans_bmr.setTypeface(this.typefaceManager.getLight());
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }

        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BodySurfaceArea_Result.this.onBackPressed();
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.bsa);
        Log.d("bmr_val->", sb.toString());
        TextView textView = this.tv_ans_bmr;
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getString(R.string.your_body_surface_Area));
        sb3.append(" :\n");
        sb3.append(String.format("%.02f", new Object[]{Double.valueOf(this.bsa)}));
        sb2.append(String.valueOf(sb3.toString()));
        sb2.append(" m<sup>2</sup>");
        textView.setText(Html.fromHtml(sb2.toString()));
    }

}
