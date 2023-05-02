package com.workout.bodyfat.activity.BMR;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class BMR_Result extends Activity {
    String TAG = getClass().getSimpleName();
    
    double bmr;
    Bundle extras;
    String from = "";
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

        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.rl_main = (LinearLayout) findViewById(R.id.rl_main);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.extras = getIntent().getExtras();
        this.bmr = this.extras.getDouble("bmr");
        this.from = this.extras.getString("from", "");

        this.tv_ans_bmr = (TextView) findViewById(R.id.tv_ans_bmr);
        if (this.from.equals("bmr")) {
//            this.rl_main.setBackgroundResource(R.drawable.popup_background_gradient1);
        } else {
//            this.rl_main.setBackgroundResource(R.drawable.popup_background_gradient7);
        }
        this.tv_ans_bmr.setTypeface(this.typefaceManager.getLight());
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.bmr);
        Log.d("bmr_val->", sb.toString());
        if (this.from.equals("bmr")) {
            TextView textView = this.tv_ans_bmr;
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(getString(R.string.Your_BMR_is));
            sb3.append(" :  \n");
            sb3.append(String.format("%.0f", new Object[]{Double.valueOf(this.bmr)}));
            sb2.append(String.valueOf(sb3.toString()));
            sb2.append(" ");
            sb2.append(getString(R.string.cal_per_day));
            textView.setText(sb2.toString());
        } else {
            TextView textView2 = this.tv_ans_bmr;
            StringBuilder sb4 = new StringBuilder();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(getString(R.string.Your_RMR_is));
            sb5.append(" :  \n");
            sb5.append(String.format("%.0f", new Object[]{Double.valueOf(this.bmr)}));
            sb4.append(String.valueOf(sb5.toString()));
            sb4.append(" ");
            sb4.append(getString(R.string.cal_per_day));
            textView2.setText(sb4.toString());
        }
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BMR_Result.this.onBackPressed();
            }
        });
    }


}
