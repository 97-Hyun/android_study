package com.hyun.contacts_manager_app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final ContactDAO contactDAO;
    ExecutorService executorService;
    Handler handler;

    public Repository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        // 백그라운드 스레드에서 데이터베이스 작업을 위해 사용한다.
        executorService = Executors.newSingleThreadExecutor();
        // UI 업데이트를 위해 사용한다.
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contact) {
        // Runnable : 별도 스레드에서 작업 실행
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 별도 스레드에서 비동기적으로 실행된다.
                contactDAO.insert(contact);
            }
        });
    }

    public void deleteContact(Contacts contact) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });
    }

    public LiveData<List<Contacts>> getAllContacts() {
        return contactDAO.getAllContacts();
    }
}
