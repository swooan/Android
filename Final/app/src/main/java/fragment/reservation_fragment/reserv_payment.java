package fragment.reservation_fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.final_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class reserv_payment extends Fragment {

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

Button btn_comp;
com.example.final_app.reservation reservation;

    public reserv_payment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_reserv_payment, container, false);

        btn_comp = (Button)rootView.findViewById(R.id.btn_comp);
        btn_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservation.onChangeFragment(3);
            }
        });
        return rootView;
    }

}
