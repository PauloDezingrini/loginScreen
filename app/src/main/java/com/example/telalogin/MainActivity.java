package com.example.telalogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView recyclerView;


    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference myRef;

    String listName ;

    String listEmail ;


    final ArrayList<User> list =  new ArrayList<User>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);

        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        myRef = firebaseDatabase.getReference();


        myRef.child("user");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //list.add(dsp.getValue(User.class));
//                for (DataSnapshot dsp : dataSnapshot.getChildren()){
//                    Log.i("carambolas", dsp.getValue().toString());
//                    Log.i("carambolas", dsp.getValue(User.class).getEmail());
//
//                    //list.add(dsp.getValue(User.class));
//                }
//                    //Log.i("carambolas", dsp.getValue(User.class).toString());
//                //list.add(dataSnapshot.getValue(User.class));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                Toast.makeText(MainActivity.this, "OPA", Toast.LENGTH_SHORT).show();
//            }
//        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fetchData(dataSnapshot);
                Log.i("carambolas81", dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        recyclerView.setLayoutManager(layoutManager);

    }
    private void fetchData(DataSnapshot dataSnapshot)
    {

        User user;
        for (DataSnapshot dsp : dataSnapshot.getChildren()){
                Log.i("carambolas122", dsp.getValue().toString());
                Log.i("carambolas123", dsp.getValue(User.class).getEmail());
                user = dsp.getValue(User.class);

                list.add(user);
                Log.i("carambolas", "--------"+list.size()+"----------");
                RecyclerAdapter adapter = new RecyclerAdapter(list);
                recyclerView.setAdapter(adapter);
            }
    }

}
