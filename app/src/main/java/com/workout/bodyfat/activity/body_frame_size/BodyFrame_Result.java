package com.workout.bodyfat.activity.body_frame_size;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class BodyFrame_Result extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    
    String body_frame;
    Bundle extras;
    GlobalFunction globalFunction;
    ImageView iv_close;
    ImageView iv_imoji;
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
        setContentView(R.layout.popup_body_frame);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);

        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);

        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.extras = getIntent().getExtras();
        this.body_frame = this.extras.getString("body_frame");
        this.tv_ans_bmr = (TextView) findViewById(R.id.tv_ans_bmr);
        this.iv_close = (ImageView) findViewById(R.id.iv_close);
        this.iv_imoji = (ImageView) findViewById(R.id.iv_imoji);
        this.tv_ans_bmr.setTypeface(this.typefaceManager.getLight());

        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.body_frame);
        Log.d("body_frame->", sb.toString());
        TextView textView = this.tv_ans_bmr;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R.string.Body_frame_text));
        sb2.append(" : ");
        sb2.append(this.body_frame);
        textView.setText(sb2.toString());
        if (this.body_frame.equals(getString(R.string.Body_frame_text_small))) {
            this.iv_imoji.setImageResource(R.drawable.imogi_sad);
        } else if (this.body_frame.equals(getString(R.string.Body_frame_text_medium))) {
            this.iv_imoji.setImageResource(R.drawable.imoji_smile);
        } else if (this.body_frame.equals(getString(R.string.Body_frame_text_large))) {
            this.iv_imoji.setImageResource(R.drawable.imogi_unhappy);
        }
        this.iv_close.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BodyFrame_Result.this.onBackPressed();
            }
        });
    }



}
