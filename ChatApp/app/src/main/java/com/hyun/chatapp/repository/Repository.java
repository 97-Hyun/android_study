package com.hyun.chatapp.repository;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hyun.chatapp.model.ChatGroup;
import com.hyun.chatapp.model.ChatMessage;
import com.hyun.chatapp.views.GroupsActivity;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    MutableLiveData<List<ChatGroup>> chatGroupMutableLiveData;

    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference groupReference;

    MutableLiveData<List<ChatMessage>> messageMutableLiveData;

    public Repository() {
        this.chatGroupMutableLiveData = new MutableLiveData<>();
        this.messageMutableLiveData = new MutableLiveData<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    public void firebaseAnonymousAuth(Context context) {
        FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(context, GroupsActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                    }
                });
    }

    public String getCurrentUserId() {
        return FirebaseAuth.getInstance().getUid();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public MutableLiveData<List<ChatGroup>> getChatGroupMutableLiveData() {
        List<ChatGroup> groups = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                groups.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatGroup group = new ChatGroup(dataSnapshot.getKey());
                    groups.add(group);
                }

                chatGroupMutableLiveData.setValue(groups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return chatGroupMutableLiveData;
    }

    public void createNewChatGroup(String groupName) {
        reference.child(groupName).setValue(groupName);
    }

    public MutableLiveData<List<ChatMessage>> getMessageMutableLiveData(String groupName) {
        groupReference = database.getReference().child(groupName);

        List<ChatMessage> messages = new ArrayList<>();

        groupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                    messages.add(message);
                }

                messageMutableLiveData.postValue(messages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return messageMutableLiveData;
    }

    public void sendMessage(String messageTxt, String chatGroup) {
        DatabaseReference ref = database.getReference(chatGroup);

        if (!messageTxt.trim().equals("")) {
            ChatMessage msg = new ChatMessage(
                    getCurrentUserId(),
                    messageTxt,
                    System.currentTimeMillis()
            );

            String randomKey = ref.push().getKey();

            ref.child(randomKey).setValue(msg);
        }
    }
}
