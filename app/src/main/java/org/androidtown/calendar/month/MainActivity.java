package org.androidtown.calendar.month;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



//그리드뷰를 이용해 월별 캘린더를 만드는 방법에 대해 알 수 있습니다.
public class MainActivity extends ActionBarActivity {

    CalendarMonthView monthView; //월별 캘린더 뷰 객체
    CalendarMonthAdapter monthViewAdapter; //월별 캘린더 어댑터
    TextView monthText; //월을 표시하는 텍스트뷰
    int curYear; //현재 연도
    int curMonth; //현재 월

    public static final int REQUEST_CODE_SCH_POP = 1001;
    public static final int RESULT_OK = 1;

    //리스트뷰추가
    ListView listView1;
    IconTextListAdapter adapter;

    static ArrayList<DaySchedule> daySchedule = new ArrayList<DaySchedule>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 월별 캘린더 뷰 객체 참조
        monthView = (CalendarMonthView) findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        // 리스너 설정
        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                // 현재 선택한 일자 정보 표시
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();
                Log.d("CalendarMonthViewActivity", "month : " + monthViewAdapter.getCurMonth() + " day : " + day);

                String dayS;
                if(day < 10){
                    dayS = "0"+Integer.toString(day);
                }else {
                    dayS = Integer.toString(day);
                }

                DaySchedule selectedDay = new DaySchedule();
                selectedDay.setDate(curYear + "" + (curMonth + 1) + "" + dayS);
                Log.d("MainActivity", "selectedDay : " + selectedDay.getDate());
                Intent intent = new Intent(MainActivity.this, SchedulePopUpActivity.class);
                intent.putExtra("selectedDay", selectedDay);

//                String selectedDay = curYear + "" + (curMonth + 1) + "" + day;
//                Intent intent = new Intent(getApplicationContext(), SchedulePopUpActivity.class);
//                intent.putExtra("selectedDay", selectedDay);

                startActivityForResult(intent, REQUEST_CODE_SCH_POP);


//                daySchedule.set(monthViewAdapter.getCurYear() + monthViewAdapter.getCurMonth() + day);
//                daySchedule.set(title);
//                daySchedule.set(time);

            }
        });



        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        // 이전 월로 넘어가는 이벤트 처리
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });


        //리스트뷰추가

        // 리스트뷰 객체 참조
        listView1 = (ListView) findViewById(R.id.listView1);
        // 어댑터 객체 생성
        adapter = new IconTextListAdapter(this);

        // 아이템 데이터 만들기
//        Resources res = getResources();
//        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "추억의 테트리스", "30,000 다운로드", "900 원"));
//        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "고스톱 - 강호동 버전", "26,000 다운로드", "1500 원"));
//        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "친구찾기 (Friends Seeker)", "300,000 다운로드", "900 원"));


        // 리스트뷰에 어댑터 설정
        listView1.setAdapter(adapter);

        // 새로 정의한 리스너로 객체를 만들어 설정
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IconTextItem curItem = (IconTextItem) adapter.getItem(position);

                String[] curData = curItem.getData();
                Toast.makeText(getApplicationContext(), "Selected : " + curData[0], Toast.LENGTH_LONG).show();

            }

        });

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "onItemLongClick onItemLongClick onItemLongClick", Toast.LENGTH_LONG).show();
                Log.d("SchedulePopUpActivity", "onItemLongClick onItemLongClick onItemLongClick11111111111111111111111111111111111111");


                adapter.delItem(position);
                adapter.notifyDataSetChanged();
                Log.d("SchedulePopUpActivity", "onItemLongClick onItemLongClick onItemLongClick2222222222222222222222222222222222222222");

                return false;
            }

        });
    }

    /**
     * 새로운 액티비티에서 돌아올 때 자동 호출되는 메소드
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        String buttonName = "";
        String date = "";
        String title = "";
        String time = "";

        if (requestCode == REQUEST_CODE_SCH_POP) {
            Toast toast = Toast.makeText(getBaseContext(), "onActivityResult() 메소드가 호출됨. 요청코드 : " + requestCode + ", 결과코드 : " + resultCode, Toast.LENGTH_LONG);
            //toast.show();

            if (resultCode == RESULT_OK) {
                buttonName = intent.getExtras().getString("buttonName");
                date = intent.getExtras().getString("date");
                title = intent.getExtras().getString("title");
                time = intent.getExtras().getString("time");
                toast = Toast.makeText(getBaseContext(), buttonName +"\n"+ "     date : " + date +"\n"+"     title : " + title +"\n"+ "     time : " + time, Toast.LENGTH_LONG);
                toast.show();

            }
        }

        Resources res = getResources();
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), date, title, time));
//        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "고스톱 - 강호동 버전", "26,000 다운로드", "1500 원"));
//        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "친구찾기 (Friends Seeker)", "300,000 다운로드", "900 원"));

    }


    /**
     * 월 표시 텍스트 설정
     */
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth+1) + "월");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
