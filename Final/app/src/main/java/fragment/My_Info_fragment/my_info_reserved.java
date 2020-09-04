package fragment.My_Info_fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.final_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class my_info_reserved extends Fragment {

    public CalendarView calendarView;

    public my_info_reserved() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_info_reserved, container, false);

        calendarView = (CalendarView)rootView.findViewById(R.id.my_info_cal);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

            }
        });

        return rootView;
    }

}
