package com.workout.bodyfat.activity.trademill;

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

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Trademill_Result extends Activity {
    String TAG = getClass().getSimpleName();
    
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    SharedPreferenceManager sharedPreferenceManager;
    Double trademill;
    TextView tv_trademill_result;
    TypefaceManager typefaceManager;


     @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    private FrameLayout adContainerView;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_trademill);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.tv_trademill_result = (TextView) findViewById(R.id.tv_trademill_result);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);

        this.tv_trademill_result.setTypeface(this.typefaceManager.getLight());
        this.extras = getIntent().getExtras();
        this.trademill = Double.valueOf(this.extras.getDouble("trademill"));
        TextView textView = this.tv_trademill_result;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.Bruce_trade_mill));
        sb.append("%.2f");
        textView.setText(String.format(sb.toString(), new Object[]{this.trademill}));
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }

        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Trademill_Result.this.onBackPressed();
            }
        });
    }



}
