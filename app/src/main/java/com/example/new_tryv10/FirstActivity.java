package com.example.new_tryv10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button st,rt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        st = findViewById(R.id.button3);
        rt = findViewById(R.id.button4);

        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(FirstActivity.this,SearchActivity.class);
                startActivity(intent1);
            }
        });

        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
