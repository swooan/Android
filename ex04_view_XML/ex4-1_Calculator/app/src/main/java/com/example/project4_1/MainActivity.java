package com.example.project4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button add,sub,mul,div, left;
    TextView textResult;
    String num1, num2;
    Integer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기");

        edit1 = (EditText)findViewById(R.id.editText);
        edit2 = (EditText)findViewById(R.id.editText2);
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        left = (Button)findViewById(R.id.left);
        textResult = (TextView)findViewById(R.id.result);

//        add.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                num1 = edit1.getText().toString();
//                num2 = edit2.getText().toString();
//                result = Integer.parseInt(num1) + Integer.parseInt(num2);
//                textResult.setText("계산 결과 : " + result.toString());
//                return false;
//            }
//        });
//        sub.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                num1 = edit1.getText().toString();
//                num2 = edit2.getText().toString();
//                result = Integer.parseInt(num1) - Integer.parseInt(num2);
//                textResult.setText("계산 결과 : " + result.toString());
//                return false;
//            }
//        });
//        mul.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                num1 = edit1.getText().toString();
//                num2 = edit2.getText().toString();
//                result = Integer.parseInt(num1) * Integer.parseInt(num2);
//                textResult.setText("계산 결과 : " + result.toString());
//                return false;
//            }
//        });
//        div.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                num1 = edit1.getText().toString();
//                num2 = edit2.getText().toString();
//                result = Integer.parseInt(num1) / Integer.parseInt(num2);
//                textResult.setText("계산 결과 : " + result.toString());
//                return false;
//            }
//        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());

                if(num1 == null || num2 == null) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());

                if(num1 == null || num2 == null) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());

                if(num1 == null || num2 == null) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());

                if(num1 == null || num2 == null) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(num2) == 0) {
                    Toast.makeText(getApplicationContext(), "분모는 0이 될 수 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) % Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());

                if(num1 == null || num2 == null) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(num2) == 0) {
                    Toast.makeText(getApplicationContext(), "분모는 0이 될 수 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
