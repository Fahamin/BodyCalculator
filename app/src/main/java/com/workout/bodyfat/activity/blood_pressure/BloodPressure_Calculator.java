package com.workout.bodyfat.activity.blood_pressure;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import java.io.PrintStream;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class BloodPressure_Calculator extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    
    String diastolic_val;
    EditText et_diastolic_pressure;
    EditText et_systolic_pressure;
    GlobalFunction globalFunction;
    ImageView iv_back;
    SharedPreferenceManager sharedPreferenceManager;
    String systolic_val;
    TextView tv_bloodpressure;
    TextView tv_calculate_bloodpressure;
    TypefaceManager typefaceManager;

    private FrameLayout adContainerView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.bloodpressure_calculator);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        this.globalFunction = new GlobalFunction(this);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.set_locale_language();
        this.et_systolic_pressure = (EditText) findViewById(R.id.et_systolic_pressure);
        this.et_diastolic_pressure = (EditText) findViewById(R.id.et_diastolic_pressure);
        this.tv_bloodpressure = (TextView) findViewById(R.id.tv_bloodpressure);
        this.tv_calculate_bloodpressure = (TextView) findViewById(R.id.tv_calculate_bloodpressure);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);


        this.tv_bloodpressure.setTypeface(this.typefaceManager.getBold());
        this.tv_calculate_bloodpressure.setTypeface(this.typefaceManager.getBold());
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_back.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BloodPressure_Calculator.this.onBackPressed();
            }
        });
        this.tv_calculate_bloodpressure.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BloodPressure_Calculator.this.et_systolic_pressure.getText().toString().trim().equals("") || BloodPressure_Calculator.this.et_systolic_pressure.getText().toString().trim().equals(".")) {
                    Toast.makeText(BloodPressure_Calculator.this.getApplicationContext(), BloodPressure_Calculator.this.getString(R.string.Enter_systolic_value), 0).show();
                } else if (BloodPressure_Calculator.this.et_diastolic_pressure.getText().toString().trim().equals("") || BloodPressure_Calculator.this.et_diastolic_pressure.getText().toString().trim().equals(".")) {
                    Toast.makeText(BloodPressure_Calculator.this.getApplicationContext(), BloodPressure_Calculator.this.getString(R.string.Enter_diastolic_value), 0).show();
                } else {
                    BloodPressure_Calculator.this.systolic_val = BloodPressure_Calculator.this.et_systolic_pressure.getText().toString().trim();
                    BloodPressure_Calculator.this.diastolic_val = BloodPressure_Calculator.this.et_diastolic_pressure.getText().toString().trim();
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
                    Intent intent = new Intent(BloodPressure_Calculator.this, BloodPressure_Result.class);
                    intent.putExtra("systolic_val", Float.parseFloat(BloodPressure_Calculator.this.systolic_val));
                    intent.putExtra("diastolic_val", Float.parseFloat(BloodPressure_Calculator.this.diastolic_val));
                    BloodPressure_Calculator.this.startActivity(intent);
                }
            }
        });
    }



    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        
        ActivityCompat.finishAfterTransition(this);
    }


}
