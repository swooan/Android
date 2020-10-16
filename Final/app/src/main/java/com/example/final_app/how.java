package com.example.final_app;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import res.network.NetworkPay;
import res.resources;

public class how extends Activity {
    TextView txtText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.how);

        final EditText pointET = (EditText)findViewById(R.id.pointET);

        Button ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(pointET.getText())){
                    Toast.makeText(how.this, "충전하실 금액을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    new NetworkPay(how.this, 1, Integer.parseInt(pointET.getText().toString())).execute("?user_email="+ resources.gets.get("user_email")+"&point="+pointET.getText());
                    finish();
                }

            }
        });
    }

    //확인 버튼 클릭
    public void mOnClose(View v){

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
