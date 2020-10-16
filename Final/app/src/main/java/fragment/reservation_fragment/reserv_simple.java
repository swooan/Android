package fragment.reservation_fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.final_app.R;
import com.example.final_app.reservation;
import com.example.final_app.shop_detail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import model.ReservationVo;
import model.ShopUserVo;
import res.network.NetworkReservation;
import res.resources;

/**
 * A simple {@link Fragment} subclass.
 */
public class reserv_simple extends Fragment {

    com.example.final_app.reservation reservation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        reservation = (com.example.final_app.reservation)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        reservation = null;
    }

    DatePickerDialog.OnDateSetListener callbackMethod;
    EditText signup_id, res_name, rev_phone;
    TextView txt_date;
    TextView txt_time;

    Button btn_order;
    Button btn_comp;
    Button btn_back;

    public reserv_simple() {

    }

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_reserv_simple, container, false);
        signup_id = (EditText) rootview.findViewById(R.id.signup_id);
        res_name = (EditText) rootview.findViewById(R.id.res_name);
        rev_phone = (EditText) rootview.findViewById(R.id.rev_phone);
        txt_date = (TextView)rootview.findViewById(R.id.txt_date);

        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(reserv_simple.this.getActivity(), myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txt_time = (TextView)rootview.findViewById(R.id.txt_time);
        txt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                final TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(reserv_simple.this.getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "AM";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        // EditText에 출력할 형식 지정
                        txt_time.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mTimePicker.show();
            }
        });

        btn_order = (Button) rootview.findViewById(R.id.btn_order);
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(signup_id.getText())||TextUtils.isEmpty(txt_date.getText())||TextUtils.isEmpty(txt_time.getText())
                        ||TextUtils.isEmpty(res_name.getText())||TextUtils.isEmpty(rev_phone.getText())){
                    Toast.makeText(reservation, "공백값이 존재합니다", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean check = true;
                    String value = rev_phone.getText().toString().trim();
                    try {
                        int rt = Integer.parseInt(value);
                    } catch(NumberFormatException e){
                        check = false;
                        rev_phone.requestFocus();
                        Toast.makeText(reservation, "숫자만 입력하세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    value = signup_id.getText().toString().trim();
                    try {
                        int rt = Integer.parseInt(value);
                    } catch(NumberFormatException e){
                        check = false;
                        signup_id.requestFocus();
                        Toast.makeText(reservation, "숫자만 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    if(check) {
                        reservation.tempArr.add("" + txt_date.getText());
                        reservation.tempArr.add("" + txt_time.getText());
                        reservation.tempArr.add("" + signup_id.getText());
                        reservation.tempArr.add("" + rev_phone.getText());
                        reservation.tempArr.add("" + res_name.getText());
                        reservation.onChangeFragment(1);
                    }
                }
            }
        });

        btn_comp = (Button) rootview.findViewById(R.id.btn_reserv1);
        btn_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(signup_id.getText())||TextUtils.isEmpty(txt_date.getText())||TextUtils.isEmpty(txt_time.getText())
                        ||TextUtils.isEmpty(res_name.getText())||TextUtils.isEmpty(rev_phone.getText())){
                    Toast.makeText(reservation, "공백값이 존재합니다", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean check = true;
                    String value = rev_phone.getText().toString().trim();
                    try {
                        int rt = Integer.parseInt(value);
                    } catch(NumberFormatException e){
                        check = false;
                        rev_phone.requestFocus();
                        Toast.makeText(reservation, "숫자만 입력하세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    value = signup_id.getText().toString().trim();
                    try {
                        int rt = Integer.parseInt(value);
                    } catch(NumberFormatException e){
                        check = false;
                        signup_id.requestFocus();
                        Toast.makeText(reservation, "숫자만 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    if(check) {
                        String time_h, time_m;
                        if((txt_time.getText().toString().substring(0, 2)).equals("AM")){
                            time_h = txt_time.getText().toString().substring(3, txt_time.getText().toString().indexOf("시"));
                        }else{
                            int temp = Integer.parseInt(txt_time.getText().toString().substring(3, txt_time.getText().toString().indexOf("시")));
                            time_h = ""+(temp+12);
                        }
                        time_m = txt_time.getText().toString().substring(txt_time.getText().toString().indexOf("시")+2,txt_time.getText().toString().indexOf("분"));
                        ArrayList<ReservationVo> reservationList = (ArrayList<ReservationVo>)resources.gets.get("reservation");
                        reservationList.add(new ReservationVo(((reservation) getActivity()).shop_title, txt_date.getText()+time_h+":"+time_m+":00.0" , Integer.parseInt(signup_id.getText()+"")));
                        Log.i("parse : ", reservationList+"");
                        resources.gets.put("reservation", reservationList);//2020-10-14 17:35:00.0
                        new NetworkReservation(reservation).execute("1", (String) resources.gets.get("user_email"), ((reservation) getActivity()).shop_title,
                                "" + txt_date.getText(), "" + txt_time.getText(), "" + signup_id.getText(), ((reservation) getActivity()).shop_id,
                                "" + rev_phone.getText(), "" + res_name.getText());
                    }
                }
            }
        });

        btn_back = (Button) rootview.findViewById(R.id.btn_back1);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservation.finish();
            }
        });

        return rootview;
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        txt_date.setText(sdf.format(myCalendar.getTime()));
    }

}
