package fragment.reservation_fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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
public class reserv_complete extends Fragment {

    com.example.final_app.reservation reservation;
    Button complete;

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


    public reserv_complete() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_reserv_complete, container, false);

        complete = (Button)rootView.findViewById(R.id.completeReservation);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.search");

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intent.setComponent(name);
                startActivityForResult(intent, 101);
            }
        });

        return rootView;
    }

}
