package com.hyun.planets_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Planet> planetArrayList;
    private static MyCustomAdapter adapter;

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

        listView = findViewById(R.id.listview);
        planetArrayList = new ArrayList<>();
        planetArrayList.add(new Planet("Earth", "1 Moon", R.drawable.earth));
        planetArrayList.add(new Planet("Mercury", "0 Moons", R.drawable.mercury));
        planetArrayList.add(new Planet("Venus", "0 Moons", R.drawable.venus));
        planetArrayList.add(new Planet("Mars", "2 Moons", R.drawable.mars));
        planetArrayList.add(new Planet("Jupiter", "79 Moons", R.drawable.jupiter));
        planetArrayList.add(new Planet("Saturn", "83 Moons", R.drawable.saturn));
        planetArrayList.add(new Planet("Uranus", "27 Moons", R.drawable.uranus));
        planetArrayList.add(new Planet("Neptune", "14 Moons", R.drawable.neptune));

        adapter = new MyCustomAdapter(planetArrayList, getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity.this, "Planet Name: " + adapter.getItem(position).getPlanetName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}