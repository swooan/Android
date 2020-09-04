package fragment.reservation_fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.final_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class reserv_order extends Fragment {

    com.example.final_app.reservation reservation;

    Button btn_pay;
    Button btn_back;

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

    public reserv_order() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_reserv_order, container, false);
        // Inflate the layout for this fragment

        btn_back = (Button)rootview.findViewById(R.id.btn_back2);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservation.onChangeFragment(0);
            }
        });
        btn_pay = (Button)rootview.findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservation.onChangeFragment(2);
            }
        });
        return rootview;
    }

}
