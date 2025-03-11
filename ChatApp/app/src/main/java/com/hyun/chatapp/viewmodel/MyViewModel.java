package com.hyun.chatapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hyun.chatapp.model.ChatGroup;
import com.hyun.chatapp.model.ChatMessage;
import com.hyun.chatapp.repository.Repository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    Repository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }

    // Auth
    public void signUpAnonymousUser() {
        Context context = this.getApplication();
        repository.firebaseAnonymousAuth(context);
    }

    public String getCurrentUserId() {
        return repository.getCurrentUserId();
    }

    public void signOut() {
        repository.signOut();
    }

    public MutableLiveData<List<ChatGroup>> getGroups() {
        return repository.getChatGroupMutableLiveData();
    }

    public void createNewGroup(String groupName) {
        repository.createNewChatGroup(groupName);
    }

    public MutableLiveData<List<ChatMessage>> getMessageLiveData(String groupName) {
        return repository.getMessageMutableLiveData(groupName);
    }

    public void sendMessage(String msg, String chatGroup) {
        repository.sendMessage(msg, chatGroup);
    }
}
