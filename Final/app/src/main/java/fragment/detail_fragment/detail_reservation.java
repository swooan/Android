package fragment.detail_fragment;


import android.content.ComponentName;
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

        toTheReservation = (Button)rootView.findViewById(R.id.toTheReservation);

        toTheReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.reservation");

                intent.setComponent(name);
                startActivityForResult(intent, 101);
            }
        });

        return rootView;
    }

}
