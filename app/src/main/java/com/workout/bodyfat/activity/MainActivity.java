package com.workout.bodyfat.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.workout.bodyfat.ItemClickListener;
import com.workout.bodyfat.R;
import com.workout.bodyfat.activity.BMR.bmr_calculator;
import com.workout.bodyfat.activity.Body_Surface_Area_Calculator.C1376Body_Surface_Area_Calculator;
import com.workout.bodyfat.activity.alcohol.Alcohol_Calculator;
import com.workout.bodyfat.activity.blood_donation.Blood_Donation_Calculator;
import com.workout.bodyfat.activity.blood_pressure.BloodPressure_Calculator;
import com.workout.bodyfat.activity.blood_volume.Blood_Volume_Calculator;
import com.workout.bodyfat.activity.bmi.BMI_Calculator;
import com.workout.bodyfat.activity.body_adiposity_index.Body_Adiposity_Index_Calculator;
import com.workout.bodyfat.activity.body_fat.Bodyfat_Calculator;
import com.workout.bodyfat.activity.body_frame_size.Body_Frame_Size_Calculator;
import com.workout.bodyfat.activity.calories_burn.Calories_burn_Calculator;
import com.workout.bodyfat.activity.calories_intake.Daily_Calories_Intake_Calculator;
import com.workout.bodyfat.activity.chest_hip_ratio.Chest_Hip_Ratio_Calculator;
import com.workout.bodyfat.activity.child_growth.Child_Growth_Calculator;
import com.workout.bodyfat.activity.children_height_growth.Children_Height_Growth_Prediction_Calculator;
import com.workout.bodyfat.activity.cholestrol.Cholestrol_Calculator;
import com.workout.bodyfat.activity.daily_water.Daily_WaterIntake_Calculator;
import com.workout.bodyfat.activity.heart_rate.Heart_Rate_Calculator;
import com.workout.bodyfat.activity.ideal_body_weight.Ideal_Body_Weight_Calculator;
import com.workout.bodyfat.activity.lean_body_mass.Lean_Body_Mass_Calculator;
import com.workout.bodyfat.activity.menstrual_and_ovulation.Menstrual_Ovulation_Calculator;
import com.workout.bodyfat.activity.pregnancy_due_date.Pregnancy_Due_Date_Calculator;
import com.workout.bodyfat.activity.smoking_risk.Smoking_Risk_Calculator;
import com.workout.bodyfat.activity.sugar.Sugar_calculator;
import com.workout.bodyfat.activity.trademill.Trademill_Calculator;
import com.workout.bodyfat.activity.waist_height_ratio.Waist_Height_Ratio_Calculator;
import com.workout.bodyfat.activity.waist_hip_ratio.Waist_Hip_Ratio_Calculator;
import com.workout.bodyfat.adapter.Home_Adapter;
import com.workout.bodyfat.model.DataModel;
import com.workout.bodyfat.utils.TypefaceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    ArrayList<DataModel> all_home = new ArrayList<>();
    List<Integer> arr_image;
    String[] arr_title;
    boolean doubleBackToExitPressedOnce = false;
    EditText et_sesrch;
    Home_Adapter home_adapter;
    GridLayoutManager manager;
    RecyclerView recyclerview_homepage;
    TypefaceManager typefaceManager;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        et_sesrch = (EditText) findViewById(R.id.et_sesrch);
        recyclerview_homepage = (RecyclerView) findViewById(R.id.recyclerview_homepage);
        manager = new GridLayoutManager(this, 2);
        arr_image = new ArrayList<>();


        List<Integer> ImageList = Arrays.asList(
                R.drawable.img_basal_metabolic_rate,
                R.drawable.img_blood_donation,
                R.drawable.img_blood_donation,
                R.drawable.img_blood_pressure,
                R.drawable.img_blood_sugar,
                R.drawable.img_blood_volume,
                R.drawable.img_body_adiposity_index,
                R.drawable.img_body_fat,
                R.drawable.img_body_frame_size,
                R.drawable.img_body_mass,
                R.drawable.img_body_surface_area,
                R.drawable.img_bruce_trade_mill,
                R.drawable.img_calorie_burn,
                R.drawable.img_chest_to_hip_ratio,
                R.drawable.img_childrens_height_predictor,
                R.drawable.img_cholestrol_ratio,
                R.drawable.img_daily_calorie_intake,
                R.drawable.img_daily_water_intake,
                R.drawable.img_heart_rate,
                R.drawable.img_ideal_body_weight,
                R.drawable.img_kids_growth,
                R.drawable.img_lean_body_mass,
                R.drawable.img_menstraul_ovulation,
                R.drawable.img_pregnancy_due_date,
                R.drawable.img_basal_metabolic_rate,
                R.drawable.img_smoking_risk,
                R.drawable.img_waist_to_height_ratio,
                R.drawable.img_waist_to_hip_ratio

        );
        arr_image.addAll(ImageList);

        arr_title = getResources().getStringArray(R.array.arr_home_title);

        for (int i = 0; i < arr_title.length; i++) {
            all_home.add(new DataModel(i, arr_image.get(i), arr_title[i]));
        }
        recyclerview_homepage.setLayoutManager(manager);
        home_adapter = new Home_Adapter(this, all_home, this, this);
        recyclerview_homepage.setAdapter(home_adapter);


        et_sesrch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                try {
                    home_adapter.getFilter().filter(charSequence);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public void clickListener(int pos) {
        if (pos == 0) {
            Intent intent = new Intent(this, bmr_calculator.class);
            startActivity(intent);
        } else if (pos == 1) {
            Intent intent2 = new Intent(this, Alcohol_Calculator.class);
            startActivity(intent2);
        } else if (pos == 2) {
            Intent intent3 = new Intent(this, Blood_Donation_Calculator.class);
            startActivity(intent3);
        } else if (pos == 3) {
            Intent intent4 = new Intent(this, BloodPressure_Calculator.class);
            startActivity(intent4);
        } else if (pos == 4) {
            Intent posntent5 = new Intent(this, Sugar_calculator.class);
            startActivity(posntent5);
        } else if (pos == 5) {
            Intent intent6 = new Intent(this, Blood_Volume_Calculator.class);
            startActivity(intent6);
        } else if (pos == 6) {
            Intent intent7 = new Intent(this, Body_Adiposity_Index_Calculator.class);
            startActivity(intent7);
        } else if (pos == 7) {
            Intent intent8 = new Intent(this, Bodyfat_Calculator.class);
            startActivity(intent8);
        } else if (pos == 8) {
            Intent intent9 = new Intent(this, Body_Frame_Size_Calculator.class);
            startActivity(intent9);
        } else if (pos == 9) {
            Intent intent10 = new Intent(this, BMI_Calculator.class);
            startActivity(intent10);
        } else if (pos == 10) {
            Intent intent11 = new Intent(this, C1376Body_Surface_Area_Calculator.class);
            startActivity(intent11);
        } else if (pos == 11) {
            Intent intent12 = new Intent(this, Trademill_Calculator.class);
            startActivity(intent12);
        } else if (pos == 12) {
            Intent intent13 = new Intent(this, Calories_burn_Calculator.class);
            startActivity(intent13);
        } else if (pos == 13) {
            Intent intent14 = new Intent(this, Chest_Hip_Ratio_Calculator.class);
            startActivity(intent14);
        } else if (pos == 14) {
            Intent intent15 = new Intent(this, Children_Height_Growth_Prediction_Calculator.class);
            startActivity(intent15);
        } else if (pos == 15) {
            Intent intent16 = new Intent(this, Cholestrol_Calculator.class);
            startActivity(intent16);
        } else if (pos == 16) {
            Intent intent17 = new Intent(this, Daily_Calories_Intake_Calculator.class);
            startActivity(intent17);
        } else if (pos == 17) {
            Intent intent18 = new Intent(this, Daily_WaterIntake_Calculator.class);
            startActivity(intent18);
        } else if (pos == 18) {
            Intent intent19 = new Intent(this, Heart_Rate_Calculator.class);
            startActivity(intent19);
        } else if (pos == 19) {
            Intent intent20 = new Intent(this, Ideal_Body_Weight_Calculator.class);
            startActivity(intent20);
        } else if (pos == 20) {
            Intent intent21 = new Intent(this, Child_Growth_Calculator.class);
            startActivity(intent21);
        } else if (pos == 21) {
            Intent intent22 = new Intent(this, Lean_Body_Mass_Calculator.class);
            startActivity(intent22);
        } else if (pos == 22) {
            Intent intent23 = new Intent(this, Menstrual_Ovulation_Calculator.class);
            startActivity(intent23);
        } else if (pos == 23) {
            Intent intent24 = new Intent(this, Pregnancy_Due_Date_Calculator.class);
            startActivity(intent24);
        } else if (pos == 24) {
            Intent intent25 = new Intent(this, bmr_calculator.class);
            intent25.putExtra("from", "rmr");
            startActivity(intent25);
        } else if (pos == 25) {
            Intent intent26 = new Intent(this, Smoking_Risk_Calculator.class);
            startActivity(intent26);

        } else if (pos == 26) {
            Intent intent27 = new Intent(this, Waist_Height_Ratio_Calculator.class);
            startActivity(intent27);

        } else if (pos == 27) {
            Intent intent28 = new Intent(this, Waist_Hip_Ratio_Calculator.class);
            startActivity(intent28);
        }
    }
}