package fragment.reservation_fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_app.R;
import com.example.final_app.additional_order;
import com.example.final_app.payment;
import com.example.final_app.reservation;

import java.util.ArrayList;

import adapter.additional_order_adapter;
import model.MenuVo;
import res.network.NetworkMenuSearch;
import res.network.NetworkPay;
import res.network.NetworkReservation;
import res.resources;

/**
 * A simple {@link Fragment} subclass.
 */
public class reserv_order extends Fragment {

    com.example.final_app.reservation reservation;

    Button btn_pay;
    Button btn_back;
    ArrayList<MenuVo> orderList = new ArrayList<MenuVo>();
    int pos;
    int sum=0;
    additional_order_adapter food_adapter, order_adapter;
    TextView sumtv;

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
        final ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_reserv_order, container, false);
        // Inflate the layout for this fragment

        btn_back = (Button)rootview.findViewById(R.id.btn_back2);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderList.clear();
                order_adapter.notifyDataSetChanged();
                reservation.onChangeFragment(0);
            }
        });
        btn_pay = (Button)rootview.findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((int)resources.gets.get("point") < sum){
                    Toast.makeText(reservation, "포인트가 부족합니다", Toast.LENGTH_SHORT).show();
                }else{
                    String coma = ",";
                    String food_name ="";
                    String food_count ="";
                    String eachSum = "";
                    for(int i=0;i< orderList.size();i++){
                        if(i==orderList.size())
                            coma = "";
                        food_name += orderList.get(i).getFood_name()+coma;
                        food_count += orderList.get(i).getFood_price().substring
                                (orderList.get(i).getFood_price().indexOf('X')+2,
                                        orderList.get(i).getFood_price().indexOf('='))+coma;
                        eachSum += orderList.get(i).getFood_price().substring
                                (orderList.get(i).getFood_price().indexOf('=')+2)+coma;
                    }
                    new NetworkReservation(reservation, ((reservation)getActivity()).shop_id, sum, food_name, food_count, eachSum).execute("2", (String) resources.gets.get("user_email"), ((reservation) getActivity()).shop_title,
                            ((reservation) getActivity()).tempArr.get(0), ((reservation) getActivity()).tempArr.get(1), "" + ((reservation) getActivity()).tempArr.get(2)
                            , ((reservation) getActivity()).shop_id, ((reservation) getActivity()).tempArr.get(3), ((reservation) getActivity()).tempArr.get(4));
                }
            }
        });


        sumtv = (TextView)rootview.findViewById(R.id.sum);

        ListView listView = (ListView)rootview.findViewById(R.id.listView);
        ListView orderView = (ListView)rootview.findViewById(R.id.order);

        food_adapter = new additional_order_adapter(rootview.getContext(),new ArrayList<MenuVo>());
        order_adapter = new additional_order_adapter(rootview.getContext(),orderList);

        listView.setAdapter(food_adapter);
        orderView.setAdapter(order_adapter);

        new NetworkMenuSearch((additional_order_adapter) listView.getAdapter(), getActivity()).execute("?shop_id="+((reservation)getActivity()).shop_id);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                if(food_adapter.getDatas().get(position).getCheck() == 0){
                    pos = position;
                    AlertDialog.Builder ad = new AlertDialog.Builder(rootview.getContext());
                    ad.setTitle("음식 주문");
                    ad.setMessage("추가하실 개수를 입력하세요");

                    final EditText et = new EditText(rootview.getContext());
                    ad.setView(et);

                    // 확인 버튼 설정
                    ad.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Text 값 받아서 로그 남기기
                            String value = et.getText().toString().trim();

                            try {
                                int rt = Integer.parseInt(value);
                                InitializeOrderData(food_adapter.getDatas().get(pos), rt);
                                food_adapter.getDatas().get(pos).setCheck(1);
                                orderList.get(orderList.size()-1).setCheck(pos);
                            } catch(NumberFormatException e){
                                Toast.makeText(rootview.getContext(), "숫자만 입력하세요", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();     //닫기
                            // Event
                        }
                    });

                    // 취소 버튼 설정
                    ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();     //닫기
                            // Event
                        }
                    });
                    ad.show();


                }else {
                    Toast.makeText(rootview.getContext(),
                            "이미 추가되어 있는 상품입니다",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        orderView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, final int position, long id){

                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(rootview.getContext());
                alert_confirm.setMessage("상품을 제거 하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RemoveOrderData(position);
                                // 'YES'
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'No'
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();

            }
        });
        return rootview;
    }

    public void InitializeOrderData(MenuVo order, int num)
    {
        MenuVo temp = new MenuVo(order.getFood_name(), order.getFood_price()+" X "+num+"= "+Integer.parseInt(order.getFood_price())*num, order.getFood_info(),0);
        sum += Integer.parseInt(order.getFood_price())*num;
        sumtv.setText("총 합계\n"+sum+"원");
        orderList.add(temp);
    }

    public void RemoveOrderData(int pos)
    {
        String temp = orderList.get(pos).getFood_price();
        sum -= Integer.parseInt(temp.substring(temp.lastIndexOf("=")+2));
        sumtv.setText("총 합계\n"+sum+"원");
        food_adapter.getDatas().get(orderList.get(pos).getCheck()).setCheck(0);
        orderList.remove(pos);
        order_adapter.notifyDataSetChanged();
    }

}
