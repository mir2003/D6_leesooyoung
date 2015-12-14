package org.androidtown.calendar.month;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class SchedulePopUpActivity extends Activity {

    public static final int RESPONSE_SAVE_CODE = 1;
    public static final int RESPONSE_CLOSE_CODE = 0;

    EditText editText1;
    EditText editText2;

    DaySchedule selectedDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schdule_popup);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        // Data 객체를 받을 Intent 생성
        Intent intent = getIntent();

//        String selectedDay = intent.getStringExtra("selectedDay");
//        Log.d("SchedulePopUpActivity", "selectedDay : "+ selectedDay);

        selectedDay = (DaySchedule)intent.getSerializableExtra("selectedDay");
        Log.d("SchedulePopUpActivity", "selectedDay11111111111111 : " + selectedDay.getDate().toString());
        //Log.d("SchedulePopUpActivity", "selectedDay22222222222222 : " + selectedDay.getDate().toString());

        //Toast.makeText(getApplicationContext(), "SchedulePopUpActivity : " + selectedDay.getDate().toString() + "            day : " + selectedDay, Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), "SchedulePopUpActivity : " + "            day : ", Toast.LENGTH_LONG).show();



    }

    public void onButton1Clicked(View v) {

        Intent resultIntent = new Intent();

        //추가변수
        resultIntent.putExtra("buttonName", "저장");
        resultIntent.putExtra("date", selectedDay.getDate().toString());
        resultIntent.putExtra("title", editText1.getText().toString());
        resultIntent.putExtra("time", editText2.getText().toString());

        Log.d("SchedulePopUpActivity", "editText1 : " + editText1.getText().toString() + "            editText2 : " + editText2.getText().toString());
        //Toast.makeText(getApplicationContext(), "editText1 : " + editText1.getText().toString() + "            editText2 : " + editText2.getText().toString(), Toast.LENGTH_LONG).show();


        setResult(RESPONSE_SAVE_CODE, resultIntent);
        finish();
    }

    public void onButton2Clicked(View v) {

        Intent resultIntent = new Intent();

        //추가변수
        resultIntent.putExtra("buttonName", "닫기");
        resultIntent.putExtra("title", "title!");
        resultIntent.putExtra("time", "time!");

        setResult(RESPONSE_CLOSE_CODE, resultIntent);
        finish();
    }
}