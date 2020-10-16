package fragment.My_Info_fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.final_app.R;

import java.util.ArrayList;

import adapter.payment_adapter2;
import model.PaymentVo;
import res.network.NetworkPay;
import res.resources;

/**
 * A simple {@link Fragment} subclass.
 */
public class my_info_payed extends Fragment {
    payment_adapter2 payment_adapter2;

    public my_info_payed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_my_info_payed, container, false);

        ListView listView = (ListView)rootview.findViewById(R.id.listView);

        payment_adapter2 = new payment_adapter2(rootview.getContext(), new ArrayList<PaymentVo>());
        listView.setAdapter(payment_adapter2);

        new NetworkPay((payment_adapter2) listView.getAdapter(), getActivity(), 6).execute("?user_email="+ resources.gets.get("user_email"));
        return rootview;
    }

}
