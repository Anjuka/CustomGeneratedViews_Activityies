package com.anjuka.deviceactivitypoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout cl_parent;
    int height;
    int width;
    int item_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        cl_parent = findViewById(R.id.cl_parent);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        MyConstrainLayout myConstrainLayout = new MyConstrainLayout(this, width);
        setContentView(myConstrainLayout);

        //set.applyTo(cl_parent);

       /* item_count = width/200;
        //ll_parent.setWeightSum(item_count);

        for (int x=0; x < item_count; x++){
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            button.setText("Hello " + x);
            button.setId(100+ x);
            button.setOnClickListener(getOnClick(x));

            ll_parent.addView(button, layoutParams) ;
        }
    }

    private View.OnClickListener getOnClick(int x) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "CLICKED " + x, Toast.LENGTH_SHORT).show();
            }
        };*/
    }
}