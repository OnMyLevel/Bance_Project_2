package com.example.christanismerilbanzouzi.bance_project.Popup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.christanismerilbanzouzi.bance_project.Common.Common;
import com.example.christanismerilbanzouzi.bance_project.R;

import static com.example.christanismerilbanzouzi.bance_project.Authentification.EXTRA_USER_NAME;

/**
 * Created by christanismerilbanzouzi on 20/03/2018.
 */

public class Popup extends Activity {

    private TextView textTitleHello;
    private TextView textCurentUser;
    private Typeface myCust;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        textTitleHello= (TextView) findViewById(R.id.helloView);
        textCurentUser= (TextView) findViewById(R.id.currentuser);
        myCust= Typeface.createFromAsset(getAssets(),"fonts/FunSized.ttf");
        textTitleHello.setTypeface(myCust);
        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_USER_NAME);
        if(name != null || name!="") {
            textCurentUser.setText(name.toString());
        }

        myCust= Typeface.createFromAsset(getAssets(),"fonts/FunSized.ttf");
        //textCurentUser.setTypeface(myCust);
        int width = (int)((dm.widthPixels)*0.5);
        int height= (int) ((dm.heightPixels)*0.3);
        getWindow().setLayout(width,height);
        Common.Pop = false;
    }
}
