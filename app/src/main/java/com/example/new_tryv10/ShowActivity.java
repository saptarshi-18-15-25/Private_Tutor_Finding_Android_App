package com.example.new_tryv10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button btn;
    private RecyclerView mBlogListview;
    private DatabaseReference mDatabase;
    private ArrayList<User> arrayList;
    private ArrayList<User> searchList;
    private FirebaseRecyclerOptions<User> options;
    private FirebaseRecyclerAdapter<User,UserViewHolder> adapter;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User").child("Teacher");
        mDatabase.keepSynced(true);
        btn = findViewById(R.id.button2);

        //btn = findViewById(R.id.srBtn);
      //  txt = findViewById(R.id.srText);


         mBlogListview = findViewById(R.id.myrecycleview);
         mBlogListview.setHasFixedSize(true);
         mBlogListview.setLayoutManager(new LinearLayoutManager(this));
         arrayList = new ArrayList<User>();


         btn.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        Intent is =  new Intent(ShowActivity.this,SearchActivity.class);
                                        startActivity(is);
                                    }
                                  }
         );

        // String st ="math";


        Query  searchQuery  = mDatabase.orderByChild("Teacher");//.startAt(st).endAt(st+"\uf8ff");
         options = new FirebaseRecyclerOptions.Builder<User>().setQuery(searchQuery,User.class).build();

         adapter = new FirebaseRecyclerAdapter<User, UserViewHolder> (options) {
             @Override
             protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull final User model) {

                 holder.shname.setText(model.getUanme());
                 //holder.shnum.setText(model.getUnumb());
               //  holder.shdomain.setText(model.getUaddress());
                 holder.shsubj.setText(model.getUsubj());

                 holder.itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent = new Intent(ShowActivity.this,DetailsActivity.class);
                         intent.putExtra("shname",model.getUanme());
                         intent.putExtra("shnum",model.getUnumb());
                         intent.putExtra("sdomain",model.getUaddress());
                         intent.putExtra("ssub",model.getUsubj());
                         startActivity(intent);
                     }
                 });

             }

             @NonNull
             @Override
             public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 return new UserViewHolder(LayoutInflater.from(ShowActivity.this).inflate(R.layout.blog_row,parent,false));
             }
         };

         mBlogListview.setAdapter(adapter);
    }
}
