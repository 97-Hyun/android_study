package com.hyun.lucky_number_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumberTxt;
    Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumberTxt = findViewById(R.id.lucky_number_txt);
        shareBtn = findViewById(R.id.share_btn);

        // 메인 액티비티로부터 데이터 수신
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        // 난수 생성
        int randomNum = generateRandomNumber();
        luckyNumberTxt.setText(""+randomNum);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(userName, randomNum);
            }
        });
    }

    public int generateRandomNumber() {
        Random random = new Random();
        int upper_limit = 1000;

        // 0 ~ 1000
        int randomNumberGenerated = random.nextInt(upper_limit);

        return randomNumberGenerated;
    }

    public void shareData(String userName, int randomNum) {
        // 암묵적 인텐트 사용
        // Send는 미리 정의된 상수로 데이터를 전송하는 행동, 일반적으로 장치의 다른 응용프로그램이나 구성 요소와
        // 콘텐츠를 공유할 때 사용된다. 다른 응용프로그램간의 통신 가능.
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, userName + " got lucky today");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: "+ randomNum);

        // createChooser는 인텐트 클래스에 제공하는 유틸리티로 특정 의도를 다룰 수 있는 애플리케이션
        // 목록을 보여주는 창을 만들 수 있다. 콘텐츠 공유 같은 특정 행동을 수행하기 위해 어떤 응용 프로그램을 쓸지 선택권을 준다
        startActivity(Intent.createChooser(i, "Choose a Platform"));
    }
}