package com.hyun.market_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    RecyclerView recyclerView;
    List<Item> itemList;
    MyCustomAdapter adapter;

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

        recyclerView = findViewById(R.id.recycler_view);
        itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.fruit, "Fruits", "Fresh Fruits from the Garden"));
        itemList.add(new Item(R.drawable.vegitables, "Vegetables", "Delicious Vegetables"));
        itemList.add(new Item(R.drawable.bread, "Bakery", "Bread, Wheat and Beans"));
        itemList.add(new Item(R.drawable.beverage, "Beverage", "Juice, Tea, Coffee and Soda"));
        itemList.add(new Item(R.drawable.milk, "Milk", "Mlk, Shakes and Yogurt"));
        itemList.add(new Item(R.drawable.popcorn, "Snacks", "Pop Corn, Donut and Drinks"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyCustomAdapter(itemList);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(View v, int pos) {
        Toast.makeText(this, "You Choose: " + itemList.get(pos).getItemName(), Toast.LENGTH_SHORT).show();
    }
}