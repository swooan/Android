package fragment.My_Info_fragment;


import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.final_app.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

import res.calendar_deco.EventDecorator;
import res.calendar_deco.SaturdayDecorator;
import res.calendar_deco.SundayDecorator;
import res.calendar_deco.OneDayDecorator;

/**
 * A simple {@link Fragment} subclass.
 */
public class my_info_reserved extends Fragment {

    String time,kcal,menu;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    Cursor cursor;
    MaterialCalendarView calendarView;


    public my_info_reserved() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_info_reserved, container, false);

        // prolificinteractive

        calendarView = (MaterialCalendarView)rootView.findViewById(R.id.my_info_cal);

        calendarView.state().edit()
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator
        );

        String[] result = {"2020,10,11","2020,08,25","2020,09,11","2020,09,18","2020,09,30"};

        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year = " , Year + "");
                Log.i("Month = " , Month + "");
                Log.i("Day = " , Day + "");

                String choose_Date = Year + "," + Month + "," + Day;

                Log.i("choose_Date = ", choose_Date + "");
                calendarView.clearSelection();

                TextView text = (TextView)rootView.findViewById(R.id.my_info_txt);
                text.setText(choose_Date);

            }
        });

        return rootView;
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] result;

        ApiSimulator(String[] result) {
            this.result = result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();

            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for (int i = 0; i < result.length; i++) {

                //이부분에서 day를 선언하면 초기 값에 오늘 날짜 데이터 들어간다.
                //오늘 날짜 데이터를 첫 번째 인자로 넣기 때문에 데이터가 하나씩 밀려 마지막 데이터는 표시되지 않고, 오늘 날짜 데이터가 표시 됨.
                // day선언 주석처리

                //                CalendarDay day = CalendarDay.from(calendar);
                //                Log.e("데이터 확인","day"+day);
                String[] time = result[i].split(",");

                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                //선언문을 아래와 같은 위치에 선언
                //먼저 .set 으로 데이터를 설정한 다음 CalendarDay day = CalendarDay.from(calendar); 선언해주면 첫 번째 인자로 새로 정렬한 데이터를 넣어 줌.
                calendar.set(year, month - 1, dayy);
                CalendarDay day = CalendarDay.from(calendar);
                dates.add(day);
            }

            return dates;
        }
        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            com.example.final_app.my_Info my_info = (com.example.final_app.my_Info)getActivity();

            calendarView.addDecorator(new EventDecorator(Color.RED, calendarDays, my_info));
        }
    }
}