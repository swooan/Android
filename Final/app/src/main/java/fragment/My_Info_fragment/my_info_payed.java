package fragment.My_Info_fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class my_info_payed extends Fragment {


    public my_info_payed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_info_payed, container, false);
    }

}
