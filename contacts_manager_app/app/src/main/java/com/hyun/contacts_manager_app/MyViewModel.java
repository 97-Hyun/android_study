package com.hyun.contacts_manager_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// 안드로이드 뷰모델은 뷰모델의 서브 클래스로 UI 관련 데이터를 저장하고 관리하도록 디자인되어있다.
public class MyViewModel extends AndroidViewModel {
    private Repository myRepository;
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);
    }

    public LiveData<List<Contacts>> getAllContacts() {
        allContacts = myRepository.getAllContacts();
        return allContacts;
    }

    public void addNewContact(Contacts contact) {
        myRepository.addContact(contact);
    }

    public void deleteContact(Contacts contact) {
        myRepository.deleteContact(contact);
    }
}
