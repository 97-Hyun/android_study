package com.hyun.chatapp.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hyun.chatapp.BR;
import com.hyun.chatapp.R;
import com.hyun.chatapp.databinding.RowChatBinding;
import com.hyun.chatapp.model.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatAdapterViewHolder> {
    private List<ChatMessage> chatMessageList;
    private Context context;

    public ChatAdapter(List<ChatMessage> chatMessageList, Context context) {
        this.chatMessageList = chatMessageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.row_chat, parent, false);

        RowChatBinding binding = DataBindingUtil.bind(view);

        return new ChatAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapterViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.chatMessage, chatMessageList.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    public class ChatAdapterViewHolder extends RecyclerView.ViewHolder{
        RowChatBinding binding;

        public ChatAdapterViewHolder(RowChatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public RowChatBinding getBinding() {
            return binding;
        }

        public void setBinding(RowChatBinding binding) {
            this.binding = binding;
        }
    }
}
