package com.anjuka.deviceactivitypoc;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MyConstrainLayout extends ConstraintLayout {

    private static final String TAG = "MyConstrainLayout";
    private ArrayList<ActivityData> activityDataList = new ArrayList<>();
    private ArrayList<ActivityData> activityDataListBackup = new ArrayList<>();
    private ArrayList<Boolean> bands_list = new ArrayList<>();
    int top_margin = 0;
    int start_margin = 0;
    int multiply_val = 100;
    int activity_item_width = 0;
    int band_count = 360;
    int screen_width = 0;
    int screen_dev_count =10;

    public MyConstrainLayout(@NonNull Context context, int width) {
        super(context);

        this.screen_width = width;

        Toast.makeText(context, "width " + screen_width, Toast.LENGTH_SHORT).show();

        activityDataList.add(new ActivityData(8, 10, "Device 1", "#C8FF5733", 0, 0, 2));
        activityDataList.add(new ActivityData(8, 10, "Device 7", "#C8FF5733", 0, 0, 2));

        activityDataList.add(new ActivityData(8, 16, "Device 8", "#C8FF5733", 0, 0, 8));
        activityDataList.add(new ActivityData(10, 12, "Device 4", "#C833FFD7", 0, 0, 2));
        activityDataList.add(new ActivityData(10, 12, "Device 4", "#C833FFD7", 0, 0, 2));
        activityDataList.add(new ActivityData(10, 12, "Device 4", "#C833FFD7", 0, 0, 2));
        activityDataList.add(new ActivityData(10, 12, "Device 4", "#C833FFD7", 0, 0, 2));
        /* activityDataList.add(new ActivityData(9, 11, "Device 2", "#C8FCFF33", 0, 0, 2));
        activityDataList.add(new ActivityData(8, 11, "Device 3", "#71FF33", 0, 0, 3));
        activityDataList.add(new ActivityData(10, 11, "Device 5", "#E633FF", 0, 0, 1));
        activityDataList.add(new ActivityData(13, 16, "Device 6", "#E6335F", 0, 0, 3));
        activityDataList.add(new ActivityData(8, 10, "Device 7", "#E6335F", 0, 0, 2));
        activityDataList.add(new ActivityData(8, 10, "Device 8", "#E6335F", 0, 0, 2));*/
        activityDataList.add(new ActivityData(8, 10, "Device 9", "#E6335F", 0, 0, 2));

//        activityDataList.add(new ActivityData(8, 10, "Device 10", "#E6335F", 0, 0, 2));
//        activityDataList.add(new ActivityData(8, 10, "Device 11", "#E6335F", 0, 0, 2));
//        activityDataList.add(new ActivityData(8, 10, "Device 12", "#E6335F", 0, 0, 2));
//        activityDataList.add(new ActivityData(8, 10, "Device 12", "#E6335F", 0, 0, 2));
//        activityDataList.add(new ActivityData(8, 10, "Device 12", "#E6335F", 0, 0, 2));
//        activityDataList.add(new ActivityData(8, 10, "Device 12", "#E6335F", 0, 0, 2));
//        activityDataList.add(new ActivityData(8, 10, "Device 12", "#E6335F", 0, 0, 2));
//        activityDataList.add(new ActivityData(8, 10, "Device 12", "#E6335F", 0, 0, 2));
        activityDataList.add(new ActivityData(8, 10, "Device 12", "#E6335F", 0, 0, 2));
        activityDataList.add(new ActivityData(8, 10, "Device 12", "#E6335F", 0, 0, 2));
        activityDataList.add(new ActivityData(8, 10, "Device 12", "#E6335F", 0, 0, 2));
        activityDataList.add(new ActivityData(13, 18, "Device 12", "#E6335F", 0, 0, 5));
       // activityDataList.add(new ActivityData(13, 18, "Device 12", "#E6335F", 0, 0, 5));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Collections.sort(activityDataList, Comparator.comparingInt(ActivityData::getStart_time).reversed()
                    .thenComparingInt(ActivityData::getDuration).reversed());
        }

       /* Collections.sort(activityDataList);
        Log.d("TAG", "MyConstrainLayout: " + activityDataList);*/
       /* Collections.sort(activityDataList, ActivityData.sortByEndTime);
        Log.d("TAG", "MyConstrainLayout: " + activityDataList);*/

        setId(View.generateViewId());
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        setLayoutParams(layoutParams);
        setBackgroundColor(getResources().getColor(R.color.white));

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);

        initContainer(context, constraintSet);

        constraintSet.applyTo(this);
    }

    private void initContainer(Context context, ConstraintSet constraintSet) {
        initBands();
        activity_item_width = screen_width/screen_dev_count;
        setupItemPositions(context, constraintSet);
    }

    private void setupItemPositions(Context context, ConstraintSet constraintSet) {
        int item_iterate_count = 1;
        for (int x=0; x < activityDataList.size(); x++){
            if (activityDataListBackup.size() !=0){
                if (activityDataListBackup.get(activityDataListBackup.size() -1).getStart_time() == activityDataList.get(x).getStart_time()){
                    //same start time
                    item_iterate_count += 1;

                    if (item_iterate_count > screen_dev_count){
                        //overlap mode
                        int extra = ((activity_item_width * item_iterate_count) - screen_width)/item_iterate_count;
                        Log.d(TAG, "setupItemPositions: extra " + extra);

                        for (int a=1; a < activityDataListBackup.size(); a++){

                            activityDataListBackup.get(a).setLeft_margin((activityDataListBackup.get(a).getLeft_margin()) - extra * a);
                        }

                        activityDataList.get(x).setLeft_margin(activityDataListBackup.get(activityDataListBackup.size() - 1).getLeft_margin() + activity_item_width - extra);

                    }
                    else {
                        //normal mode
                        activityDataList.get(x).setLeft_margin(activityDataListBackup.get(activityDataListBackup.size() - 1).getLeft_margin() + activity_item_width);
                    }
                    activityDataList.get(x).setTop_margin(activityDataListBackup.get(activityDataListBackup.size() - 1).getTop_margin());
                    //activityDataList.get(x).setDuration(activityDataList.get(x).getDuration() * 60);
                }
                else {
                    //different start time
                    item_iterate_count = 0;

                    if (activityDataListBackup.size() > screen_dev_count){
                        int extra = ((activity_item_width * activityDataList.size()) - screen_width)/activityDataList.size();
                        Log.d(TAG, "setupItemPositions: extra " + extra);

                        for (int a=1; a < activityDataListBackup.size(); a++){

                            activityDataListBackup.get(a).setLeft_margin((activityDataListBackup.get(a).getLeft_margin()) - extra * a);
                        }

                        activityDataList.get(x).setLeft_margin(activityDataListBackup.get(activityDataListBackup.size() - 1).getLeft_margin() + activity_item_width - extra);

                    }
                    else {
                        activityDataList.get(x).setLeft_margin(activityDataListBackup.get(activityDataListBackup.size() -1).getLeft_margin() + activity_item_width);
                    }

                    activityDataList.get(x).setTop_margin((activityDataList.get(x).getStart_time() - activityDataListBackup.get(0).getStart_time()) * 60);
                    //activityDataList.get(x).setDuration(activityDataList.get(x).getDuration() * 60);
                }
            }
            activityDataList.get(x).setDuration(activityDataList.get(x).getDuration() * 60);
            activityDataListBackup.add(activityDataList.get(x));
        }

        Log.d(TAG, "setupItemPositions: activityDataListBackup " + activityDataListBackup);
        createItems(context, constraintSet);
    }

    private void createItems(Context context, ConstraintSet constraintSet) {
        for (int x=0; x < activityDataListBackup.size(); x++) {
            Button button = new Button(context);
            button.setId(View.generateViewId());
            button.setText(activityDataListBackup.get(x).getDevice_name() + String.valueOf(x));

            button.setHeight(activityDataListBackup.get(x).getDuration());
            Log.d(TAG, "createItems: activity_item_width " + activity_item_width);
            button.setWidth(activity_item_width);
            //button.setBackgroundColor(Color.parseColor(activityDataListBackup.get(x).getColor()));

            Random rnd = new Random();
            int color = Color.argb(100, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            //button.setBackgroundColor(color);
            button.setBackgroundResource(R.drawable.button_background);


            constraintSet.constrainWidth(button.getId(), ConstraintSet.WRAP_CONTENT);
            constraintSet.constrainHeight(button.getId(), ConstraintSet.WRAP_CONTENT);

            constraintSet.connect(button.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
            constraintSet.connect(button.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);

            constraintSet.setMargin(button.getId(), ConstraintSet.START, activityDataListBackup.get(x).getLeft_margin());
            constraintSet.setMargin(button.getId(), ConstraintSet.TOP, activityDataListBackup.get(x).getTop_margin());


            addView(button);
        }

    }

    void createButtons(Context context, ConstraintSet constraintSet){


/*
        for (int x=0; x < activityDataList.size(); x++){
            Button button = new Button(context);
            button.setId(View.generateViewId());
            button.setText(activityDataList.get(x).getDevice_name());

            button.setHeight((activityDataList.get(x).getEnd_time() - activityDataList.get(x).getStart_time())* multiply_val);
            button.setWidth(activity_item_width);
            button.setBackgroundColor(Color.parseColor(activityDataList.get(x).getColor()));


            constraintSet.constrainWidth(button.getId(), ConstraintSet.WRAP_CONTENT);
            constraintSet.constrainHeight(button.getId(), ConstraintSet.WRAP_CONTENT);

            constraintSet.connect(button.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
            constraintSet.connect(button.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);


if (activityDataListBackup.size() !=0){
                    if (activityDataListBackup.get(activityDataListBackup.size()-1).getStart_time() != activityDataList.get(x).getStart_time()) {
                        int top = activityDataList.get(x).getStart_time() - activityDataListBackup.get(activityDataListBackup.size()-1).getStart_time();
                        top_margin = top_margin + (activityDataListBackup.get(activityDataListBackup.size()-1).getEnd_time() - activityDataListBackup.get(activityDataListBackup.size()-1).getStart_time()) * multiply_val;
                        constraintSet.setMargin(button.getId(), ConstraintSet.TOP, top_margin);
                    } else {
                        constraintSet.setMargin(button.getId(), ConstraintSet.START, activity_item_width * x);
                    }
            }


            if (activityDataListBackup.size() !=0){
                if (activityDataListBackup.get(activityDataListBackup.size()-1).getStart_time() != activityDataList.get(x).getStart_time()) {
                    //new start time
                    start_margin = 0;
                    top_margin = activityDataListBackup.get(activityDataListBackup.size()-1).getTop_margin() + (activityDataListBackup.get(activityDataListBackup.size()-1).getEnd_time() - activityDataListBackup.get(activityDataListBackup.size()-1).getStart_time()) * multiply_val;

                    constraintSet.setMargin(button.getId(), ConstraintSet.TOP, top_margin);
                    constraintSet.setMargin(button.getId(), ConstraintSet.START, start_margin);
                } else {
                    //already added start time
                    start_margin = activityDataListBackup.get(activityDataListBackup.size()-1).getLeft_margin() + activity_item_width;
                    top_margin = activityDataListBackup.get(activityDataListBackup.size()-1).getTop_margin();

                    constraintSet.setMargin(button.getId(), ConstraintSet.TOP, top_margin);
                    constraintSet.setMargin(button.getId(), ConstraintSet.START, start_margin);
                }
            }
            addView(button);
            activityDataListBackup.add(activityDataList.get(x));
            activityDataListBackup.get(activityDataListBackup.size()-1).setLeft_margin(start_margin);
            activityDataListBackup.get(activityDataListBackup.size()-1).setTop_margin(top_margin);
        }
*/

       /* //Button 1
        Button b1 = new Button(context);
        b1.setId(View.generateViewId());
        b1.setText("Button 1");
        addView(b1);

        constraintSet.constrainWidth(b1.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(b1.getId(), ConstraintSet.WRAP_CONTENT);

        constraintSet.connect(b1.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(b1.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);

        //Button 2
        Button b2 = new Button(context);
        b2.setId(View.generateViewId());
        b2.setText("Button 2");
        b2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b2.setHeight(150);
        addView(b2);

        constraintSet.constrainWidth(b2.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(b2.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.setMargin(b2.getId(), ConstraintSet.START, 40);
        constraintSet.setMargin(b2.getId(), ConstraintSet.TOP, 40);

        constraintSet.connect(b2.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(b2.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);*/
    }

    private void initBands() {
        for (int x=0; x < band_count; x++){
            bands_list.add(false);
        }
        Log.d(TAG, "initBands: bands_list SIZE " + bands_list);
    }
}
