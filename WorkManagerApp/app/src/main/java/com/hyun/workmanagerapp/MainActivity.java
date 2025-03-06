package com.hyun.workmanagerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn = findViewById(R.id.btn);

        // 워커매니저에게 값을 넘겨줄때 사용
        Data data = new Data.Builder()
                .putInt("max_limit", 500).build();

        // 제약 조건
        // 작업에 적용할 수 있는 조건이나 요구사항
        // 관련된 백그라운드 작업이 언제, 어떻게 실행되어야 하는지 지정하도록 요청
        Constraints constraints = new Constraints.
                Builder().
                setRequiresCharging(true).
                build();


        WorkRequest wr = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setConstraints(constraints)
                .setInputData(data)
                .build();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance(getApplicationContext()).enqueue(wr);
            }
        });

        // 워커매니저의 상태 모니터링
        WorkManager.getInstance(getApplicationContext())
                .getWorkInfoByIdLiveData(wr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null) {
                            Toast.makeText(MainActivity.this, "Status: "+workInfo.getState().name(), Toast.LENGTH_SHORT).show();
                        }

                        if (workInfo.getState().isFinished()) {
                            Data data = workInfo.getOutputData();
                            Toast.makeText(MainActivity.this, ""+data.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}