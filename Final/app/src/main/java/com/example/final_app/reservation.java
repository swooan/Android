package com.example.final_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import fragment.reservation_fragment.reserv_complete;
import fragment.reservation_fragment.reserv_order;
import fragment.reservation_fragment.reserv_payment;
import fragment.reservation_fragment.reserv_simple;

public class reservation extends AppCompatActivity {

    reserv_simple simple;
    reserv_order order;
    reserv_complete complete;
    reserv_payment payment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        simple = new reserv_simple();
        order = new reserv_order();
        complete = new reserv_complete();
        payment = new reserv_payment();

        getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,simple).commit();
    }

    public void onChangeFragment(int index) {
        if(index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,simple).commit();
        }
        else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,order).commit();
        }
        else if (index == 2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,payment).commit();
        }
        else if (index == 3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,complete).commit();
        }
    }
}
