package com.hyun.market_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {
    private List<Item> itemList;
    public ItemClickListener clickListener;

    public void setClickListener(ItemClickListener myListener) {
        this.clickListener = myListener;
    }

    public MyCustomAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    // 아이템을 위해 새로운 뷰홀더를 만들 때 반응
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    // 데이터를 특정 아이템에 대한 뷰소유자의 뷰로 바인딩을 하는 일을 한다
    // 화면에 띄우기 직전 아이템
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.imageView.setImageResource(item.getItemImg());
        holder.title.setText(item.getItemName());
        holder.description.setText(item.getItemDesc());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    // static을 붙이는 이유 : 상위 클래스의 메서드나 속성을 사용하지 않으려고
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView title;
        TextView description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.image_view);
            this.title = itemView.findViewById(R.id.title_txt);
            this.description = itemView.findViewById(R.id.description_txt);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(v, getAdapterPosition());
            }
        }
    }
}
