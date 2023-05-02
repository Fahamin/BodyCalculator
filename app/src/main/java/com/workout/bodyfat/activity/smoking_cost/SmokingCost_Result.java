package com.workout.bodyfat.activity.smoking_cost;

import static com.workout.bodyfat.utils.Fun.showBanner;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.workout.bodyfat.R;import com.workout.bodyfat.utils.Fun;
import com.workout.bodyfat.utils.GlobalFunction;
import com.workout.bodyfat.utils.SharedPreferenceManager;
import com.workout.bodyfat.utils.TypefaceManager;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class SmokingCost_Result extends Activity {
    String TAG = getClass().getSimpleName();

    Bundle extras;
    GlobalFunction globalFunction;
    Double monthly_expense;
    SharedPreferenceManager sharedPreferenceManager;
    TextView tv_smokingcost_month;
    TextView tv_smokingcost_week;
    TextView tv_smokingcost_year;
    TypefaceManager typefaceManager;
    Double weekely_expense;
    Double yearly_expense;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private FrameLayout adContainerView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_smokingcost);
        this.sharedPreferenceManager = new SharedPreferenceManager(this);
        this.globalFunction = new GlobalFunction(this);
        this.typefaceManager = new TypefaceManager(getAssets(), this);
        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.tv_smokingcost_week = (TextView) findViewById(R.id.tv_smokingcost_week);
        this.tv_smokingcost_month = (TextView) findViewById(R.id.tv_smokingcost_month);
        this.tv_smokingcost_year = (TextView) findViewById(R.id.tv_smokingcost_year);

        new Fun(this);
        adContainerView = findViewById(R.id.ad_view_container);
        showBanner(this, adContainerView);

        this.tv_smokingcost_week.setTypeface(this.typefaceManager.getLight());
        this.tv_smokingcost_month.setTypeface(this.typefaceManager.getLight());
        this.tv_smokingcost_year.setTypeface(this.typefaceManager.getLight());
        this.extras = getIntent().getExtras();
        this.weekely_expense = Double.valueOf(this.extras.getDouble("weekely_expense"));
        this.monthly_expense = Double.valueOf(this.extras.getDouble("monthly_expense"));
        this.yearly_expense = Double.valueOf(this.extras.getDouble("yearly_expense"));
        TextView textView = this.tv_smokingcost_week;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.Weekly_Expense));
        sb.append(" : %.2f");
        textView.setText(String.format(sb.toString(), new Object[]{this.weekely_expense}));
        TextView textView2 = this.tv_smokingcost_month;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R.string.Monthly_Expense));
        sb2.append(" : %.2f");
        textView2.setText(String.format(sb2.toString(), new Object[]{this.monthly_expense}));
        TextView textView3 = this.tv_smokingcost_year;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getString(R.string.Yearly_Expense));
        sb3.append(" : %.2f");
        textView3.setText(String.format(sb3.toString(), new Object[]{this.yearly_expense}));
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }

    }


}
