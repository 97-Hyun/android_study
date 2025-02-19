package com.hyun.greeting_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btn;
    TextView title;

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

        editText = findViewById(R.id.edit_text);
        btn = findViewById(R.id.btn);
        title = findViewById(R.id.title);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = editText.getText().toString();

                if (val.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Empty EditText", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Toast.LENGTH_LONG : 3.5초동안 토스트 메시지를 보여준다
                // Toast.LENGTH_SHORT : 2초동안 토스트 메시지를 보여준다
                Toast.makeText(MainActivity.this, "Welcom " + val + " to our App!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}