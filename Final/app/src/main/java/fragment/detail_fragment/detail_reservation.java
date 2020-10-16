package fragment.detail_fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_app.R;
import com.example.final_app.shop_detail;

import res.resources;

/**
 * A simple {@link Fragment} subclass.
 */
public class detail_reservation extends Fragment {

    Button toTheReservation;

    public detail_reservation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_detail_reservation, container, false);

        TextView shop_reserve = (TextView)rootView.findViewById(R.id.shop_reserve);
        TextView shop_phone = (TextView)rootView.findViewById(R.id.shop_phone);
        TextView shop_time = (TextView)rootView.findViewById(R.id.shop_time);
        TextView shop_tb = (TextView)rootView.findViewById(R.id.shop_tb);
        TextView shop_car = (TextView)rootView.findViewById(R.id.shop_car);
        TextView shop_close = (TextView)rootView.findViewById(R.id.shop_close);

        shop_reserve.setText(""+((shop_detail)getActivity()).shop_info_list.get(0).getShop_reserve());
        shop_phone.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_phone());
        shop_time.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_time());
        shop_tb.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_tb());
        if(((shop_detail)getActivity()).shop_info_list.get(0).getShop_car() == '0'){
            shop_car.setText("불가능");
        }else {
            shop_car.setText("가능");
        }
        shop_close.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_close());

        toTheReservation = (Button)rootView.findViewById(R.id.toTheReservation);

        toTheReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Boolean) resources.gets.get("login")) {
                    Intent intent = new Intent();
                    intent.putExtra("shop_title", ((shop_detail)getActivity()).shop_info_list.get(0).getShop_title());
                    intent.putExtra("shop_id", ((shop_detail)getActivity()).shop_info_list.get(0).getShop_id());
                    ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.reservation");
                    intent.setComponent(name);
                    startActivityForResult(intent, 101);
                }else {
                    Toast.makeText(getContext(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

}
