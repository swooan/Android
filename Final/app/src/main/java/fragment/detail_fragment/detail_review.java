package fragment.detail_fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_app.R;
import com.example.final_app.shop_detail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import adapter.fragment_detail_review_adapter;
import model.ReviewVo;
import res.network.NetworkReview;
import res.resources;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class detail_review extends Fragment {

    public fragment_detail_review_adapter review_adapter;

    public detail_review() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_detail_review, container, false);

        ListView listView = (ListView)rootview.findViewById(R.id.listView);
        review_adapter = new fragment_detail_review_adapter(rootview.getContext(), new ArrayList<ReviewVo>(), ((shop_detail)getActivity()).shop_info_list.get(0).getShop_id());
        listView.setAdapter(review_adapter);

        new NetworkReview((fragment_detail_review_adapter) listView.getAdapter(), getActivity()).execute("?shop_id="+((shop_detail)getActivity()).shop_info_list.get(0).getShop_id());

        TextView shop_titleTV = (TextView)rootview.findViewById(R.id.shop_title);
        shop_titleTV.setText("가게 이름 : "+((shop_detail)getActivity()).shop_info_list.get(0).getShop_title());

        final RatingBar score = (RatingBar)rootview.findViewById(R.id.score);
        final TextView scoreTV = (TextView)rootview.findViewById(R.id.scoreTV);
        score.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                scoreTV.setText("별점 : "+rating);
            }
        });

        Button reviewWriteBtn = (Button)rootview.findViewById(R.id.reviewWriteBtn);
        reviewWriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((Boolean) resources.gets.get("login")){
                    EditText reviewET = (EditText)rootview.findViewById(R.id.reviewET);

                    if(TextUtils.isEmpty(reviewET.getText())){
                        Toast.makeText(getActivity(), "리뷰를 작성해 주세요", Toast.LENGTH_SHORT).show();
                    }else{
                        if(score.getRating() == 0){
                            Toast.makeText(getActivity(), "별점을 입력해 주세요", Toast.LENGTH_SHORT).show();
                        }else{
                            SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
                            Date today = new Date();
                            String time1 = format1.format(today);
                            review_adapter.getDatas().add(new ReviewVo((String) resources.gets.get("user_email"),reviewET.getText().toString(), time1, score.getRating(), 0, 0,0));
                            review_adapter.notifyDataSetInvalidated();
                            new NetworkReview(4).execute("?shop_id="+((shop_detail)getActivity()).shop_info_list.get(0).getShop_id()+
                                    "&user_email="+resources.gets.get("user_email")+"&review_score="+score.getRating()+"&review="+reviewET.getText());
                            InputMethodManager imm = (InputMethodManager) rootview.getContext().getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(reviewET.getWindowToken(), 0);
                            reviewET.setText("");
                            score.setRating(0);
                            scoreTV.setText("별점 : ");
                        }
                    }
                }else{
                    Toast.makeText(getActivity(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootview;
    }
}
