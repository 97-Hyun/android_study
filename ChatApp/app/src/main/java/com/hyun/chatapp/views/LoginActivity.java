package com.hyun.chatapp.views;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.hyun.chatapp.R;
import com.hyun.chatapp.databinding.ActivityLoginAcitivtyBinding;
import com.hyun.chatapp.viewmodel.MyViewModel;

public class LoginActivity extends AppCompatActivity {
    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ViewModelProvider 는 해당 뷰모델이 존재할 경우 기존 인스턴스를 돌려주고 없을 경우 새로 생성해준다
        // 뷰모델의 전체적인 관리자
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        ActivityLoginAcitivtyBinding activityLoginAcitivtyBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_login_acitivty);

        activityLoginAcitivtyBinding.setVModel(viewModel);
    }
}