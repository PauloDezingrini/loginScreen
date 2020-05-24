package com.example.telalogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
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

        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        myRef = firebaseDatabase.getReference();


        myRef.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*for (final DataSnapshot dsp : DataSnapshot.getChildren()) {

                    list.add(dsp.getValue(User.class));*/

                list.add(dataSnapshot.getValue(User.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, "OPA", Toast.LENGTH_SHORT).show();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerAdapter adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

}
