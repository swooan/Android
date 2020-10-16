package fragment.detail_fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.final_app.R;
import com.example.final_app.shop_detail;

import java.util.ArrayList;

import model.ShopVo;
import res.resources;


/**
 * A simple {@link Fragment} subclass.
 */
public class detail_info extends Fragment {


    public detail_info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail_info, container, false);

        TextView shop_title = (TextView)v.findViewById(R.id.shop_title);
        TextView shop_addr = (TextView)v.findViewById(R.id.shop_addr);
        TextView food_type = (TextView)v.findViewById(R.id.food_type);
        TextView shop_tip = (TextView)v.findViewById(R.id.shop_tip);
        TextView budget = (TextView)v.findViewById(R.id.budget);
        TextView shop_reserve = (TextView)v.findViewById(R.id.shop_reserve);
        TextView shop_comment = (TextView)v.findViewById(R.id.shop_comment);
        TextView shop_view = (TextView)v.findViewById(R.id.shop_view);
        TextView shop_score = (TextView)v.findViewById(R.id.shop_score);
        TextView shop_phone = (TextView)v.findViewById(R.id.shop_phone);
        TextView shop_time = (TextView)v.findViewById(R.id.shop_time);
        TextView shop_addInfo = (TextView)v.findViewById(R.id.shop_addInfo);
        TextView shop_tb = (TextView)v.findViewById(R.id.shop_tb);
        TextView shop_alcohol = (TextView)v.findViewById(R.id.shop_alcohol);
        TextView shop_car = (TextView)v.findViewById(R.id.shop_car);
        TextView shop_close = (TextView)v.findViewById(R.id.shop_close);
        TextView shop_review = (TextView)v.findViewById(R.id.shop_review);
        TextView hashTag = (TextView)v.findViewById(R.id.hashTag);

        shop_title.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_title());
        shop_addr.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_addr());
        food_type.setText(((shop_detail)getActivity()).shop_info_list.get(0).getFood_type());
        shop_tip.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_tip());
        budget.setText(((shop_detail)getActivity()).shop_info_list.get(0).getBudget());
        shop_reserve.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_reserve()+"");
        shop_comment.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_comment());
        shop_view.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_view()+"");
        shop_score.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_score().toString());
        shop_phone.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_phone());
        shop_time.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_time());
        shop_addInfo.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_addinfo());
        shop_tb.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_tb());
        shop_alcohol.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_alcohol());
        if(((shop_detail)getActivity()).shop_info_list.get(0).getShop_car() == '0'){
            shop_car.setText("불가능");
        }else {
            shop_car.setText("가능");
        }
        shop_close.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_close());
        shop_review.setText(((shop_detail)getActivity()).shop_info_list.get(0).getShop_review()+"");
        hashTag.setText(((shop_detail)getActivity()).shop_info_list.get(0).getHash_tag());
        return v;
    }

}
