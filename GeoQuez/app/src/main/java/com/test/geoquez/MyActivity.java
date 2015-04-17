package com.test.geoquez;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {

    private Button btTrue;
    private Button btFalse;
    private Button btNext;
    private TextView tvQuestion;
    private int count = 0  ;

    private TrueFalse[] questionList = new TrueFalse[]{
            new TrueFalse(R.string.one,true),
            new TrueFalse(R.string.two,true),
            new TrueFalse(R.string.three,false),
            new TrueFalse(R.string.four,true),
            new TrueFalse(R.string.five,false),
            new TrueFalse(R.string.six,false),
            new TrueFalse(R.string.seven,false),
            new TrueFalse(R.string.eight,true),
    };

    private int currentIndex=0;  //目前问题在数组中的位置

    private void updateQuestion(){
        currentIndex = (currentIndex+1) % questionList.length;
        int question = questionList[currentIndex].getQuestionId();
        tvQuestion.setText(question);
    }

    private void checkAnswer(boolean userPressedIsTrue){
        boolean answerIsTrue = questionList[currentIndex].getIsTrue();
        int messageResId = 0 ;
        if (answerIsTrue == userPressedIsTrue){
            Toast.makeText(this,"回答正确",Toast.LENGTH_SHORT).show();
            count ++ ;
            updateQuestion();
        }else {
            Toast.makeText(this,"回答错误",Toast.LENGTH_SHORT).show();
            updateQuestion();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my);
        tvQuestion = (TextView) findViewById(R.id.tv_question);
        updateQuestion();

//        btNext = (Button) findViewById(R.id.bt_next);
//        btNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                currentIndex = (currentIndex+1) % questionList.length;
//                updateQuestion();
//            }
//        });

        btFalse = (Button) findViewById(R.id.bt_false);
        btFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex ==0 ){
                    if (count == 8){
                        Toast.makeText(MyActivity.this,"答题结束,经验证，你是个好老公",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MyActivity.this,"答题结束,经验证，你是不是个好老公",Toast.LENGTH_LONG).show();

                    }
                }else {
                    checkAnswer(false);
                }

            }
        });

        btTrue = (Button) findViewById(R.id.bt_true);
        btTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex == 0){
                    if (count>6){
                        Toast.makeText(MyActivity.this,"答题结束！经验证，你是个好老公",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MyActivity.this,"答题结束！经验证，你是不是个好老公",Toast.LENGTH_LONG).show();
                    }
                }else {
                    checkAnswer(true);
                }
            }
        });

    }
}