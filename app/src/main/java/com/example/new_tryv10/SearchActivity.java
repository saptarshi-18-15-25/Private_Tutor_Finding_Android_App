package com.example.new_tryv10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    //TextView sublist,loclist;
    ImageButton imgb;
    LinearLayoutManager layoutManager;
    private RecyclerView mBlogListview;
    private DatabaseReference sDatabase;
    private ArrayList<User> arrayList;
    private ArrayList<User> searchList;
    private FirebaseRecyclerOptions<User> options;
    private FirebaseRecyclerAdapter<User, UserViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

            sDatabase = FirebaseDatabase.getInstance().getReference().child("User").child("Teacher");
            sDatabase.keepSynced(true);

            //sublist = findViewById(R.id.subs);
            //loclist = findViewById(R.id.locs);
            imgb = findViewById(R.id.ib);

            mBlogListview = findViewById(R.id.srecycleview);
            mBlogListview.setHasFixedSize(true);
            layoutManager =new LinearLayoutManager(this);  //,RecyclerView.HORIZONTAL,false
            mBlogListview.setLayoutManager(layoutManager);
            arrayList = new ArrayList<User>();

            String st="null" ;



        Query searchQuery = sDatabase.orderByChild("usubj").startAt(st).endAt(st + "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<User>().setQuery(searchQuery, User.class).build();

        adapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull final User model) {

               holder.shname.setText(model.getUanme());
               // holder.shnum.setText(model.getUnumb());
               // holder.shdomain.setText(model.getUdomain());
                holder.shsubj.setText(model.getUsubj().toUpperCase());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                        intent.putExtra("shname", model.getUanme());
                        intent.putExtra("shnum", model.getUnumb());
                        intent.putExtra("sdomain",model.getUaddress());
                        intent.putExtra("ssub",model.getUsubj());
                        startActivity(intent);
                    }
                });


            }

            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                return new UserViewHolder(LayoutInflater.from(SearchActivity.this).inflate(R.layout.blog_row, parent, false));
            }
        };

        mBlogListview.setAdapter(adapter);




        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inttentll = new Intent(SearchActivity.this,FirstActivity.class);
                startActivity(inttentll);
            }
        });
    }


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
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseUserSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseUserSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void firebaseUserSearch(String st) {


        String str = st.toLowerCase();
        String query = str;
        Query fbaseSquery = sDatabase.orderByChild("usubj").startAt(query).endAt(query+"\uf8ff");
        //fbaseSquery=sDatabase.orderByChild("udomain").startAt("teacher").endAt("teacher"+"\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<User>().setQuery(fbaseSquery,User.class).build();

        adapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull final User mode) {

                holder.shname.setText(mode.getUanme());
               // holder.shnum.setText(mode.getUnumb());
               // holder.shdomain.setText(mode.getUdomain());
                holder.shsubj.setText(mode.getUsubj());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                        intent.putExtra("shname", mode.getUanme());
                        intent.putExtra("shnum", mode.getUnumb());
                        intent.putExtra("saddress",mode.getUaddress().toUpperCase());
                        intent.putExtra("ssub",mode.getUsubj().toUpperCase());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row,parent,false);
                UserViewHolder viewHolder = new UserViewHolder(itemView);
                return viewHolder;
            }
        };
        mBlogListview.setLayoutManager(layoutManager);
        adapter.startListening();
        mBlogListview.setAdapter(adapter);


        }
}


