package com.workout.bodyfat.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workout.bodyfat.ItemClickListener;
import com.workout.bodyfat.R;
import com.workout.bodyfat.model.DataModel;

import java.util.ArrayList;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.MyHolder> implements Filterable {
    ArrayList<DataModel> FilteredList = new ArrayList<>();
    Activity activity;
    public ArrayList<DataModel> allVideo;
    ArrayList all_videohistoryArrayList;
    Context context;
    boolean isFilter = false;
    ItemClickListener itemClickListener;

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView image_home;
        RelativeLayout rl_home;
        TextView tv_title_home;

        public MyHolder(View view) {
            super(view);
            this.tv_title_home = (TextView) view.findViewById(R.id.tv_title_home);
            this.image_home = (ImageView) view.findViewById(R.id.image_home);
            this.rl_home = (RelativeLayout) view.findViewById(R.id.rl_home);
        }
    }

    public Home_Adapter(Activity activity2, ArrayList<DataModel> arrayList, Context context2, ItemClickListener itemClickListener) {
        this.activity = activity2;
        this.context = context2;
        this.all_videohistoryArrayList = arrayList;
        this.allVideo = arrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_home, viewGroup, false));
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "DiscouragedApi"})
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        try {
            myHolder.tv_title_home.setText(((DataModel) this.all_videohistoryArrayList.get(i)).getTitle());
            try {
                Resources resources = this.context.getResources();
                myHolder.image_home.setImageDrawable(resources.getDrawable(resources.getIdentifier(((DataModel) this.all_videohistoryArrayList.get(i)).getImage(), "drawable", this.context.getPackageName())));
            } catch (Exception e) {
                e.printStackTrace();
            }
            myHolder.rl_home.setTag(i);
        } catch (Exception e2) {
            e2.printStackTrace();
        }


    }

    public int getItemCount() {
        return this.all_videohistoryArrayList.size();
    }

    public Filter getFilter() {
        return new Filter() {

            @SuppressLint("NotifyDataSetChanged")
            public void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Home_Adapter.this.all_videohistoryArrayList = (ArrayList) filterResults.values;
                Home_Adapter.this.notifyDataSetChanged();
            }


            public FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence == null || charSequence.length() == 0) {
                    filterResults.values = Home_Adapter.this.allVideo;
                    filterResults.count = Home_Adapter.this.allVideo.size();
                    Home_Adapter.this.isFilter = false;
                } else {
                    Home_Adapter.this.FilteredList.clear();
                    Home_Adapter.this.isFilter = true;
                    for (int i = 0; i < Home_Adapter.this.allVideo.size(); i++) {
                        DataModel DataModel = (DataModel) Home_Adapter.this.allVideo.get(i);
                        if (DataModel.getTitle().toString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("pos->");
                            sb.append(String.valueOf(i));
                            Log.d("pos->", sb.toString());
                            Home_Adapter.this.FilteredList.add(DataModel);
                        }
                    }
                    filterResults.values = Home_Adapter.this.FilteredList;
                    filterResults.count = Home_Adapter.this.FilteredList.size();
                }
                return filterResults;
            }
        };
    }
}
