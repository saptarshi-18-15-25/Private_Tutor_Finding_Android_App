package com.example.new_tryv10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText name,number,add,subj;
    Button reg;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editText);
        number = findViewById(R.id.editText2);
        add = findViewById(R.id.editText3);
        subj = findViewById(R.id.editText4);
        reg = findViewById(R.id.button);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = name.getText().toString().trim();
                String usernumber = number.getText().toString().trim();
                String useraddress = add.getText().toString().trim();
                String usersubj = subj.getText().toString().trim();

                if(username.isEmpty() || usernumber.isEmpty() || useraddress.isEmpty()|| usersubj.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Enter all fields !!",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    String id = databaseReference.push().getKey();

                    User user = new User(username,usernumber,useraddress.toLowerCase(),usersubj.toLowerCase());

                    databaseReference.child("Teacher").child(id).setValue(user);
                    Toast.makeText(MainActivity.this,"Your account is created",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
