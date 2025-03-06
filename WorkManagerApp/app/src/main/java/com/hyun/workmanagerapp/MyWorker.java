package com.hyun.workmanagerapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    // 백그라운드에서 비동기로 실행.
    // 워커매니저가 제공한 스레드에서 실행된다.
    @NonNull
    @Override
    public Result doWork() {
        int limit = getInputData().getInt("max_limit", 0);

        for (int i = 0; i < limit; i++) {
            Log.i("TAGY", "Count is "+ i);
        }

        Data dataToSend = new Data.Builder()
                .putString("msg", "Task Done Successfully").build();

        return Result.success(dataToSend);
    }
}
